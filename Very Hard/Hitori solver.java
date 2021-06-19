import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] board = new char[n][];
        char[][] copy = new char[n][];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            board[i] = line.toCharArray();
            copy[i] = line.toCharArray();
        }

        rec(board,0,0,n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] != '*') continue;
                board[i][j] = copy[i][j];
                if(validRow(board, j, i, n) && validCol(board, j, i, n) && !shadedTouching(board, j, i ,n)) continue;
                board[i][j] = '*';
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }

    static boolean rec(char[][] board, int y, int x, int n) {
        for (int i = y; i < n; i++) {
            for (int j = x; j < n; j++) {
                if(board[i][j] == '*') {
                    if (shadedTouching(board, j, i, n))
                        return false;
                    if(j==n-1 && !validCol(board, j, i, n)) return false;
                    if(i == n-1 && !validRow(board, j, i, n)) return false;
                    continue;
                }
                if (!validRow(board, j, i, n) || !(validCol(board, j, i, n))) {
                    char c = board[i][j];
                    board[i][j] = '*';
                    if (rec(board, i, j,n)) {
                        continue;
                    }
                    else board[i][j] = c;
                }
                if(j==n-1 && !validCol(board, j, i, n)) return false;
                if(i==n-1 && !validRow(board, j, i, n)) return false;
            }
            x=0;
        }
        return true;
    }

    static boolean shadedTouching(char[][] board, int x, int y, int n) {
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] a : dirs) {
            int X =x+a[1];
            int Y =y+a[0];
            if(X>=0 && X < n && Y>=0 && Y < n && board[Y][X] == '*') return true;
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j]!='*') {
                    boolean b = false;
                    for(int[] a : dirs) {
                        int X =j+a[1];
                        int Y =i+a[0];
                        if(X>=0 && X < n && Y>=0 && Y < n && board[Y][X] != '*') {
                            b=true;
                            break;
                        }
                    }
                    if(!b) return true;
                }
            }
        }
        return false;
    }

    static boolean validRow(char[][] board, int x, int y, int n) {
        List<Character> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (board[i][x] != '*' && nums.contains(board[i][x]))
                return false;
            if(board[i][x] != '*')
            nums.add(board[i][x]);
        }
        return true;
    }

    static boolean validCol(char[][] board, int x, int y, int n) {
        List<Character> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (board[y][i] != '*' && nums.contains(board[y][i]))
                return false;
            if(board[y][i] != '*')
            nums.add(board[y][i]);
        }
        return true;
    }
}
