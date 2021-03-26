import java.util.*;
import java.io.*;
import java.math.*;
import java.awt.Polygon;
import java.awt.Point;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] x_array = new int[N];
        int[] y_array = new int[N];
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x_array[i] = x;
            y_array[i] = y;
        }
        int M = in.nextInt();
        for (int i = 0; i < M; i++) {
            int x = in.nextInt();
            int y = in.nextInt();a
            boolean isInPolygon = 
    new Polygon(x_array,y_array,x_array.length).contains(new Point(x, y));
            if(isInPolygon) 
                System.out.println("hit");
                else System.out.println("miss");
            
        }

        
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }

    
}
