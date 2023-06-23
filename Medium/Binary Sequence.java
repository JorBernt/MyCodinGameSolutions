import java.util.Scanner;

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
        for (int i = 0; i < N; i++) {
            String INDEX = in.nextLine();
            int index = Integer.parseInt(INDEX, 2);
            if (index < 4) {
                System.out.println(index == 0 || index == 3 ? 0 : 1);
                continue;
            }
            long length = 4;
            long n = 4, k = 2, temp = 2;
            while (true) {
                long p = k * ((n - temp - 1)) + k + 1;
                length += p;
                if (length > index) {
                    length -= p;
                    n /= 2;
                    break;
                }
                temp = n;
                n *= 2;
                k++;
            }
            long dif = index - length + 1;
            int a = (int) Math.ceil(dif / (double) k);
            n += a;
            length += (a - 1) * k;
            dif = index % length;
            System.out.println(Long.toBinaryString(n).charAt((int)dif));
        }
    }
}
