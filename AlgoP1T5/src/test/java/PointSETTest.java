import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;


/**
 * Created by ASUS on 26.03.2015.
 */
public class PointSETTest {

    @Test
    public void test() {
        PointSET pointSET = new PointSET();
        Assert.assertTrue(pointSET.isEmpty());
        Assert.assertEquals(0, pointSET.size());

        Point2D point = new Point2D(1, 1);
        pointSET.insert(point);
        Assert.assertFalse(pointSET.isEmpty());
        Assert.assertEquals(1, pointSET.size());
        Assert.assertTrue(pointSET.contains(point));

        Point2D point2 = new Point2D(2, 2);
        pointSET.insert(point2);
        Assert.assertFalse(pointSET.isEmpty());
        Assert.assertEquals(2, pointSET.size());
        Assert.assertTrue(pointSET.contains(point2));

        Point2D point3 = new Point2D(2, 2);
        pointSET.insert(point3);
        Assert.assertEquals(2, pointSET.size());
        Assert.assertTrue(pointSET.contains(point2));
    }

    @Test
    public void testRect() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(1, 1));
        pointSET.insert(new Point2D(2, 2));
        pointSET.insert(new Point2D(3, 3));
        pointSET.insert(new Point2D(4, 4));

        Assert.assertEquals(0, counter(pointSET, new RectHV(0, 0, 0.9, 0.9)));
        Assert.assertEquals(1, counter(pointSET, new RectHV(0, 0, 1, 1)));

        for (double i = 0.1; i < 5; i += 0.1) {
            int expected = (int) Math.floor(i);
            Assert.assertEquals(expected, counter(pointSET, new RectHV(0, 0, i, i)));
        }
    }

    @Test
    public void testNearest() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(1, 1));
        pointSET.insert(new Point2D(2, 2));
        pointSET.insert(new Point2D(3, 3));
        pointSET.insert(new Point2D(4, 4));

        Assert.assertEquals(new Point2D(1, 1), pointSET.nearest(new Point2D(0, 0)));
        Assert.assertEquals(new Point2D(2, 2), pointSET.nearest(new Point2D(2.1, 2.1)));
        Assert.assertEquals(new Point2D(3, 3), pointSET.nearest(new Point2D(3.49, 3.49)));
        Assert.assertEquals(new Point2D(4, 4), pointSET.nearest(new Point2D(9, 9)));
    }

    @Test
    public void files() {
        String folder = "D:\\Projects\\Algos\\AlgoP1T5\\src\\resources\\";

        File file = new File(folder);
        Stopwatch stopwatch1 = new Stopwatch();

        for (File f : file.listFiles()) {
            int expected = Integer.parseInt(f.getName()
                    .replaceAll("circle", "").replace(".txt", ""));
            Stopwatch stopwatch2 = new Stopwatch();
            PointSET p = readFile(f, expected);
            Assert.assertEquals(expected, counter(p, new RectHV(0, 0, 1, 1)));
            System.out.println(expected + " took:  " + stopwatch2.elapsedTime());
        }
        System.out.println("all took:  " + stopwatch1.elapsedTime());

    }

    public PointSET readFile(File file, int n) {
        In in = new In(file);
        PointSET pointSET = new PointSET();
        for (int i = 0; i < n; i++) {
            pointSET.insert(new Point2D(in.readDouble(), in.readDouble()));
        }
        return pointSET;
    }

    private int counter(PointSET pointSET, RectHV rectHV) {
        Iterator<Point2D> iterator = pointSET.range(rectHV).iterator();
        int counter = 0;

        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }

        return counter;
    }

}
