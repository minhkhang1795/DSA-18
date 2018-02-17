import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        // TODO
        PriorityQueue<Integer> medianLow = new PriorityQueue<>();
        PriorityQueue<Integer> medianHigh = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < inputStream.length; i++) {
            int val = inputStream[i];
            medianHigh.offer(val);

            while (medianHigh.size() > medianLow.size())
                medianLow.offer(medianHigh.poll());

            while (medianLow.size() > medianHigh.size())
                medianHigh.offer(medianLow.poll());

            runningMedian[i] = i % 2 == 0 ? medianHigh.peek() : (medianHigh.peek() + medianLow.peek()) / 2.0;
        }
        return runningMedian;
    }

}
