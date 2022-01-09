import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Solution {
    static Map<Character, Character> map = Map.of(
            '<', '>',
            '>', '<',
            '(', ')',
            ')', '(',
            '[', ']',
            ']', '[',
            '{', '}',
            '}', '{'
    );

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            System.out.println(valid(in.nextLine().replaceAll("[\\w]", ""), 0, new Stack<>()));
        }
    }

    static boolean valid(String str, int ind, Stack<Character> stack) {
        if(ind == str.length()) return stack.isEmpty();
        for(int i = 0; i < 2; i++) {
            Stack<Character> temp = new Stack<>();
            temp.addAll(stack);
            char c = ' ';
            if(i == 0)
            c = str.charAt(ind);
            else c = map.get(str.charAt(ind));
            if(c == '<' || c == '{' || c == '(' || c == '[') {
                temp.add(c);
            }
            else {
                if(temp.isEmpty() || map.get(temp.pop()) != c) continue;
            }
            if(valid(str, ind + 1, temp)) return true;

        }
        return false;
    }
}
