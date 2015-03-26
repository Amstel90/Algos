import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by Artem_Mikhalevitch on 3/18/15.
 */
public class BoardTest {

    private int[][] board = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    @Test
    public void testFunc() {
        Board b = new Board(board);
        Assert.assertEquals(5, b.hamming());
        Assert.assertEquals(10, b.manhattan());
        Assert.assertEquals(3, b.dimension());
        Assert.assertTrue(b.equals(new Board(board)));
        Assert.assertFalse(b.isGoal());
        Assert.assertTrue(new Board(goal).isGoal());
    }

    @Test
    public void testPrint() {
        Board b = new Board(board);
        Iterator<Board> boards = b.neighbors().iterator();

      while(boards.hasNext()){
          System.out.println(boards.next());
        }
        //System.out.println(b);
        //System.out.println(b.twin());

    }


}
