import java.util.*;

import static java.lang.Math.abs;

public class SplitCoins {

    public static int splitCoins(int[] coins) {
        // TODO
        int sum = 0;
        for (int i : coins)
            sum += i;

        int[][] dp = new int[coins.length][sum / 2 + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        int half = sum/2;
        int minSplit = splitCoinsDP(coins, coins.length - 1, half, dp);
        return abs(sum  - 2*(half - minSplit));
    }

    private static int splitCoinsDP(int[] coins, int i, int half, int[][] dp) {
        if (i == -1)
            return half;
        if (dp[i][half] != -1)
            return dp[i][half];
        if (half < coins[i])
            dp[i][half] = splitCoinsDP(coins, i - 1, half, dp);
        else
            dp[i][half] = Math.min(splitCoinsDP(coins, i - 1, half, dp), splitCoinsDP(coins, i - 1, half - coins[i], dp));
        return dp[i][half];
    }
}
