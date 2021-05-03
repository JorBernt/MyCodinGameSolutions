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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            sb.append(line+"&&");
        }
        char[] ca = sb.toString().toCharArray();
        Map<String, String> choices = new HashMap<>();
        int parStart = 0;
        int count = 0;
        for(int i = 0; i < ca.length; i++) {
            if(ca[i] == '(') parStart = i;
            else if(ca[i] == ')') {
                String s = sb.substring(parStart, i);
                if(ca[i-1] == '|') s +="**";
                String[] c = s.replaceAll("[()]", "").split("\\|");
                choices.put(sb.substring(parStart, i+1)+count, c[count%c.length].equals("")?"":c[count%c.length]);
                count++;
            }
        }
        int start = 0, end = 0;
        count = 0;
        w:while(true) {
            out:for(int i = 0; i < sb.length(); i++) {
                if(i == sb.length()-1) break w;
                if(sb.charAt(i) == '(') {
                    for(int j = i+1; j < sb.length(); j++) {
                        if(sb.charAt(j) == ')') {
                            sb.replace(i, j+1, choices.get(sb.substring(i, j+1)+count));
                            count++;
                            break out;
                        }
                    }
                }
            }
        }
        String s = sb.toString();
        System.out.println(s.replace("&&", "\n").replace("**",""));
    }
}
