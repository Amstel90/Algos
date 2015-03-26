/**
 * Created by Artem_Mikhalevitch on 3/18/15.
 */
public class Board {
    /**
     * Dimension
     */
    private int n;
    /**
     * Blocks Matrix
     */
    private int[] blocks;
    /**
     * I coordinate
     */
    private int zero;
    /**
     * Board.
     */
    private boolean toLeft;
    /**
     * Board.
     */
    private boolean toRight;
    /**
     * Board.
     */
    private boolean toUp;
    /**
     * Board.
     */
    private boolean toDown;

    /**
     * Board.
     */
    private int manhattan = -1;
    /**
     * Board.
     */
    private int hamming = -1;


    /**
     * Constructor
     */
    public Board(int[][] blockInts) {

        this.n = blockInts.length;

        blocks = new int[n * n];

        for (int i = 0; i < n; i++) {
            //System.arraycopy(blockInts[i], 0, blocks[i], 0, n);
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                this.blocks[index] = blockInts[i][j];
                if (this.blocks[index] == 0) {
                    this.zero = index;
                }
            }
        }
    }

    /**
     * Constructor
     */
    private Board(Board prev, int empty) {

        this.n = (int) Math.sqrt(prev.blocks.length);

        this.blocks = new int[n * n];

        System.arraycopy(prev.blocks, 0, blocks, 0, n * n);

        this.zero = empty;
    }

    /**
     * Returns dimension
     *
     * @return dimension
     */
    public int dimension() {
        return this.n;
    }

    /**
     * Returns number of blocks out of place
     *
     * @return number of blocks out of place
     */
    public int hamming() {
        if (hamming == -1) {
            calcPrices();
        }
        return hamming;
    }

    /**
     * Returns  sum of Manhattan distances between blocks and goal
     *
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        if (manhattan == -1) {
            calcPrices();
        }
        return manhattan;
    }

    private void calcPrices() {
        int hammingRes = 0;
        int manhattanRes = 0;
        for (int i = 0; i < n * n; i++) {
            if (blocks[i] != 0 && blocks[i] != i + 1) {
                hammingRes++;
            }
            if (blocks[i] != 0) {
                manhattanRes += Math.abs((blocks[i] - 1) / n - i / n) +
                        Math.abs((blocks[i] - 1) % n - i % n);
            }
        }
        this.hamming = hammingRes;
        this.manhattan = manhattanRes;
    }

    /**
     * Returns is this board the goal board?
     *
     * @return is this board the goal board?
     */
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    /**
     * Returns a board that is obtained by exchanging two adjacent blocks in the same row
     *
     * @return a board that is obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {

        Board b = new Board(this, zero);

        if (zero >= n) {
            swap(b, 0, 1);
        } else {
            swap(b, n, n + 1);
        }

        return b;
    }

    /**
     * Returns does this board equal y?
     *
     * @return does this board equal y?
     */
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if (y instanceof Board) {
            Board anotherBoard = (Board) y;
            return (this.n == anotherBoard.n)
                    && deepEquals(anotherBoard.blocks);
        }

        return false;
    }

    /**
     * Returns true if equals
     *
     * @return true if equals
     */
    private boolean deepEquals(int[] array) {
        for (int i = 0; i < n * n; i++) {
            if (this.blocks[i] != array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns all neighboring boards
     *
     * @return all neighboring boards
     */
    public Iterable<Board> neighbors() {

        Queue<Board> boards = new Queue<Board>();

        int zeroJ = zero % n;
        int zeroI = zero / n;

        if (zeroJ != 0 && !toRight) {
            Board b = new Board(this, zero - 1);
            swap(b, zero, zero - 1);
            b.toLeft = true;
            boards.enqueue(b);
        }

        if (zeroJ != n - 1 && !toLeft) {
            Board b = new Board(this, zero + 1);
            swap(b, zero, zero + 1);
            b.toRight = true;
            boards.enqueue(b);
        }

        if (zeroI != 0 && !toDown) {
            Board b = new Board(this, zero - n);
            swap(b, zero, zero - n);
            b.toUp = true;
            boards.enqueue(b);
        }

        if (zeroI != n - 1 && !toUp) {
            Board b = new Board(this, zero + n);
            swap(b, zero, zero + n);
            b.toDown = true;
            boards.enqueue(b);
        }

        return boards;
    }

    /**
     * Returns string representation of this board (in the output format specified below)
     */
    private void swap(Board b, int from, int to) {
        int tmp = b.blocks[from];
        b.blocks[from] = b.blocks[to];
        b.blocks[to] = tmp;
    }

    /**
     * Returns string representation of this board (in the output format specified below)
     *
     * @return string representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i * n + j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Main
     */
    public static void main(String[] args) {

    }
}
