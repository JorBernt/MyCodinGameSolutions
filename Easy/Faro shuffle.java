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
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String[] input = in.nextLine().split(" ");
        List<String> deck = new ArrayList<>();
        for(String s : input) deck.add(s);
        System.out.println(String.join(" ", rec(deck, n)));
    }

    static List<String> rec(List<String> deck, int n) {
        int mid = (int)Math.ceil(deck.size()/2d);
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < mid; i++) {
            ans.add(deck.get(i));
            if(i+mid < deck.size())
            ans.add(deck.get(i+mid));
        }
        return n == 1 ? ans : rec(ans, n-1);
    }
}
