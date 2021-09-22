import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
class Solution {
    static Map<String, Integer> months = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String time = in.nextLine();
        String[] address = in.nextLine().split(" ");
        months.put("jan", 0);
        months.put("feb", 1);
        months.put("mar", 2);
        months.put("apr", 3);
        months.put("may", 4);
        months.put("jun", 5);
        months.put("jul", 6);
        months.put("aug", 7);
        months.put("sep", 8);       
        months.put("oct", 9);  
        months.put("nov", 10);  
        months.put("dec", 11);
        System.out.println(timeDecrypt(time)); 
        System.out.println(addressDecrypt(address));
    }

    static String addressDecrypt(String[] address) {
        StringBuilder addressString = new StringBuilder();
        for(String s : address) {
            int base12 = months.get(s.substring(0,3)) * 12 + months.get(s.substring(3,6));
            addressString.append((char)base12);
           
        }
        return addressString.toString();
    }

    static String timeDecrypt(String time) {
        StringBuilder binary = new StringBuilder();
        for(char c : time.toCharArray()) {
            binary.append(c == '#' ? 1 : 0);
        }
        String t = String.format("%04d", Integer.parseInt(binary.toString(), 2));
        return t.substring(0,2)+":"+t.substring(2,4);
    }
}
