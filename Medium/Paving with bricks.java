import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

public class Solution {
    static Map<String, Integer> log = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();

        var pavement = new boolean[H][W];
        System.out.println(solve(pavement));

    }

    static int solve(boolean[][] pavement) {
        String s = toString(pavement);
        if (log.containsKey(s)) return log.get(s) ;
        if (done(pavement)) {
            return 1;
        }
        List<int[][]> possible = getPossible(pavement);
        if(possible.size() == 0) return 0;
        int n = 0;
        for (var pos : possible) {
            if (pos[1][1] == 2) {
                pavement[pos[0][0]][pos[0][1]] = true;
                pavement[pos[0][0]][pos[0][1] + 1] = true;
            } else {
                pavement[pos[0][0]][pos[0][1]] = true;
                pavement[pos[0][0] + 1][pos[0][1]] = true;
            }
            n+= solve(pavement);
            if (pos[1][1] == 2) {
                pavement[pos[0][0]][pos[0][1]] = false;
                pavement[pos[0][0]][pos[0][1] + 1] = false;
            } else {
                pavement[pos[0][0]][pos[0][1]] = false;
                pavement[pos[0][0] + 1][pos[0][1]] = false;
            }
        }
        log.put(s, n);
        return log.get(s);
    }

    static List<int[][]> getPossible(boolean[][] pavement) {
        int h = pavement.length;
        int w = pavement[0].length;
        List<int[][]> possible = new ArrayList<>();
        out:for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (j < w - 1) {
                    if (!pavement[i][j] && !pavement[i][j + 1]) possible.add(new int[][]{{i, j}, {1, 2}});
                }
                if (i < h - 1) {
                    if (!pavement[i][j] && !pavement[i + 1][j]) possible.add(new int[][]{{i, j}, {2, 1}});
                }
                if(possible.size() > 0)
                break out;
            }
        }
        return possible;
    }

    static boolean done(boolean[][] pavement) {
        for (var b : pavement)
            for (var r : b) if (!r) return false;
        return true;
    }


    static String toString(boolean[][] pavement) {
        StringBuilder sb = new StringBuilder();
        for (var b : pavement) {
            sb.append(Arrays.toString(b));
        }
        return sb.toString();
    }
}
