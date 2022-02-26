public class ArrayDeque<T> implements Deque<T> {
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
    @Override
    public void addFirst(T item) {
        if (front == 0) {
            int l = items.length;
            resize(2 * l, 0, l);
            front += l;
            back += l;
        }
        front--;
        items[front] = item;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    @Override
    public void addLast(T item) {
        if (back == items.length) {
            resize(2 * items.length, front, front);
        }
        items[back] = item;
        back++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return front == back;
    }

    /**
     * Returns the number of items in the deque. (Constant time)
     */
    @Override
    public int size() {
        return back - front;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        for (int i = front; i < back; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.print("\n");
    }

    /**
     * Helper method for remove.
     */
    private void checkSizeDown() {
        if (items.length >= 16 && 4 * size() < items.length) {
            int newLength = items.length / 2;
            resize(newLength, front, newLength / 4);
            back += (newLength / 4 - front);
            front = newLength / 4;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[front];
        items[front] = null; // Don't loiter
        front++;
        checkSizeDown();
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[back - 1];
        back--;
        items[back] = null;
        checkSizeDown();
        return last;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return items[index + front];
    }
}
