import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        String[] numIn = {" _     _  _     _  _  _  _  _ ", 
                         "| |  | _| _||_||_ |_   ||_||_|",
                         "|_|  ||_  _|  | _||_|  ||_| _|"};
        Map<Integer, String[]> nums = new HashMap<>();
        int k = 0;
        for(int i = 0; i < 10; i++) {
            String[] s = new String[3];
            nums.put(i, s);
            
            for(int j = 0; j < 3; j++) {
                nums.get(i)[j] = numIn[j].substring(k, k+3);
            }
            k += 3;
        }

        Scanner in = new Scanner(System.in);
        String[] inLine = {in.nextLine(), in.nextLine(), in.nextLine()};
        StringBuilder sb = new StringBuilder();
        k = 0;
        for(int i = 0; i < inLine[0].length()/3; i++) {
            String[] s = new String[3];
            
            for(int j = 0; j < 3; j++) {
                s[j] = inLine[j].substring(k, k+3);             
            }
            k += 3;
            for(int q : nums.keySet()) {
                if(Arrays.equals(nums.get(q), s)) {
                    sb.append(q);
                }
            }
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sb.toString());
    }
}
