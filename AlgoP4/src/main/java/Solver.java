import java.util.Iterator;

/**
 * Created by Artem_Mikhalevitch on 3/18/15.
 */
public class Solver {
    /*
    * Board.
    */
    private BoardState boardState;
    /*
    * Board.
    */
    private int moves;
    /*
    * Board.
    */
    private boolean solvable;
    /*
    * Board.
    */
    private MinPQ<BoardState> open;
    /*
    * Board.
    */
    private Stack<Board> solution;

    /*
     * Constructor. Find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        boardState = new BoardState(initial, null);
        open = new MinPQ<BoardState>();
        this.solve();
    }

    /*
    * Solution.
     */
    private void solve() {

        open.insert(this.boardState);

        int max = 0;
        int actions = 0;
        while (open.size() != 0) {
            //actions++;
            BoardState min = open.delMin();
            //max = open.size() > max ? open.size() : max;

            if (min.board.isGoal()) {
                solvable = true;
                this.restorePath(min);
                break;
            }

            if (min.board.twin().isGoal()) {
                break;
            }

            Iterator<Board> iterator = min.board.neighbors().iterator();

            while (iterator.hasNext()) {
                Board next = iterator.next();
                open.insert(new BoardState(next, min));
            }
        }

        //System.out.println("pq max size: " + max);
        //System.out.println("actions: " + actions);

    }

    private void restorePath(final BoardState another) {
        BoardState state = another;

        solution = new Stack<Board>();

        while (state.parent != null) {
            solution.push(state.board);
            state = state.parent;
            moves++;
        }

        solution.push(state.board);
    }

    /*
     * Returns true if the initial board solvable?
     * @return true if solvable
     */
    public boolean isSolvable() {
        return solvable;
    }

    /*
     * Returns min number of moves to solve initial board; -1 if unsolvable
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if (!solvable) {
            return -1;
        }
        return moves;
    }

    /*
     * Returns sequence of boards in a shortest solution; null if unsolvable
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return solution;
    }

    /*
     * Returns min number of moves to solve initial board; -1 if unsolvable
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public static void main(String[] args) {
        // create initial board from file
        //String folder = "D:\\Projects\\Algos\\AlgoP4\\src\\resources\\";
        //String fileName = "puzzle3x3-unsolvable2.txt";
        //In in = new In(folder + fileName);
        String fileName = args[0];

        In in = new In(fileName);

        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class BoardState implements Comparable {

        /*
        * Constructor.
        */
        private Board board;
        /*
        * Constructor.
        */
        private BoardState parent;
        /*
       * Constructor.
       */
        private int level;

        /*
        * Constructor.
        */
        private BoardState(Board board, BoardState parent) {
            this.board = board;
            this.parent = parent;
            if (parent != null) {
                this.level = parent.level + 1;
            }
        }

        /*
        * Board.
        * @param o comparasion element
        * @return value
        */
        @Override
        public int compareTo(Object o) {

            BoardState that = (BoardState) o;

            int result = this.board.manhattan() + this.level - that.board.manhattan() - that.level;
            if (result == 0) {
                result = this.board.hamming() + this.level - that.board.hamming() - that.level;
            }
            return result;
        }
    }
}
