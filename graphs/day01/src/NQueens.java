import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     * <p>
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     * <p>
     * Returns true if a Queen is found.
     * <p>
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        nQueensSolutions(new char[n][n], 0, answers);
        return answers;
    }

    private static void nQueensSolutions(char[][] current, int row, List<char[][]> answers) {
        if (row == current.length) {
            answers.add(copyOf(current));
            return;
        }
        for (int col = 0; col < current.length; col++) {
            if (!checkValid(current, row, col)) {
                continue;
            }
            fillBoard(current, row, col);
            nQueensSolutions(current, row + 1, answers);
        }
    }

    private static boolean checkValid(char[][] current, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (current[i][col] == 'Q')
                return false;
        }
        return !checkDiagonal(current, row, col);
    }

    private static void fillBoard(char[][] current, int row, int col) {
        for (int i = 0; i < current.length; i++) {
            current[row][i] = i == col ? 'Q' : '.';
        }
    }

}
