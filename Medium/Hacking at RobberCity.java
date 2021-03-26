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
        String message1 = in.nextLine();
        String message2 = in.nextLine();
        String message3 = in.nextLine();

        String a = xor(message1, message2);
        System.out.println(decode(xor(a,message3)));
    }

    static String xor(String a, String b) {
        String out ="";
        for(int i = 0; i < a.length(); i+=2) {
            int nA = Integer.parseInt(a.substring(i,i+2), 16);
            int nB = Integer.parseInt(b.substring(i,i+2), 16);
            String hex = Integer.toHexString(nA^nB);
            hex = hex.length()==1?"0"+hex:hex;
            out+=hex;
        }
        return out;
    }

    static String decode(String a) {
        String out ="";
        for(int i = 0; i < a.length(); i+=2) {
            int n = Integer.parseInt(a.substring(i,i+2), 16);
            out+=(char)n;
        }
        return out;
    }
}
