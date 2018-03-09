import java.util.ArrayList;

public class Problems {

    public static int leastSum(int[] A) {
        //TODO
        int[] count = new int[10];
        for (int a : A)
            count[a]++;

        int a = 0, b = 0;
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                if (j++ % 2 == 0)
                    a = a * 10 + i;
                else
                    b = b * 10 + i;
            }
        }
        return a + b;
    }
}
