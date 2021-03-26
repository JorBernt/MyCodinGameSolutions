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
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }


        List<List<Integer>> lists = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < N; i++) {
            List<Integer> nums = new ArrayList<>();
            nums.add(arr[i]);
            for(int j = i+1; j< N; j++) {
                if(arr[j] == nums.get(nums.size()-1)+1) {
                    nums.add(arr[j]);
                }
            }
            if(nums.size() >= max) {
                max = nums.size();
                lists.add(nums);
            }
        }
        int minStart = Integer.MAX_VALUE;
        for(List<Integer> l: lists) {
            if(l.size() == max) {
                minStart = Math.min(minStart, l.get(0));
            }
        }

        for(List<Integer> l:lists) {
            if(l.size() == max && l.get(0) == minStart) {
                for(int i = 0; i < max; i++) {
                    System.out.print(l.get(i));
                    if(i < max-1) System.out.print(" ");
                }
            }
        }


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
