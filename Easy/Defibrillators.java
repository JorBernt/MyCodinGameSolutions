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
        String LON = in.next();
        String LAT = in.next();
        LON = LON.replace(",", ".");
        LAT = LAT.replace(",", ".");
        int N = in.nextInt();
        float lonA = Float.parseFloat(LON);
        float latA = Float.parseFloat(LAT);
        Map<Integer, List<Float>> locations = new HashMap<>();
        Map<Integer, String> nameLoc = new HashMap<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            String[] s = DEFIB.split(";");
            s[s.length-1] = s[s.length-1].replace(",", ".");
            s[s.length-2] = s[s.length-2].replace(",", ".");
            float lon = Float.parseFloat(s[s.length-2]);
            float lat = Float.parseFloat(s[s.length-1]);
            List<Float> cord = new ArrayList<>();
            cord.add(lon);
            cord.add(lat);
            locations.put(i, cord);
            nameLoc.put(i, s[1]);
        }
        double shortestDis = Integer.MAX_VALUE;
        String shortName = "";
        for(int i : locations.keySet()) {
            float lonB = locations.get(i).get(0);
            float latB = locations.get(i).get(1);
            double x = (lonB-lonA)*Math.cos((latA+latB)/2);
            double y = latB-latA;
            double d = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2))*6371;
            if(d < shortestDis) {
                shortestDis = d;
                shortName = nameLoc.get(i);
            }
            
        }


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(shortName);
    }
}
