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
        String order = in.nextLine();
        String side = in.nextLine();
        Map<Character, Integer> sides = new HashMap<>();
        sides.put('R', 1);
        sides.put('L', 1);
        sides.put('D', 1);
        sides.put('U', 1);

        for(char c:order.toCharArray()) {
            switch(c) {
                case 'R': 
                    sides.put('L', sides.get('L')+sides.get('R'));
                    sides.put('U', sides.get('U')*2);
                    sides.put('D', sides.get('D')*2);
                    sides.put('R', 1);
                    break;
                case 'L': 
                    sides.put('R', sides.get('L')+sides.get('R'));
                    sides.put('U', sides.get('U')*2);
                    sides.put('D', sides.get('D')*2);
                    sides.put('L', 1);
                    break;
                case 'U': 
                    sides.put('D', sides.get('U')+sides.get('D'));
                    sides.put('L', sides.get('L')*2);
                    sides.put('R', sides.get('R')*2);
                    sides.put('U', 1);
                    break;
                case 'D': 
                    sides.put('U', sides.get('U')+sides.get('D'));
                    sides.put('L', sides.get('L')*2);
                    sides.put('R', sides.get('R')*2);
                    sides.put('D', 1);
                    break;
            }
        }
        System.out.println(sides.get(side.charAt(0)));
    }
}
