import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Cell {
    int x, y, value;
    boolean visited, rabbit, start;
    List<Cell> adj;
    List<Cell> path;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        adj = new ArrayList<>();
        path = new ArrayList<>();
        visited = false;
    }

    public void addAdj(Cell[] cells) {
        for(Cell c : cells) {
            if((value & 4) == 0) 
                if(c.x == x && c.y == y-1) adj.add(c);
            if((value & 1) == 0) 
                if(c.x == x && c.y == y+1) adj.add(c);
            if((value & 8) == 0) 
                if(c.x == x+1 && c.y == y) adj.add(c);
            if((value & 2) == 0) 
                if(c.x == x-1 && c.y == y) adj.add(c);    
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int xs = in.nextInt();
        int ys = in.nextInt();
        int xr = in.nextInt();
        int yr = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();
        int startID = 0, rabbitID = 0;
        Cell[] cells = new Cell[h*w];
        int id = 0;
        for (int i = 0; i < h; i++) {
            char[] l = in.next().toCharArray();
            for(int j = 0; j < l.length; j++) {
                Cell cell = new Cell(j, i, Integer.parseInt(""+l[j], 16));
                if(i==ys && j == xs) {
                    cell.start = true;
                    startID = id;
                }
                if(i == yr && j == xr) {
                    cell.rabbit = true;
                    rabbitID = id;
                }
                cells[id] = cell;
                id++;
            }
            
        }
        for(Cell c:cells) c.addAdj(cells);
       
        System.out.print(traverse(cells[startID], true).size());
        for(Cell c:cells) {
            c.visited = false;
            c.path.clear();
        }
        System.out.print(" "+traverse(cells[rabbitID], false).size());
    }

    static List<Cell> traverse(Cell cell, boolean start) {
        List<Cell> toVisit = new ArrayList<>();
        Stack<Cell> temp = new Stack<>();
        toVisit.addAll(cell.adj);
        while(true) {
            for(Cell c:toVisit) {
                if(!c.visited) {
                    c.visited = true;
                    for(Cell cc:c.adj) {
                        if(cc.path.isEmpty()){
                            cc.path.addAll(c.path);
                            cc.path.add(c);
                        }
                        temp.add(cc);
                    }
                    if((c.start && !start) || ( c.rabbit && start)) {
                        c.path.add(c);
                        return c.path;
                    }
                }
            }
            while(!temp.isEmpty()) {
                    toVisit.add(temp.pop());
                }
        }
    }
}
