import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Cell {
    int x, y;
    boolean alive, change;
    List<Cell> adj;

    public Cell(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
        change = false;
        adj = new ArrayList<>();
    }

    public List<Cell> getAdj(List<Cell> cells) {
        if(!adj.isEmpty()) return adj;
        for(Cell c : cells) {
            if(c.x == x && c.y == y+1) adj.add(c);
            if(c.x == x+1 && c.y == y+1) adj.add(c);
            if(c.x == x-1 && c.y == y+1) adj.add(c);
            if(c.x == x && c.y == y-1) adj.add(c);
            if(c.x == x+1 && c.y == y-1) adj.add(c);
            if(c.x == x-1 && c.y == y-1) adj.add(c);
            if(c.x == x+1 && c.y == y) adj.add(c);
            if(c.x == x-1 && c.y == y) adj.add(c);
        }
        return adj;
    }

    public void update() {
        if(change) alive = !alive;
        change = false;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int w = in.nextInt();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String a = in.nextLine();
        boolean[] alive = new boolean[a.length()];
        String d = in.nextLine();
        boolean[] dead = new boolean[d.length()];
        for(int i = 0; i < 9; i++) {
            if(a.charAt(i)=='1') alive[i] = true;
            if(d.charAt(i)=='1') dead[i] = true;
        }
        List<Cell> cells = new ArrayList<>();
        Cell[][] map = new Cell[h][w];
        for (int i = 0; i < h; i++) {
            String line = in.nextLine();
            for(int j = 0; j < line.length(); j++) {
                Cell cell = new Cell(j,i,line.charAt(j)=='O');
                cells.add(cell);
                map[i][j] = cell;
            }
        }
        while(n>0) {
            for(Cell c : cells) {
                int count = 0;
                for(Cell adj : c.getAdj(cells)) {
                    if(adj.alive) count++;
                }
                if((c.alive && !alive[count]) || (!c.alive && dead[count])) {
                    c.change = true;
                }
            }
            for(Cell c : cells) c.update();
            n--;
        }

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j].alive?"O":".");
            }
            System.out.println();
        }
    }
}
