/**
 * Created by Artem_Mikhalevitch on 4/3/15.
 */
public class KdTree {

    private Node root;

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

            if (parentSearchNode == null) {//root
                searchNode.vertical = true;
                searchNode.rect = new RectHV(LOW, LOW, HIGH, HIGH);
            } else {
                double cmp = compareTo(parentSearchNode, searchNode.point);
                this.fillNode(searchNode, parentSearchNode, cmp > 0);
            }
            size++;
            return searchNode;
        }
        double cmp = compareTo(searchNode, point);

        if (cmp < 0) {
            searchNode.left = insert(searchNode, searchNode.left, point);
        } else if (cmp > 0) {
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
            if (rightSubtree) {//right subtree
                if (node.point.x() != parent.point.x()) {
                    xMin = parent.point.x();
                }
            } else {//left subtree
                xMax = parent.point.x();
            }
        } else {
            if (rightSubtree) {//right subtree
                if (node.point.y() != parent.point.y()) {
                    yMin = parent.point.y();
                }
            } else {//left subtree
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
        if (cmp < 0) return search(searchNode.left, point);
        else if (cmp > 0) return search(searchNode.right, point);
        else return searchNode;
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
            //x compare
            result = point.x() - node.point.x();
        } else {
            result = point.y() - node.point.y();
        }
        if (result < 0) {
            return -1;
        }
        return 1;
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
        return this.nearest(root, point, root.point.distanceSquaredTo(point)).point;
    }

    private Node nearest(Node node, Point2D point, double minDistance) {

        if(node == null){
            return null;
        }

        double rectDistance = getDistance(node, point);
        double pointDistance = node.point.distanceSquaredTo(point);

        if (rectDistance > minDistance || pointDistance > minDistance) {
            return null;
        }

        Node lNode = nearest(node.left, point, pointDistance);
        Node rNode = nearest(node.right, point, pointDistance);

        if (lNode == null && rNode == null) {
            return node;
        } else if (lNode == null) {
            return rNode;
        } else if (rNode == null) {
            return lNode;
        } else {
            double rDistance = rNode.point.distanceSquaredTo(point);
            double lDistance = lNode.point.distanceSquaredTo(point);

            if (lDistance < rDistance) {
                return lNode;
            } else {
                return rNode;
            }
        }
    }

    private double getDistance(Node node, Point2D point) {
        if (node == null) {
            return Double.MAX_VALUE;
        }
        return node.rect.distanceSquaredTo(point);
    }

    /**
     * Main.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
    }

    private final int LOW = 0;
    private final int HIGH = 1;

    private static class Node {
        private Point2D point;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node left;        // the left/bottom subtree
        private Node right;        // the right/top subtree
        boolean vertical;

        private Node(Point2D point) {
            this.point = point;
        }

        private Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }
    }
}
