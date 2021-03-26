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
        Map<Integer, String> words = new HashMap<>();
        List<String> uniqWords = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String word = in.next();
            words.put(i, word);
            if(!uniqWords.contains(word)) uniqWords.add(word); 
        }
        Map<String, String> prefixes = new HashMap<>();
        for(int i = 0; i < uniqWords.size(); i++) {
            int k = 1;
            String s1 = "";
            for(int j = 0; j < uniqWords.size();) {
                    s1 = (k < uniqWords.get(i).length() ? uniqWords.get(i).substring(0,k) : uniqWords.get(i));
                    String s2 = (k < uniqWords.get(j).length() ? uniqWords.get(j).substring(0,k) : uniqWords.get(j));
                    if(s1.equals(s2) && j != i) { 
                        k++;
                        j = 0;
                    }
                    else {
                        j++;
                    }
            }
            prefixes.put(uniqWords.get(i), s1);
            
        }
        for (int i = 0; i < N; i++) System.out.println(prefixes.get(words.get(i)));
        
    }
}
