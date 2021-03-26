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
        char[][] map = new char[16][16];
        for (int i = 0; i < 16; i++) {
            String line = in.nextLine();
            for(int j = 0; j < line.length(); j++) {
                map[j][i] = line.charAt(j);
            }

        }
        int molecount = 0;
        for (int i = 0; i < 16; i++) {
            for(int j = 0; j < 16; j++) {
                
                if(map[j][i] == 'o') {
                    molecount++;
                    boolean outside = false;
                    for(int q = j; q < 15;) {
                        if(map[q+1][i] != '+' && map[q+1][i] != '-' && map[q+1][i] != '|') {
                            if(map[q+1][i] == '.') outside = true;
                            q++;
                            if(q == 15) {
                                outside = true;
                            }
                        }
                        else break;
                    }
                    for(int q = j; q > 0;) {
                        if(map[q-1][i] != '+' && map[q-1][i] != '-' && map[q-1][i] != '|') {
                            if(map[q-1][i] == '.') outside = true;
                            q--;
                            if(q == 0) {
                                outside = true;
                            }
                        }
                        
                        else break;
                    }
                    for(int q = i; q < 15;) {
                        if(map[j][q+1] != '+' && map[j][q+1] != '-' && map[j][q+1] != '|') {
                            if(map[j][q+1] == '.') outside = true;
                            q++;
                            if(q == 15) {
                                outside = true;
                            }
                        }
                        else break;
                    }
                    for(int q = i; q > 0;) {
                        if(map[j][q-1] != '+' && map[j][q-1] != '-' && map[j][q-1] != '|') {
                            if(map[j][q-1] == '.') outside = true;
                            q--;
                            if(q == 0) {
                                outside = true;
                            }
                        }
                        else break;
                    }
                    if(j > 1) {
                        if(map[j-1][i] == '|' || map[j-1][i] == 'X') {
                            if(map[j-2][i] == ' ' || map[j-2][i] == 'o' )
                                outside = true;
                        }
                    }
                    if(j < 14) {
                        if(map[j+1][i] == '|' || map[j+1][i] == 'X') {
                            if(map[j+2][i] == ' ')
                                outside = true;
                        }
                    }
                    if(i > 1) {
                        if(map[j][i-1] == '-' || map[j][i-1] == 'X') {
                            if(map[j][i-2] == ' ')
                                outside = true;
                        }
                    }
                    if(i < 14) {
                        if(map[j][i+1] == '-' || map[j][i+1] == 'X') {
                            if(map[j][i+2] == ' ')
                                outside = true;
                        }
                    }
                    if(j > 0) {
                        if(map[j-1][i] == 'X') 
                            outside = true;
                    }
                    if(j < 15) {
                        if(map[j+1][i] == 'X') 
                                outside = true;
                    }
                    if(i > 0) {
                        if(map[j][i-1] == 'X') 
                                outside = true;
                    }
                    if(i < 15) {
                        if(map[j][i+1] == 'X') 
                                outside = true;
                    }
                    if(j == 0 && i == 0) {
                        outside = true;
                    }
                    if(j == 15 && i == 0) {
                        outside = true;
                    }
                    if(j == 15 && i == 0) {
                        outside = true;
                    }
                    if(j == 15 && i == 15) {
                        outside = true;
                    }
                    if(outside) {
                        molecount--;
                        map[j][i] = 'X';
                    }
                }
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(molecount);
    }
}
