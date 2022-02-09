public class LinkedListDeque<T> {
    /**
     * Structure: sentinel => first <=> ... <=> last => sentinel
     */
    private LinkedListNode sentinel;
    private int size;

    private class LinkedListNode {
        private T item;
        private LinkedListNode prev;
        private LinkedListNode next;

        LinkedListNode(T f, LinkedListNode p, LinkedListNode n) {
            item = f;
            prev = p;
            next = n;
        }
    }

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new LinkedListNode(null, null, null);
        sentinel.prev = sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * `add` and `remove` operation must take constant time
     */
    public void addFirst(T item) {
        LinkedListNode newNode = new LinkedListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        LinkedListNode newNode = new LinkedListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
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
        LinkedListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
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
        LinkedListNode n = sentinel.next;
        n.next.prev = sentinel;
        sentinel.next = n.next;
        n.prev = n.next = null;
        size--;
        return n.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        LinkedListNode n = sentinel.prev;
        n.prev.next = sentinel;
        sentinel.prev = n.prev;
        n.prev = n.next = null;
        size--;
        return n.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        LinkedListNode p = sentinel.next;
        int i = 0;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    /**
     * Same as get, but uses recursion.
     */
    private T getRecursiveHelper(LinkedListNode p, int index) {
        if (index == 0) {
            if (p == sentinel) {
                return null;
            }
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }
}
