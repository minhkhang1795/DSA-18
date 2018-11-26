/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State> {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.cost = moves + board.manhattan();
        }

        @Override
        public int hashCode() {
            return this.board.hashCode();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        public int compareTo(State s) {
            return this.cost == s.cost ? -this.moves + s.moves : this.cost - s.cost;
        }
    }


    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        this.solutionState = new State(initial, 0, null);
        if (this.isSolvable()) {
            this.solveAStar();
//            this.solveIDAStar();
        }
    }

    private void solveIDAStar() {
        int bound = solutionState.cost;
        Stack<State> path = new Stack<>();
        HashSet<State> pathRef = new HashSet<>();
        path.push(solutionState);
        pathRef.add(solutionState);
        while (!solutionState.board.isGoal()) {
            bound = searchIDAStar(path, pathRef, solutionState.cost, bound);
            System.out.println(bound);
        }
        minMoves = solutionState.moves;
    }

    private int searchIDAStar(Stack<State> path, HashSet<State> pathRef, int f, int bound) {
        State currState = path.lastElement();
        if (f > bound) {
            return f;
        }
        if (currState.board.isGoal()) {
            solutionState = currState;
            return -Math.abs(f); // FOUND SOLUTION
        }
        int min = Integer.MAX_VALUE;
        PriorityQueue<Board> neighbors = currState.board.neighbors();
        while (!neighbors.isEmpty()) {
            State state = new State(neighbors.poll(), currState.moves + 1, currState);
            if (!pathRef.contains(state)) {
                path.push(state);
                pathRef.add(state);
                int t = searchIDAStar(path, pathRef, state.cost, bound);
                if (t < 0) {
                    return -Math.abs(t); // FOUND SOLUTION
                }
                if (t < min) {
                    min = t;
                }
                path.pop();
                pathRef.remove(state);
            }
        }
        return min;
    }

    private void solveAStar() {
        PriorityQueue<State> open = new PriorityQueue<>();
        HashMap<State, Integer> closed = new HashMap<>();
        open.add(this.solutionState);
        closed.put(this.solutionState, this.solutionState.cost);
        while (!open.isEmpty()) {
            State q = open.poll();
//            System.out.println(q.moves);
            if (q.board.isGoal()) {
                // Stop search
                this.solutionState = q;
                this.minMoves = q.moves;
                break;
            }
            PriorityQueue<Board> neighbors = q.board.neighbors();
            while (!neighbors.isEmpty()) {
                State state = new State(neighbors.poll(), q.moves + 1, q);

                if (!closed.containsKey(state)) {
                    open.add(state);
                    closed.put(state, state.cost);
                } else if (closed.get(state) > state.cost) {
                    open.add(state);
                    closed.replace(state, state.cost);
                }
            }
        }
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return this.solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public List<Board> solution() {
        State state = this.solutionState;
        List<Board> list = new LinkedList<>();
        while (state != null) {
            state.board.printBoard();
            list.add(state.board);
            state = state.prev;
        }
        return list;
    }


    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
    }


}