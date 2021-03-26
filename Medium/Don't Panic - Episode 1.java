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
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators
        Map<Integer, Integer> elevators = new HashMap<>();
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevators.put(elevatorFloor, elevatorPos);
        }

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
            String s = "WAIT";
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            if(elevators.containsKey(cloneFloor)) {
                if(elevators.get(cloneFloor) < clonePos && direction.equals("RIGHT")) 
                    s = "BLOCK";
                else if(elevators.get(cloneFloor) > clonePos && direction.equals("LEFT"))
                    s = "BLOCK";
            }
            else if(cloneFloor == exitFloor) {
                if(exitPos < clonePos && direction.equals("RIGHT"))
                    s = "BLOCK";
                else if((exitPos > clonePos && direction.equals("LEFT")))
                    s = "BLOCK";
            }
            else if(clonePos > width-2 || clonePos < 2) {
                s = "BLOCK";
                System.err.println(clonePos);
            }
            
            System.out.println(s); // action: WAIT or BLOCK
        }
    }
}
