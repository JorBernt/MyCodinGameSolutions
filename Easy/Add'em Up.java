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
            int x = in.nextInt();
            pq.add(x);
        }
        int total = 0;
        while(pq.size() > 1) {
            int temp = pq.poll()+pq.poll();
            total +=temp;
            pq.add(temp);
        }   
        System.out.println(total);
    }
}
