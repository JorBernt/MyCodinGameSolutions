import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTx = in.nextInt(); // Thor's starting X position
        int initialTy = in.nextInt(); // Thor's starting Y position
        int curX = initialTx;
        int curY = initialTy;

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.
            StringBuilder sb = new StringBuilder();
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            if(curY < lightY) {
                sb.append("S");
                curY++;
            }
            else if(curY > lightY) {
                sb.append("N");
                curY--;
            }
            if(curX < lightX) {
                sb.append("E");
                curX++;
            }
            else if(curX > lightX) {
                sb.append("W");
                curX--;
            }
            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(sb.toString());
        }
    }
}
