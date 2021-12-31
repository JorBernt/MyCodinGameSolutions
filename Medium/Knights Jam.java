import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    static Map<Integer, int[]> moves = Map.of(
            1, new int[]{6, 8},
            2, new int[]{7, 9},
            3, new int[]{4, 8},
            4, new int[]{3, 9},
            5, new int[]{},
            6, new int[]{1, 7},
            7, new int[]{2, 6},
            8, new int[]{1, 3},
            9, new int[]{2, 4}
    );
    static int[] target = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    static int min = Integer.MAX_VALUE;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        var board = new int[9];
        for (int i = 0, j = 0; i < 3; i++) {
            var input = in.nextLine().toCharArray();
            for (int n = 0; n < input.length; n++, j++) {
                board[j] = input[n] == '.' ? 0 : Character.getNumericValue(input[n]);
            }
        }
        solve(board, 0, new ArrayList<>());
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void solve(int[] board, int n, List<String> log) {
        if(board[4] != target[4]) return;
        if (log.contains(Arrays.toString(board)) || n >= min) return;
        if (Arrays.equals(board, target)) {
            min = n;
            return;
        }
        log.add(Arrays.toString(board));
        for (int i = 0; i < 9; i++) {
            int move = getMove(i, board);
            if (move != -1) {
                int temp = board[i];
                board[i] = board[move];
                board[move] = temp;
                solve(board, n + 1, log);
                temp = board[i];
                board[i] = board[move];
                board[move] = temp;
            }
        }
        log.remove(log.size() - 1);
    }


    static int getMove(int pos, int[] board) {
        for (int n : moves.get(pos + 1)) {
            if (board[n - 1] == 0) {
                return n - 1;
            }
        }
        return -1;
    }
}
