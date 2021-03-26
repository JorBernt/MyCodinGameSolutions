import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    String name;
    int boardPosition, jailTries, doublesInRow;
    boolean inJail;

    public Player(String name, int boardPosition) {
        this.name = name;
        this.boardPosition = boardPosition;
        inJail = false;
        jailTries = 0;
        doublesInRow = 0;
    }
    public void goToJail() {
        boardPosition = 10;
        doublesInRow = 0;
        inJail = true;
    }

    public void move(int n) {
        boardPosition +=n;
        if(boardPosition >= 40) boardPosition %=40;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        Player[] players = new Player[P];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < P; i++) {
            String[] p = in.nextLine().split(" ");
            Player player = new Player(p[0], Integer.parseInt(p[1]));
            players[i] = player;
        }
        int D = in.nextInt();
        int[][] diceRolls = new int[D][2];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < D; i++) {
            String[] dice = in.nextLine().split(" ");
            diceRolls[i][0] = Integer.parseInt(dice[0]);
            diceRolls[i][1] = Integer.parseInt(dice[1]);
            
        }
        for (int i = 0; i < 40; i++) {
            String boardline = in.nextLine();
        }

        int currentPlayer = 0;
        for(int i = 0; i < D; i++) {
            if(currentPlayer >= P) currentPlayer = 0;
            int n = diceRolls[i][0] + diceRolls[i][1];
            boolean d = false;
            if(!players[currentPlayer].inJail) {
                if(diceRolls[i][0] == diceRolls[i][1]) {
                    players[currentPlayer].doublesInRow++;
                    if(players[currentPlayer].doublesInRow == 3) {
                        players[currentPlayer].goToJail();
                        currentPlayer++;
                        continue;
                    }
                    d = true;
                }
                else {
                    players[currentPlayer].doublesInRow = 0;
                }
                    players[currentPlayer].move(n);
                    if(players[currentPlayer].boardPosition == 30) {
                        players[currentPlayer].goToJail();
                        d=false;
                    }
                    if(!d)currentPlayer++;
            }
            else {
                boolean move = false;
                if(diceRolls[i][0] == diceRolls[i][1]) { 
                    move = true;
                }
                else {
                    players[currentPlayer].jailTries++;
                    if(players[currentPlayer].jailTries == 3) {
                        players[currentPlayer].jailTries = 0;
                        move = true;
                    }
                }
                if(move) {
                    players[currentPlayer].move(n);
                    players[currentPlayer].inJail = false;
                }
                currentPlayer++;
            }
        }

        for(Player p : players) {
            System.out.println(p.name + " " + p.boardPosition);
        }
    }
}
