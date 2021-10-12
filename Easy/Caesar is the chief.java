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
        String textToDecode = in.nextLine();
        for(String s : textToDecode.split(" ")) {
            if(s.equals("CHIEF")) {
                System.out.println(textToDecode);
                return;
            }
        }
        for(String s : textToDecode.split(" ")) {
            if(s.length() == 5) {
                int diff = 'C'-s.charAt(0);
                StringBuilder sb = new StringBuilder();
                for(char c : textToDecode.toCharArray()) {
                    if(c == ' ') {
                        sb.append(c);
                        continue;
                    }
                    int n = (c-'A'+diff) % 26;
                    if(n < 0) n += 26;
                    sb.append((char)(n+'A'));
                }
                for(String t : sb.toString().split(" ")) {
                    if(t.equals("CHIEF")) {
                        System.out.println(sb.toString());
                        return;
                    }
                }
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("WRONG MESSAGE");
    }
}
