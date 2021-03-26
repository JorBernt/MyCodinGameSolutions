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
        
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            String[] inputs = line.split(" ");
            Stack<Integer> stack = new Stack<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> queue = new LinkedList<>();
            int isStack = 0;
            int isPQ = 0;
            int isQueue = 0;
            int operations = 0;
            for(String s : inputs) {
                int n = Integer.parseInt(s.substring(1,s.length()));
                if(s.charAt(0) == 'i') {
                    stack.add(n);
                    pq.add(n);
                    queue.add(n);
                }
                else {
                    operations++;
                    if(!stack.isEmpty())
                    if(stack.pop() == n) isStack++;
                    if(!pq.isEmpty())
                    if(pq.poll() == n) isPQ++;
                    if(!queue.isEmpty())
                    if(queue.poll() == n) isQueue++;
                }
            }
        
            int options = 0;
            String ans = "mystery";
            if(isStack == operations) {
                options++;
                ans = "stack";

            }
            if(isPQ == operations) {
                options++;
                ans = "priority queue";
            }
            if(isQueue == operations) {
                options++;
                ans = "queue";
            }
            
            // Write an answer using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println((options > 1 ? "unsure" : ans));
        }
    }
}
