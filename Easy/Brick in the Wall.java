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
        int X = in.nextInt();
        int N = in.nextInt();
        List<Integer> bricks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int m = in.nextInt();
            bricks.add(m);
        }
        bricks.sort((a,b)->b-a);
        double ans = 0d;
        int row = 1;
        int r = 0;
        for(int m : bricks) {
            ans += ((row-1)*6.5/100)*10*m;
            r++;
            if(r==X) {
                r=0;
                row++;
            }
        }
        System.out.println(String.format("%.3f",ans));
    }
}
