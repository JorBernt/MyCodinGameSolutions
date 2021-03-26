import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Bender {
    int x, y;
    boolean breaker, inverted, reversed;
    Map<Integer, String> priorities;

    public Bender() {
        x = 0;
        y = 0;
        breaker = false;
        inverted = false;
        reversed = false;
        priorities = new HashMap<>();
        priorities.put(0, "SOUTH");
        priorities.put(1, "EAST");
        priorities.put(2, "NORTH");
        priorities.put(3, "WEST");
    }

    public void move(int dir) {
        switch(priorities.get(dir)) {
            case "NORTH":y--;break;
            case "WEST":x--;break;
            case "EAST":x++;break;
            case "SOUTH":y++;break;
        }
    }

    public void reverse() {
        if(!reversed) {
            priorities.put(0, "WEST");
            priorities.put(1, "NORTH");
            priorities.put(2, "EAST");
            priorities.put(3, "SOUTH");
            reversed = true;
        }
        else if(reversed) {
            priorities.put(0, "SOUTH");
            priorities.put(1, "EAST");
            priorities.put(2, "NORTH");
            priorities.put(3, "WEST");
            reversed = false;
        }
    }
}

class Teleporter {
    int x,y, telX,telY;
    public Teleporter(int x, int y) {
        this.x= x;
        this.y= y;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int W = in.nextInt();
        char[][] map = new char[H][W];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Bender bender = new Bender();
        int curdir = 0;
        List<Teleporter> teleporters = new ArrayList<>();
        boolean invert = false;
        for (int i = 0; i < H; i++) {
            String row = in.nextLine();
            for(int j = 0; j < W;j++) {
                char c = row.charAt(j);
                map[i][j] = c;
                if(c=='@') {
                    bender.x = j;
                    bender.y = i;
                }
                if(c=='T') {
                    Teleporter teleporter = new Teleporter(j, i);
                    if(!teleporters.isEmpty()) {
                         teleporters.get(0).telX = j;
                         teleporters.get(0).telY = i;
                         teleporter.telX = teleporters.get(0).x;
                         teleporter.telY = teleporters.get(0).y;
                    }
                    teleporters.add(teleporter);
                }
            }
        }
        Map<String, int[]> pathHistory = new HashMap<>();
        List<String> ans = new ArrayList<>();
        int brokenWalls = 0;
        out:while(true) {
                if((vision(bender, map).get(bender.priorities.get(curdir))!='#' && vision(bender, map).get(bender.priorities.get(curdir))!='X') || (vision(bender, map).get(bender.priorities.get(curdir))=='X' && bender.breaker)) {
                    bender.move(curdir);
                    String point = bender.x+"x"+bender.y+"y";
                    int[] log = new int[4];
                    log[0] = curdir;
                    log[1] = bender.breaker?1:0;
                    log[2] = brokenWalls;
                    log[3] = bender.inverted?1:0;
                    if(pathHistory.containsKey(point)) {
                        int p = 0;
                        for(int i = 0; i < 4; i++) {
                            if(pathHistory.get(point)[i] == log[i]) p++;
                        }
                        if(p==4) {
                            System.out.print("LOOP");
                            return;
                        }
                    }
                    else {
                        pathHistory.put(point, log);
                    }
                    
                    ans.add(bender.priorities.get(curdir));
                }
                else {
                    if(invert){
                        bender.reverse();
                        invert = false;
                    }
                    for(int i = 0; i < 4; i++) {
                        if((vision(bender, map).get(bender.priorities.get(i))!='#' && vision(bender, map).get(bender.priorities.get(i))!='X') || (vision(bender, map).get(bender.priorities.get(i))=='X' && bender.breaker)) {
                            curdir = i;
                            continue out;
                        }
                    }
                }
            switch(map[bender.y][bender.x]) {
                case '$':break out;
                case 'X':map[bender.y][bender.x] = ' ';brokenWalls++;break;
                case 'I':invert = true;break;
                case 'B':bender.breaker = bender.breaker?false:true;break;
                case 'T':teleport(bender, teleporters);break;
                case 'N':curdir = bender.reversed?1:2;break;
                case 'E':curdir = bender.reversed?2:1;break;
                case 'S':curdir = bender.reversed?3:0;break;
                case 'W':curdir = bender.reversed?0:3;break;
            }
        }
        for(String s : ans) {
            System.out.println(s);
        }
    }

    static Map<String, Character> vision(Bender bender, char[][] map) {
        Map<String, Character> v = new HashMap<>();
        v.put("NORTH", '#');
        v.put("EAST", '#');
        v.put("WEST", '#');
        v.put("SOUTH", '#');
        if(bender.x>0) v.put("WEST", map[bender.y][bender.x-1]);
        if(bender.x<map.length) v.put("EAST", map[bender.y][bender.x+1]);
        if(bender.y>0) v.put("NORTH", map[bender.y-1][bender.x]);
        if(bender.y<map.length) v.put("SOUTH", map[bender.y+1][bender.x]);
        return v;
        
    }

    static void teleport(Bender bender, List<Teleporter> teleporters) {
        for(Teleporter t: teleporters) {
            if(t.x == bender.x && t.y == bender.y) {
                bender.x = t.telX;
                bender.y = t.telY;
                break;
            }
        }
    }
}
