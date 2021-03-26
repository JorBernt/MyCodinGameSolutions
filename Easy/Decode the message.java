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
        long P = in.nextLong();

        if (in.hasNextLine()) in.nextLine();

        List<Character> alphabet = new ArrayList<>();
        for(char c : in.nextLine().toCharArray()) alphabet.add(c);

    /*  String s = "test";
        int sum = alphabet.indexOf(s.charAt(0));
        for(int i = 1; i < s.length(); i++) 
            sum += (int)((alphabet.indexOf(s.charAt(i)) + 1)* Math.pow(alphabet.size(), i));
        System.err.println(sum); 
        
        Encoder ^^^ for fun
        */

        StringBuilder sb = new StringBuilder();
        long k = P%alphabet.size();
        sb.append(alphabet.get((int)k));
        while(P > alphabet.size()) {
            P = P/alphabet.size() -1;
            long l = P%alphabet.size() ;
            sb.append(alphabet.get((int)l));
        }

        System.out.println(sb.toString());
    }
}


