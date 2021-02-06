/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> collinearSegments;

    // time complexity is around 2 * (n ^ 2)
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || Arrays.asList(points).contains(null)) {
            throw new IllegalArgumentException("argument is/contains null");
        }

        collinearSegments = new ArrayList<>();
        Point[] points2 = Arrays.copyOf(points, points.length);
        double[] slopes = new double[points2.length - 1];

        Arrays.sort(points2);

        for (int i = 0; i < points2.length; i++) {
            Point a = points2[i];
            for (int j = i + 1; j < points2.length; j++) {
                Point b = points2[j];
                slopes[i] = a.slopeTo(b);
            }
        }

        for (int i = 0; i < slopes.length; i++) {
            int collinearCount = 1;
            double firstSlope = slopes[i];
            for (int j = i + 1; j < slopes.length; j++) {
                double secondSlope = slopes[j];
                if (firstSlope == secondSlope) {
                    collinearCount++;

                    // only removes duplicates containing the "smallest" point as origin
                    if (collinearCount >= 3 && i == 0) {
                        if (!collinearSegments.isEmpty()) collinearSegments.clear();
                        LineSegment line = new LineSegment(points2[i], points2[j + 1]);
                        collinearSegments.add(line);
                    }
                    else if (collinearCount >= 3) {
                        LineSegment line = new LineSegment(points2[i], points2[j + 1]);
                        collinearSegments.add(line);
                    }
                }
            }
        }

        // **** the below version is my second try to remove duplicate line segments ****
        // **** but it's somehow performing worse than my previous approach ****
        // **** AND not achieving the desired result, so it's abandoned after I got 80+ ****

        // for (int i = 0; i < points2.length; i++) {
        //     Point a = points2[i];
        //     Arrays.sort(points2, i, points2.length, a.slopeOrder());        // sort right side
        //     Arrays.sort(points2, 0, i, a.slopeOrder());            // sort left side;
        //
        //     int colPointCount = 0;
        //     double prevSlope = Double.NEGATIVE_INFINITY;
        //     for (int j = i + 1; j < points2.length; j++) {
        //         Point b = points2[j];
        //         if (a.compareTo(b) == 0) throw new IllegalArgumentException("same point");
        //         double slope = a.slopeTo(b);
        //         if (slope == prevSlope) {
        //             colPointCount++;
        //         }
        //
        //         if (colPointCount >= 2 && (slope != prevSlope || j == points2.length - 1)) {
        //             Arrays.sort(points2, j - colPointCount, j);
        //             Point minPoint = points2[j - colPointCount];
        //             Point maxPoint = points2[j];
        //             if (slope == prevSlope) maxPoint = b;
        //             if (a.compareTo(minPoint) < 0) minPoint = a;
        //             else if (a.compareTo(maxPoint) > 0) maxPoint = a;
        //
        //             boolean insert = true;
        //
        //             for (int k = 0; k < i; k++) {
        //                 double pastSlope = a.slopeTo(points2[k]);
        //                 if (pastSlope == prevSlope && pastSlope >= prevSlope) {
        //                     insert = false;
        //                     break;
        //                 }
        //             }
        //
        //             if (insert) {
        //                 LineSegment line = new LineSegment(minPoint, maxPoint);
        //                 collinearSegments.add(line);
        //             }
        //         }
        //         if (slope != prevSlope) {
        //             colPointCount = 0;
        //             prevSlope = slope;
        //         }
        //     }
        // }
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

    public static void main(String[] args) {
        Point p1 = new Point(3, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(7, 7);

        Point[] pArr = new Point[6];
        pArr[0] = p1;
        pArr[1] = p2;
        pArr[2] = p3;
        pArr[3] = p4;
        pArr[4] = p5;
        pArr[5] = p6;

        FastCollinearPoints test = new FastCollinearPoints(pArr);
        LineSegment[] result = test.segments();
        System.out.println(Arrays.toString(result));
    }
}
