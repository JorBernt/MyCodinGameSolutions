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
        String TEXT = in.nextLine().toLowerCase().replaceAll("[^a-zA-Z]", "");
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String ans = "";
        int longest = 0;
        for (int i = 0; i < N; i++) {
            String word = in.nextLine();
            String message = search(word, TEXT);
            if (message != null && word.length() > longest) {
                ans = message;
                longest = Math.max(longest, word.length());
            }
        }
        System.out.println(ans);
    }

    static String search(String WORD, String text) {
        char[] tc = text.toCharArray();
        char[] word = WORD.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int startIndex = 0;
        out:for (int i = 0; i < word.length; i++) {
            for (int j = index; j < tc.length; j++) {
                if (i == 0 && word[i] == tc[j]) {
                    index = j+1;
                    startIndex = j;
                    continue out;
                }
                if (word[i] == tc[j]) {
                    StringBuilder t = new StringBuilder();
                    int spacing = j - index+1;
                    int ind = j + spacing;
                    t.append(tc[j - spacing]+""+tc[j]);
                    while (t.length() < word.length) {
                        if (ind < tc.length) {
                            t.append(tc[ind]);
                        } else break;
                        ind += spacing;
                    }
                    int c = 0;
                    if (t.toString().equals(WORD)) {
                        for (int n = j-spacing; n <= ind-spacing; n++) {
                            sb.append(c % spacing == 0 ? Character.toUpperCase(tc[n]) : tc[n]);
                            c++;
                        }
                        return sb.toString();
                    }
                }
                if(j==tc.length-1) {
                    if(i==0) {
                        break out;
                    }
                    i = -1;
                    index = startIndex+1;
                }
            }
        }
        return null;
    }
}
