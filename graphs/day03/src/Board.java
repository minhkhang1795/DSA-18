import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    private int manhattan = -1;

    // Create a 2D array representing the solved board state
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        this.tiles = b;
        this.n = b.length;
        if (this.n == 3) {
            this.goal = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        } else if (this.n == 4) {
            this.goal = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        } else {
            this.initGoal();
        }
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    private void initGoal() {
        goal = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goal[i][j] = count++;
            }
        }
        goal[n - 1][n - 1] = 0;
    }

    /*
     * Prints out the board state nicely for debugging purposes
     */
    public void printBoard() {
        for (int[] tile : this.tiles) {
            for (int cell : tile) System.out.print(cell + "\t");
            System.out.println();
        }
        System.out.println("==============");
    }

    /*
     * Size of the board
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return this.n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        if (this.manhattan != -1)
            return this.manhattan;
        this.manhattan = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                int value = this.tiles[i][j];
                if (value == 0) {
                    continue;
                }
                value -= 1;
                int hori = Math.abs(j - (value % n));
                int verti = Math.abs(i - value / n);
                this.manhattan += hori + verti;
            }
        }
        return this.manhattan;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] != this.goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        boolean[] reference = new boolean[n * n - 1];
        int numInv = 0;
        int posFromBottom = 0;
        for (int i = 0; i < tiles.length; i++) {
            int[] tile = tiles[i];
            for (int value : tile) {
                if (value != 0) {
                    reference[value - 1] = true;
                    for (int k = 0; k < value - 1; k++) {
                        if (!reference[k]) {
                            numInv++;

                        }
                    }
                } else {
                    posFromBottom = n - i;
                }
            }
        }
        if (n % 2 != 0) { // Odd board
            return numInv % 2 == 0;
        } else { // Even board
            return posFromBottom % 2 == 0 && numInv % 2 != 0 || posFromBottom % 2 != 0 && numInv % 2 == 0;
        }

    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Queue<Board> neighbors() {
        // all the edge cases when trying to move pieces into the blank spaces

        // find blank tile
        int i0 = 0;
        int j0 = 0;
        outerloop:
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    break outerloop;
                }
            }
        }

        Queue<Board> boards = new LinkedList<>();

        if (i0 + 1 < this.n) {
            Board board = new Board(clone(this.tiles));
            swap(board.tiles, i0, j0, i0 + 1, j0);
            boards.add(board);
        }
        if (i0 - 1 >= 0) {
            Board board = new Board(clone(this.tiles));
            swap(board.tiles, i0, j0, i0 - 1, j0);
            boards.add(board);
        }
        if (j0 + 1 < this.n) {
            Board board = new Board(clone(this.tiles));
            swap(board.tiles, i0, j0, i0, j0 + 1);
            boards.add(board);
        }
        if (j0 - 1 >= 0) {
            Board board = new Board(clone(this.tiles));
            swap(board.tiles, i0, j0, i0, j0 - 1);
            boards.add(board);
        }
        return boards;
    }

    private void swap(int[][] tiles, int i1, int j1, int i2, int j2) {
        int temp = tiles[i1][j1];
        tiles[i1][j1] = tiles[i2][j2];
        tiles[i2][j2] = temp;
    }

    private int[][] clone(int[][] tiles) {
        int[][] new_tiles = new int[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            new_tiles[i] = tiles[i].clone();
        }
        return new_tiles;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
