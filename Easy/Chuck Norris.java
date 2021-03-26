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
            StringBuilder encoded = new StringBuilder();
            String s = convertStringToBinary(in.nextLine());
            List<int[]> binList = new ArrayList<int[]>();
            int zC = 0;
            int oC = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '1') {
                    if(zC > 0) {
                        int[] l = {0, zC};
                        binList.add(l);
                        zC = 0;
                    }
                    oC++;
                }
                else if(s.charAt(i) == '0') {
                    if(oC > 0) {
                        int[] l = {1, oC};
                        binList.add(l);
                        oC = 0;
                    }
                    zC++;
                }
                if(i == s.length()-1) {
                    if(oC > 0) {
                        int[] l = {1, oC};
                        binList.add(l);
                    }
                    else if(zC > 0) {
                        int[] l = {0, zC};
                        binList.add(l);
                    }
                }
            }
            for(int[] o : binList) {
                if(o[0] == 1) {
                    encoded.append("0 ");
                    for(int i = 0; i < o[1]; i++) {
                        encoded.append("0");
                    }
                    encoded.append(" ");
                }
                else if(o[0] == 0) {
                    encoded.append("00 ");
                    for(int i = 0; i < o[1]; i++) {
                        encoded.append("0");
                    }
                    encoded.append(" ");
                }
            }

        encoded.deleteCharAt(encoded.length()-1);
        System.out.println(encoded.toString());
    }

        public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%7s", Integer.toBinaryString(aChar)) 
                            .replaceAll(" ", "0")                        
            );
        }
        return result.toString();

    }
}
