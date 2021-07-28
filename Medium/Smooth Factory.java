import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    static long ans = 0;
    static int wins = 0;
    static int V;
    static List<Long> memo = new ArrayList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        V = in.nextInt();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1l);
        rec(pq);
        System.out.println(ans);
    }

    static void rec(PriorityQueue<Long> pq) {
        if(wins == V) {
            return;
        }
        long n = pq.poll();
        while (true) {
            if(memo.contains(n)) n = pq.poll();
            else break;
        }
        ans += n;
        wins++;
        memo.add(n);
        pq.add(n*2);
        pq.add(n*3);
        pq.add(n*5);
        rec(pq);
    }
}
