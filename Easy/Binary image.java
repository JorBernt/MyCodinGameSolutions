import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String> ans = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < h; i++) {
            int[] row = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean black = row[0] == 0;
            StringBuilder sb = new StringBuilder();
            for (int j = row[0] == 0 ? 1 : 0; j < row.length; j++) {
                sb.append((black?"O":".").repeat(row[j]));
                black = !black;
            }
            if (size == 0)
                size = sb.length();
            else if (sb.length() != size) {
                System.out.println("INVALID");
                return;
            }
            ans.add(sb.toString());
        }
        for (String s : ans)
            System.out.println(s);
    }
}
