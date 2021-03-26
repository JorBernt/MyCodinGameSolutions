import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        char[][] grid = new char[W][H];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String line = in.nextLine();
            for(int j = 0; j < W; j++) {
                grid[j][i] = line.charAt(j);
            }
        }
        int k = 0;
        while (k < W) {
            int j = k;
            int l = 0;
            for(int i = 0; i < H; ) {
                l = i;
                if(j < W-3) {
                    if(grid[j+1][i] == '-') {
                        j += 3;
                        if(i < H-1) i++;
                    }
                }
                if(j > 1) {
                    if(grid[j-1][i] == '-') {
                        j -= 3;
                        if(i < H-1) i++;
                    }
                }
                if(l == i) i++;
                if(i == H-1) System.out.println(grid[k][0]+""+grid[j][i]);
            }
            k += 3;
        }          
    }
}
