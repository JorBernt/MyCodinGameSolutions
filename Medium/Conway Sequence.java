import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int L;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        L = in.nextInt();
        System.out.println(conway(new int[]{R}, 1));
    }

    static String conway(int[] seq, int step) {
        if(step == L) {
            return Arrays.stream(seq).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        }
        List<Integer> list = new ArrayList<>();
        int count = 0;
       
        for(int i = 0; i < seq.length; i++) {
            count++;
            if(seq.length == 1){
                list.add(1);
                list.add(seq[0]);
            }
            if(i == 0) {
                continue;
            }
            if(seq[i-1] != seq[i]) {
                list.add(count-1);
                list.add(seq[i-1]);
                count = 1;
            }
            if(i == seq.length-1) {
                list.add(count);
                list.add(seq[i]);
            }
        }
        return conway(list.stream().mapToInt(i -> i).toArray(), step + 1);
        
    }
}
