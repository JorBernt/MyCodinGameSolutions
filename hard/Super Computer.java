import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Task implements Comparable<Task>{
    int start, end;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Task t) {
        return Integer.compare(end, t.end);
    }
}

class Solution {
    int max = Integer.MIN_VALUE;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int J = in.nextInt();
            int D = in.nextInt();
            tasks.add(new Task(J, J+D));
        }
        tasks.sort((a,b) -> a.compareTo(b));
        int day = 0;
        int ans = 0;
        for(Task t: tasks) {
            if(t.start >= day) { 
                day = t.end;
                ans++;
            }
        }
        System.out.println(ans);

    }
}
