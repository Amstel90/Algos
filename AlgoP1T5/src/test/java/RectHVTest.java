import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Artem_Mikhalevitch on 4/2/15.
 */
public class RectHVTest {
    private static final double DELTA = 0.0001;
    RectHV hv;

    @Test
    public void testConstructor() {
        hv = new RectHV(1, 1, 5, 5);
        Assert.assertEquals(1, hv.xmin(), DELTA);
        Assert.assertEquals(1, hv.ymin(), DELTA);
        Assert.assertEquals(5, hv.xmax(), DELTA);
        Assert.assertEquals(5, hv.ymax(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException1() {
        hv = new RectHV(5, 1, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException2() {
        hv = new RectHV(6, 1, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException3() {
        hv = new RectHV(1, 5, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException4() {
        hv = new RectHV(1, 6, 5, 5);
    }

    @Test
    public void testContains() {
        hv = new RectHV(1, 1, 3, 3);
        double step = 0.1;
        for (double x = 1; x <= 3; x += step) {
            for (double y = 1; y <= 3; y += step) {
                Assert.assertTrue(hv.contains(new Point2D(x, y)));
            }
        }
        Assert.assertFalse(hv.contains(new Point2D(1 - step, 1 - step)));
        Assert.assertFalse(hv.contains(new Point2D(3 + step, 3 + step)));
    }

    @Test
    public void testIntersect() {
        hv = new RectHV(1, 1, 3, 3);
        //near
        Assert.assertFalse(hv.intersects(new RectHV(4, 1, 6, 3)));
        Assert.assertFalse(hv.intersects(new RectHV(-2, 1, 0, 3)));
        Assert.assertFalse(hv.intersects(new RectHV(1, 4, 3, 6)));
        Assert.assertFalse(hv.intersects(new RectHV(1, -2, 3, 0)));
        //far
        Assert.assertFalse(hv.intersects(new RectHV(4, 4, 6, 6)));
        Assert.assertFalse(hv.intersects(new RectHV(-2, -1, 0, 0)));
        Assert.assertFalse(hv.intersects(new RectHV(-2, 4, 0, 6)));
        Assert.assertFalse(hv.intersects(new RectHV(4, -2, 6, 0)));
        //one point
        Assert.assertTrue(hv.intersects(new RectHV(-1, -1, 1, 1)));
        Assert.assertTrue(hv.intersects(new RectHV(-1, 3, 3, 5)));
        Assert.assertTrue(hv.intersects(new RectHV(3, 3, 5, 5)));
        Assert.assertTrue(hv.intersects(new RectHV(3, -1, 5, 1)));
        //edge
        Assert.assertTrue(hv.intersects(new RectHV(-1, 1, 1, 3)));
        Assert.assertTrue(hv.intersects(new RectHV(1, 3, 3, 5)));
        Assert.assertTrue(hv.intersects(new RectHV(3, 1, 5, 3)));
        Assert.assertTrue(hv.intersects(new RectHV(1, -1, 3, 1)));
        //intersects
        Assert.assertTrue(hv.intersects(new RectHV(0, 0, 2, 2)));
    }

    @Test
    public void testDistance() {
        hv = new RectHV(1, 1, 3, 3);
        //inside
        Assert.assertEquals(0, hv.distanceTo(new Point2D(2, 2)), DELTA);
        Assert.assertEquals(0, hv.distanceSquaredTo(new Point2D(2, 2)), DELTA);
        //top
        Assert.assertEquals(2, hv.distanceTo(new Point2D(2, 5)), DELTA);
        Assert.assertEquals(4, hv.distanceSquaredTo(new Point2D(2, 5)), DELTA);
        //top-right
        Assert.assertEquals(Math.sqrt(8), hv.distanceTo(new Point2D(5, 5)), DELTA);
        Assert.assertEquals(8, hv.distanceSquaredTo(new Point2D(5, 5)), DELTA);
        //right
        Assert.assertEquals(2, hv.distanceTo(new Point2D(5, 2)), DELTA);
        Assert.assertEquals(4, hv.distanceSquaredTo(new Point2D(5, 2)), DELTA);
        //bottom-right
        Assert.assertEquals(Math.sqrt(8), hv.distanceTo(new Point2D(5, -1)), DELTA);
        Assert.assertEquals(8, hv.distanceSquaredTo(new Point2D(5, -1)), DELTA);
        //bottom
        Assert.assertEquals(2, hv.distanceTo(new Point2D(2, -1)), DELTA);
        Assert.assertEquals(4, hv.distanceSquaredTo(new Point2D(2, -1)), DELTA);
        //bottom-left
        Assert.assertEquals(Math.sqrt(8), hv.distanceTo(new Point2D(-1, -1)), DELTA);
        Assert.assertEquals(8, hv.distanceSquaredTo(new Point2D(-1, -1)), DELTA);
        //left
        Assert.assertEquals(2, hv.distanceTo(new Point2D(-1, 2)), DELTA);
        Assert.assertEquals(4, hv.distanceSquaredTo(new Point2D(-1, 2)), DELTA);
        //top-left
        Assert.assertEquals(Math.sqrt(8), hv.distanceTo(new Point2D(-1, 5)), DELTA);
        Assert.assertEquals(8, hv.distanceSquaredTo(new Point2D(-1, 5)), DELTA);
    }

    @Test
    public void testEquals() {
        hv = new RectHV(1, 1, 3, 3);

        Assert.assertTrue(hv.equals(new RectHV(1, 1, 3, 3)));

        Assert.assertFalse(hv.equals(new RectHV(0, 0, 1, 1)));
        Assert.assertFalse(hv.equals(new RectHV(0, 0, 2, 2)));
        Assert.assertFalse(hv.equals(new RectHV(0, 0, 3, 3)));

        Assert.assertFalse(hv.equals(new RectHV(1, 1, 2, 2)));
        Assert.assertFalse(hv.equals(new RectHV(1, 1, 4, 4)));
    }

   // @Test
    public void testDraw() {
        hv = new RectHV(100, 100, 200, 200);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        hv.draw();
        // display to screen all at once
        StdDraw.show(10000);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
