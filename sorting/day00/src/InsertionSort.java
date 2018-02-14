public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     * <p>
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime:  O(n^2)
     * <p>
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        for (int i = 1; i < array.length; i++) {
            int k = i;
            while (k > 0 && array[k] < array[k - 1])
                swap(array, k, --k);
        }
        return array;
    }
}
