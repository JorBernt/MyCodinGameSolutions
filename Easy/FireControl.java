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
        var forest = new char[6][6];
        for (int i = 0; i < 6; i++) {
            forest[i] = in.nextLine().toCharArray();
        }
        int count = 0;
        boolean fire = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (forest[i][j] == '#') {
                    if (cut(forest, i, j, '*')) {
                        forest[i][j] = '=';
                        count++;
                    }
                }
                if (forest[i][j] == '*') fire = true;
            }
        }
        out:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (forest[i][j] == '=') {
                    if (!cut(forest, i, j, '=')) {
                        count = 0;
                        break out;
                    }
                }
                if (forest[i][j] == 'o') {
                    if (cut(forest, i, j, '*')) {
                        count = 0;
                        break out;
                    }
                }
            }
        }

        System.out.println(count == 0 && fire ? "JUST RUN" : count == 0 ? "RELAX" : count);
    }

    static boolean cut(char[][] forest, int Y, int X, char c) {
        for (int i = 1; i <= 2; i++) {
            for (int y = Y - i; y < Y + 1 + i; y++) {
                for (int x = X - i; x < X + 1 + i; x++) {
                    if(x == X && y == Y) continue;
                    if (x >= 0 && x < forest[0].length && y >= 0 && y < forest.length && forest[y][x] == c)
                        return true;
                }
            }
        }
        return false;
    }
}
