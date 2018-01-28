public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        // Amortization
        if (size >= 0.25 * elems.length) {
            Cow [] tempElems = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, tempElems, 0, size);
            elems = tempElems;
        }
        elems[size++] = c;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return elems[index];
    }

    // TODO: Runtime: O(?)
    public Cow remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Cow c = elems[index];
        System.arraycopy(elems, index + 1, elems, index, size - index);
        size--;
        // Amortization
        if (size < 0.25 * elems.length) {
            Cow [] tempElems = new Cow[elems.length / 2];
            System.arraycopy(elems, 0, tempElems, 0, elems.length / 2);
            elems = tempElems;
        }
        return c;
    }

    // TODO: Runtime: O(n)
    public void add(int index, Cow c) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        // Add cow to the end
        add(c);
        // Shift the last cow to index
        System.arraycopy(elems, index, elems, index + 1, size - index);
        elems[index] = c;
    }
}
