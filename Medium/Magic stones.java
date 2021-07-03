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
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int level = in.nextInt();
            pq.add(level);
        }
        int ans = 0;
        while(pq.size()>0) {
            int a = pq.poll();
            if(pq.size() == 0) {
                ans++;
                break;
            }
            int b = pq.peek();
            if(a==b) pq.add(pq.poll()+1);
            else ans++;
        }
        System.out.println(ans);
    }
}
