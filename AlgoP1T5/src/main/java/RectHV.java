/**
 * Created by Artem_Mikhalevitch on 4/2/15.
 */
public class RectHV {

    private Point2D minPoint;
    private Point2D maxPoint;

    /**
     * Construct the rectangle [xmin, xmax] x [ymin, ymax]
     * Throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
     */
    public RectHV(double xmin, double ymin,
                  double xmax, double ymax) {
        if (xmin >= xmax || ymin >= ymax) {
            throw new IllegalArgumentException();
        }
        this.minPoint = new Point2D(xmin, ymin);
        this.maxPoint = new Point2D(xmax, ymax);
    }

    /**
     * Minimum x-coordinate of rectangle.
     *
     * @return minimum x-coordinate of rectangle
     */
    public double xmin() {
        return minPoint.x();
    }

    /**
     * Minimum y-coordinate of rectangle.
     *
     * @return y-coordinate of rectangle
     */
    public double ymin() {
        return minPoint.y();
    }

    /**
     * Maximum x-coordinate of rectangle.
     *
     * @return maximum x-coordinate of rectangle
     */
    public double xmax() {
        return maxPoint.x();
    }

    /**
     * Maximum y-coordinate of rectangle.
     *
     * @return maximum y-coordinate of rectangle
     */
    public double ymax() {
        return maxPoint.y();
    }

    /**
     * Maximum y-coordinate of rectangle.
     *
     * @param point 2d point
     * @return true if point is inside rectangle
     */
    public boolean contains(final Point2D point) {
        return !(point.x() > xmax()
                || point.x() < xmin()
                || point.y() > ymax()
                || point.y() < ymin());
    }

    /**
     * Rectangle intersect that rectangle (at one or more points).
     *
     * @param that Rectangle
     * @return true if rectangle intersect that rectangle (at one or more points)?
     */
    public boolean intersects(final RectHV that) {
        //horizontal intereprions
        return !(this.xmax() < that.xmin()
                || that.xmax() < this.xmin()
                || this.ymax() < that.ymin()
                || that.ymax() < this.ymin());
    }

    /**
     * Euclidean distance from point p to closest point in rectangle.
     *
     * @param point 2D point
     * @return Euclidean distance from point p to closest point in rectangle
     */
    public double distanceTo(final Point2D point) {
        if (contains(point)) {
            return 0;
        } else {
            Point2D nearest = getNearestPoint(point);
            return nearest.distanceTo(point);
        }
    }

    /**
     * Square of Euclidean distance from point p to closest point in rectangle.
     *
     * @param point 2D point
     * @return Square of Euclidean distance from point p to closest point in rectangle
     */
    public double distanceSquaredTo(final Point2D point) {
        if (contains(point)) {
            return 0;
        } else {
            Point2D nearest = getNearestPoint(point);
            return nearest.distanceSquaredTo(point);
        }
    }

    /**
     * Square of Euclidean distance from point p to closest point in rectangle.
     *
     * @param point 2D point
     * @return nearestPoint
     */
    private Point2D getNearestPoint(final Point2D point) {
        boolean toRight = point.x() >= xmax();
        boolean toLeft = point.x() <= xmin();
        boolean toBottom = point.y() <= ymin();
        boolean toTop = point.y() >= ymax();

        if (toTop) {//1
            if (toRight) {//1.1 top right diagonal
                return maxPoint;
            } else if (toLeft) {//1.2 top left diagonal
                return new Point2D(xmin(), ymax());
            } else {
                return new Point2D(point.x(), ymax()); //perp. line from top
            }
        } else if (toBottom) {//2
            if (toRight) {//2.1
                return new Point2D(xmax(), ymin());
            } else if (toLeft) {//2.2
                return minPoint;
            } else {
                return new Point2D(point.x(), ymin());
            }
        } else if (toRight) {//3
            return new Point2D(xmax(), point.y());
        } else {//4
            return new Point2D(xmin(), point.y());
        }
    }

    /**
     * Ff rectangle equals another.
     *
     * @return true if rectangle equals another
     */
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (that instanceof RectHV) {
            RectHV another = (RectHV) that;
            return this.maxPoint.equals(another.maxPoint)
                    && this.minPoint.equals(another.minPoint);
        }
        return false;

    }

    /**
     * Standard draw.
     */
    public void draw() {
        StdDraw.rectangle(xmin(), ymin(), (xmax() - xmin()) / 2, (ymax() - ymax()) / 2);
    }

    /**
     * String representation.
     *
     * @return string representation
     */

    @Override
    public String toString() {
        return String.format("[%s, %s]",
                minPoint.toString(),
                maxPoint.toString());
    }

    /**
     * Main.
     *
     * @param args comand line arguments
     */
    public static void main(String[] args) {

    }
}
