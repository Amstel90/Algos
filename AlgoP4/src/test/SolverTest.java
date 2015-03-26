import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ASUS on 26.03.2015.
 */
public class SolverTest {

    @Test
    public void testTiming() {
        String folder = "D:\\Projects\\Algos\\AlgoP4\\src\\resources\\";
        File file = new File(folder);
        ArrayList<Board> boards = new ArrayList<Board>();
        for (File f : file.listFiles()) {
            boards.add(readBoard(f));
        }

        Stopwatch stopwatch = new Stopwatch();
        for (Board b : boards) {
            new Solver(b).isSolvable();
        }
        System.out.println(stopwatch.elapsedTime());


    }

    private Board readBoard(File file) {
        In in = new In(file);

        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        return initial;
    }
}
