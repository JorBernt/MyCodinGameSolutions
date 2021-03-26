import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Civ {
    String type;
    int x, y;

    public Civ(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        Civ[][] map = new Civ[h][w];
        Civ[][] tempmap = new Civ[h][w];
        List<Civ> civs = new ArrayList<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < h; i++) {
            String line = in.nextLine();
            for(int j = 0; j < w; j++) {
                Civ civ = new Civ(Character.toString(line.charAt(j)), j, i);
                civs.add(civ);
                map[i][j] = civ;
            }
        }

        while(n > 0) {
            for (int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    List<Civ> opponents = getOpponents(map, j, i, h, w);
                    List<Civ> winners = new ArrayList<>();
                    if(!opponents.isEmpty()) {
                        tempmap[i][j] = map[i][j];
                        for(Civ c : opponents) winners.add(winner(tempmap[i][j], c));
                        for(Civ c : winners) tempmap[i][j] = winner(tempmap[i][j], c);
                    }
                }
            }
            for (int i = 0; i < h; i++) 
                for(int j = 0; j < w; j++) 
                    map[i][j] = tempmap[i][j] == null ? map[i][j] : tempmap[i][j];
            n--;
        }
      
        for (int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(map[i][j].type);
            }
            System.out.println();
        }

    }

    public static List<Civ> getOpponents(Civ[][] map, int x, int y, int h, int w) {
        List<Civ> opponents = new ArrayList<>();
        if(x > 0) {
            if(!map[y][x-1].type.equals(map[y][x].type))
            opponents.add(map[y][x-1]);
        }
        if(x < w-1) {
            if(!map[y][x+1].type.equals(map[y][x].type))
            opponents.add(map[y][x+1]);
        }
        if(y > 0) {
            if(!map[y-1][x].type.equals(map[y][x].type))
            opponents.add(map[y-1][x]);
        }
        if(y < h-1) {
            if(!map[y+1][x].type.equals(map[y][x].type))
            opponents.add(map[y+1][x]);
        }
        return opponents;
    }

    public static Civ winner(Civ civ1, Civ civ2) {
        if(civ1 != null && civ2 != null) {
            if(civ1.type.equals("R")) { 
                    if(civ2.type.equals("L") || civ2.type.equals("C")) return civ1;
                   
                    else return civ2;
            }
            if(civ1.type.equals("C")) { 
                    if(civ2.type.equals("P") || civ2.type.equals("L")) return civ1;
                  
                    else return civ2;
            }
            if(civ1.type.equals("P")) { 
                    if(civ2.type.equals("R") || civ2.type.equals("S")) return civ1;
                    else return civ2;
                    
            }
            if(civ1.type.equals("L")) { 
                    if(civ2.type.equals("S") || civ2.type.equals("P")) return civ1;
                    else return civ2;
            }
            if(civ1.type.equals("S")) { 
                    if(civ2.type.equals("C") || civ2.type.equals("R")) return civ1;
                    else return civ2;
            }
        }
        return civ1;
    }
}
