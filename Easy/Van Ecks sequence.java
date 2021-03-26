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
        int A1 = in.nextInt();
        int N = in.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        int k=A1,n=k,i=1;
        while(i != N) {
            n=(map.containsKey(k) ? i-map.get(k) : 0);
            map.put(k, i);
            k=n;
            i++;}
        System.out.println(k);
    }
}
