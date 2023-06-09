import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            arr[i] = a;
        }
        int l = 0, r = n - 1, max = 0;
        while (l < r) {
            max = Math.max(max, Math.min(arr[l], arr[r]) * (r - l));
            if (arr[l] < arr[r]) l++;
            else r--;
        }
        System.out.println(max);
    }
}
