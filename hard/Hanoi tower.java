import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int rings;
    static int turns = 0;
    static int turnToPrint;
    static Stack<Integer> start = new Stack<>();
    static Stack<Integer> end = new Stack<>();
    static Stack<Integer> mid = new Stack<>();
    static List<String> printList = new ArrayList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        rings = N;
        turnToPrint = in.nextInt();

        for(int i = N; i >= 1; i--) {
            start.push(i);
        }

        solve(N, start, end, mid);
        System.out.println(turns);
    }

    static void solve(int N, Stack<Integer> start, Stack<Integer> end, Stack<Integer> mid) {
        if(N == 1) {
            end.push(start.pop());
            turns++;
            if(turns == turnToPrint) print();
            return;
        }
        solve(N-1, start, mid, end);
        turns++;
        end.push(start.pop());
        if(turns == turnToPrint) print();

        solve(N-1, mid, end, start);
    }

    static void print() {
        Stack<Integer> a = (Stack<Integer>)start.clone();
        Stack<Integer> b = (Stack<Integer>)mid.clone();
        Stack<Integer> c = (Stack<Integer>)end.clone();

        List<String> left = new ArrayList<>();
        List<String> mid = new ArrayList<>();
        List<String> right = new ArrayList<>();
        int w = 2*rings+1;
        while(left.size() < rings-a.size()) left.add((" ").repeat(w/2)+"|"+(" ").repeat(w/2));
        while(mid.size() < rings-b.size()) mid.add((" ").repeat(w/2)+"|"+(" ").repeat(w/2));
        while(right.size() < rings-c.size()) right.add((" ").repeat(w/2)+"|");

        while(!a.isEmpty()) {
            int n = a.pop();
            left.add((" ").repeat((w-(2*n+1))/2)+""+("#").repeat(2*n+1)+""+(" ").repeat((w-(2*n+1))/2));
        }
        while(!b.isEmpty()) {
            int n = b.pop();
            mid.add((" ").repeat((w-(2*n+1))/2)+""+("#").repeat(2*n+1)+""+(" ").repeat((w-(2*n+1))/2));
        }
        while(!c.isEmpty()) {
            int n = c.pop();
            right.add((" ").repeat((w-(2*n+1))/2)+""+("#").repeat(2*n+1));
        }
        for(int i = 0; i < rings; i++) {
            System.out.println((left.get(i)+" "+mid.get(i)+" "+right.get(i)));
        }
    }
}
