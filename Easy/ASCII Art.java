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
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();
        Map<Character, List<String>> letters = new HashMap<>();
        char[] chars = new char[27];
        int u = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            chars[u] = c;
            u++;
        }
        chars[26] = '?';

        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            int j = 0;
            for (int y = 0; y < chars.length; y++) {
                char c = chars[y];
                String s = ROW.substring(j, j+L);
                
                
                if(letters.containsKey(c)) {
                    letters.get(c).add(s);
                }
                else {
                    List<String> list = new ArrayList<String>();
                    list.add(s);
                    letters.put(c, list);
                }
                j += L;
            }
        }
        

        StringBuilder out = new StringBuilder();
        for(int i = 0; i < H; i++) {
            for(char c : T.toCharArray()) {
                if(letters.containsKey(Character.toLowerCase(c)))
                 out.append(letters.get(Character.toLowerCase(c)).get(i));
                else
                out.append(letters.get('?').get(i));
            }
            if(i < H) {
                    out.append("\n");
                }
        } 

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(out.toString());
    }
}
