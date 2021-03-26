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
        String ip = in.nextLine();
        String[] arr = ip.split(":");
        List<String> ans = new ArrayList<>();
        int index = 0, mapIndex = 0;;
        Map<Integer, String> map = new HashMap<>();
        int maxL = -1, maxI = -1;
        int zeroCount = 0;
        while (index < arr.length) {
            if(isZero(arr[index])) {
                zeroCount++;
                if(map.containsKey(mapIndex)) {
                    map.put(mapIndex, map.get(mapIndex)+":0");
                }
                else map.put(mapIndex, "0");
                if(map.get(mapIndex).length() > maxL) {
                    maxL = map.get(mapIndex).length();
                    maxI = mapIndex;
                }  
                index++;
            }
            else {
                if(map.containsKey(mapIndex)) mapIndex++;
                map.put(mapIndex, removeZero(arr[index]));
                mapIndex++;
                index++;
            }
        }
        for(int i = 0; i < map.size(); i++) {
            if(i == maxI && zeroCount > 1) {
                ans.add("");
            }
            else ans.add(map.get(i));
        }
        
        

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        String out = String.join(":", ans);
        if(out.charAt(0) == ':') out = ":"+out;
        System.out.println(out);
    }

    static String removeZero(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '0') {
                return s.substring(i, s.length());
            }
        }
        return s;
    }

    static boolean isZero(String s) {
        int n = 0;
        try{
            n = Integer.parseInt(s);
        }   
        catch(Exception e) {
            return false;
        }
        n = Integer.parseInt(s);
        return n==0;
    }
}
