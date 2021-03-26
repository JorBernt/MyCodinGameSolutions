import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int max  = 1;
        int index = 1;

        if (n == 2) {
            System.out.println("1 1");
            return;
        }

        for(int i = 0; i <= n/2; i++) {
            int sum = (i <= 1 ? 2 : 3) + (map.containsKey(i) ? map.get(i) : calc(0, i)) + (map.containsKey(n - i - 1) ? map.get(n-i-1) : calc(i, n-1));

            if(sum > max) {
                max = sum;
                index = i + 1;
            }
        }

        System.out.println(max + " " + index);
    }


    static int calc(int start, int end) {
        int dist = end-start;
        if(dist <= 3) {
            return 0;
        }

        if(map.containsKey(dist)) {
            return map.get(dist);
        }

        int mid = (start+end)/2;
        int n = 1 + calc(start, mid) + calc(mid, end);
        map.put(dist, n);
        return n;

    }
}
