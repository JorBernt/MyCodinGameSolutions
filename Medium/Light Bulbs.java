import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int count = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[] s = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        int[] t = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        int n = s.length;
        for (int i = 0; i < n; i++) {
            if (s[i] != t[i]) {
                rec(s, s[i], t[i], i);
            }
        }
        System.out.println(count);
    }

    static void rec(int[] s, int source, int target, int index) {
        if (index == s.length - 1) {
            if (source != target) {
                s[index] = target;
                count += 1;
                return;
            }
        }
        if (index + 1 < s.length && s[index + 1] != 1) {
            rec(s, s[index + 1], 1, index + 1);
        } else {
            boolean allZeroes = true;
            for (int i = index + 2; i < s.length; i++) {
                if (s[i] == 1) {
                    rec(s, s[i], 0, i);
                    allZeroes = false;
                    break;
                }
            }
            if (allZeroes) {
                s[index] = s[index] == 1 ? 0 : 1;
                count += 1;
            }
        }
        if (s[index] == target) return;
        else rec(s, source, target, index);
    }
}
