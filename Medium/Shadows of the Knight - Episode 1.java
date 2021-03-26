import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        // game loop
        int[] y = {0, H};
        int[] x = {0, W};
        while (in.hasNext()) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL),
            switch(bombDir) {
                case "U": {
                    y[1] = Y0;
                    break;
                }
                case "UR": {
                    y[1] = Y0;
                    x[0] = X0;
                    break;
                }
                case "R": {
                    x[0] = X0;
                    break;
                }
                case "DR": {
                    y[0] = Y0;
                    x[0] = X0;
                    break;
                }
                case "D": {
                    y[0] =Y0;
                    break;
                }
                case "DL": {
                    y[0] = Y0;
                    x[1] = X0;
                    break;
                }
                case "L": {
                    x[1] = X0;
                    break;
                }
                case "UL": {
                    y[1]  = Y0;
                    x[1] = X0;
                    break;
                }
            }
            X0 = (x[1]+x[0])/2;
            Y0 = (y[1]+y[0])/2;
            System.out.println(X0 + " " + Y0);
        }
    }
}
