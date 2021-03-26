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
        int orbSizeMin = in.nextInt();
        int orbSizeMax = in.nextInt();
        int glowingSize1 = in.nextInt();
        int glowingSize2 = in.nextInt();
        double glowVol = volume(glowingSize1) + volume(glowingSize2);
        int max = -1;
        String ans = "VALID";
        for(int i = orbSizeMin; i <= orbSizeMax; i++) {
            for(int j = i; j <= orbSizeMax; j++) {
                if((long)(volume(i)+volume(j)) == (long)glowVol) {
                    if(i!=j && i!=glowingSize1 && i!= glowingSize2) {
                        if(Math.abs(i-j) > max) {
                            max = Math.abs(i-j);
                            ans = i +" "+j;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static double volume(double n) {
        return Math.pow(((4*Math.PI)*(n/2)), 3)/3;
    }
}


