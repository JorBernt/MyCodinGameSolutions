import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private final char[][] lut;
    private String encodedMessage;

    public Solver(int n) {
        lut = new char[n][];
    }

    public void addLUTRow(String row, int index) {
        row = row.replace(" ", "");
        lut[index] = row.toCharArray();
    }

    public void encodeMessage(String message) {
        char[] ca = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : ca) {
            sb.append(getLUTPos(c));
        }
        encodedMessage = sb.toString();
    }

    private String getLUTPos(char c) {
        for (int i = 0; i < lut.length; i++) {
            for (int j = 0; j < lut[i].length; j++) {
                if (lut[i][j] == c) return i + "" + j;
            }
        }
        return "NOT FOUND";
    }

    public String toString() {
        return encodedMessage;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Solver solver = new Solver(rows);
        for (int i = 0; i < rows; i++) {
            String row = in.nextLine();
            solver.addLUTRow(row, i);
        }
        String message = in.nextLine();
        solver.encodeMessage(message);
        System.out.println(solver);
    }
} 
