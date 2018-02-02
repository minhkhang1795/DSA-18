package your_code;

import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxElement;

    public MyStack() {
        ll = new LinkedList<>();
        maxElement = new LinkedList<>();
    }

    // Runtime: O(1)
    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (maxElement.isEmpty() || e.compareTo(maxElement.getFirst()) > 0)
            maxElement.addFirst(e);
        else
            maxElement.addFirst(maxElement.getFirst());
    }

    // Runtime: O(1)
    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        maxElement.removeFirst();
        return pop;
    }

    // Runtime: O(1)
    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    // Runtime: O(1)
    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    // Runtime: O(1)
    public Integer maxElement() {
        // TODO
        if (maxElement.isEmpty())
            return null;
        return maxElement.getFirst();
    }
}
