package leetcode;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[][] board = new int[9][];
        for (int i = 0; i < 9; i++) {
            board[i] = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solve(board);

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static boolean solve(int[][] board) {
        for(int y = 0; y < 9; y++) {
            out:for(int x = 0; x < 9; x++) {
                if(board[y][x] == 0) {
                  List<Integer> possible = getPossible(board, x, y);
                    for(int n : possible) {
                      board[y][x] = n;
                      if(solve(board)) continue out;
                      else board[y][x] = 0;
                  }
                    if(board[y][x] == 0) return false;
                }
            }
        }
        return true;
    }

    static List<Integer> getPossible(int[][] board, int x, int y) {
        List<Integer> possible = new ArrayList<>();
        for(int i = 1; i <= 9; i++) possible.add(i);
        for(int i = 0; i < 9; i++) {
            if(possible.contains(board[y][i])) possible.remove(Integer.valueOf(board[y][i]));
            if(possible.contains(board[i][x])) possible.remove(Integer.valueOf(board[i][x]));
        }
        int sx = x/3;
        int sy = y/3;
        for(int i = sy*3; i < sy*3+3;i++) {
            for(int j = sx*3; j < sx*3+3;j++) {
                if(possible.contains(board[i][j])) possible.remove(Integer.valueOf(board[i][j]));
            }
        }
        return possible;
    }
}
