import java.util.Arrays;

public class BalloonPopping {

    public static int maxPoints(int[] B) {
        // TODO
        int[][] pts = new int[B.length][B.length];
        for (int len = 0; len < B.length; len++) {
            for (int i = 0; i < B.length - len; i++) {
                int j = i + len;
                int max_pts = -1;

                for (int k = i; k <= j; k++) {
                    int B_left = i - 1 < 0 ? 1 : B[i - 1];
                    int B_right = j + 1 >= B.length ? 1 : B[j + 1];
                    int left_pts = i > k - 1 ? 0 : pts[i][k - 1];
                    int right_pts = k + 1 > j ? 0 : pts[k + 1][j];
                    int pts_temp = B_left * B[k] * B_right + left_pts + right_pts;
                    if (pts_temp > max_pts) {
                        max_pts = pts_temp;
                    }
                }
                pts[i][j] = max_pts;
            }
        }
        return pts[0][B.length - 1];
    }

}
