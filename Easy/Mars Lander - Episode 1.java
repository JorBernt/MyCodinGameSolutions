import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    
    static public final double G = 3.711f;
    static public final int MIN_POWER = 0;
    static public final int MAX_POWER = 4;
    static public final int LANDING_VSPEED = -40;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        int[] yPts = new int[surfaceN];
        int yGround = -1;
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            yPts[i] = landY;
            if (i > 0 && yPts[i] == yPts[i-1]) {
                yGround = yPts[i];
                System.err.println("Flat ground found");
            }
        }
        
        System.err.format("yGround: %d\n", yGround);

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).
            
            // On résout l'équation de vitesse par rapport à la distance à parcourir
            // On obtient l'accélération nécessaire au module pour atterrir à la bonne vitesse
            double a = ((Math.pow(LANDING_VSPEED, 2) - Math.pow(vSpeed, 2)) / (yGround - Y)) / 2;
            double r = a + G;
            System.err.format("a: %.3f, r: %.3f\n", a, r);
            
            power = r >= G ? MAX_POWER : MIN_POWER;

            // 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
            System.out.println("0 " + power);
        }
    }
}
