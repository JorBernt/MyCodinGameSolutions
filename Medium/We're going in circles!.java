import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    static char[][] board;
    static Map<Character, int[]> moves = Map.of(
            '<', new int[]{0, -1},
            '>', new int[]{0, 1},
            '^', new int[]{-1, 0},
            'v', new int[]{1, 0}
    );

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        board = new char[h][];
        in.nextLine();
        for (int i = 0; i < h; i++) {
            board[i] = in.nextLine().toCharArray();
        }
        int loops = 0;
        for (int y = 0; y < h; y++) {   
            for (int x = 0; x < w; x++) {
                if (board[y][x] != '.' && board[y][x] != 'y') {
                    if (solve(board, new int[]{y, x}, board[y][x])) loops++;
                }
            }
        }
        System.out.println(loops);
    }

    static boolean solve(char[][] board, int[] pos, char move) {
        if (pos[0] < 0 || pos[0] >= board.length || 
        pos[1] < 0 || pos[1] >= board[0].length || 
        board[pos[0]][pos[1]] == 'y') {
            copyArray(board);
            return false;
        }
        if (board[pos[0]][pos[1]] == 'x') {
            copyArray(board);
            return true;
        }
        if (board[pos[0]][pos[1]] != '.' && board[pos[0]][pos[1]] != 'x') {
            move = board[pos[0]][pos[1]];
        }
        board[pos[0]][pos[1]] = board[pos[0]][pos[1]] == '.' ? '.' : 'x';
        int[] m = moves.get(move);
        for (int i = 0; i < 2; i++) pos[i] += m[i];
        return solve(board, pos, move);
    }

    private static void copyArray(char[][] board) {
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                Solution.board[y][x] = board[y][x] == 'x' ? 'y' : board[y][x];
            }
        }
    }

}
