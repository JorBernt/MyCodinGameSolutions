import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        char[][] map = new char[height][width];
        for (int i = 0; i < height; i++) {
            char[] line = in.next().toCharArray();
            for(int j = 0; j < width; j++) {
                map[i][j] = line[j];
            }
            
        }
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(map[i][j] == '0') System.out.print(pikaSense(map, j, i));
                else System.out.print("#");
                
            }
            System.out.println();
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

        }
    }

    static int pikaSense(char[][] map, int w, int h) {
        int sum = 0;
        if(w > 0) 
            if(map[h][w-1] == '0') sum++;
        if(w < map[h].length-1) 
            if(map[h][w+1] == '0') sum++;
        if(h > 0)
            if(map[h-1][w] == '0') sum++;
        if(h < map.length-1)
            if(map[h+1][w] == '0') sum++;
        return sum;         
    }
}
