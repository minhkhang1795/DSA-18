package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }
    }

    // Runtime: O(1)
    public MyLinkedList() {
        // TODO
        head = null;
        tail = null;
        size = 0;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Runtime: O(1)
    public void add(Chicken c) {
        addLast(c);
    }

    // Runtime: O(1)
    public Chicken pop() {
        return removeLast();
    }

    // Runtime: O(1)
    public void addLast(Chicken c) {
        // TODO
        if (size == 0) {
            head = new Node(c, null, null);
            tail = head;
        } else {
            // tail and head have the same pointer when size = 1
            tail.next = new Node(c, tail, null);
            tail = tail.next;
        }

        size++;
    }

    // Runtime: O(1)
    public void addFirst(Chicken c) {
        // TODO
        if (size == 0) {
            head = new Node(c, null, null);
            tail = head;
        } else {
            // tail and head have the same pointer when size = 1
            head.prev = new Node(c, null, head);
            head = head.prev;
        }

        size++;
    }

    // Runtime: O(n)
    public Chicken get(int index) {
        // TODO
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.next;

        return current.val;
    }

    // Runtime: O(n)
    public Chicken remove(int index) {
        // TODO
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            return removeFirst();
        if (index == size - 1)
            return removeLast();

        Node previousNode = head;
        for (int i = 0; i < index - 1; i++)
            previousNode = previousNode.next;
        Node indexNode = previousNode.next;
        Node nextNode = indexNode.next;

        previousNode.next = nextNode;
        nextNode.prev = previousNode;
        indexNode.next = null;
        indexNode.prev = null;

        size--;
        return indexNode.val;
    }

    // Runtime: O(1)
    public Chicken removeFirst() {
        // TODO
        if (size == 0)
            return null;

        Node node = head;
        if (size == 1)
            head = null;
        else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return node.val;
    }

    // Runtime: O(1)
    public Chicken removeLast() {
        // TODO
        if (size == 0)
            return null;

        Node node = tail;
        if (size == 1)
            tail = null;
        else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return node.val;
    }
}