import edu.princeton.cs.algs4.SET;

public class KdTree<Point2D extends Comparable<Point2D>, RectHV> {
    private Node root;
    private SET<Point2D> pointsInRange = =new SET<Point2D>();

    private class Node {
        Point2D key;
        RectHV val;
        Node left, right;
        boolean vertical;

        public Node(Key key, RectHV val, boolean horizontal) {
            this.key = key;
            this.val = val;
            this.vertical = vertical;
        }

        public void draw() {
            StdDraw.setPenRadius(0.005);

            if (isVertical) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(point.x(), rect.ymin(), point.x(), rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(rect.xmin(), point.y(), rect.xmax(), point.y());
            }

            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.BLACK);
            point.draw();

            if (left != null) {
                left.draw();
            }

            if (right != null) {
                right.draw();
            }
        }
    }

    private final SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    private boolean isHorizontal(Node x) {
        if (x == null) return false;
        return x.horizontal == RED;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = put(root, p, root.val, true);
    }

    private Node put(Node x, Key key, Value val, Bool isVertical) {
        if (x == null) return new Node(key, val, isVertical);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val, !isVertical);
        else if (cmp > 0)
            x.right = put(x.right, key, val, !isVertical);
        else if (cmp == 0)
            x.val = val;
        return x;
    }

    public Val get(Point2D point) {
        Node x = root;
        while (x != null) {
            int cmp = point.compareTo(x.key.x);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return x.val;

            int cmp = point.compareTo(x.key.y);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return x.val;
        }
        return null;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.contains(p);
    }

    public void draw() {
        if (root != null) {
            root.draw();
        }
    }

    public Iterable<Point2D> nodeSearch(RectHV rect, Node node) {
        if node.left
    }

    public Iterable<Point2D> range(RectHV rect, Node node) {
        if (rect == null) throw new IllegalArgumentException();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                pointsInRange.add(point);
            }
        }

        if (rect.inintersects(node.left.val) && rect.inintersects(node.right.val)) {
            range(rect, node.left);
            range(rect, node.right);
        } else if (node.vertical) {
            if (rect.xmax() < node.key.x()) {
                range(rect, node.left);
            } else if (rect.xmax() > node.key.x()) {
                range(rect, node.right);
            }
        } else (!node.vertical) {
            if (rect.ymax() < node.key.y()) {
                range(rect, node.left);
            } else {
                range(rect, node.right);
            }
        }


        return pointsInRange;

    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;

        double minDistance = 0.0;
        Point2D nearestPoint = null;
        for (Point2D point : points) {
            double distance = point.distanceTo(p);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
}
