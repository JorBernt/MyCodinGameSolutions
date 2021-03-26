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
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            set.add(telephone);
            for(int j = 1; j < telephone.length(); j++) {
                set.add(telephone.substring(0,j));
            }

        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // The number of elements (referencing a number) stored in the structure.
        System.out.println(set.size());
    }
}
