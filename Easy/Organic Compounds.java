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
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            String COMPOUND = in.nextLine();
            map[i] = COMPOUND.toCharArray();
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'H') {
                    if(!validate(map, i, j, 4-Character.getNumericValue(map[i][j+1]))) {
                        System.out.println("INVALID");
                        return;
                    }

                }
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("VALID");
    }

    static boolean validate(char[][] map, int y, int x, int bonds) {
        int[][] dirs = {{0,3},{1,0},{0,-3},{-1,0}};
        int count = 0;
        for(int[] d: dirs) {
            if(y+d[0] >= 0 && y+d[0] < map.length && x+d[1] >= 0 && x +d[1] < map[y+d[0]].length) {
                if(Character.isDigit(map[y+d[0]][x+d[1]])) count+=Character.getNumericValue(map[y+d[0]][x+d[1]]);
            }
        }
        return count == bonds;
    }
}
