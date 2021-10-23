import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private char[][] table;
    private char[][] message;
    private String action;
    enum Rule {
        ONE,
        TWO,
        THREE
    }

    public Solver() {
        table = new char[5][5];
    }

    public void addTable(String line, int i) {
        table[i] = line.replace(" ","").toCharArray();
    }

    public void addMessage(String... message) {
        this.message = new char[message.length][];
        for(int i = 0; i < message.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < message[i].length(); j++) {
                char c = Character.toUpperCase(message[i].charAt(j));
                if(getCoord(c) != null)
                    sb.append(c);
            }
            this.message[i] = sb.toString().toCharArray();
        }
    }
    public void print() {
        for(char[] ca : message) {
            if(!validate(ca)) {
                System.out.println("DUD");
                continue;
            }
            for(int i = 0; i < ca.length; i++) if(ca[i] != '*') System.out.print(ca[i]);
            System.out.println();
        }

    }

    public void solve(String action) {
        this.action = action;
        crypt();
        print();
    }

    private boolean validate(char[] ca) {
        return ca.length % 2 == 0;
    }

    private Coord getCoord(char c) {
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[0].length; j++) {
                if(table[i][j] == c) return new Coord(j, i);
            }
        }
        return null;
    }

    private Rule getRule(Coord a, Coord b) {
        if(a.y == b.y && a.x == b.x) return Rule.ONE;
        if(a.y == b.y) return Rule.ONE;
        if(a.x == b.x) return Rule.TWO;
        else return Rule.THREE;
    }

    private void swap(Coord m, Coord a, Coord b, Rule rule) {
        switch (rule) {
            case ONE :
                int x = 0;
                if(action.equals("ENCRYPT")) {
                    x = (a.x + 1) % 5;
                }
                else {
                    x = a.x - 1;
                    if(x < 0) x += 5;
                }
                message[m.y][m.x] = table[a.y][x]; break;
            case TWO :
                if(action.equals("ENCRYPT")) {
                    x = (a.y + 1) % 5;
                }
                else {
                    x = a.y - 1;
                    if(x < 0) x += 5;
                }
                message[m.y][m.x] = table[x][a.x]; break;
            case THREE:
                if(action.equals("ENCRYPT")) {
                    message[m.y][m.x] = table[a.y][b.x];
                }
                else {
                    message[m.y][m.x] = table[a.y][b.x];
                }
                break;
        }
    }

    private void crypt() {
        for (int i = 0; i < message.length; i++) {
            if(!validate(message[i])) continue;
            for (int j = 0; j < message[i].length; j += 2) {
                Coord a = getCoord(message[i][j]);
                Coord b = getCoord(message[i][j+1]);
                Rule rule = getRule(a, b);
                swap(new Coord(j, i), a, b, rule);
                swap(new Coord(j + 1, i), b, a, rule);
            }

        }
    }



    private class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Solver solver = new Solver();
        for (int i = 0; i < 5; i++) {
            String keyTable = in.nextLine();
            solver.addTable(keyTable, i);
        }
        String action = in.nextLine();
        int N = in.nextInt();
        String[] message = new String[N];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            message[i] = in.nextLine();
        }
        solver.addMessage(message);
        solver.solve(action);
    }
}
