import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private final SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        SET<Point2D> pointsInRange = =new SET<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                pointsInRange.add(point);
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
