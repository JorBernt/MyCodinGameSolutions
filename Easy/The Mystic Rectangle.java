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
        int x = in.nextInt();
        int y = in.nextInt();
        int u = in.nextInt();
        int v = in.nextInt();
        int X = Math.min(200-Math.abs(u-x), Math.abs(x-u));
        int Y = Math.min(150-Math.abs(v-y), Math.abs(y-v));
        double time = Math.abs(X-Y)*(X<Y?0.4:0.3)+0.5*Math.min(X,Y);
        System.out.println(String.format("%.1f",time));
    }
}
