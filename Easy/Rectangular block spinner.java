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
        int size = in.nextInt();
        int angle = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] map = new char[size][size];
        for (int i = 0; i < size; i++) {
            char[] line = in.nextLine().replace(" ", "").toCharArray();
            map[i] = line;
        }

        int cstart = 0, rstart = 0;
        boolean col = false, row = false;
        int rotation = (angle/90)%4;
        switch(rotation) {
            case 0:{
                cstart = 0;
                rstart = size-1;
                col = true;
                row = true;
                break;
            }
            case 1: {
                cstart = size-1;
                rstart = size-1;
                col = true;
                row = false;
                break;
            }
            case 2: {
                cstart = size-1;
                rstart = 0;
                col = false;
                row = true;
                break;
            }
            case 3: {
                cstart = 0;
                rstart = 0;
                col = false;
                row = true;
                break;
            }
        }
        System.err.println(rotation);
        int m = size-1;
        List<String> list = new ArrayList<>();
        while(list.size()!=size*2-1) {
            StringBuilder sb = new StringBuilder();
            sb.append((" ").repeat(m));
            for(int i = cstart, j = rstart; i < map.length && j < map[0].length && i >= 0 && j >= 0; i+=col?1:-1, j+=row?1:-1) {
                sb.append(map[i][j]);
            }

            if(rotation == 0) {
                if(cstart == 0 && rstart > 0) {
                    rstart--;
                    m--;
                }
                else if(rstart == 0) {
                    cstart++;
                    m++;
                }
            }
            else if(rotation == 1) {
                if(cstart > 0) {
                    cstart--;
                    m--;
                }
                else if(cstart == 0) {
                    rstart--;
                    m++;
                }
            }
            else if(rotation == 2) {
                if(cstart > 0) {
                    cstart--;
                    m--;
                }
                else if(cstart == 0) {
                    rstart--;
                    m++;
                }
            }else if(rotation == 3) {
                if(cstart < size-1) {
                    cstart++;
                    m--;
                }
                else if(cstart == size-1) {
                    rstart++;
                    m++;
                }
            }
            list.add(sb.toString());
        }
        for(String s : list) {
            int c = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != ' ')
                System.out.print(s.charAt(i)+(i < s.length() -1 ? " ":""));
                else {
                    System.out.print(" ");
                    c++;
                }
            }
            System.out.print((" ").repeat(c));
            System.out.println();
        }


    }
}
