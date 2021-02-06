import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> collinearSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || Arrays.asList(points).contains(null)) {
            throw new IllegalArgumentException("argument is/contains null");
        }

        collinearSegments = new ArrayList<>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int i = 0; i < pointsCopy.length; i++) {
            Point a = pointsCopy[i];
            for (int j = i + 1; j < pointsCopy.length; j++) {
                Point b = pointsCopy[j];
                if (a.compareTo(b) == 0) throw new IllegalArgumentException("same point");
                for (int k = j + 1; k < pointsCopy.length; k++) {
                    Point c = pointsCopy[k];
                    for (int m = k + 1; m < pointsCopy.length; m++) {
                        Point d = pointsCopy[m];
                        if (isCollinear(a, b, c, d)) {
                            LineSegment line = new LineSegment(a, d);
                            collinearSegments.add(line);
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return collinearSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        segments = collinearSegments.toArray(segments);
        return segments;
    }

    private boolean isCollinear(Point a, Point b, Point c, Point d) {
        if (a.slopeTo(b) == a.slopeTo(c)) {
            return a.slopeTo(b) == a.slopeTo(d);
        }
        return false;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}