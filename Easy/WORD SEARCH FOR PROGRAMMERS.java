import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int[][] words;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] grid = new char[size][];
        for (int i = 0; i < size; i++) {
            String ROW = in.nextLine();
            grid[i] = ROW.toCharArray();
        }
        String clueString = in.nextLine();
        List<String> clues = new ArrayList<>();
        for (String s : clueString.split(" "))
            clues.add(s.toUpperCase(Locale.ROOT));
        words = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                List<String> temp = new ArrayList<>();
                for (String s : clues) {
                    if (grid[i][j] == s.charAt(0) && solve(grid, s, 0, i, j, size)) {
                        temp.add(s);
                    }
                }
                clues.removeAll(temp);
            }
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        //print characters that match true in words
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (words[i][j] > 0)
                    System.out.print(grid[i][j]);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

    }

    public static boolean solve(char[][] grid, String clue, int index, int col, int row, int size) {
        return solve(grid, clue, index, col, row, 0, 1, size) ||
                solve(grid, clue, index, col, row, 1, 0, size) ||
                solve(grid, clue, index, col, row, 0, -1, size) ||
                solve(grid, clue, index, col, row, -1, 0, size) ||
                solve(grid, clue, index, col, row, 1, 1, size) ||
                solve(grid, clue, index, col, row, 1, -1, size) ||
                solve(grid, clue, index, col, row, -1, 1, size) ||
                solve(grid, clue, index, col, row, -1, -1, size);
    }

    public static boolean solve(char[][] grid, String clue, int index, int col, int row, int dx, int dy, int size) {
        if (row < 0 || row >= size || col < 0 || col >= size)
            return false;
        if (grid[col][row] != clue.charAt(index))
            return false;
        words[col][row]++;
        if (index == clue.length() - 1) {
            return true;
        }
        if (solve(grid, clue, index + 1, col + dy, row + dx, dx, dy, size)) {
            return true;
        } else {
            words[col][row]--;
            return false;
        }
    }
}
