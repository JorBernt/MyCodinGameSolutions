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
        String ALPHABET = in.nextLine();
        String MESSAGE = in.nextLine();
        String WORD = in.nextLine();
        int shift = 0;
        int mult = 1;
        while(true) {
            String t = shiftString(ALPHABET, MESSAGE, shift, mult);
            if(t.contains(WORD)) {
                MESSAGE = t;
                break;
            }
            shift++;
            if(shift == ALPHABET.length()) {
                shift = 0;
                mult++;
            }
        }
        System.out.println(MESSAGE);
    }

    static String shiftString(String a, String s, int shift, int mult) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            int n = a.indexOf(c);
            n = ((n+shift)*mult)%a.length();
            sb.append(a.charAt(n));
        }
        return sb.toString();
    }
}
