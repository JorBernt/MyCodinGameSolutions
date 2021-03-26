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
        String[] ENCRYPT = in.nextLine().split(" ");
        if(ENCRYPT.length%2!=0) {
            System.out.print("INVALID");
            return;
        }
        StringBuilder sb = new StringBuilder();
        List<Character> ans = new ArrayList<>();
        for(int i = 0; i < ENCRYPT.length; i+=2) {
            if(!Character.isDigit(ENCRYPT[i].charAt(0)) || !Character.isDigit(ENCRYPT[i+1].charAt(0)) || ENCRYPT[i].length()>2) {
                System.out.println("INVALID");
                return;
            }
            sb.append((ENCRYPT[i].length()==1?"1":"0").repeat(ENCRYPT[i+1].length()));
            if(sb.length() >= 7) {
                ans.add((char)Integer.parseInt(sb.substring(0,7),2));
               
                sb.delete(0, 7);
            }
        }
        if(sb.length() < 7 && sb.length() > 0) {
             System.out.print("INVALID");
             return;
        }

        for(char c:ans)System.out.print(c);

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
