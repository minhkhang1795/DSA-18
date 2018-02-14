import java.util.HashMap;
import java.util.Map;

public class SillyString {
    private final String innerString;

    public SillyString(String innerString) {
        this.innerString = innerString;
    }

    public String toString() {
        return innerString;
    }

    @Override
    public boolean equals(Object other) {
        return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        // TODO What is bad about this hash function??
        // Answer: hashCode of 'abc' will be equal to 'cba' because it just sums all the characters' ASCII positions
        int total = 0;
        for (int i = 0; i < innerString.length(); i++) {
            total += innerString.charAt(i);
        }
        return total;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SillyString first = new SillyString("Hello");
        SillyString first_same = new SillyString("Holle");
        SillyString second = new SillyString("World");
        SillyString second_Same = new SillyString("dlroW");
        System.out.println(first.hashCode());
        System.out.println(first_same.hashCode());
        System.out.println(second.hashCode());
        System.out.println(second_Same.hashCode());
    }
}
