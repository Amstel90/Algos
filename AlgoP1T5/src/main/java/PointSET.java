import java.util.Iterator;

/**
 * Created by Artem_Mikhalevitch on 3/18/15.
 */
public class PointSET {
    /**
     * Set.
     */
    SET<Point2D> set;

    /**
     * Constructor.
     */
    public PointSET() {
        set = new SET<Point2D>();
    }

    /**
     * Returns if set is empty.
     *
     * @return if set is empty.
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns number of points in the set.
     *
     * @return number of points in the set.
     */
    public int size() {
        return set.size();
    }

    /**
     * Adds pont to set.
     *
     * @param point new point.
     */
    public void insert(Point2D point) {
        set.add(point);
    }

    /**
     * Returns true if contains.
     *
     * @param point point
     * @return true if contains.
     */
    public boolean contains(Point2D point) {
        return set.contains(point);
    }

    /**
     * Draws all points.
     */
    public void draw() {
        Iterator<Point2D> iterator = set.iterator();
        while (iterator.hasNext()) {
            iterator.next().draw();
        }
    }

    /**
     * Returns true if contains.
     *
     * @param rect rectangle
     * @return all points in rectangle.
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> result = new Queue<Point2D>();
        Iterator<Point2D> iterator = set.iterator();

        while (iterator.hasNext()) {
            Point2D point2D = iterator.next();
            if (rect.contains(point2D)) {
                result.enqueue(point2D);
            }
        }
        return result;
    }

    /**
     * Returns true if contains.
     *
     * @param point point
     * @return nearest point.
     */
    public Point2D nearest(Point2D point) {

        if (isEmpty()) {
            return null;
        }

        Iterator<Point2D> iterator = set.iterator();
        Point2D minPoint = iterator.next();
        double minDistance = point.distanceTo(minPoint);

        while (iterator.hasNext()) {
            Point2D point2D = iterator.next();
            double newDistance = point.distanceTo(point2D);
            if (newDistance < minDistance) {
                minDistance = newDistance;
                minPoint = point2D;
            }
        }
        return minPoint;
    }

    /**
     * Returns true if contains.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        StdOut.println("Hello World");
    }
}
