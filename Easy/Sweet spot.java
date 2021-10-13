import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private int h;
    private char[][] map;
    Stack<Bbomb> bbombs;
    List<Bbomb> exploded;

    public Solver(int h) {
        this.h = h;
        map = new char[h][];
        bbombs = new Stack<>();
        exploded = new ArrayList<>();
    }

    enum Bomb {
        A,
        B,
        H
    }

    class Bbomb {
        int x, y;
        boolean explode;

        public Bbomb(int x, int y) {
            this.x = x;
            this.y = y;
            explode = false;
        }
    }

    public void addMapLine(String line, int i) {
        map[i] = line.toCharArray();
    }

    public void solve() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 'A' : explode(Bomb.A, j, i, false);break;
                    case 'H' : explode(Bomb.H, j, i, false);break;
                }
            }
        }
        while (!bbombs.empty()) {
            Bbomb bbomb = bbombs.pop();
            explode(Bomb.B, bbomb.x, bbomb.y, true);
            exploded.add(bbomb);
        }
    }

    private boolean exploded(int x, int y) {
        for(Bbomb bbomb : exploded) {
            if(bbomb.x == x && bbomb.y == y) return true;
        }
        return false;
    }

    private void explode(Bomb bomb, int X, int Y, boolean bbomb) {
        char val = bomb == Bomb.H ? '5' : '3';
        int size = 2;
        int row = Y-1;
        int col = X-1;
        if(bomb != Bomb.B) {
            for(int i = 0; i < 3; i++) {
                for(int y = row; y <= row + size; y++) {
                    for(int x = col; x <= col + size; x++) {
                        update(y, x, val);
                    }
                }
                size += 2;
                col--;
                row--;
                if(bomb != Bomb.H) val--;
            }
        }
        else if(bbomb){
            for(int i = 1; i < 4; i++) {
                update(Y-i, X, val);
                update(Y+i, X, val);
                update(Y, X+i, val);
                update(Y, X-i, val);
                val--;
            }
        }
    }

    private void update(int y, int x, char val) {
        if (y < 0 || y >= h || x < 0 || x >= map[0].length) {
            return;
        }
        if(map[y][x] == 'B' && !exploded(x, y)) {
            bbombs.add(new Bbomb(x, y));
        }
        else if(!Character.isDigit(map[y][x])) return;
        map[y][x] = val > map[y][x] ? val : map[y][x];
    }

    public void print() {
        for (char[] c : map) {
            for (int i = 0; i < c.length; i++) {
                System.out.print(c[i]);
            }
            System.out.println();
        }
    }


}

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Solver solver = new Solver(N);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            solver.addMapLine(line, i);
        }
        solver.solve();
        solver.print();
    }
}
