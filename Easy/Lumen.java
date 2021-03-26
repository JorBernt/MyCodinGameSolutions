import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Cell {
    int x, y, light, id;
    List<Cell> adjacentCells;
    boolean visited;
    public Cell(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        adjacentCells = new  ArrayList<>();
        visited = false;
    }

    public void addNeighbours(Map<Integer,Cell> cells) {
        for(int  id : cells.keySet()) {
            Cell c = cells.get(id);
            if(x+1 == c.x && y == c.y) adjacentCells.add(c);
            else if(x+1 == c.x && y+1 == c.y) adjacentCells.add(c);
            else if(x == c.x && y+1 == c.y) adjacentCells.add(c);
            else if(x-1 == c.x && y+1 == c.y) adjacentCells.add(c);
            else if(x-1 == c.x && y == c.y) adjacentCells.add(c);
            else if(x-1 == c.x && y-1 == c.y) adjacentCells.add(c);
            else if(x == c.x && y-1 == c.y) adjacentCells.add(c);
            else if(x+1 == c.x && y-1 == c.y) adjacentCells.add(c);
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int L = in.nextInt();
        int id = 1;
        Map<Integer, Cell> cells = new HashMap<>();
        List<Integer> lights = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String c = in.next();
                Cell cell = new Cell(j, i, id);
                if(c.equals("C")){
                    lights.add(id);
                    cell.light = L;
                }
                cells.put(id, cell);
                id++;
            }
        }
        for(int i : cells.keySet()) {
            cells.get(i).addNeighbours(cells);
        }
        int ans = 0;
        for(int i : lights) {
            cells = traverse(cells, cells.get(i));
            for(int j : cells.keySet()) {
                cells.get(j).visited = false; 
            }
        }
        for(int i : cells.keySet()) {
            cells = traverse(cells, cells.get(i));
            for(int j : cells.keySet()) {
                cells.get(j).visited = false; 
            }
        }
        for(int i : cells.keySet()) {
            System.err.print(cells.get(i).light);
                if(cells.get(i).light == 0) ans++;
                if(i%N == 0) System.err.println();
        }

        System.out.println(ans);
    }

    public static Map<Integer, Cell> traverse(Map<Integer, Cell> cells, Cell c) {
        c.visited = true;
        for(Cell n : c.adjacentCells) {
            if(!n.visited) {
                cells.get(n.id).light = Math.max(c.light-1, cells.get(n.id).light);
            }
        }
        for(Cell n : c.adjacentCells) {
            if(!n.visited) {
                return traverse(cells, cells.get(n.id));
            }
        }
        return cells;
    }
}
