public class ArrayDeque<T> {
    private T[] items;
    // In order to make addFirst take constant time.
    private int front; // Pointing to the first element.
    private int back; // Pointing to back of the last element.
    // size = back - front

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = back = 4;
    }

    /**
     * Resize the array.
     */
    private void resize(int newSize, int srcPos, int destPos) {
        T[] a = (T[]) new Object[newSize];
        System.arraycopy(items, srcPos, a, destPos, back - front);
        items = a;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (front == 0) {
            int l = items.length;
            resize(2 * l, 0, l);
            front += l;
            back += l;
        }
        items[--front] = item;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (back == items.length) {
            resize(2 * items.length, front, front);
        }
        items[back++] = item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return front == back;
    }

    /**
     * Returns the number of items in the deque. (Constant time)
     */
    public int size() {
        return back - front;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = front; i < back; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[front];
        items[front++] = null; // Don't loiter
        if (items.length >= 16 && 4 * size() < items.length) {
            int newLength = items.length / 2;
            resize(newLength, front, front - newLength);
        }
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[--back];
        items[back] = null;
        if (items.length >= 16 && 4 * size() < items.length) {
            resize(items.length / 2, front, front);
        }
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return items[index + front];
    }
}
