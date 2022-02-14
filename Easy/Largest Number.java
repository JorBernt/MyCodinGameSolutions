import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int max = 0;
    static int D;
    static int number;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        number = in.nextInt();
        D = in.nextInt();
        findNum(new StringBuilder(), Integer.toString(number), 0);
        System.out.println(max);
    }

    static void findNum(StringBuilder sb, String n, int index) {
        if(sb.length() >= n.length()-2) {
            if(sb.length() == 0) return;
            int m = Integer.parseInt(sb.toString());
            if(m%D == 0)
                max = Math.max(max, m);
        }
        if(index == n.length()) return;
        sb.append(n.charAt(index));
        findNum(sb, n, index + 1);
        sb.setLength(sb.length()-1);
        findNum(sb, n, index + 1);
    }
}
