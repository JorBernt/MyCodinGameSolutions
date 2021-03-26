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
        for (int i = 0; i < n; i++) {
            String card = in.nextLine();
            card = card.replace(" ", "");
            int k = 0;
            int sum1 = 0;
            int sum2 = 0;
            for(int j = 0; j < 8; j++) {
                int m = 2*(Integer.parseInt(Character.toString(card.charAt(k))));
                if(m > 9) {
                    m -= 9;
                }
                sum1 += m;
                sum2 += Integer.parseInt((Character.toString(card.charAt(k+1))));
                k +=2;
            }

            if((sum1+sum2)%10 == 0) {
                System.out.println("YES");
            } 
            else
                System.out.println("NO");
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
