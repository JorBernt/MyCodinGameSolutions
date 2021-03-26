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
        StringBuilder sb = new StringBuilder();
        if(n == 0) {
            sb.append("DECODE FAIL AT INDEX 0");
        }
        else {
        Map<String, Character> map = new HashMap<>();
        int l = 0;
        for (int i = 0; i < n; i++) {
            String b = in.next();
            int c = in.nextInt();
            map.put(b, (char)c);
            if(b.length() > l) l = b.length(); 
        }
        String s = in.next();
        outerloop:
        for(int i = 0; i < s.length();) {
            for(int j = l; j > 0; j--) {
                if(i+j <= s.length()) {
                String k = s.substring(i, i+j);
                if(map.containsKey(k)) {
                    sb.append(map.get(k));
                    i += k.length();
                    break;
                }
                else if( j == 1 && !map.containsKey(k)) {
                    sb.delete(0, sb.length());
                    sb.append("DECODE FAIL AT INDEX "+i);
                    break outerloop;
                }
                }
            }
        }
    }
        System.out.println(sb.toString());
    }
}
