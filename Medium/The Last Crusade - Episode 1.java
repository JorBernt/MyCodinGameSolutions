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
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        Map<Integer, String[]> rooms = new HashMap<>();
        for(int i = 0; i <= 13; i++) {
            int top = 0, right = 1, down = 2, left = 3;
            String[] t = new String[4];
            switch(i) {
                case 0: t[top] = "BLOCK";
                        t[right] = "BLOCK";
                        t[left] = "BLOCK";
                        t[down] = "BLOCK";
                        rooms.put(i, t);
                        break; 
                case 1: t[top] = "DOWN";
                        t[right] = "DOWN";
                        t[left] = "DOWN";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 2: t[top] = "BLOCK";
                        t[right] = "LEFT";
                        t[left] = "RIGHT";
                        t[down] = "BLOCK";
                        rooms.put(i, t);
                        break;
                case 3: t[top] = "DOWN";
                        t[right] = "BLOCK";
                        t[left] = "BLOCK";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 4: t[top] = "LEFT";
                        t[right] = "DOWN";
                        t[left] = "BLOCK";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 5: t[top] = "RIGHT";
                        t[right] = "EXIT";
                        t[left] = "DOWN";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 6: t[top] = "BLOCK";
                        t[right] = "LEFT";
                        t[left] = "RIGHT";
                        t[down] = "BLOCK";
                        rooms.put(i, t);
                        break;
                case 7: t[top] = "DOWN";
                        t[right] = "DOWN";
                        t[left] = "BLOCK";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 8: t[top] = "BLOCK";
                        t[right] = "DOWN";
                        t[left] = "DOWN";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 9: t[top] = "DOWN";
                        t[right] = "BLOCK";
                        t[left] = "DOWN";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 10: t[top] = "LEFT";
                        t[right] = "BLOCK";
                        t[left] = "EXIT";
                        t[down] = "BLOCK";
                        rooms.put(i, t);
                        break;
                case 11: t[top] = "RIGHT";
                        t[right] = "EXIT";
                        t[left] = "BLOCK";
                        t[down] = "BLOCK";
                        rooms.put(i, t);
                        break;
                case 12: t[top] = "BLOCK";
                        t[right] = "DOWN";
                        t[left] = "BLOCK";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;
                case 13: t[top] = "BLOCK";
                        t[right] = "BLOCK";
                        t[left] = "DOWN";
                        t[down] = "EXIT";
                        rooms.put(i, t);
                        break;                                         

           }
        }


        int[][] map = new int[W][H];
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            String[] k = LINE.split(" ");
            for(int j = 0; j < W; j++) {
                map[j][i] = Integer.parseInt(k[j]);
                
            }
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            
            switch(convert(rooms.get(map[XI][YI])[convert(POS)])) {
                case 1 : XI++; break;
                case 2 : YI++; break;
                case 3 : XI--; break;
        }
            System.out.println(XI+" "+YI);
        }
    }

    public static int convert(String s) {
        if(s.equals("RIGHT")) return 1;
        else if(s.equals("DOWN")) return 2;
        else if(s.equals("LEFT")) return 3;
        return 0;
    }
}
