import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        double filled = 0, notFilled = 0;
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String row = in.nextLine();
            for(char c:row.toCharArray()) {
                if(c=='X') filled++;
                else if(c!= '.' && c!= 'X') {
                    filled+=0.5;
                    notFilled+=.5;
                }
                else notFilled++;
            }
        }

        if(notFilled == 0.0) {
            System.out.println("Rectangle");
        }
        else if(filled == notFilled) {
            System.out.println("Triangle");
        }
        else if(filled > notFilled) {
            System.out.println("Ellipse");
        }
    }
}
