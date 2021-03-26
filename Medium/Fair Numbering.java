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
        PrintWriter pw = new PrintWriter(System.out);
        long[] pages = new long[10000001];
        long totalDigits = 0;
        for(int i = 1; i < pages.length; i++) {
            totalDigits += getDigitsCount(i);
            pages[i] = totalDigits;
        }
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int st = in.nextInt();
            int ed = in.nextInt();
            totalDigits = pages[ed]+pages[st-1];
            totalDigits /= 2;
            pw.println(getPage(pages, st, ed+1, totalDigits, 10000001, 0));
        }
        pw.close();
    }

    static int getDigitsCount(int n) {
        if(n < 10) return 1;
        if(n < 100) return 2;
        if(n < 1000) return 3;
        if(n < 10000) return 4;
        if(n < 100000) return 5;
        if(n < 1000000) return 6;
        if(n < 10000000) return 7;
        return 8;
    }

    static int getPage(long[] pages, int start, int end, long digits, long closest, int nearestPage) {
        if(start <= end) {
            int mid = (start+end)/2;
            if(pages[mid] == digits) return mid;
            if(pages[mid] < digits) {
                if(digits-pages[mid] < closest) {
                    closest = digits-pages[mid];
                    nearestPage = mid;
                }
                return getPage(pages, mid+1, end, digits, closest, nearestPage);
            }
            if(pages[mid] >  digits) return getPage(pages, start, mid-1, digits, closest, nearestPage);
        }
        return nearestPage;
    }
}
