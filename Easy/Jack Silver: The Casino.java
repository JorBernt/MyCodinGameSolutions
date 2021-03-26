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
        int ROUNDS = in.nextInt();
        int CASH = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < ROUNDS; i++) {
            String PLAY = in.nextLine();
            String[] play = PLAY.split(" ");
            if(PLAY.contains("PLAIN")) 
                CASH += bets(play[1], Integer.parseInt(play[0]), Integer.parseInt(play[2]), CASH);
            else 
                CASH += bets(play[1], Integer.parseInt(play[0]), -1, CASH);
        }
        System.out.println(CASH);
    }

    static int bets(String type, int result, int guess, int cash) {
        int bet = (int)Math.ceil((double)cash/4);
        if(type.equals("PLAIN"))
                if(result == guess) return bet*35 ;
        if(type.equals("ODD")) {
                if(result%2 != 0 && result != 2) return bet;
        }
        if(type.equals("EVEN")) {
                if(result == 0) return bet*-1;
                if(result%2 == 0) return bet;    
        }    
        
        return bet*-1;
    }
}
