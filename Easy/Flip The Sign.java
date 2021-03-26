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
        int height = in.nextInt();
        int width = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        int[][] nums = new int[height][width];
        for (int h = 0; h < height; h++) {
            String[] line = in.nextLine().split(" ");
            for(int w = 0; w < width; w++){
                nums[h][w] = Integer.parseInt(line[w]);
            }
        }
        String[][] chars = new String[height][width];
        for (int h = 0; h < height; h++) {
            String[] line = in.nextLine().split(" ");
            for(int w = 0; w < width; w++) {
                chars[h][w] = line[w];
            }
        }
        Deque<Integer> dq = new LinkedList<>();
        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                if(chars[h][w].equals("X")) {
                    if(dq.isEmpty()) {
                        dq.push(nums[h][w]);
                        continue;
                    }
                    else if(!dq.isEmpty()) {
                        if(nums[h][w] < 0 && dq.peek() < 0) {
                            System.out.println("false");
                            return;
                        }
                        else if(nums[h][w] < 0 && dq.peek() > 0) {
                            dq.push(nums[h][w]);
                            continue;
                        } 
                        else if(nums[h][w] > 0 && dq.peek() > 0) {
                            System.out.println("false");
                            return;
                        }
                        else if(nums[h][w] > 0 && dq.peek() < 0){
                            dq.push(nums[h][w]);
                            continue;
                        } 
                    }
                }

            }
        }
        System.out.println("true");

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
