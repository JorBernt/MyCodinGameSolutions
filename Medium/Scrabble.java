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
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Map<Character, Integer> letterValues = new HashMap<>();
        letterValues.put('e', 1);
        letterValues.put('a', 1);
        letterValues.put('i', 1);
        letterValues.put('o', 1);
        letterValues.put('n', 1);
        letterValues.put('r', 1);
        letterValues.put('t', 1);
        letterValues.put('l', 1);
        letterValues.put('s', 1);
        letterValues.put('u', 1);
        letterValues.put('d', 2);
        letterValues.put('g', 2);
        letterValues.put('b', 3);
        letterValues.put('c', 3);
        letterValues.put('m', 3);
        letterValues.put('p', 3);
        letterValues.put('f', 4);
        letterValues.put('h', 4);
        letterValues.put('v', 4);
        letterValues.put('w', 4);
        letterValues.put('y', 4);
        letterValues.put('k', 5);
        letterValues.put('j', 8);
        letterValues.put('x', 8);
        letterValues.put('q', 10);
        letterValues.put('z', 10);

        
        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String W= in.nextLine();
            words.add(W);
        }
        Map<Character, Integer> letterMap = new HashMap<>();
        String LETTERS = in.nextLine();
        for(char c : LETTERS.toCharArray()) {
            letterMap.put(c, letterMap.getOrDefault(c, 0)+1);
        }
        int max = 0;
        String ans ="";
        Deque<Character> temp = new LinkedList<>();
        for(String s : words) {
            int sum = 0;
            if(!temp.isEmpty()) {
                while(!temp.isEmpty())
                    letterMap.put(temp.peek(), letterMap.getOrDefault(temp.poll(), 0)+1);
            }
            for(char c : s.toCharArray()) {
                if(!letterMap.containsKey(c) || letterMap.get(c) == 0) {
                    sum = 0;
                    break;
                }
                else {
                    sum+=letterValues.get(c);
                    if(letterMap.get(c) > 0) {
                        letterMap.put(c, letterMap.get(c)-1);
                        temp.add(c);
                    }
                   
                }

            }
            if(sum > max) {
                max = sum;
                ans = s;
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(ans);
    }
}
