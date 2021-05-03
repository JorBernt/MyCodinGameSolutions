import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static List<String> ans = new ArrayList<>();
    static Set<String> memo = new HashSet<>();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        calc(N, 0, 0, 0, false);
        Collections.sort(ans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int[] a = Arrays.stream(o1.split(" ")).mapToInt(Integer::parseInt).toArray();
                int[] b = Arrays.stream(o2.split(" ")).mapToInt(Integer::parseInt).toArray();
                int c = Integer.compare(a[0], b[0]);
                if(c != 0) return c;
                c = Integer.compare(a[1], b[1]);
                if(c != 0) return c;
                c = Integer.compare(a[2], b[2]);
                return c;
            }
        });
        for(String s : ans) System.out.println(s);
    }


    static void calc(int score, int tries, int trans, int pen, boolean tried) {
        String t = tries +" " + trans + " " + pen;

        if(memo.contains(t)) {
            return;
        }
        memo.add(t);
        if(score == 0) {
            ans.add(t);
            return;
        }
        if(score <= 1 || score == 4) return;
        if(tried) {
            calc(score-2, tries, trans+1, pen, false);
        }
        calc(score-5, tries+1, trans, pen, true);
        calc(score-3, tries, trans, pen+1, false);
    }

}
