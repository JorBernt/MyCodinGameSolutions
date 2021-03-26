import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Gear {
    int x, y, radius, id;
    List<Gear> connectedGears;
    boolean visited, rotated;
    String rotation;

    public Gear(int x, int y, int radius, int id) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.id = id;
        rotation = "NOT MOVING"; 
        visited = false;
        rotated = false;
        connectedGears = new ArrayList<>();
    }

    public void addConnectedGears(Gear[] gears) {
        for(Gear g:gears) {
            if(id!=g.id) {
                int xDif = x - g.x;
                int yDif = y - g.y;
                int distanceSquared = xDif * xDif + yDif * yDif;
                boolean collision = distanceSquared == (radius + g.radius) * (radius + g.radius);
                if(collision) connectedGears.add(g);
            }
        }
       
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Gear[] gears = new Gear[N];
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            Gear gear = new Gear(x, y, r, i);
            if(i==0) gear.rotation = "CW";
            gears[i] = gear;
        }
        for(Gear g:gears) g.addConnectedGears(gears);
        bfs(gears[0]);
        for(Gear g : gears) {
            g.visited = false;
        }
        System.out.println(bfsBack(gears[0]).equals("NOT MOVING")?"NOT MOVING":gears[N-1].rotation);

       
    }

    static void bfs(Gear gear) {
        Queue<Gear> toVisit = new LinkedList<>();
        toVisit.add(gear);
        String last = "CCW";
        while(!toVisit.isEmpty()) {
            Gear polled = toVisit.poll();
            if(!polled.visited) {
                polled.visited = true;
                for(Gear c:polled.connectedGears) {
                    if(!c.rotated) {
                        c.rotation = polled.rotation.equals("CW")?"CCW":"CW";
                        if(polled.rotation.equals("NOT MOVING")) c.rotation = "NOT MOVING";
                        c.rotated = true;
                    }
                    else {
                        if(c.rotation == polled.rotation) c.rotation = "NOT MOVING";
                    }
                }
                toVisit.addAll(polled.connectedGears);
            }
        }
    }

    static String bfsBack(Gear gear) {
        Queue<Gear> toVisit = new LinkedList<>();
        toVisit.add(gear);
        while(!toVisit.isEmpty()) {
            Gear polled = toVisit.poll();
            if(!polled.visited) {
                polled.visited = true;
               
                toVisit.addAll(polled.connectedGears);
            }
            if(polled.rotation.equals("NOT MOVING")) return "NOT MOVING";
        }
        return gear.rotation;
    }
}
