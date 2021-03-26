import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String b = in.nextLine();
        int n = 0;
        int max = 0;
        while(true) {
            int q = 0;
            StringBuilder k =new StringBuilder();
            for(char c : b.toCharArray()) {
                if(c=='0' && q==n) {
                    k.append("1");
                    q++;
                    continue;
                }
                k.append(c);
                q++;
            }
            max = Math.max(longest(k.toString()), max);
            n++;
            if(n==b.length()) break;
        }
        System.out.println(max);
    }

    public static int longest(String b) {
        String[] k = b.split("0");
        int max = 0;
        for(String s:k) {
            if(s.contains("1")) {
                max = Math.max(max, s.length());
            }
        }
        return max;
    }
}
