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
        String[] opBowls = in.nextLine().split(" ");
        String[] myBowls = in.nextLine().split(" ");
        int num = in.nextInt();
        int[] oa = new int[7];
        int[] ma = new int[7];
        int grains = 0;
        for(int i = 0; i < 7; i++) {
            oa[i] = Integer.parseInt(opBowls[i]);
            ma[i] = Integer.parseInt(myBowls[i]);
            if(i == num) grains = ma[i];
        }
        ma[num] = 0;
        boolean myTurn = true;
        boolean replay = false;
        int r = num+1;
        for(int i = num+1; i <= num+grains; i++) {
            if(myTurn)
                if(r > 6) {
                    myTurn = false;
                    r = 0;
            }
            if(!myTurn) 
                if(r > 5)  {
                    myTurn = true;
                    r=0;
                }
            if(myTurn) ma[r]++;
            else oa[r]++;
            if(i == num+grains && r == 6 && myTurn) {
                replay = true;
            }
            r++;

        }
        for(int i = 0; i < 7; i++) {
            if(i < 6) {
            System.out.print(oa[i]);
             System.out.print(" ");
            }
            else System.out.print("["+oa[i]+"]");
        }
        System.out.println();
        for(int i = 0; i < 7; i++) {
            if(i < 6) {
            System.out.print(ma[i]);
             System.out.print(" ");
            }
            else System.out.print("["+ma[i]+"]");
        }
        if(replay) System.out.println("\nREPLAY");
    }
}
