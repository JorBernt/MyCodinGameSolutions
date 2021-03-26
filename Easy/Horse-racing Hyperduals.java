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
        int[] horses = new int[N];
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int V = in.nextInt();
            int E = in.nextInt();
            int[] vec = {V, E};
            map.put(i, vec);
        }
        Arrays.sort(horses);
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for(int j = 0; j < N-1; j++) {
            for(int i = j+1; i < N; i++) {
                min = Math.min(min, Math.abs(map.get(j)[0]-map.get(i)[0]) + Math.abs(map.get(j)[1]-map.get(i)[1]));
            }
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(min);
    }
}
