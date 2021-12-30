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
        int wallyWidth = in.nextInt();
        int wallyHeight = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] wally = new char[wallyHeight][wallyWidth];
        for (int i = 0; i < wallyHeight; i++) {
            String wallyRow = in.nextLine();
            wally[i] = wallyRow.toCharArray();
        }
        int pictureWidth = in.nextInt();
        int pictureHeight = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] map = new char[pictureHeight][pictureWidth];
        for (int i = 0; i < pictureHeight; i++) {
            String pictureRow = in.nextLine();
            map[i] = pictureRow.toCharArray();
        }

        for(int i = 0; i <= pictureHeight - wallyHeight; i++) {
            for(int j = 0; j <= pictureWidth - wallyWidth; j++) {
                if(valid(i, j, map, wally)) {
                    System.out.println(j + " " + i);
                    return;
                }
            }
        }
    }

    static boolean valid(int y, int x, char[][] map, char[][] wally) {
        for(int i = y, a = 0; a < wally.length; i++, a++) {
            for(int j = x, b = 0; b < wally[0].length; j++, b++) {
                if(wally[a][b] != ' ' && wally[a][b] != map[i][j]) return false;
            }
        }
        return true;
    }
}
