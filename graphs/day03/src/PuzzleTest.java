import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for 8 Puzzle functionality
 */


public class PuzzleTest {

    private Board board;

    @BeforeEach
    public void setUp() throws Exception {
        int[][] initState = {{1, 2, 3}, {4, 6, 0}, {7, 5, 8}};
        board = new Board(initState);
    }

    // Test board methods

    /**
     * Test method for void manhattan().
     */
    @Test
    public void testManhattan() {
        assertEquals(board.manhattan(), 3);
    }

    /**
     * Test method for boolean isGoal().
     */
    @Test
    public void testGoal() {
        assertFalse(board.isGoal());
    }

    // Test solver with several initial board states

    /**
     * Test method for Solver - Unsolvable puzzle
     */
    @Test
    public void testSolverUnsolvable() {
        // Unsolvable puzzle
        int[][] initState = {{1, 0, 3}, {2, 4, 5}, {6, 7, 8}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertFalse(solver.isSolvable());
        solver = new Solver(new Board(new int[][]{{1, 8, 2},{0,4,3},{7,6,5}}));
        assertTrue(solver.isSolvable());
        solver = new Solver(new Board(new int[][]{{8, 1, 2},{0,4,3},{7,6,5}}));
        assertFalse(solver.isSolvable());
    }

    /**
     * Test method for Solver - Easy puzzle
     */
    @Test
    public void testSolverEasy() {
        // Easy solve puzzle
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Create solution boards list
        assertEquals(solver.minMoves, 1);
    }

    @Test
    public void testSolverAverage() {
        int[][] initState = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 4);
    }


    @Test
    public void testSolverMedium() {
        int[][] initState = {{2, 3, 6}, {1, 5, 0}, {4, 7, 8}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 7);
    }

    @Test
    public void testSolverHard() {
        int[][] initState = {{0, 3, 5}, {2, 1, 8}, {4, 7, 6}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 12);
    }

    @Test
    public void testSolverReallyHard() {
        int[][] initState = {{3, 5, 6}, {1, 2, 8}, {0, 4, 7}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 16);
    }


    @Test
    public void testSolverRidiculouslyHard() {
        int[][] initState = {{3, 5, 2}, {6, 0, 1}, {7, 8, 4}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 22);
    }

    /**
     * Test method for Solver - Hard puzzle
     * Will take a long time to run
     */
    @Test
    public void testSolverInsane() {
        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 31);
    }

    @Test
    public void testSolverInsane2() {
        int[][] initState = {{6, 4, 7}, {8, 5, 0}, {3, 2, 1}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
        assertTrue(solver.isSolvable());
        // Check number of moves
        assertEquals(solver.minMoves, 31);
    }

	@Test
	public void test15SolverSolvable() {
		int[][] initState = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
		Board initial = new Board(initState);
		Solver solver = new Solver(initial);
		assertTrue(solver.isSolvable());
		// Check number of moves
		assertEquals(solver.minMoves, 0);
	}

//	@Test
//	public void test15SolverMedium() {
//		int[][] initState = {{6, 8, 0, 4}, {13, 9, 10, 14}, {15, 2, 12, 5}, {1, 3, 7, 11}};
//		Board initial = new Board(initState);
//		Solver solver = new Solver(initial);
//		assertTrue(solver.isSolvable());
//		// Check number of moves
//		assertEquals(solver.minMoves, 50);
//		solver.solution();
//	}
//
//	@Test
//	public void test15SolverMedium2() {
//		int[][] initState = {{2, 7, 4, 3}, {1, 12, 8, 6}, {0, 14, 15, 9}, {13, 5, 11, 10}};
//		Board initial = new Board(initState);
//		Solver solver = new Solver(initial);
//		assertTrue(solver.isSolvable());
//		// Check number of moves
//		assertEquals(solver.minMoves, 40);
//		solver.solution();
//	}
//
//	@Test
//	public void test15SolverMedium3() {
//		int[][] initState = {{2, 3, 1, 9}, {5, 4, 7, 11}, {10, 0, 14, 15}, {12, 8, 6, 13}};
//		Board initial = new Board(initState);
//		Solver solver = new Solver(initial);
//		assertTrue(solver.isSolvable());
//		// Check number of moves
//		assertEquals(solver.minMoves, 51);
//		solver.solution();
//	}

//    @Test
//    public void test15SolverInsane() {
//        int[][] initState = {{0, 12, 9, 13}, {15, 11, 10, 14}, {3, 7, 2, 5}, {4, 8, 6, 1}};
//        Board initial = new Board(initState);
//        Solver solver = new Solver(initial);
//		assertTrue(solver.isSolvable());
//        // Check number of moves
//        assertEquals(solver.minMoves, 80);
//    }
}
