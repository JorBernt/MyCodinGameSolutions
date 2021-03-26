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
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Map<Character, Integer> map = new HashMap<>();
        boolean consistent = true;
        for (int i = 0; i < n; i++) {
            String row = in.nextLine();
            char left = row.charAt(0);
            char right = row.charAt(row.length()-1);
            if(!map.containsKey(left)) {
                map.put(left, 100-i);
            }
            if(!map.containsKey(right)) {
                map.put(right, 50-i);
            }
            if(map.get(left) < map.get(right)) {
                consistent = false;
            }
        }
        

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if(consistent)
        System.out.println("consistent");
        else
        System.out.println("contradiction");
    }
}
