import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Map {
    Tower[][] map;
    List<Tower> towers;
    int w, h;

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        map = new Tower[h][w];
        towers = new ArrayList<>();
    }

    public void addTower(Tower tower) {
        if(tower.isTower) towers.add(tower);
        map[tower.y][tower.x] = tower;
    }

    public List<Tower> getAdjacent(Tower tower) {
        List<Tower> adj = new ArrayList<>();
        int x = tower.x, y = tower.y;
        if(x > 0) {
            adj.add(map[y][x-1]);
        }
        if(x < w-1) {
            adj.add(map[y][x+1]);
        }
        if(y > 0) {
            adj.add(map[y-1][x]);
        }
        if(y < h-1) {
            adj.add(map[y+1][x]);
        }
        return adj;
    }

    public void floodFill() {
        for(Tower tower:towers) {
            Queue<Tower> toVisit = new LinkedList<>();
            toVisit.add(tower);
            int[][] fillMap = new int[h][w];
            fillMap[tower.y][tower.x] = 1;
            while(!toVisit.isEmpty()) {
                Tower t = toVisit.poll();
                for(Tower adj : getAdjacent(t)) {
                    if(fillMap[adj.y][adj.x] == 0 && adj.type != '#') {
                        fillMap[adj.y][adj.x] = fillMap[t.y][t.x]+1;
                        if(!adj.isTower)
                        adj.fill(tower.type, fillMap[adj.y][adj.x]);
                        toVisit.add(adj);
                    }
                }
            }


                for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    System.err.print(map[i][j].type);
                }
                System.err.println();
            }
            System.err.println();
        }

        
    }


    public void printMap() {
        for(Tower[] row : map) {
            for(int i = 0; i < w; i++) {
                System.out.print(row[i].type);
            }
            System.out.println();
        }
    }


} 

class Tower {
    char type;
    int x, y;
    boolean visited, isTower;
    int step;

    public Tower(char type, int y, int x) {
        this.type = type;
        this.y = y;
        this.x = x;
        if(type == '#' || type == '.') isTower = false;
        else isTower = true;
        visited = isTower;
        step = 10000;
    }

    public void fill(char t, int step) {
        if(step < this.step) {
            type = t;
            this.step = step;
        }
        else if(step == this.step) {
            type = '+';
        }
    }

}


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Map map = new Map(W, H);

        for (int i = 0; i < H; i++) {
            String line = in.nextLine();
            for(int j = 0; j < W; j++) {
                map.addTower(new Tower(line.charAt(j), i, j));
            }
        }
        map.floodFill();
        map.printMap();
    }
} 
