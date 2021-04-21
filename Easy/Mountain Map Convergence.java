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
        int m = in.nextInt(); // the number of mountains
        int[] heights = new int[m];
        StringBuilder sb = new StringBuilder();
        int currentLevel = 0;
        int h = -15, l = 0;
        for (int i = 0; i < m; i++) {
            int height = in.nextInt();
            h = Math.max(height, h);
            l = Math.min(height, l);
            if(currentLevel < height-1) {
                while(currentLevel < height-1) {
                    sb.append("/");
                    currentLevel++;
                }
            }
            else if(currentLevel > height-1) {
                while(currentLevel > height-1) {
                    sb.append("\\");
                    currentLevel--;
                }
            }
            if(currentLevel == height-1) {
                sb.append("/\\");
            }
        }
        if(currentLevel > 0) sb.append(("\\").repeat(currentLevel));
        else if(currentLevel < 0) sb.append(("/").repeat(-1*currentLevel));
        char[][] map = new char[30][sb.length()];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = ' ';
            }
        }
        int mid = 15;
        for(int i = mid, j = 0; j < sb.length(); j++) {
            char c = sb.charAt(j);
            if(j > 0) {
                if(c == '/' && sb.charAt(j-1) != '\\') i--;
                else if(c == '\\' && sb.charAt(j-1) != '/') i++;
            }
            map[i][j] = c;
        }
        for(int i = 0; i < map.length; i++) {
            StringBuilder out = new StringBuilder();
            int last = 0;
            boolean b = false;
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] != ' ') {
                    last = j+1;
                    b=true;
                }
                out.append(map[i][j]);
            }
            if(b) {
                out.setLength(last);
                System.out.print(out+(i < map.length-1?"\n":""));
            }
        }
    }
}
