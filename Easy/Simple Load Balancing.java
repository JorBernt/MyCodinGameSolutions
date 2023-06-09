import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int li = in.nextInt();
            list.add(li);
            max = Math.max(max, li);
        }
        list.sort(Comparator.naturalOrder());
        int min = 0;
        for (int i = 0; i < n - 1 && k > 0; i++) {
            int m = (list.get(i + 1) - list.get(i)) * (i + 1);
            if (k < m) {
                m = k / (i + 1);
                min = list.get(i) + m;
                k = 0;
                break;
            }
            min = list.get(i + 1);
            k -= m;
        }
        System.out.println(k == 0 ? max - min : 1);
    }
}
