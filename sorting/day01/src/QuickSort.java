import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i + 1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(nlog(n))
     * <p>
     * Space-complexity: O(log(n))
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case
        shuffleArray(array);
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (hi - lo < INSERTION_THRESHOLD) {
            insertionSort(a, lo, hi);
            return;
        }
        if (lo < hi) {
            int p = partition(a, lo, hi);
            // TODO
            quickSort(a, lo, p - 1);
            quickSort(a, p + 1, hi);
        }
    }

    private void insertionSort(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int k = i;
            while (k > lo && array[k] < array[k - 1])
                swap(array, k, --k);
        }
    }

    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        // TODO
        if (lo == hi)
            return lo;

        int i = lo + 1;
        while (i <= hi) {
            if (array[i] >= array[lo])
                swap(array, i, hi--);
            else
                i++;
        }

        swap(array, lo, --i);
        return i;
    }

}
