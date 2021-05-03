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
        int w = in.nextInt();
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        char[][] map = new char[h][w];
        for (int i = 0; i < h; i++) {
            char[] line = in.nextLine().toCharArray();
            map[i] = line;
        }
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == 'x') {
                    System.out.print(".");
                    continue;
                }
                int n = count(map, i, j);
                System.out.print(n==0?".":n);
            }
            System.out.println();
        }

    }

    static int count(char[][] map, int y, int x) {
        int[][] dirs = {{-1,-1},{-1,0},{-1,1}, {0,1},{1,1},{1,0}, {1,-1},{0,-1}};
        int count = 0;
        for(int[] d : dirs) {
            if(y+d[0] >= 0 && y+d[0] < map.length && x+d[1] >= 0 && x+d[1] < map[0].length)
                if(map[y+d[0]][x+d[1]] == 'x') count++; 
        }
        return count;
    }
}
