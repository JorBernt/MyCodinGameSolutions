import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Cell {
    int x,y;
    List<Cell> adjacent;
    boolean dead;

    public Cell(int x, int y, char dead) {
        this.x = x;
        this.y = y;
        this.dead = dead=='0'?true:false;
        adjacent = new ArrayList<>();
    }

    public void addAdjacent(Cell[] cells) {
        for(Cell c:cells) {
            if(x == c.x && y-1 == c.y) adjacent.add(c);
            if(x+1 == c.x && y-1 == c.y) adjacent.add(c);
            if(x+1 == c.x && y == c.y) adjacent.add(c);
            if(x+1 == c.x && y+1 == c.y) adjacent.add(c);
            if(x == c.x && y+1 == c.y) adjacent.add(c);
            if(x-1 == c.x && y+1 == c.y) adjacent.add(c);
            if(x-1 == c.x && y == c.y) adjacent.add(c);
            if(x-1 == c.x && y-1 == c.y) adjacent.add(c);
        }
    }

    public Cell evolve() {
        int score = 0;
        Cell cell = new Cell(x,y,dead?'0':'1');
        for(Cell c:adjacent) {
            if(!c.dead) score++;
        }
        if(dead && score == 3)cell.dead = false;
        if(!dead && score > 3) cell.dead = true;
        if(!dead && score < 2) cell.dead = true;
        return cell;
        
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        Cell[][] map = new Cell[h][w];
        Cell[] cells = new Cell[h*w];
        int index = 0;
        for (int i = 0; i < h; i++) {
            String line = in.next();
            for(int j = 0; j < w; j++) {
                char c= line.charAt(j);
                Cell cell = new Cell(j, i, c);
                cells[index++] = cell;
                map[i][j] = cell;
            }
        }
        
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                map[i][j].addAdjacent(cells);
                map[i][j] = map[i][j].evolve();
                System.out.print(map[i][j].dead?0:1);
            }
            System.out.println();
        }

      

    }
}
