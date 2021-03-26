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
        int n = in.nextInt();
        String packet = in.next();
        while(packet.length() > 0) {
            int bits = Integer.parseInt(packet.substring(3,7), 2);
            if(packet.substring(0,3).equals("101")) {
                String item = packet.substring(7, 7+bits);
                System.out.print("001"+String.format("%4s", Integer.toBinaryString(item.length())).replace(' ', '0')+item);
            }
            packet = packet.substring(7+bits, packet.length());
        }
    }
}
