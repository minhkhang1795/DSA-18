import jdk.nashorn.api.tree.BinaryTree;
import jdk.nashorn.api.tree.ExpressionTree;
import jdk.nashorn.api.tree.TreeVisitor;

import java.util.ArrayList;

public class LongestIncreasingSubsequence {

    // Runtime: N^2
    // Space: N
    public static int LIS(int[] A) {
        // TODO
        int max = -1;
        int[] memo = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] < A[j]) {
                    memo[i] = memo[i] < memo[j] + 1 ? memo[j] + 1 : memo[i];
                }
            }
            max = memo[i] > max ? memo[i] : max;
        }
        return max + 1;
    }
}