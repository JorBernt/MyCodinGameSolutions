import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static Map<Character, String> map = new HashMap<>();
    static char rep = 'Z';
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String line = in.next();
            sb.append(line);
        }
        System.out.println(rec(sb.toString()));
        for(char c = 'Z'; c >= 'A'; c--) {
            if(!map.containsKey(c)) break;
            System.out.println(c + " = " + map.get(c));
        }
    }

    static String rec(String s) {
        Map<String, Integer> pairs = new TreeMap<>();
        int max = 0;
        for(int i = 0; i < s.length()-1; i++) {
            String pair = s.substring(i,i+2);
            if(pairs.containsKey(pair)) continue;
            int c = 1;
            for(int j = i+2; j < s.length()-1; j++) {
                String match = s.substring(j,j+2);
                if(pair.equals(match)) {
                    c++;
                    j++;
                }
            }
            if(c > 1) {
                pairs.put(pair, c);
                max = Math.max(max, c);
            }
        }
        if(max > 1) {
            for(int i = 0; i < s.length()-1; i++) {
                String pair = s.substring(i,i+2);
                if(pairs.containsKey(pair) && pairs.get(pair) == max) {
                    map.put(rep, pair);
                    s = s.replace(pair, ""+rep);
                    rep--;
                    return rec(s);
                }
            }
        }
        return s;
    }
}
