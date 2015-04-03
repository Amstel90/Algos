import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

/**
 * Created by ASUS on 26.03.2015.
 */
public class KdTreeTest {

    //@Test
    public void test() {
        KdTree kdtree = new KdTree();
        Assert.assertTrue(kdtree.isEmpty());
        Assert.assertEquals(0, kdtree.size());

        Point2D point = new Point2D(0.1, 0.1);
        kdtree.insert(point);
        Assert.assertFalse(kdtree.isEmpty());
        Assert.assertEquals(1, kdtree.size());
        Assert.assertTrue(kdtree.contains(point));

        Point2D point2 = new Point2D(0.2, 0.2);
        kdtree.insert(point2);
        Assert.assertFalse(kdtree.isEmpty());
        Assert.assertEquals(2, kdtree.size());
        Assert.assertTrue(kdtree.contains(point2));

        Point2D point3 = new Point2D(0.2, 0.2);
        kdtree.insert(point3);
        Assert.assertEquals(2, kdtree.size());
        Assert.assertTrue(kdtree.contains(point2));
    }

    //@Test
    public void testRect() {
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.1, 0.1));
        kdtree.insert(new Point2D(0.2, 0.2));
        kdtree.insert(new Point2D(0.3, 0.3));
        kdtree.insert(new Point2D(0.4, 0.4));

        Assert.assertEquals(3, counter(kdtree, new RectHV(0, 0, 0.35, 0.35)));
        Assert.assertEquals(4, counter(kdtree, new RectHV(0, 0, 1, 1)));

        for (double i = 0.01; i < 0.5; i += 0.01) {
            int expected = (int) Math.floor(i * 10);
            Assert.assertEquals(expected, counter(kdtree, new RectHV(0, 0, i, i)));
        }
    }

    @Test
    public void testNearest() {
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.1, 0.1));
        kdtree.insert(new Point2D(0.2, 0.2));
        kdtree.insert(new Point2D(0.3, 0.3));
        kdtree.insert(new Point2D(0.4, 0.4));

        Assert.assertEquals(new Point2D(0.1, 0.1), kdtree.nearest(new Point2D(0, 0)));
        Assert.assertEquals(new Point2D(0.2, 0.2), kdtree.nearest(new Point2D(0.21, 0.21)));
        Assert.assertEquals(new Point2D(0.3, 0.3), kdtree.nearest(new Point2D(0.349, 0.349)));
        Assert.assertEquals(new Point2D(0.4, 0.4), kdtree.nearest(new Point2D(0.9, 0.9)));
    }

    //@Test
    public void files() {
        String folder = "D:\\Projects\\STUDY\\src\\Algos\\AlgoP1T5\\src\\resources\\";

        File file = new File(folder);
        Stopwatch stopwatch1 = new Stopwatch();

        for (File f : file.listFiles()) {
            int expected = Integer.parseInt(f.getName()
                    .replaceAll("circle", "").replace(".txt", ""));
            Stopwatch stopwatch2 = new Stopwatch();
            KdTree p = readFile(f, expected);
            Assert.assertEquals(expected, counter(p, new RectHV(0, 0, 1, 1)));
            System.out.println(f.getName() + " took:  " + stopwatch2.elapsedTime());
        }
        System.out.println("all took:  " + stopwatch1.elapsedTime());

    }

    // @Test
    public void file() {
        String folder = "D:\\Projects\\STUDY\\src\\Algos\\AlgoP1T5\\src\\resources\\";

        File file = new File(folder + "circle10000.txt");


        int expected = Integer.parseInt(file.getName()
                .replaceAll("circle", "").replace(".txt", ""));

        Stopwatch stopwatch2 = new Stopwatch();
        KdTree p = readFile(file, expected);
        Assert.assertEquals(expected, counter(p, new RectHV(0, 0, 1, 1)));
        System.out.println(expected + " took:  " + stopwatch2.elapsedTime());

    }

    public KdTree readFile(File file, int n) {
        In in = new In(file);
        KdTree kdtree = new KdTree();
        for (int i = 0; i < n; i++) {
            double d1 = in.readDouble();
            double d2 = in.readDouble();
            if (d1 == 0.5 && d2 == 1) {
                int a = 0;
            }
            //System.out.println(d1 + " " + d2);
            kdtree.insert(new Point2D(d1, d2));

        }
        return kdtree;
    }

    private int counter(KdTree kdtree, RectHV rectHV) {
        Iterator<Point2D> iterator = kdtree.range(rectHV).iterator();
        int counter = 0;

        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }

        return counter;
    }

}
