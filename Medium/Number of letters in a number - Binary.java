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
        long start = in.nextLong();
        long n = in.nextLong();
        long prev = start;
        long mem = 0;
        for(long i = start; i < start+n;i++) {
            long cur = Long.bitCount(prev)*3+(Long.toBinaryString(prev).length()-Long.bitCount(prev))*4;
            if(cur == mem) break;
            prev = cur;
            mem = prev;
            
        }

        System.out.println(prev);
    }
}
