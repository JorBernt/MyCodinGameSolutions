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
        String input = in.nextLine();
        String states = in.nextLine();
        int numberOfTransitions = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Map<String, String> transitions = new HashMap<>();
        for (int i = 0; i < numberOfTransitions; i++) {
            String transition = in.nextLine();
            transition = transition.replace(" ", "");
            transitions.put(transition.substring(0,2), Character.toString(transition.charAt(2)));
        }
        String startState = in.nextLine();
        String[] endState = in.nextLine().split(" ");
        List<String> endStates = new ArrayList<>();
        for(int i = 0; i < endState.length; i++) {
            endStates.add(endState[i]);
        }
        int numberOfWords = in.nextInt();
        String curState = startState;
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = in.nextLine().toCharArray();
            for(int j = 0; j < word.length; j++) {
                if(transitions.containsKey(curState+word[j]))
                    curState = transitions.get(curState+word[j]);
                else {
                    System.out.println("false");
                    curState = startState;
                    break;
                }
                if(j == word.length-1) {
                    System.out.println((endStates.contains(curState) ? "true" : "false"));
                    curState = startState;
                } 
            }   
        
        }
    }
}
