/**
 * Created by Artem_Mikhalevitch on 3/18/15.
 */
public class PointSET {

    public PointSET() {

    }

    // is the set empty?
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    // number of points in the set
    public int size() {
        throw new UnsupportedOperationException();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        throw new UnsupportedOperationException();
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        throw new UnsupportedOperationException();
    }

    // draw all points to standard draw
    public void draw() {
        throw new UnsupportedOperationException();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        throw new UnsupportedOperationException();
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        StdOut.println("Hello World");
    }
}
