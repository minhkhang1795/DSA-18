package your_code;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private MyQueue<Integer> queue;

    public MyPriorityQueue() {
        queue = new MyQueue<>();
    }

    // Runtime: O(n)
    public void enqueue(int item) {
        // TODO
        boolean isAdded = false;

        MyQueue<Integer> temp = new MyQueue<>();
        while (!queue.isEmpty()) {
            if (queue.front() < item && !isAdded) {
                temp.enqueue(item);
                isAdded = true;
            }
            temp.enqueue(queue.dequeue());
        }

        if (!isAdded)
            temp.enqueue(item);
        queue = temp;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    // Runtime: O(1)
    public int dequeueMax() {
        // TODO
        return queue.dequeue();
    }

}