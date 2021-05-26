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
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        boolean start = false;
        String[] k = new String[H];

        for(int i = 0; i < H; i++) 
            k[i] = in.nextLine();
        
        for(int i = 0; i < H; i++) {
            Stack<Character> stack = new Stack<>();
            char[] S = k[i].toCharArray();
            for(int j = 0; j < S.length; j++) {
                if(S[j] != '.') {
                    start = true;
                    if((i<H-2 && (k[i+1].charAt(j) == S[j] || k[i+1].charAt(j) == '.')) || (!stack.isEmpty() && stack.peek() == (S[j] == '/'?'/':'\\'))) {
                        System.out.println("UNSTABLE");
                        return;
                    }
                    else stack.add(S[j]);
                }
            }
            if((stack.isEmpty() && start) || (!stack.isEmpty() && stack.peek() == '/')) {
                System.out.println("UNSTABLE");
                return;
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("STABLE");
    }
}
