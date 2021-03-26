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
        int n = in.nextInt();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String aword = in.next();
            words.add(aword);
        }
        int h = in.nextInt();
        int w = in.nextInt();
        char[][] map = new char[h][w];
        boolean[][] bmap = new boolean[h][w];
        for (int y = 0; y < h; y++) {
            String line = in.next();
            for(int x = 0;  x < w; x++) 
                map[y][x] = line.charAt(x);
        }
        for(String s : words) 
            for(int y = 0; y < h; y++) 
                for(int x = 0; x < w; x++) 
                    if(map[y][x] == s.charAt(0)) 
                        if(checkAdjacent(map, bmap, y, x, h, w, s)) 
                            bmap[y][x] = true;

        for(int y = 0; y < h; y++) 
            for(int x = 0; x < w; x++) 
                if(!bmap[y][x]) System.out.print(map[y][x]);
    }

    static boolean checkAdjacent(char[][] map,boolean[][] bmap, int y, int x, int h, int w, String word) {
        List<int[]> adjacent = new ArrayList<>();
        if(x<w-1) {
            adjacent.add(new int[]{y,x+1});
            if(y<h-1)
                adjacent.add(new int[]{y+1,x+1});
            if(y>0)
                adjacent.add(new int[]{y-1,x+1});
        }
        if(x>0) {
            adjacent.add(new int[]{y,x-1});
            if(y<h-1)
                adjacent.add(new int[]{y+1,x-1});
            if(y>0)
                adjacent.add(new int[]{y-1,x-1});
        }
        if(y >= 0)
            adjacent.add(new int[]{y-1,x});
        if(y < h-1)
            adjacent.add(new int[]{y+1,x});

        for(int[] a : adjacent) 
            if(scan(map, bmap, y, x, a[0]-y, a[1]-x, h, w, 1, word, new ArrayList<int[]>())) 
                return true;
        return false;
    }

    static boolean scan(char[][] map,boolean[][] bmap, int y, int x, int dirY, int dirX, int h, int w, int pointer, String word, List<int[]> log) {
        if(y+dirY == h || y+dirY < 0 || x+dirX == w || x+dirX < 0) return false;
        if(map[y+dirY][x+dirX] == word.charAt(pointer)) {
            log.add(new int[]{y+dirY, x+dirX});
            if(pointer == word.length()-1) {
                for(int[] l : log) 
                    bmap[l[0]][l[1]] = true;
                
                return true;
            }
            return scan(map, bmap, y+dirY, x+dirX, dirY, dirX, h, w, pointer+1, word,log);
        }
        return false;
    }
}
