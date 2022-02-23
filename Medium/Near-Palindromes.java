import java.util.Scanner;

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String word = in.nextLine();
            if (solve(word.toCharArray(), 0, word.length() - 1, false)) 
                System.out.print(1);
            else
                System.out.print(0);
        }

    }

    static boolean solve(char[] ca, int l, int r, boolean changes) {
        while (l < r) {
            if (ca[l] != ca[r]) {
                if (changes)
                    return false;
                return solve(ca, l + 1, r - 1, true) ||
                        solve(ca, l + 1, r, true) ||
                        solve(ca, l, r - 1, true);
            }
            l++;
            r--;
        }
        return true;
    }

}
