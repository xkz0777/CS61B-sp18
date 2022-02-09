public class ArrayDeque<T> {
    T[] items;
    int size;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /**
     * Creates a deep copy of OTHER.
     */
    public ArrayDeque(ArrayDeque<T> other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, size);
    }

    /**
     * Resize the array.
     */
    private void resize(int newSize, int srcPos, int destPos) {
        T[] a = (T[]) new Object[newSize];
        System.arraycopy(items, srcPos, a, destPos, size);
        items = a;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * items.length, 0, 1);
        } else {
            System.arraycopy(items, 0, items, 1, size); // SRC and DEST can be the same array
        }
        items[0] = item;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * items.length, 0, 0);
        }
        items[size++] = item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque. (Constant time)
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
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
        T first = items[0];
        size--;
        if (items.length >= 16 && 4 * size < items.length) {
            resize(items.length / 2, 1, 0);
        } else{
            System.arraycopy(items, 1, items, 0, size);
        }
        items[size] = null; // Don't loiter
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[--size];
        items[size] = null;
        if (items.length >= 16 && 4 * size < items.length) {
            resize(items.length / 2, 0, 0);
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
        return items[index];
    }
}
