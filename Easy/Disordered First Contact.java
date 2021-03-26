import java.util.*;


import java.io.*;
import java.math.*;

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
        String MESSAGE = in.nextLine();

        List<Integer> it = new ArrayList<>();
        int n = 1;
        int sum = 0;
        int a = 0;
        while(true) {
            if(sum+n < MESSAGE.length()) it.add(n);
            else {
                it.add(MESSAGE.length()-sum);
                a = (a == 0 ? 1 : 0);
                break;
            }
            a = (a == 0 ? 1 : 0);
            sum += n;
            n++;
        }
        
        String ans = MESSAGE;
        for(int i = 0; i < Math.abs(N); i++) {
            if(N < 0)
            ans = encode(ans);
            else
            ans = decode(ans, it, a);
        }
        System.out.println(ans);
    }

    static String encode(String mes) {
        StringBuilder sb = new StringBuilder();
        StringBuilder m = new StringBuilder();
        m.append(mes);
        int end = 1;
        int a = 0;
        while(m.length() > 0) {
            if(end >= m.length()) end = m.length();
            String s = m.substring(0,end);
            m.delete(0, end);
            if(a == 0)sb.append(s);
            else sb.insert(0, s);
            end++;
            a = (a == 0 ? 1 : 0);
        }
        return sb.toString();
    }

    static String decode(String mes, List<Integer> it, int a) {
        
        StringBuilder sb = new StringBuilder();
        StringBuilder m = new StringBuilder();
        m.append(mes);
        for(int i = it.size()-1; i >= 0; i--) {
            String s = "";
            if(a == 0) {
                s = m.substring(0, it.get(i));
                m.delete(0, it.get(i));
            }
            else {
                s = m.substring(m.length()-it.get(i), m.length());
                m.delete(m.length()-it.get(i), m.length());
            }
            a = (a == 0 ? 1 : 0);
            sb.insert(0,s);
        }
        return sb.toString();
    }
}
