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
        int W = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int index = 0;
        boolean black = true;
        for(int i = 0; i < H; i++) {
            System.out.print("|");
            for(int j = 0; j < W; j++) {
                if(nums[index] == 0) {
                    index++;
                    black = !black;
                }
                nums[index]--;
                System.out.print(black ? "*":" ");
            }
            System.out.println("|");
        }
    }
}
