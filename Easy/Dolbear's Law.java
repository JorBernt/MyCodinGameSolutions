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
        int M = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }        
        double N60 = 0.;
        double N8 = 0;
        int c = 0;
        for (int i = 0; i < M; i++) {
            String[] LINE = in.nextLine().split(" ");
            double sum = 0;
            for(String s : LINE) {
                sum+=Integer.parseInt(s);
                N8 +=Integer.parseInt(s);
                c++;
            }
            if((15*M)%2 != 0 && i == M-1) {
                N8 -= Integer.parseInt(LINE[LINE.length-1]);
                c--;
            }
            N60 +=10+(sum-40)/7;
        }
        c=c/2;
        N8 = (N8+(5*c))/c;
        
        N60= Double.parseDouble(String.format("%.1f", N60/M));
        System.out.println(N60);
        if(N60 >= 5.0 && N60<=30.0) System.out.println(String.format("%.1f", N8));

    }
}
