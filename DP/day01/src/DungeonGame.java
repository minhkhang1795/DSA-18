import static java.lang.Math.abs;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        // TODO: Your code here
        // This does not check the case in which map only has one row or one column
        int[][] healths = new int[map.length][map[0].length];
        int[][] minHealths = new int[map.length][map[0].length];
        for (int i = 0; i < healths.length; i++) {
            for (int j = 0; j < healths[i].length; j++) {
                if (i == 0 && j == 0) {
                    healths[i][j] = map[i][j];
                    minHealths[i][j] = healths[i][j] < 0 ? abs(healths[i][j]) + 1 : 1;
                } else {
                    int health = Integer.MIN_VALUE;
                    int topMinHealth = Integer.MAX_VALUE;
                    int leftMinHealth = Integer.MAX_VALUE;
                    if (i - 1 >= 0) {
                        health = healths[i - 1][j];
                        topMinHealth = healths[i - 1][j] + map[i][j] < 0 ? abs(healths[i - 1][j] + map[i][j]) + 1 : 1;
                        topMinHealth = topMinHealth > minHealths[i - 1][j] ? topMinHealth : minHealths[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        health = health > healths[i][j - 1] ? health : healths[i][j - 1];
                        leftMinHealth = healths[i][j - 1] + map[i][j] < 0 ? abs(healths[i][j - 1] + map[i][j]) + 1 : 1;
                        leftMinHealth = leftMinHealth > minHealths[i][j - 1] ? leftMinHealth : minHealths[i][j - 1];
                    }
                    healths[i][j] = health + map[i][j];
                    minHealths[i][j] = topMinHealth > leftMinHealth ? leftMinHealth : topMinHealth;
                }

            }
        }
        return minHealths[minHealths.length - 1][minHealths[0].length - 1];
    }
}
