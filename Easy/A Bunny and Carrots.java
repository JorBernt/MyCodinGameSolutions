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
        int M = in.nextInt();
        int N = in.nextInt();
        int t = in.nextInt();
        var garden = new boolean[M][N];
        in.nextLine();
        int[] choices = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < t; i++) {
            for (int j = M - 1; j >= 0; j--) {
                if (!garden[j][choices[i] - 1]) {
                    garden[j][choices[i] - 1] = true;
                    break;
                }
            }
            System.out.println(count(garden));
        }

    }

    static int count(boolean[][] garden) {
        int count = 0;
        for (int i = 0; i < garden.length; i++) {
            for (int j = 0; j < garden[0].length; j++) {
                if (garden[i][j]) continue;
                if (j == 0) count++;
                if (j == garden[0].length - 1) count++;
                if (j > 0 && garden[i][j - 1]) count++;
                if (j < garden[0].length - 1 && garden[i][j + 1]) count++;
                if (i == 0) count++;
                if (i == garden.length - 1) count++;
                if (i < garden.length - 1 && garden[i + 1][j]) count++;
                if (i > 0 && garden[i - 1][j]) count++;
            }
        }
        return count;
    }
}
