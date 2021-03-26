import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class License {
    Letters front, end;
    Digits digits;
    public License(String s) {
        front = new Letters(s.substring(0,2));
        digits = new Digits(s.substring(3,6));
        end = new Letters(s.substring(7,9));
    }

    public void increment(int n) {
        boolean d = false, e = false;
        for(int i = 0; i< n; i++) {
            d = digits.increment();
            if(d) {
                e = end.increment();
                if(e) {
                    front.increment();
                }
            }
        }
    }

    public String toString() {
        return front.toString()+"-"+digits.toString()+"-"+end.toString();
    }
}

class Letters {
    char a,b;
    public Letters(String s) {
        a = s.charAt(0);
        b = s.charAt(1);
    }
    public boolean increment() {
        b++;
        if(b>'z') {
            b='a';
            a++;
        }
        if(a>'z') {
            a='a';
            return true;
        }
        return false;
    }
    public String toString() {
        return ""+a+b;
    } 
}

class Digits {
    int a,b,c;
    public Digits(String s) {
        a = Integer.parseInt(s.substring(0,1));
        b = Integer.parseInt(s.substring(1,2));;
        c = Integer.parseInt(s.substring(2,3));;
    }
    public boolean increment() {
        c++;
        if(c>9) {
            c = 0;
            b++;
        }
        if(b>9) {
            b=0;
            a++;
        }
        if(a>9) {
            a=0;
            c=1;
            return true;
        }
        return false;
    }
    public String toString() {
        return ""+a+b+c;
    }
} 
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String x = in.nextLine();
        int n = in.nextInt();

        License license = new License(x.toLowerCase());
        license.increment(n);

        System.out.println(license.toString().toUpperCase());
    }
}
