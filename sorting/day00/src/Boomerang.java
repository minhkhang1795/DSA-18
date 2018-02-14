import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO O(n^2)
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> boomerang = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j == i)
                    continue;
                int d = distance(points[i], points[j]);
                // Increment count in hash map, put key & value = 1 if key does not exist
                boomerang.merge(d, 1, Integer::sum);
            }
            for (Integer n : boomerang.values())
                result += n * (n - 1);
        }
        return result;
    }

    private static int distance(int[] a, int[] b) {
        int x = a[0] - b[0], y = a[1] - b[1];
        return x * x + y * y;
    }
}

