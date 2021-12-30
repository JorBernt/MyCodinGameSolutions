import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            names.add(in.nextLine());
        }
        Collections.sort(names);
        String shortest = "";
        for (String name : names) {
            int x = name.length();
            while (x >= 0) {
                if (valid(name.substring(0, x), names) && (shortest.isEmpty() || x < shortest.length())) {
                    shortest = name.substring(0, x);
                }
                x--;
            }
       
        }
         if(shortest.length() == 1) {
            char c = shortest.charAt(0);
            while(c >= 'A') {
                c--;
                if(valid(""+c, names)) shortest = ""+c;
            }
        }
        System.out.println(shortest);
    }

    static boolean valid(String name, List<String> names) {
        int l = 0, r = 0;
        for (String n : names) {
            if (n.compareTo(name) <= 0)
                l++;
            else
                r++;
        }
        return l == r;
    }
}
