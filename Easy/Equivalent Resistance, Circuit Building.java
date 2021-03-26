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
        int N = in.nextInt();
        Map<String, Integer> resistors = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String name = in.next();
            int R = in.nextInt();
            resistors.put(name, R);
        }
        in.nextLine();
        String cS = in.nextLine();
        String temp = cS;
        Map<String, Float> elements = new HashMap<>();
        while(true) {
            String[] circuit = cS.split("");
            for(int i = 0; i < circuit.length; i++) {
                if(circuit[i].equals("(")) {
                    for(int j = i+1; j < circuit.length; j++) {
                        if(circuit[j].equals("[") || circuit[j].equals("(")) break;
                        else if(circuit[j].equals(")")) {
                            String s = cS.substring(i, j+1);
                            String[] e = s.split(" ");
                            Double n = 0.0;
                            for(String k : e) {
                                try {
                                    n += Double.parseDouble(k);
                                }
                                catch (Exception p) {
                                if(resistors.get(k) != null)
                                n += (double)resistors.get(k);
                                }
                            } 
                            temp = temp.replace(s,Double.toString(n));
                            break;
                        }
                    }
                }
                else if(circuit[i].equals("[")) {
                    for(int j = i+1; j < circuit.length; j++) {
                        if(circuit[j].equals("[") || circuit[j].equals("(")) break;
                        else if(circuit[j].equals("]")) {
                            String s = cS.substring(i, j+1);
                            String[] e = s.split(" ");
                            Double n = 0.0;
                            for(String k : e) {
                                try{
                                    n += 1/Double.parseDouble(k);
                                }
                                catch(Exception p) {
                                if(resistors.get(k) != null)
                                n += (double)1/resistors.get(k);
                                }
                            } 
                            n = 1/n;
                            temp = temp.replace(s, Double.toString(Math.round(n * 10) / 10.0));
                            break;
                        }
                    }
                }
            }
            cS = temp;
            try {
                Double.parseDouble(cS);
                break;
            }
            catch (Exception e) {
            
            }
        }

         System.out.println(cS);
    }
}
