import java.util.*;
import java.io.*;
import java.math.*;

class Player {
    int nr;
    String sign;
    List<Integer> opponents;

    public Player(int nr, String sign) {
        this.nr = nr;
        this.sign = sign;
        opponents = new ArrayList<>();
    }

    public void addOpponent(int opponent) { opponents.add(opponent);}
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int NUMPLAYER = in.nextInt();
            String SIGNPLAYER = in.next();
            Player player = new Player(NUMPLAYER, SIGNPLAYER);
            players.add(player);
        }
       
        List<Player> winners = new ArrayList<>();
        while(players.size() > 1) {
            for(int i = 0; i < players.size()-1; i+=2) {
                if(players.get(i) != null && players.get(i+1) != null) {
                    int win = winner(players.get(i), players.get(i+1));
                    if(win == players.get(i).nr) winners.add(players.get(i));
                    else winners.add(players.get(i+1));
                }
            }
            players.clear();
            players.addAll(winners);
            winners.clear();
        }
        System.out.println(players.get(0).nr);
        StringBuilder sb = new StringBuilder();
        for(int i:players.get(0).opponents) sb.append(i + " ");
        sb.delete(sb.length()-1, sb.length());
        System.out.println(sb.toString());
    }

    public static int winner(Player player1, Player player2) {
        if(player1 != null && player2 != null) {
            player1.addOpponent(player2.nr);
            player2.addOpponent(player1.nr);
            if(player1.sign.equals("R")) { 
                    if(player2.sign.equals("L") || player2.sign.equals("C")) return player1.nr;
                    else if(player2.sign.equals("R")) {
                        if(player1.nr < player2.nr) return player1.nr;
                        else return player2.nr;
                    }
                    else return player2.nr;
            }
            if(player1.sign.equals("C")) { 
                    if(player2.sign.equals("P") || player2.sign.equals("L")) return player1.nr;
                    else if(player2.sign.equals("C")) {
                        if(player1.nr < player2.nr) return player1.nr;
                        else return player2.nr;
                    }
                    else return player2.nr;
            }
            if(player1.sign.equals("P")) { 
                    if(player2.sign.equals("R") || player2.sign.equals("S")) return player1.nr;
                    else if(player2.sign.equals("P")) {
                        if(player1.nr < player2.nr) return player1.nr;
                        else return player2.nr;
                    }
                    else return player2.nr;
                    
            }
            if(player1.sign.equals("L")) { 
                    if(player2.sign.equals("S") || player2.sign.equals("P")) return player1.nr;
                    else if(player2.sign.equals("L")) {
                        if(player1.nr < player2.nr) return player1.nr;
                        else return player2.nr;
                    }
                    else return player2.nr;
            }
            if(player1.sign.equals("S")) { 
                    if(player2.sign.equals("C") || player2.sign.equals("R")) return player1.nr;
                    else if(player2.sign.equals("S")) {
                        if(player1.nr < player2.nr) return player1.nr;
                        else return player2.nr;
                    }
                    else return player2.nr;
            }
        }
        return 0;
    }
}
