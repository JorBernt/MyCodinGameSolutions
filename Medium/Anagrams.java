import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    String phrase;

    public Solver(String phrase) {
        this.phrase = phrase;
        reverseWordLength();
        fourthRightShift();
        thirdLeftShift();
        secondAndReverse();
    }

    private void secondAndReverse() {
        Stack<Character> chars = new Stack<>();
        List<Integer> indices = new ArrayList<>();
        for(int j = 0; j < phrase.length(); j++) {
            char c = phrase.charAt(j);
            for(int i = 2; i < 26; i++) {
                if((c-'A'+1) == i && i%2==0) {
                    chars.add(c);
                    indices.add(j);
                    break;
                }
            }
        }
        char[] pc = phrase.toCharArray();
        for(int n : indices) {
            pc[n] = chars.pop();
        }
        phrase = String.valueOf(pc);
    }

    private void thirdLeftShift() {
        List<Character> chars = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for(int j = 0; j < phrase.length(); j++) {
            char c = phrase.charAt(j);
            for(int i = 3; i < 26; i++) {
                if((c-'A'+1) == i && i%3==0) {
                    chars.add(c);
                    indices.add(j);
                    break;
                }
            }
        }
        Queue<Character> shifted = new LinkedList<>();
        for(int i = 1; i <= chars.size(); i++) {
            shifted.add(chars.get(i%chars.size()));
        }
        char[] pc = phrase.toCharArray();
        for(int n : indices) {
            pc[n] = shifted.poll();
        }
        phrase = String.valueOf(pc);
    }

    private void fourthRightShift() {
        List<Character> chars = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for(int j = 0; j < phrase.length(); j++) {
            char c = phrase.charAt(j);
            for(int i = 4; i < 26; i++) {
                if((c-'A'+1) == i && i%4==0) {
                    chars.add(c);
                    indices.add(j);
                    break;
                }
            }
        }
        Queue<Character> shifted = new LinkedList<>();
        for(int i = 0; i < chars.size(); i++) {
            shifted.add(chars.get((chars.size()-1+i)%chars.size()));
        }
        char[] pc = phrase.toCharArray();
        for(int n : indices) {
            pc[n] = shifted.poll();
        }
        phrase = String.valueOf(pc);
    }

    private void reverseWordLength() {
        String[] words = phrase.split(" ");
        phrase = phrase.replace(" ","");
        int start = 0;
        for(int i = words.length-1; i >= 0; i--) {
            words[i] = phrase.substring(start, start+words[i].length());
            start += words[i].length();
        }
        phrase = "";
        for(int i = words.length-1; i >= 0; i--) {
            phrase += words[i] + (i>0?" ":"");
        }
    }

    public String toString() {
        return phrase;
    } 
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String phrase = in.nextLine();
        Solver solver = new Solver(phrase);
        System.out.println(solver);
    }
}
