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
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String x = in.nextLine();
            if(isHappy(x)) {
                System.out.println(x + " :)");
            }
            else System.out.println(x + " :(");
        }


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }

    public static boolean isHappy(String x) {
            int sum = 0;
            for(char c : x.toCharArray()) {
                sum+=Math.pow(Character.getNumericValue(c), 2);
            }
            if(sum==4) return false;
            else if(sum==1) return true;
            return isHappy(Integer.toString(sum));
    }
}
