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
        int[] king = new int[2];
        int[] enemy = new int[2];
        char enemyType = 'a';
        for (int i = 0; i < 8; i++) {
            char[] chessRow = in.nextLine().replace(" ", "").toCharArray();
            for(int j = 0; j < chessRow.length; j++) {
                if(chessRow[j] == 'K') {
                    king[0] = i;
                    king[1] = j;
                }
                if(chessRow[j] != 'K' && chessRow[j] != '_') {
                    enemy[0] = i;
                    enemy[1] = j;
                    enemyType = chessRow[j];
                }
            }
        }
        System.out.println(checkMap(enemyType, enemy, king)? "Check":"No Check");
    }

    static boolean checkMap(char type, int[] enemy, int[] king) {
        switch(type) {
            case 'B': {
               if(Math.abs(enemy[0]-king[0]) == Math.abs(enemy[1]-king[1])) return true;
               else return false;
            }
            case 'N': {
                if((Math.abs(enemy[0]-king[0]) == 1 && Math.abs(enemy[1]-king[1]) == 2) || 
                (Math.abs(enemy[0]-king[0]) == 2 && Math.abs(enemy[1]-king[1]) == 1))
                    return true;
                else return false;
            }
            case 'R': {
                if((enemy[0] == king[0] && enemy[1] != king[1]) || 
                (enemy[1] == king[1] && enemy[0] != king[0]))
                    return true;
                else return false;
            }
            case 'Q': {
                if(checkMap('B', enemy, king) || checkMap('R', enemy, king)) return true;
                else return false;
            }
            default: return false;
        }
    }
}
