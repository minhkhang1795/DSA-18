public abstract class SortAlgorithm {

    abstract int[] sort(int[] array);

    void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    int[] split(int[] a, int start, int end) {
        if (start > end)
            return null;
        int[] b = new int[end - start];
        System.arraycopy(a, start, b, 0, end - start);
        return b;
    }

}
