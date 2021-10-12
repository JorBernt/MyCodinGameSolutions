import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private int h, w;
    private char[][] map;
    private int x,y;

    public Solver(int h, int w) {
        this.h = h;
        this.w = w;
        map = new char[h][w];
    }

    public void addMap(String s, int ind) {
        map[ind] = s.toCharArray();
    }

    private void addPlayer() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 'X') {
                    x = j;
                    y = i;
                    return;
                }
            }
        }
    }

    public int solve() {
        addPlayer();
        return rec(x, y, map, 0);
    }

    private int rec(int x, int y, char[][] map, int sum) {
        if(x < 0 || x >= map[0].length || y < 0 || y >= map.length || map[y][x] == '#') return sum;
        if(Character.isDigit(map[y][x])) {
            sum += Character.getNumericValue(map[y][x]);
        }
        char temp = map[y][x];
        map[y][x] = '#';
        int east = rec(x-1, y, map, sum);
        int west = rec(x+1, y, map, sum);
        int north = rec(x, y-1, map, sum);
        int south = rec(x, y+1, map, sum);
        map[y][x] = temp;
        return Math.max(Math.max(east, west), Math.max(north, south));
    }


}

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int W = in.nextInt();
        Solver solver = new Solver(H, W);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String row = in.nextLine();
            solver.addMap(row, i);
        }
        System.out.println(solver.solve());

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
    }
}
