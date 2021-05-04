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
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String[] lines = new String[n];
        int start = -1;
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            if(Character.isDigit(line.charAt(0))) {
                if(start == -1) {
                    start = Integer.parseInt(line);
                    start -= i;
                }
            }
            lines[i] = line;
        }
        if(start == -1) start = 1;
        Set<Integer> fizz = new HashSet<>();
        Set<Integer> buzz = new HashSet<>();
        for(int i = 0, j = start; i < n; i++,j++) {
            if(lines[i].equals("Fizz")) fizz.add(j);
            if(lines[i].equals("Buzz")) buzz.add(j);
            if(lines[i].equals("FizzBuzz")) {
                fizz.add(j);
                buzz.add(j);
            }
        }
        int f = 0;
        int b = 0;
        out:for(int i = 1; i < 60; i++) {
            for(int d : fizz) {
                if(d%i!=0) continue out;
            }
            f = i;

        }
        out:for(int i = 1; i < 60; i++) {
            for(int d : buzz) {
                if(d%i!=0) continue out;
            }
            b = i;

        }

        System.out.println(f+" "+b);
    }
}
