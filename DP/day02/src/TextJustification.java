import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    private static double cost(String[] words, int lo, int hi, int m) {
        if (hi <= lo)
            throw new IllegalArgumentException("Hi must be higher than Lo");
        int length = hi - lo - 1; // account for spaces;
        for (int i = lo; i < hi; i++) {
            length += words[i].length();
        }
        if (length > m)
            return Double.POSITIVE_INFINITY;
        return Math.pow(m - length, 3);
    }

    // Time Complexity: N^2
    // Space Complexity: N
    static List<Integer> justifyText(String[] w, int m) {
        int[] lineBreaks = new int[w.length + 1];
        double[] dp = new double[w.length + 1];
        dp[w.length] = 0;
        for (int i = w.length - 1; i >= 0; i--) {
            dp[i] = Double.MAX_VALUE;
            for (int j = i + 1; j <= w.length; j++) {
                if (dp[i] > dp[j] + cost(w, i, j, m)) {
                    dp[i] = dp[j] + cost(w, i, j, m);
                    lineBreaks[i] = j;
                }
            }
        }
        int i = 0;
        List<Integer> result = new ArrayList<>();
        while (i < w.length) {
            result.add(i);
            i = lineBreaks[i];
        }
        return result;
    }

}