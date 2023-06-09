import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] line = new char[N][2 * N - 1];
        String[] rows = new String[N];
        for (int i = 0; i < N; i++) {
            rows[i] = in.nextLine();
        }
        String outerLine = ("+" + ("-").repeat(2 * N - 1)).repeat(2) + "+";
        System.out.println(outerLine);
        for (int r = 0; r < 2; r++) {
            for (int i = 0; i < N; i++) {
                printLine(line, rows, i, false);
            }
            for (int i = line.length - 2; i >= 0; i--) {
                printLine(line, rows, i, true);
            }
            System.out.println(outerLine);
        }
    }

    private static void printLine(char[][] line, String[] rows, int i, boolean lower) {
        String ROW = rows[i];
        Arrays.fill(line[i], ' ');
        for (int j = 0; j < ROW.length(); j++) {
            line[i][j] = ROW.charAt(j);
        }
        if (lower) {
            for (int j = 0; j < line[i].length; j++) {
                switch (line[i][j]) {
                    case '^':
                        line[i][j] = 'v';
                        break;
                    case 'v':
                        line[i][j] = '^';
                        break;
                    case 'A':
                        line[i][j] = 'V';
                        break;
                    case 'V':
                        line[i][j] = 'A';
                        break;
                    case 'w':
                        line[i][j] = 'm';
                        break;
                    case 'm':
                        line[i][j] = 'w';
                        break;
                    case 'W':
                        line[i][j] = 'M';
                        break;
                    case 'M':
                        line[i][j] = 'W';
                        break;
                    case 'u':
                        line[i][j] = 'n';
                        break;
                    case 'n':
                        line[i][j] = 'u';
                        break;
                    case '/':
                        line[i][j] = '\\';
                        break;
                    case '\\':
                        line[i][j] = '/';
                        break;
                }
            }
        }
        for (int j = line[0].length - 1, k = 0; j > line[0].length / 2; j--, k++) {
            char c = line[i][k];
            switch (c) {
                case '(':
                    c = ')';
                    break;
                case ')':
                    c = '(';
                    break;
                case '{':
                    c = '}';
                    break;
                case '}':
                    c = '{';
                    break;
                case '[':
                    c = ']';
                    break;
                case ']':
                    c = '[';
                    break;
                case '>':
                    c = '<';
                    break;
                case '<':
                    c = '>';
                    break;
                case '/':
                    c = '\\';
                    break;
                case '\\':
                    c = '/';
                    break;
            }
            line[i][j] = c;
        }

        System.out.println(("|" + new String(line[i])).repeat(2) + "|");
    }
}
