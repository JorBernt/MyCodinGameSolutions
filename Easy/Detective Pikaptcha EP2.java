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
        char[][] maze = new char[height][width];
        int[][] ans = new int[height][width];
        int[] pikaPos = new int[2];
        int[] finish = new int[2];
        char dir = 'x';
        for (int i = 0; i < height; i++) {
            char[] line = in.next().toCharArray();
            for(int j = 0; j < width; j++) {
                ans[i][j] = 0;
                maze[i][j] = line[j];
                if(line[j] == '#') ans[i][j] = -1;
                if(line[j] != '0' && line[j] != '#') {
                    pikaPos[0] = i;
                    pikaPos[1] = j;
                    finish[0] = i;
                    finish[1] = j;
                    ans[i][j] = 0;
                    dir = line[j];
                    maze[i][j] = '0';
                } 
            }
        }
        String side = in.next();
        while(true) {
            dir = move(pikaPos, dir, maze, side, getSense(pikaPos, dir, maze));
            switch(dir) {
            case '>' : pikaPos[1]++; break;
            case '<' : pikaPos[1]--; break;
            case '^' : pikaPos[0]--; break;
            case 'v' : pikaPos[0]++; break;
        }
            if(dir == 'X') break;
            ans[pikaPos[0]][pikaPos[1]]++;
            if(Arrays.equals(pikaPos, finish)) break; 
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(ans[i][j] == -1) System.out.print("#");
                else System.out.print(ans[i][j]);
            }
            
            System.out.println();
        }
    }

    static Map<String, Integer> getSense(int[] pikaPos, char dir, char[][] maze) {
        Map<String, Integer> pikaSense = new HashMap<>();
        int[] ps = new int[4];
            ps[0] = (pikaPos[1] > 0 ? (maze[pikaPos[0]][pikaPos[1]-1] == '#' ? 1:0):1);
            ps[1] = (pikaPos[0] > 0 ? (maze[pikaPos[0]-1][pikaPos[1]] == '#' ? 1:0):1);
            ps[2] = (pikaPos[1] < maze[0].length-1 ? (maze[pikaPos[0]][pikaPos[1]+1] == '#' ? 1:0):1);
            ps[3] = (pikaPos[0] < maze.length-1 ? (maze[pikaPos[0]+1][pikaPos[1]] == '#' ? 1:0):1);

        switch(dir) {
            case '>' :
                pikaSense.put("front", ps[2]);
                pikaSense.put("left", ps[1]);
                pikaSense.put("right", ps[3]);
                pikaSense.put("back", ps[0]);
                break;
            case '<' :
                pikaSense.put("front", ps[0]);
                pikaSense.put("left", ps[3]);
                pikaSense.put("right", ps[1]);
                pikaSense.put("back", ps[2]);
                break;
            case '^' :
                pikaSense.put("front", ps[1]);
                pikaSense.put("left", ps[0]);
                pikaSense.put("right", ps[2]);
                pikaSense.put("back", ps[3]);
                break;
            case 'v' :
                pikaSense.put("front", ps[3]);
                pikaSense.put("left", ps[2]);
                pikaSense.put("right", ps[0]);
                pikaSense.put("back", ps[1]);
                break;
        }
        return pikaSense;
    }

    static char move(int[] pikaPos, char dir, char[][] maze, String side, Map<String, Integer> pikaSense) {
        if(pikaSense.get("front") == 1 && pikaSense.get("left") == 1 &&
        pikaSense.get("back") == 1 && pikaSense.get("right") == 1) {
            return 'X';
        }
        if(side.equals("L")) {
            if(pikaSense.get("front") == 0) {
                if(pikaSense.get("left") == 0) {
                    return turn("LEFT", dir);
                }
                else {
                    return dir;
                }
            }
            else {
                if(pikaSense.get("left") == 0) {
                    return turn("LEFT", dir);
                }
                else if(pikaSense.get("left") == 1 && pikaSense.get("right") == 1) {
                    return turn("BACK", dir);
                }
                else if(pikaSense.get("right") == 0) {
                    return turn("RIGHT", dir);
                }
            }
      

        }
        else if (side.equals("R")) {
            if(pikaSense.get("front") == 0) {
                if(pikaSense.get("right") == 0) {
                    return turn("RIGHT", dir);
                }
                else {
                    return dir;
                }
            }
            else {
                if(pikaSense.get("right") == 0) {
                    return turn("RIGHT", dir);
                }
                else if(pikaSense.get("left") == 1 && pikaSense.get("right") == 1) {
                    return turn("BACK", dir);
                }
                else if(pikaSense.get("left") == 0) {
                    return turn("LEFT", dir);
                }
            }
      

        }
        return 'X';
    }

    static char turn(String newdir, char dir) {
        if(newdir.equals("RIGHT")) {
            switch(dir) {
                case '>' : return 'v';
                case '<' : return '^';
                case '^' : return '>';
                case 'v' : return '<';
            }
        }
        else if(newdir.equals("LEFT")) {
            switch(dir) {
                case '>' : return '^';
                case '<' : return 'v';
                case '^' : return '<';
                case 'v' : return '>';
            }
        }
        else if(newdir.equals("BACK")) {
            switch(dir) {
                case '>' : return '<';
                case '<' : return '>';
                case '^' : return 'v';
                case 'v' : return '^';
            }
        }
        return 'X';
    }
}
