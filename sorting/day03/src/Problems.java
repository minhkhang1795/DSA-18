import java.util.Objects;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
        for (int i = 0; i < A.length; i++)
            A[i] += 100;
        CountingSort.countingSort(A);
        for (int i = 0; i < A.length; i++)
            A[i] -= 100;
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        int max = -1;
        for (String a : A) {
            int c = getNthCharacter(a, n);
            max = max > c ? max : c;
        }

        String[] counter = new String[max + 1];
        for (int i = 0; i < A.length; i++) {
            int index = getNthCharacter(A[i], n);
            if (counter[index] == null)
                counter[index] = "";
            counter[index] += i + " ";
        }

        int j = 0;
        String[] clonedA = A.clone();
        for (String s : counter) {
            if (s == null)
                continue;

            String[] indices = s.split(" ");
            for (String indexString : indices) {
                int index = Integer.parseInt(indexString);
                A[j++] = clonedA[index];
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
        for (int i = 0; i < stringLength; i++)
            countingSortByCharacter(S, i);
    }

    /**
     * @param A The array to count swaps in
     */

    static int countSwaps(int[] A) {
        // TODO
        int[] count = new int[1];
        mergeSort(A, count);
        return count[0];
    }

    private static int[] mergeSort(int[] array, int[] count) {
        if (array.length <= 1)
            return array;
        int mid = array.length / 2;
        int[] left = mergeSort(split(array, 0, mid), count);
        int[] right = mergeSort(split(array, mid, array.length), count);
        return merge(left, right, count);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    private static int[] merge(int[] a, int[] b, int[] count) {
        int a_index = 0, b_index = 0, r_index = 0;
        int[] r = new int[a.length + b.length];
        while (a_index < a.length && b_index < b.length) {
            if (a[a_index] < b[b_index])
                r[r_index++] = a[a_index++];
            else {
                r[r_index++] = b[b_index++];
                count[0] += a.length - a_index;
            }
        }

        if (a_index < a.length)
            System.arraycopy(a, a_index, r, r_index, a.length - a_index);
        else
            System.arraycopy(b, b_index, r, r_index, b.length - b_index);
        return r;
    }

    private static int[] split(int[] a, int start, int end) {
        if (start > end)
            return null;
        int[] b = new int[end - start];
        System.arraycopy(a, start, b, 0, end - start);
        return b;
    }

}
