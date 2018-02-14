
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * <p>
     * TODO
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     * <p>
     * Space-complexity: depends: O(n) or O(log(n)) if use linked list
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        if (array.length <= INSERTION_THRESHOLD)
            return new InsertionSort().sort(array);
        int mid = array.length / 2;
        int[] left = sort(split(array, 0, mid));
        int[] right = sort(split(array, mid, array.length));
        return merge(left, right);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {
        // TODO
        int a_index = 0, b_index = 0, r_index = 0;
        int[] r = new int[a.length + b.length];
        while (a_index < a.length && b_index < b.length)
            r[r_index++] = a[a_index] < b[b_index] ? a[a_index++] : b[b_index++];

        if (a_index < a.length)
            System.arraycopy(a, a_index, r, r_index, a.length - a_index);
        else
            System.arraycopy(b, b_index, r, r_index, b.length - b_index);
        return r;
    }

}
