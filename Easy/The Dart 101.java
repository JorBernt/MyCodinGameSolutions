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
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Map<String, Integer> players = new HashMap<>();
        List<String> playerName = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String PLAYER = in.nextLine();
            players.put(PLAYER, i);
            playerName.add(PLAYER);
        }
        int bestRound = 10000;
        String bestPlayer = "";
        for (int i = 0; i < N; i++) {
            String SHOOTS = in.nextLine();
            String[] shots = SHOOTS.split(" ");
            int sumRound = 0;
            int sumTotal = 0;
            int rounds = 0;
            int k = Math.min(3, shots.length);
            while(rounds < shots.length) {
                String x = "";
                int xcount = 0;
                for(int j = rounds; j < k; j++) {
                    if(!shots[j].equals("X") && shots[j].length() < 3)
                        sumRound += Integer.parseInt(shots[j]);
                    else if(shots[j].length() >= 3) {
                        String[] t = shots[j].split("\\*");
                        sumRound += Integer.parseInt(t[0])*Integer.parseInt(t[1]);
                    } 
                    x += (shots[j].equals("X") ? "X":"O");
                    if(shots[j].equals("X")) {
                        sumRound = Math.max(sumRound-20, 0);
                        xcount++;
                        if(xcount > 0) {
                            if(shots[j-1].equals("X")) {
                                sumRound = Math.max(sumRound-20, 0);
                                xcount++;
                            }
                        }
                        if(xcount == 3) {
                            sumRound = 0;
                        }
                    }
                    
                    rounds++;
                    if(sumRound > 101) {
                    sumRound = sumTotal;
                    break;
                }
                    
                }
                System.err.println(sumRound +" "+playerName.get(i)); 
                System.err.println(x);
                

                
                if(sumRound < 101) sumTotal = sumRound;
                else if(sumRound == 101) {
                    if(rounds < bestRound) {
                        bestRound = rounds;
                        bestPlayer = playerName.get(i);
                    }
                    for(String s : players.keySet())
                        if(players.get(s) == i) {
                            System.err.println(s +" " + rounds);
                            players.put(s, rounds);
                            sumTotal = 101;
                        }
                }
                if(rounds+3 <= shots.length) k = rounds + 3;
                else k = shots.length;
                x = "";
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.print(bestPlayer);
    }
}
