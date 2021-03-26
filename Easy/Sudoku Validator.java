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
        int[][] s = new int[9][9];
        boolean check = true;
        for (int i = 0; i < 9; i++) {
            int[] r = new int[9];
            
            for (int j = 0; j < 9; j++) {
                int n = in.nextInt();
                s[i][j] = n;
          
        }
        }


for(int row = 0; row < 9; row++)
   for(int col = 0; col < 8; col++)
      for(int col2 = col + 1; col2 < 9; col2++)
         if(s[row][col]==s[row][col2])
            check = false;

// column checker
for(int col = 0; col < 9; col++)
   for(int row = 0; row < 8; row++)
      for(int row2 = row + 1; row2 < 9; row2++)
         if(s[row][col]==s[row2][col])
            check =  false;

// grid checker
for(int row = 0; row < 9; row += 3)
   for(int col = 0; col < 9; col += 3)
      // row, col is start of the 3 by 3 grid
      for(int pos = 0; pos < 8; pos++)
         for(int pos2 = pos + 1; pos2 < 9; pos2++)
            if(s[row + pos%3][col + pos/3]==s[row + pos2%3][col + pos2/3])
               check = false;
    

    if(check) {
        System.out.println("true");
    }
    else {
        System.out.println("false");
    }
    }

}
