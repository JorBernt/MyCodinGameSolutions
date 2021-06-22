import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Point {
    String label;
    int[] coordinates;

    public Point(String[] data) {
        label = data[0];
        coordinates = new int[data.length-1];
        for(int i = 1; i < data.length; i++) {
            coordinates[i-1] = Integer.parseInt(data[i]);
        }
    }

    public Point(String label, int dimensions) {
        this.label = label;
        coordinates = new int[dimensions];
    }
    
} 
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Point> points = new ArrayList<>();
        int ndim = 0;
        for (int i = 0; i < count; i++) {
            String[] labeledPoint = in.nextLine().split(" ");
            ndim = labeledPoint.length-1;
            points.add(new Point(labeledPoint));
        }
        Point cur = new Point("", ndim);

        while(!points.isEmpty()) {
            double distance = Integer.MAX_VALUE;
            Point closest = null;
            for(Point p : points) {
                double dist = getDistance(cur, p);
                if(dist < distance) {
                    distance = dist;
                    closest = p;
                }   
            }
            for(int i = 0; i < ndim; i++) {
                int a = closest.coordinates[i]>0?1:closest.coordinates[i]==0?0:-1;
                int b = cur.coordinates[i]>0?1:cur.coordinates[i]==0?0:-1;
                if(a != b && b != 0 && a!= 0) {
                    System.out.print(" ");
                    break;
                }
            }
            System.out.print(closest.label);
            cur = closest;
            points.remove(closest);
        }
    }

    static double getDistance(Point a, Point b) {
        double der = 0;
        for(int i = 0; i < a.coordinates.length; i++) {
            der += Math.pow(a.coordinates[i]-b.coordinates[i],2);
        }
        return Math.sqrt(der);

    }
}
