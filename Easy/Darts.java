import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    String name;
    int nr, score;

    public Player(String name, int nr) {
        this.name = name;
        this.nr = nr;
        this.score = 0;
    }

    public void updateScore(int score) {
        this.score += score;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int SIZE = in.nextInt();
        int N = in.nextInt();

        if (in.hasNextLine()) {
            in.nextLine();
        }

        List<Player> players = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String name = in.nextLine();
            Player player = new Player(name, i);
            players.add(player);
        }
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String throwName = in.next();
            int throwX = in.nextInt();
            int throwY = in.nextInt();

            for(Player p : players) 
                if(p.name.equals(throwName)) p.updateScore(calcScore(throwX, throwY, SIZE/2));
        }

        int max = 0, minnr = N;
        while(players.size() > 0) {
            for(Player p : players) max = Math.max(p.score, max);
            for(Player p : players) if(p.score == max) minnr = Math.min(p.nr, minnr);
            for(Player p : players) 
                if(p.score == max && p.nr == minnr) {
                    System.out.println(p.name + " " + p.score);
                    players.remove(p);
                    max = 0;
                    minnr = N;
                    break;
                }
        }
    }

    public static int calcScore(int x,int y, int R) {
    if (Math.abs(x) + Math.abs(y) <= R)
        return 15;
    if (Math.hypot(x, y) <= R)
        return 10;
    if (-R <= Math.min(x, y) && Math.max(x, y) <= R)
        return 5; 
    return 0; 
    }    
}
