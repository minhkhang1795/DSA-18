public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        char[] newKeys = new char[keys.length];
        for (char key : keys)
            customQuickSort(locks, newKeys, key);

        result[0] = locks;
        result[1] = newKeys;
        return result;
    }

    private static void customQuickSort(char[] locks, char[] newKeys, char key) {
        int lo = 0, hi = newKeys.length - 1;
        for (int i = 0; i < newKeys.length; i++) {
            if (newKeys[i] != '\0') {
                if (key > newKeys[i])
                    lo = i + 1;
                else {
                    hi = i - 1;
                    break;
                }
            }
        }
        int p = partition(locks, lo, hi, key);
        newKeys[p] = key;
    }

    private static int partition(char[] locks, int lo, int hi, char key) {
        // TODO
        for (int i = lo; i <= hi; i++) {
            if (key == locks[i]) {
                swap(locks, lo, i);
                break;
            }
        }

        int i = lo + 1;
        while (i <= hi) {
            if (locks[i] >= key)
                swap(locks, i, hi--);
            else
                i++;
        }

        swap(locks, lo, --i);
        return i;
    }

}




