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
        int[][] map = new int[3][3];
        for (int i = 0; i < 3; i++) {
            char[] l = in.nextLine().toCharArray();
            for(int j = 0; j < 3; j++) {
                if(l[j] == 'O') map[i][j] = 1;
                else if (l[j] == '.') map[i][j] = 0;
                else if (l[j] == 'X') map[i][j] = -1;
            }
        }
        int[] row = new int[3];
        int[] col = new int[3];
        int dia1 = 0;
        int dia2 = 0;

        for (int i = 0; i < 3; i++) {
            dia1 += map[i][i];
            dia2 += map[2-i][i];
           for(int j = 0; j < 3; j++) {
               row[i] += map[i][j];
               col[i] += map[j][i]; 
            }
        }
        for(int i = 0; i < 3; i++) {
            if(row[i] == 2) {
                win("row", i, map);
                return;
            }
            else if(col[i] == 2){
                win("col", i, map);
                return;
            }
        }
        if(dia1 == 2) {
            win("dia1", 0, map);
            return;
        }
        else if(dia2 == 2){
            win("dia2", 0, map);
            return;
        } 
        System.out.println("false");
    }

    static void win(String type, int line, int[][] map) {
        for(int i = 0; i < 3; i++) {
            if(type.equals("row")) 
                map[line][i] = 1;
            else if(type.equals("col")) 
                map[i][line] = 1;
            else if(type.equals("dia1")) 
                map[i][i] = 1;
            else if(type.equals("dia2")) 
                map[2-i][i] = 1;
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(map[i][j] == 1) System.out.print("O");
                else if(map[i][j] == 0) System.out.print(".");
                else if(map[i][j] == -1) System.out.print("X");
            }
            if(i < 2) System.out.println();
        }
    }
}
