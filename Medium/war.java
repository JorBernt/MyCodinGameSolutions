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
        int n = in.nextInt(); // the number of cards for player 1
        Deque<Integer> deck1 = new LinkedList<>();
        Deque<Integer> deck2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            deck1.addLast(getValue(cardp1));
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            deck2.addLast(getValue(cardp2));
        }
        int rounds = 0;
        Deque<Integer> warPile1 = new LinkedList<>();
        Deque<Integer> warPile2 = new LinkedList<>();
        while(deck1.size() > 0 && deck2.size() > 0) {
            int card1 = deck1.poll();
            int card2 = deck2.poll();
            if(card1 > card2) {
                rounds++;             
                if(warPile1.size()>0){
                    while(warPile1.size()>0) {
                        deck1.add(warPile1.poll());
                    }
                    deck1.add(card1);
                    while(warPile2.size()>0) {
                        deck1.add(warPile2.poll());
                    }
                    deck1.add(card2);
                }
                else {
                    deck1.add(card1);
                    deck1.add(card2);
                }
            }
            if(card2 > card1) {
                rounds++;
                 if(warPile1.size()>0){
                    while(warPile1.size()>0) {
                        deck2.add(warPile1.poll());
                    }
                    deck2.add(card1);
                    while(warPile2.size()>0) {
                        deck2.add(warPile2.poll());
                    }
                    deck2.add(card2);
                }
                else {
                    deck2.add(card1);
                    deck2.add(card2);
                }
            }
            if(card1 == card2) {
               if(deck1.size() > 2 && deck2.size() > 2) {
                    warPile1.add(card1);
                    warPile2.add(card2);
                   for(int i = 0; i < 3; i++) {
                       warPile1.add(deck1.poll());
                       warPile2.add(deck2.poll());
                   }
               }
               else{
                   System.out.println("PAT");
                   return;
               }
            }
        }
        System.out.println((deck1.size()>0 ? "1 ": "2 ")+rounds);
    }

    public static int getValue(String card) {
        if(card.charAt(0) == 'J') return 11;
        else if(card.charAt(0) == 'Q') return 12;
        else if(card.charAt(0) == 'K') return 13;
        else if(card.charAt(0) == 'A') return 14;
        else return Integer.parseInt(card.length() == 3 ? card.substring(0,2):card.substring(0,1));
    }

}
