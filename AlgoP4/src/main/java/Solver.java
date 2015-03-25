import java.util.Comparator;
import java.util.Iterator;

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
    private boolean solvable;
    /*
    * Board.
    */
    private MinPQ<Board> open;
    /*
    * Board.
    */
    private Stack<Board> solution;

    /*
     * Constructor. Find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        board = initial;
        open = new MinPQ<Board>(0, Board.priceComparator);
        this.solve();
    }

    /*
    * Solution.
     */
    private void solve() {

        boolean found = false;
        open.insert(this.board);

        while (open.size() != 0 && !found) {

            Board min = open.delMin();

            if (min.isGoal()) {
                found = true;
                this.restorePath(min);
            }

            Iterator<Board> iterator = min.neighbors().iterator();

            while (iterator.hasNext()) {
                Board next = iterator.next();
                open.insert(next);
            }
        }
    }

    private void restorePath(Board board) {

        solution = new Stack<Board>();

        while (board != null) {
            solution.push(board);
            board = board.parent;
        }
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
        String folder = "D:\\Projects\\Algos\\AlgoP4\\src\\main\\resources\\";
        String fileName = "puzzle05.txt";
        //String fileName = args[0];

        In in = new In(folder + fileName);

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
