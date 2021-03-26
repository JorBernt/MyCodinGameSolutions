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
        long r1 = in.nextLong();
        long r2 = in.nextLong();
        while(r1 != r2) {
            if(r1 < r2) r1 = nextNumber(r1);
            else r2 = nextNumber(r2);
        }
        System.out.println(r1);
    }

    public static long nextNumber(long n) {
        long sum = 0;
        long temp = n;
        while(n > 0) {
            sum +=n%10;
            n=n/10;
        }
        return sum+temp;
    }
}
