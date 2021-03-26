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
        int X = in.nextInt();
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String LINE = in.next();
            for(int j = 0; j < LINE.length(); j++) {
                char c = LINE.charAt(j);
                map[i][j] = c;
            }
        }
        
        while(X>0) {
            char first = N%2!=0?map[0][N-1]:map[N-1][N-1];
            Stack<Character> stack = new Stack<>();
            int y = 0;
            while(y<N) {
                for(int i = N-1; i>= 0; i--) {
                    char c = map[i][y];
                    if(!stack.isEmpty())
                    map[i][y] = stack.pop();
                    stack.push(c);
                }
                y++;
                if(y>=N) break;
                for(int i = 0; i < N; i++) {
                    char c = map[i][y];
                    if(!stack.isEmpty())
                    map[i][y] = stack.pop();
                    stack.push(c);
                }
                y++;
            }
            X--;
            map[N-1][0] = first;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
