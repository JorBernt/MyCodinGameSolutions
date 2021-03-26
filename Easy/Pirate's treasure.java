import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) 
            for (int j = 0; j < W; j++) 
                map[i][j] = in.nextInt();
        for (int i = 0; i < H; i++) 
            for (int j = 0; j < W; j++) 
                if(map[i][j] != 1)
                if(surrounded(map, j, i)) {System.out.println(j +" "+i); break;}
    }
    static boolean surrounded(int[][] map, int w, int h) {
        int sum = 0;
        for(int i = h-1; i < h+2; i++) 
            for(int j = w-1; j < w+2; j++) 
                if(i < 0 || j < 0 || i > map.length-1 || j > map[0].length-1) sum++;
                else if(i >= 0 && j >= 0 && i < map.length && j < map[0].length) 
                    if(map[i][j] == 1) sum++;
        return (sum == 8 ? true : false);
    }
}
