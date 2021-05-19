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
        int[] n = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        int m = 1;
        for(int i = n.length-1; i>= 0; i--) {
            n[i]+=m;
            if(n[i]>9) {
                if(i>0)
                n[i] = 0;
                m = 1;
            }
            else m = 0;
        }
        StringBuilder sb = new StringBuilder();
        for(int N:n) sb.append(N);
        n = Arrays.stream(sb.toString().split("")).mapToInt(Integer::parseInt).toArray();
        m = 0;
        for(int i = 1; i < n.length; i++) {
            if(n[i]<n[i-1]) {
                m = i;
                break;
            }
        }
        for(int i = 0; i < n.length; i++) {
            System.out.print(i>=m?n[m-1]:n[i]);
        }
    }
}
