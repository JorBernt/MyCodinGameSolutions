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
        String expression = in.next();
        List<Character> lBrackets = new ArrayList<>();
        lBrackets.add('{');
        lBrackets.add('(');
        lBrackets.add('[');

        List<Character> rBrackets = new ArrayList<>();
        rBrackets.add('}');
        rBrackets.add(')');
        rBrackets.add(']');

        Deque<Character> q = new LinkedList<>();
        
        boolean valid = true;
        for(Character c : expression.toCharArray()) {
            if(lBrackets.contains(c)) q.addFirst(c);
            else if(rBrackets.contains(c)) {
                if(q.peek() == lBrackets.get(rBrackets.indexOf(c))) q.pop();
                else {
                    valid = false;
                    break;
                }
            }
        }
        System.out.println((q.size() != 0 ? false : valid));
    }
}
