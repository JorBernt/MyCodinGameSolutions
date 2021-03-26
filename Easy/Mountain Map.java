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
        int n = in.nextInt(); // the number of mountains
        
        int maxHeight = 0;
        int[] mountains = new int[n];
        for (int i = 0; i < n; i++) {
                int height = in.nextInt(); // height of the mountain
                maxHeight = Math.max(maxHeight, height);
                mountains[i] = height;
        }

        String[][] draw = new String[maxHeight][1000];
        for (String[] row: draw)
        Arrays.fill(row, " ");

        int d = 0;
        for(int i = 0; i < n; i++) {
            if(mountains[i] == 1) {
                draw[maxHeight-1][d++] = "/";
                draw[maxHeight-1][d++] = "\\";
            }
            else {
                for(int j = 0; j < mountains[i]; j++) 
                    draw[maxHeight-j-1][d++] = "/";
                for(int j = 0; j < mountains[i]; j++) 
                    draw[maxHeight-mountains[i]+j][d++] = "\\";
            }
        }
        
        for(int i = 0; i < maxHeight; i++) {
            for(int j = 0; j < draw[i].length; j++) {
                boolean canDraw = false;
                for(int q = j+1; q < draw[i].length; q++) 
                    if(draw[i][q] != " ") canDraw = true;
                System.out.print(draw[i][j]);
                if(!canDraw) break;
            }
             System.out.println();
    }
    }
}
