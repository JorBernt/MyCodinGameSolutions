import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solver {
    private int h, w, pH, pW, solutions;
    private char[][] board;
    private char[][] pattern;
    private char[][] solution;

    public Solver() {
        solutions = 0;
    }

    public void setBoardDimensions(int h, int w) {
        this.h = h;
        this.w = w;
        board = new char[h][];
    }

    public void addLine(String line, int index) {
        board[index] = line.toCharArray();
    }

    public void setPatternDimensions(int h, int w) {
        pH = h;
        pW = w;
        pattern = new char[h][];
    }

    public void addPatternLine(String line, int index) {
        pattern[index] = line.toCharArray();
    }

    public void solve() {
        int startY = 0, startX = 0;
        while (startY + pH <= h) {
            boolean valid = true;
            out: for (int i = startY, y = 0; i < startY + pH; i++, y++) {
                for (int j = startX, x = 0; j < startX + pW; j++, x++) {
                    if (pattern[y][x] == '*' && board[i][j] == '#') {
                        valid = false;
                        break out;
                    }
                }
            }
            if (valid)
                addSolution(startY, startX);
            startX++;
            if (startX + pW > w) {
                startX = 0;
                startY++;
            }
        }
    }

    private void addSolution(int startY, int startX) {
        if (solutions == 0) {
            char[][] copyBoard = new char[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }
            for (int i = startY, y = 0; i < startY + pH; i++, y++) {
                for (int j = startX, x = 0; j < startX + pW; j++, x++) {
                    if (pattern[y][x] == '.')
                        continue;
                    copyBoard[i][j] = pattern[y][x];
                }
            }
            solution = copyBoard;
        }
        solutions++;
    }

    public void print() {
        System.out.println(solutions);
        if (solutions == 1) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(solution[i][j]);
                }
                System.out.println();
            }
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Solver solver = new Solver();
        int a = in.nextInt();
        int b = in.nextInt();
        solver.setPatternDimensions(a, b);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < a; i++) {
            String objectLine = in.nextLine();
            solver.addPatternLine(objectLine, i);
        }
        int c = in.nextInt();
        int d = in.nextInt();
        solver.setBoardDimensions(c, d);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < c; i++) {
            String gridLine = in.nextLine();
            solver.addLine(gridLine, i);
        }
        solver.solve();
        solver.print();
    }
}
