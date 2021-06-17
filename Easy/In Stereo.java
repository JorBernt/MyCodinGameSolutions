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
        int P = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Character> pt = new ArrayList<>();
        for(char c : in.nextLine().toCharArray()) pt.add(c);
        int S = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Queue<Character> st = new LinkedList<>();
        for(char c : in.nextLine().toCharArray()) st.add(c);
        int H = in.nextInt();
        int W = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int pointer = 0;
        int depth = 0;
        for (int i = 0; i < H; i++) {
            List<Character> pattern = new ArrayList<>(pt);
            Queue<Character> stock = new LinkedList<>(st);
            char[] dm = in.nextLine().toCharArray();
            for(int j = 0; j < W; j++) {
                int n = dm[j]-'0';
                if(n-depth > 0) {
                    while(n-depth>0){
                        pattern.remove(pointer);
                        if(pointer >= pattern.size()) pointer = 0;
                        depth++;
                    }
                }
                else if(n-depth < 0) {
                    int t = 0;
                    while(n-depth<0) {
                        pattern.add(pointer+t, stock.poll());
                        depth--;
                        t++;
                    }

                }
                System.out.print(pattern.get(pointer));
                pointer++;
                pointer%=Math.max(pattern.size(),1);
            }
            depth = 0;
            pointer = 0;
            System.out.println();
        }
    }
}
