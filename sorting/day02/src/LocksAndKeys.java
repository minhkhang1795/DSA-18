import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        boolean[] keyUsed = new boolean[locks.length];
        quickSort(locks, keys, keyUsed, 0, locks.length - 1, 0);
        System.out.println(Arrays.toString(locks));
        System.out.println(Arrays.toString(keys));
        return result;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    private static void quickSort(char[] locks, char[] keys, boolean[] keyUsed, int lo, int hi, int keyIndex) {
        if (lo < hi) {
            int[] p_i = partition(locks, keys, keyUsed, lo, hi, keyIndex);
            if (p_i[0] == -1)
                return;
            int p = p_i[0];
            keyIndex = p_i[1];
            quickSort(locks, keys, keyUsed, lo, p - 1, keyIndex);
            quickSort(locks, keys, keyUsed, p + 1, hi, keyIndex);
        } else if (lo == hi) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == locks[lo]) {
                    swap(keys, i, lo);
                    keyUsed[lo] = true;
                    break;
                }
            }
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
    private static int[] partition(char[] locks, char[] keys, boolean[] keyUsed, int lo, int hi, int keyIndex) {
        // TODO
        // If key works, then partition, else return -1
        boolean keyWorked = false;
        for (int i = lo; i <= hi; i++)
            if (locks[i] == keys[keyIndex]) {
                swap(locks, i, lo);
                keyWorked = true;
                break;
            }
        if (!keyWorked)
            return new int[]{-1, -1};

        // Quick  sort's partition
        int p = lo + 1;
        while (p <= hi) {
            if (locks[p] >= keys[keyIndex])
                swap(locks, p, hi--);
            else
                p++;
        }

        // Swap locks and keys to the right position
        swap(locks, lo, --p);
        swap(keys, keyIndex, p);

        // Indicate that position is correctly sorted
        keyUsed[p] = true;

        // Find new position if needed
        for (int i = keyIndex; i < keyUsed.length; i++) {
            if (!keyUsed[i]) {
                keyIndex = i;
                break;
            }
        }
        return new int[]{p, keyIndex};
    }
}




