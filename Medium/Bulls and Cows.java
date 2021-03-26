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
        Map<String, String> guesses = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String guess = in.next();
            int bulls = in.nextInt();
            int cows = in.nextInt();
            guesses.put(guess, bulls + " " + cows);
        }

        out:for(int i = 1000; i < 10000; i++) {
            String num = Integer.toString(i);
            if(i < 10) {
                num="000"+i;
            }
            else if(i < 100) {
                num="00"+i;
            }
            else if(i < 1000) {
                num="0"+i;
            }
            int g = 0;
            for(String s : guesses.keySet()) {
                if(!bc(num, s).equals(guesses.get(s))){
                    continue out;
                }
                else {
                    g++;
                    if(g==guesses.size()) {
                        System.out.println(num);
                        return;
                    }
                }
            }
        }
    }

    static String bc(String ans, String guess) {
        int cows = 0, bulls = 0;
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for(char c:guess.toCharArray()) list.add(Character.getNumericValue(c));
        for(int i = 0; i < 4; i++) {
            int a = Character.getNumericValue(ans.charAt(i));
            int b = Character.getNumericValue(guess.charAt(i));
            if( a == b) {
                list.remove(Integer.valueOf(b));
                bulls++;
                set.add(i);
            }
        }
        for(int i : list) {
            for(int j = 0; j < 4; j++) {
                int n = Character.getNumericValue(ans.charAt(j));
                if(i == n && !set.contains(j)) {
                    cows++;
                    set.add(j);
                }
            }
        }
        return bulls+" "+cows; 
    }
    
}
