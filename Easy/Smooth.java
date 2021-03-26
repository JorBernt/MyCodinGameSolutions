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
        for (int i = 0; i < N; i++) {
            long F = in.nextLong();
            while(true) {
                if(F == 1) {System.out.println("VICTORY"); break;}
                if(F%5 == 0) F /= 5;
                else if(F%3 == 0) F /= 3;
                else if(F%2 == 0) F /= 2;
                else {System.out.println("DEFEAT"); break;} 
            }
        }
    }
}
