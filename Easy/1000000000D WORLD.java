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
        long[] A = Arrays.stream(in.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] B = Arrays.stream(in.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long ans = 0;
        int n = 0, m = 0;
        while(n < A.length && m < B.length) {
            ans+=Math.min(A[n], B[m])*(A[n+1]*B[m+1]);
            B[m]-=A[n];
            if(B[m] < 0) {
                A[n]=B[m]*-1;
                m+=2;
            }
            else {
                n+=2;
            }
        }
        System.out.println(ans);
    }
}
