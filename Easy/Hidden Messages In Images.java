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
        int w = in.nextInt();
        int h = in.nextInt();
        String bcount = "";
        List<String> bytes = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = in.nextInt();
                String bin = Integer.toBinaryString(pixel);
                bcount += bin.charAt(bin.length()-1);
                if(bcount.length() == 8) {
                    bytes.add(bcount);
                    bcount = "";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s : bytes) {
            byte b = Byte.parseByte(s, 2);
            sb.append((char)b);
        }
        System.out.println(sb.toString());

    }
}
