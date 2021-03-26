import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int S = in.nextInt();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int[] arr = new int[S];
        int index = 0;
        String R = "";
        String signs = "><+-.,[]";
        for (int i = 0; i < L; i++) {
            String r = in.nextLine();
            for(char c : r.toCharArray()) 
                if(signs.contains(Character.toString(c))) R+=c;
        }

        int[] ints = new int[N];

        for (int i = 0; i < N; i++) {
            int c = in.nextInt();
            ints[i] = c;
        }

        char[] ca = R.toCharArray();
        Map<Integer, Integer> brackets = new HashMap<>();
        int l = 0;

        for(int i = 0; i < ca.length; i++) {
            if(ca[i] == '[') {
                brackets.put(i, l);
                l++;
            }
            if(ca[i] == ']') {
                l--;
                for(int j : brackets.keySet()) 
                    if(brackets.get(j) == l) 
                        brackets.put(j, i);
            }
        }
        if(l != 0) {
            System.out.println("SYNTAX ERROR");
            return;
        }

        int n = 0;

        for(int i = 0; i < ca.length; i++) {
            switch(ca[i]) {
                case '>': index++;break;
                case '<': index--;break;
                case '+': arr[index]++;break;
                case '-': arr[index]--;break;
                case '.': System.out.print((char)arr[index]);break;
                case ',': arr[index] = ints[n++];break;
                case '[':
                    if(arr[index]==0) 
                        i = brackets.get(i);
                    break;
                case ']':
                    if(arr[index]!=0) 
                    for(int j : brackets.keySet()) {
                       if(brackets.get(j) == i) i=j;
                    }
                    break;
            }
            if(index < 0 || index > S-1) {
                System.out.print("POINTER OUT OF BOUNDS");
                return;
            }
            if(arr[index] < 0 || arr[index] > 255) {
                System.out.print("INCORRECT VALUE");
                return;
            }
        }
    }
}
