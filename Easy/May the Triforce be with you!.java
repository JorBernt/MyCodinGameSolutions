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
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < N*2; i++) {
            for(int j = 0; j < N*2+i; j++) {
                if(j < N*2-i-1) s.append(" ");
                else {
                    if(i < N)
                    s.append("*");
                    else {
                        if(j < i*2-j || j > (N*4-2)-i)
                        s.append("*");
                        else
                        s.append(" ");
                    }
                }
            }
            if(i == 0) s.setCharAt(0, '.');
            if(i < N*2-1) s.append("\n");
        }
        System.out.println(s.toString());
    }
}
