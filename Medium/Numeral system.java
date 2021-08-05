import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private String x, y, z;

    public Solver(String input) {
        parse(input);
    }

    private void parse(String input) {
        String[] nums = input.split("[-+*/=]");
        x = nums[0];
        y = nums[1];
        z = nums[2];
    }

    public void solve() {
        int n = 2;
        int a = 0, b = 0, c = 1;
        while(true) {
            try {
                a = Integer.parseInt(x, n);
                b = Integer.parseInt(y, n);
                c = Integer.parseInt(z, n);
            }   
            catch(Exception e) {}
            if(a + b == c || n == 36) {
                break;
            }
            n++;
        }
        System.out.println(n);
    }

}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String equality = in.nextLine();
        Solver solver = new Solver(equality);
        solver.solve();

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
