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
        List<Double> transactions = new ArrayList<>();
        int[] count = new int[10];
        double[] benford = {0, 30.1, 17.6, 12.5, 9.7, 7.9, 6.7, 5.8, 5.1, 4.6};

        for (int i = 0; i < N; i++) {
            String transaction = in.nextLine();
            String s = "";
            for(int j = 0; j < transaction.length(); j++) {
                char c = transaction.charAt(j);
                if(Character.isDigit(c) ) 
                    s+=c;
            }
            double d = Double.parseDouble(s);
            int n = Integer.parseInt(Double.toString(d).substring(0,1));
            count[n]++;
        }

        for(int i = 1; i < count.length; i++) {
            double s = 100*(double)count[i]/N;
            if(s < benford[i] - 10 || s > benford[i] + 10) {
                System.out.println("true");
                return;
            }
        }
        System.out.println("false");
    }
}
