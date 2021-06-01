import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        String d = in.next();
        Map<String, int[]> dirs = new HashMap<>();
        dirs.put("N", new int[]{-1,0});
        dirs.put("E", new int[]{0,1});
        dirs.put("S", new int[]{1,0});
        dirs.put("W", new int[]{0,-1});
        int T = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        boolean[][] map = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String C = in.nextLine();
            for(int j = 0; j < C.length(); j++){
                map[i][j] = C.charAt(j)=='.';
            }
        }
        
        for(int i=0; i < T; i++) {
            boolean b = map[y][x];
            switch(d){
                case "N":d=b?"W":"E";break;
                case "E":d=b?"N":"S";break;
                case "S":d=b?"E":"W";break;
                case "W":d=b?"S":"N";break;
            }
            map[y][x] = !b;
            y+=dirs.get(d)[0];
            x+=dirs.get(d)[1];
        
        }
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                System.out.print(map[i][j]?".":"#");
            }
            System.out.println();
        }
        
    }
}
