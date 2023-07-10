import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int N = in.nextInt();
        String pattern = in.next();
        String rule = Integer.toBinaryString(R);
        rule = ("0").repeat(8 - rule.length()) + rule;
        int[] nextValue = new int[8];
        for (int i = 0; i < 8; i++) {
            nextValue[i] = rule.charAt(7 - i) - '0';
        }
        for (int i = 0; i < N; i++) {
            System.out.println(pattern);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < pattern.length(); j++) {
                String neighborhood;
                if (j == 0) {
                    neighborhood = pattern.charAt(pattern.length() - 1) + pattern.substring(0, 2);
                } else if (j == pattern.length() - 1) {
                    neighborhood = pattern.substring(pattern.length() - 2) + pattern.charAt(0);
                } else {
                    neighborhood = pattern.substring(j - 1, j + 2);
                }
                int nValue = convertToInt(neighborhood);
                sb.append(nextValue[nValue] == 1 ? "@" : ".");
            }
            pattern = sb.toString();
        }
    }

    private static int convertToInt(String neighborhood) {
        int value = 0;
        for (int i = 0; i < neighborhood.length(); i++) {
            if (neighborhood.charAt(i) == '@') {
                value += Math.pow(2, neighborhood.length() - 1 - i);
            }
        }
        return value;
    }

}
