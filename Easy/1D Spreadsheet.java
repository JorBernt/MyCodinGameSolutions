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
        int[] cells = new int[N];
        boolean[] done = new boolean[N];
        boolean allDone = false;
        Map<Integer, String[]> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String operation = in.next();
            String arg1 = in.next();
            String arg2 = in.next();
            String[] s = {arg1, arg2, operation};
            map.put(i, s);
            if(operation.equals("VALUE") && arg1.charAt(0) != '$') {
               cells[i] = calc(arg1, arg2, operation, cells, done);
               done[i] = true;
            }
        }

        while(map.size() > 0) {
            
                for (Map.Entry me : map.entrySet()) {
                int i =(int) me.getKey();
                int r = calc(map.get(i)[0], map.get(i)[1], map.get(i)[2], cells, done);
                if(r != Integer.MIN_VALUE) {
                cells[i] = r;
                done[i] = true;
                map.remove(i);
                break;
                }
            }

        }
        if(map.size() == 0) {
            for(int i = 0; i < cells.length; i++) {
                System.out.println(cells[i]);
            }
        }
        
    }

    static int calc(String arg1, String arg2, String operation, int[] cells, boolean[] done) {
            if(arg1.charAt(0) == '$') {
                int ref = cells[(Integer.parseInt(arg1.substring(1)))];
                if(done[(Integer.parseInt(arg1.substring(1)))]) arg1 = Integer.toString(ref);
                else return Integer.MIN_VALUE; 
            }
            if(arg2.charAt(0) == '$') {
                int ref = cells[(Integer.parseInt(arg2.substring(1)))];
                if(done[(Integer.parseInt(arg2.substring(1)))]) arg2 = Integer.toString(ref);
                else return Integer.MIN_VALUE;  
            }
            try{
                Integer.parseInt(arg2);
            }
            catch(Exception e) {
                arg2 = "0";
            }
            try{
                Integer.parseInt(arg1);
            }
            catch(Exception e) {
                arg1 = "0";
            }
            switch(operation) {
                
                case "VALUE":
                    return Integer.parseInt(arg1);
               
                case "ADD":
                    return Integer.parseInt(arg1) + Integer.parseInt(arg2);
                  
                case "SUB":
                    return Integer.parseInt(arg1) - Integer.parseInt(arg2);
                    
                case "MULT":
                    return Integer.parseInt(arg1) * Integer.parseInt(arg2);
                    
            }
            return Integer.MIN_VALUE;
    }
}


 
