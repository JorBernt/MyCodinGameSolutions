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
        String s = in.nextLine();
        int n = 0;
        String temp = "";
        while(true) {
            for(int i = 0; i < s.length(); i+=2){
                int a = s.charAt(i)-'0', b=s.charAt(i+1)-'0';
                temp += (""+b).repeat(a);
            }
            if(!reverse(temp).equals(s)) break;
            if(s.equals(temp)) break;
            s = temp;
            if(temp.length()%2!=0) break;
            temp = "";
            
        }
        System.out.println(s);
        
    }

    static String reverse(String s) {
        if(s.length()==1) return "1"+s;
        String a = "";
        int n = 1;
        char[] ca = s.toCharArray();
        for(int i = 1; i < ca.length; i++) {
            if(ca[i] == ca[i-1]) n++;
            else {
                a += n+""+ca[i-1];
                n = 1;
            }
            if(i == ca.length-1)  {
                a += n+""+ca[i];
                n = 1;
            }
        }
        return a;
    }
}
