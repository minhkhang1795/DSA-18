public class EditDistance {

    public static int minEditDist(String a, String b) {
        // TODO: Your code here
        int[][] memo = new int[a.length()][b.length()];
        return minEditDistDP(a, a.length(), b, b.length(), memo);
    }

    private static int minEditDistDP(String a, int len_a, String b, int len_b, int[][] memo) {
        int cost;

        // Base case: empty strings
        if (len_a == 0) return len_b;
        if (len_b == 0) return len_a;

        if (memo[len_a - 1][len_b - 1] != 0)
            return memo[len_a - 1][len_b - 1];

        // test if last characters of the strings match
        if (a.charAt(len_a - 1) == b.charAt(len_b - 1))
            cost = 0;
        else
            cost = 1;

        // return minimum of delete char from s, delete char from t, and delete char from both
        memo[len_a - 1][len_b - 1] = Math.min(Math.min(minEditDistDP(a, len_a - 1, b, len_b, memo) + 1,
                minEditDistDP(a, len_a, b, len_b - 1, memo) + 1),
                minEditDistDP(a, len_a - 1, b, len_b - 1, memo) + cost);
        return memo[len_a - 1][len_b - 1];
    }

}
