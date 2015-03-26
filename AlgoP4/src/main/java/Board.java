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
    private int[][] blocks;
    /**
     * I coordinate
     */
    private int zeroI;
    /**
     * J coordinate
     */
    private int zeroJ;
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
     * Constructor
     */
    public Board(int[][] blockInts) {

        this.n = blockInts.length;

        blocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            //System.arraycopy(blockInts[i], 0, blocks[i], 0, n);
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = blockInts[i][j];
                if (this.blocks[i][j] == 0) {
                    this.zeroI = i;
                    this.zeroJ = j;
                }
            }
        }
    }

    /**
     * Constructor
     */
    private Board(Board prev, int emptyI, int emptyJ) {

        this.n = prev.blocks.length;

        this.blocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(prev.blocks[i], 0, blocks[i], 0, n);
        }

        this.zeroJ = emptyJ;
        this.zeroI = emptyI;
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
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * n + j + 1) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * Returns  sum of Manhattan distances between blocks and goal
     *
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != 0) {
                    int tmp = blocks[i][j] - 1;
                    result += (Math.abs(tmp / n - i)
                            + Math.abs(tmp % n - j));
                }
            }
        }

        return result;
    }

    /**
     * Returns is this board the goal board?
     *
     * @return is this board the goal board?
     */
    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * n + j + 1) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns a board that is obtained by exchanging two adjacent blocks in the same row
     *
     * @return a board that is obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {

        Board b = new Board(this, zeroI, zeroJ);

        if (zeroI > 0) {
            swap(b, 0, 0, 0, 1);
        } else {
            swap(b, 1, 0, 1, 1);
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
    private boolean deepEquals(int[][] array) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.blocks[i][j] != array[i][j]) {
                    return false;
                }
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

        if (zeroJ != 0 && !toRight) {
            Board b = new Board(this, zeroI, zeroJ - 1);
            swap(b, zeroI, zeroJ, zeroI, zeroJ - 1);
            b.toLeft = true;
            boards.enqueue(b);
        }

        if (zeroJ != n - 1 && !toLeft) {
            Board b = new Board(this, zeroI, zeroJ + 1);
            swap(b, zeroI, zeroJ, zeroI, zeroJ + 1);
            b.toRight = true;
            boards.enqueue(b);
        }

        if (zeroI != 0 && !toDown) {
            Board b = new Board(this, zeroI - 1, zeroJ);
            swap(b, zeroI, zeroJ, zeroI - 1, zeroJ);
            b.toUp = true;
            boards.enqueue(b);
        }

        if (zeroI != n - 1 && !toUp) {
            Board b = new Board(this, zeroI + 1, zeroJ);
            swap(b, zeroI, zeroJ, zeroI + 1, zeroJ);
            b.toDown = true;
            boards.enqueue(b);
        }

        return boards;
    }

    /**
     * Returns string representation of this board (in the output format specified below)
     */
    private void swap(Board b, int fromI, int fromJ, int toI, int toJ) {
        int tmp = b.blocks[fromI][fromJ];
        b.blocks[fromI][fromJ] = b.blocks[toI][toJ];
        b.blocks[toI][toJ] = tmp;
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
                s.append(String.format("%2d ", blocks[i][j]));
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
