import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] ip = in.nextLine().split("/");
        String addressString = ip[0];
        String maskString = ip[1];
        int[] address = parseAddress(addressString);
        int[] mask = parseMask(maskString);
        for (int i = 0; i < 4; i++) {
            System.out.print(address[i] & mask[i]);
            if (i < 3)
                System.out.print(".");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.print((255 & ~mask[i]) | address[i]);
            if (i < 3)
                System.out.print(".");
        }
    }

    private static int[] parseAddress(String input) {
        int[] a = new int[4];
        String[] split = input.split("\\.");
        for (int i = 0; i < 4; i++) {
            a[i] = Integer.parseInt(split[i]);
        }
        return a;
    }

    private static int[] parseMask(String input) {
        int m = Integer.parseInt(input);
        int[] a = new int[4];
        for (int i = 0; i < 4 && m > 0; i++) {
            int k = Math.min(8, m);
            a[i] = ((1 << k) - 1) << (8-k);
            m-=k;
        }

        return a;
    }
}
