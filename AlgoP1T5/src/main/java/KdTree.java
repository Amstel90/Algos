/**
 * Created by Artem_Mikhalevitch on 4/3/15.
 */
public class KdTree {
    /**
     * Constructor
     */
    private static final int LOW = 0;
    /**
     * Constructor
     */
    private static final int HIGH = 1;
    /**
     * Constructor
     */
    private Node root;
    /**
     * Constructor
     */
    private int size;

    /**
     * Constructor
     */
    public KdTree() {
    }

    /**
     * Is set empty.
     *
     * @return true if set is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Number of points in the set.
     *
     * @return number of points in the set
     */
    public int size() {
        return this.size;
    }

    /**
     * Add the point to the set.
     *
     * @param point point ot insert
     */
    public void insert(Point2D point) {
        root = insert(null, root, point);
    }

    private Node insert(Node parentSearchNode, Node searchNode, Point2D point) {

        if (searchNode == null) {
            searchNode = new Node(point);

            if (parentSearchNode == null) {
                searchNode.vertical = true;
                searchNode.rect = new RectHV(LOW, LOW, HIGH, HIGH);
            } else {
                double cmp = compareTo(parentSearchNode, searchNode.point);
                this.fillNode(searchNode, parentSearchNode, cmp >= 0);
            }
            size++;
            return searchNode;
        }
        double cmp = compareTo(searchNode, point);

        if (cmp < 0) {
            searchNode.left = insert(searchNode, searchNode.left, point);
        } else if (cmp > 0 || !searchNode.point.equals(point)) {
            searchNode.right = insert(searchNode, searchNode.right, point);
        }
        return searchNode;
    }

    private void fillNode(Node node, Node parent, boolean rightSubtree) {
        double xMin = parent.rect.xmin();
        double xMax = parent.rect.xmax();
        double yMin = parent.rect.ymin();
        double yMax = parent.rect.ymax();

        node.vertical = !parent.vertical;

        if (parent.vertical) {
            if (rightSubtree) {
                if (node.point.x() != parent.point.x()) {
                    xMin = parent.point.x();
                }
            } else {
                xMax = parent.point.x();
            }
        } else {
            if (rightSubtree) {
                if (node.point.y() != parent.point.y()) {
                    yMin = parent.point.y();
                }
            } else {
                yMax = parent.point.y();
            }
        }
        node.rect = new RectHV(xMin, yMin, xMax, yMax);
    }

    private Node search(Point2D point) {
        return search(this.root, point);
    }

    private Node search(Node searchNode, Point2D point) {

        if (searchNode == null) {
            return null;
        }
        double cmp = compareTo(searchNode, point);
        if (cmp < 0) {
            return search(searchNode.left, point);
        } else if (cmp > 0 || !searchNode.point.equals(point)) {
            return search(searchNode.right, point);
        } else {
            return searchNode;
        }
    }

    /**
     * Is set empty.
     *
     * @param point point ot insert
     * @return as ordinary compareTo
     */
    private double compareTo(Node node, Point2D point) {
        double result = 0;
        if (node.vertical) {
            result = point.x() - node.point.x();
        } else {
            result = point.y() - node.point.y();
        }
        return result;
    }

    /**
     * Is set empty.
     *
     * @param point point ot insert
     * @return true if contains
     */
    public boolean contains(Point2D point) {
        return search(point) != null;
    }

    /**
     * Draw al points in set.
     */
    public void draw() {
        draw(root);
    }

    /**
     * Draw Nodes Point.
     */
    private void draw(Node node) {
        if (node == null) {
            return;
        }
        node.point.draw();
        draw(node.left);
        draw(node.right);
    }

    /**
     * Find al points in set.
     *
     * @param rect rectangular to search in
     * @return Iterable points.
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();

        rangeSearch(queue, root, rect);

        return queue;
    }

    private void rangeSearch(Queue<Point2D> queue, Node node, RectHV rect) {
        if (node == null || !node.rect.intersects(rect)) {
            return;
        }

        if (rect.contains(node.point)) {
            queue.enqueue(node.point);
        }

        rangeSearch(queue, node.left, rect);
        rangeSearch(queue, node.right, rect);
    }

    /**
     * Find al points in set.
     *
     * @param point point to search from
     * @return newarest point.
     */
    public Point2D nearest(Point2D point) {
        if (isEmpty()) {
            return null;
        }
        return this.nearest(root, point, Double.MAX_VALUE).point;
    }

    private Node nearest(Node node, Point2D point, double minDistance) {

        if (skipSubtree(node, point, minDistance)) {
            return null;
        }

        double pointDistance = node.point.distanceSquaredTo(point);
        minDistance = Math.min(pointDistance, minDistance);

        Node lNode = null;
        Node rNode = null;
        double rDistance = Double.MAX_VALUE;
        double lDistance = Double.MAX_VALUE;

        if (node.vertical && point.x() > node.point.x()
                ||!node.vertical && point.y() > node.point.y()) {
            rNode = nearest(node.right, point,
                    minDistance);

            if (rNode != null) {
                rDistance = rNode.point.distanceSquaredTo(point);
            }
            minDistance = Math.min(minDistance,
                    rDistance);

            lNode = nearest(node.left, point,
                    minDistance);

            if (lNode != null) {
                lDistance = lNode.point.distanceSquaredTo(point);
            }
            minDistance = Math.min(minDistance,
                    lDistance );
        }else{
            lNode = nearest(node.left, point,
                    minDistance);

            if (lNode != null) {
                lDistance = lNode.point.distanceSquaredTo(point);
            }
            minDistance = Math.min(minDistance,
                    lDistance );

            rNode = nearest(node.right, point,
                    minDistance);

            if (rNode != null) {
                rDistance = rNode.point.distanceSquaredTo(point);
            }
            
            minDistance = Math.min(minDistance,
                    rDistance);
        }

        if (minDistance == rDistance) {
            return rNode;
        } else if (minDistance == lDistance) {
            return lNode;
        } else if(minDistance == pointDistance) {
            return node;
        }else{
            return null;
        }
    }

    private boolean skipSubtree(Node node, Point2D point, double minDistance) {
        return node == null
                || node.rect.distanceSquaredTo(point) >= minDistance;
    }
//    private Node nearest(Node node, Point2D point, double minDistance) {
//
//        Node result = null;
//
//        double distance = node.rect.distanceSquaredTo(point);
//
//        return result;
//    }

    /**
     * Main.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
    }

    private static class Node {
        private Point2D point;
        private RectHV rect;
        private Node left;
        private Node right;
        private boolean vertical;

        private Node(Point2D point) {
            this.point = point;
        }

        private Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }
    }
}
