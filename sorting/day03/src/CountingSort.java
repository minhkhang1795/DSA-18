import java.util.Arrays;

public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     * <p>
     * Time Complexity: O(n + k)
     * Space Complexity: O(k)
     * k: maximum element in array A
     */
    public static void countingSort(int[] A) {
        // TODO
        int max = -1;
        for (int a : A)
            max = max > a ? max : a;
        int[] counter = new int[max + 1];
        for (int a : A)
            counter[a]++;

        for (int i = 0, j = 0; i < counter.length; i++)
            while (counter[i]-- > 0)
                A[j++] = i;
    }
}
