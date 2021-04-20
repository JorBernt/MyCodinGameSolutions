import java.util.*;
import java.io.*;
import java.math.*;


//Waaay to over engineered
class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            System.out.println(rec(new ArrayList<>(), line, 0, Integer.MAX_VALUE));
        }
    }

    static int rec(List<Stack<Character>> stacks, String line, int index, int curMin) {
        if(!valid(stacks) || curMin != Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (index == line.length()) {
            if(valid(stacks)) {
                return stacks.size();
            }
            else return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        char c = line.charAt(index);

        for (int i = 0; i < stacks.size(); i++) {
            stacks.get(i).add(c);
            min = Math.min(min, rec(stacks, line, index + 1, min));
            if (!stacks.get(i).isEmpty())
                stacks.get(i).pop();
        }
        Stack<Character> list = new Stack<>();
        list.add(c);
        stacks.add(list);
        min = Math.min(min, rec(stacks, line, index + 1, min));
        stacks.remove(list);
        return min;
    }

    static boolean valid(List<Stack<Character>> stacks) {
        List<Stack<Character>> copy = new ArrayList<>();
        for(Stack<Character> stack : stacks) {
            Stack<Character> cop = (Stack<Character>)stack.clone();
            copy.add(cop);
        }
        char c = 'A';
        while(true) {
            for(Stack<Character> stack : copy) {
                if(!stack.isEmpty() && stack.peek() == c) {
                    while(!stack.isEmpty() && stack.peek() == c)
                        stack.pop();
                }
            }
            c++;
            if(c>'Z') break;
        }
        int size = 0;
        for(Stack<Character> stack : copy) size+=stack.size();
        return size==0;
    }
}
