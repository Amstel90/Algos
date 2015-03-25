import java.util.Comparator;

/**
 * Created by Artem_Mikhalevitch on 3/18/15.
 */
public class Solver {
    /*
    * Board.
    */
    private Board board;
    /*
    * Board.
    */
    private int moves;
    /*
    * Board.
    */
    private MinPQ<Board> queue;

    /*
     * Constructor. Find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        board = initial;
        queue = new MinPQ<Board>(0, new BoardComparator());
    }

    /*
     * Returns true if the initial board solvable?
     * @return true if solvable
     */
    public boolean isSolvable() {
        throw new UnsupportedOperationException();
    }

    /*
     * Returns min number of moves to solve initial board; -1 if unsolvable
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return moves;
    }

    /*
     * Returns sequence of boards in a shortest solution; null if unsolvable
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        Stack<Board> boards = new Stack<Board>();

        return boards;
    }

    /*
     * Returns sequence of boards in a shortest solution; null if unsolvable
     */
    private class BoardComparator implements Comparator<Board> {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.manhattan() - o2.dimension();
        }
    }

    /*
     * Returns min number of moves to solve initial board; -1 if unsolvable
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
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
}
