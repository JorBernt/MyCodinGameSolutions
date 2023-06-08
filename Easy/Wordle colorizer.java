import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    enum Type {
        CORRECT,
        MISPLACED,
        WRONG
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        var attempts = new String[N];
        for (int i = 0; i < N; i++) {
            attempts[i] = in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String attempt = attempts[i];
            var letters = new int[26];
            for(char c : answer.toCharArray()) {
                letters[c - 'A']++;
            }
            var result = new Type[attempt.length()];
            Arrays.fill(result, Type.WRONG);
            for(int j = 0; j < attempt.length(); j++) {
                var c = attempt.charAt(j);
                var type = validate(c, j, answer);
                if(type == Type.CORRECT) {
                    result[j] = Type.CORRECT;
                    letters[c - 'A']--;
                }
            }
            for(int j = 0; j < attempt.length(); j++) {
                if(result[j] == Type.CORRECT) {
                    continue;
                }
                var c = attempt.charAt(j);
                var type = validate(c, j, answer);
                if(letters[c - 'A'] > 0 && type == Type.MISPLACED) {
                    result[j] = Type.MISPLACED;
                    letters[c - 'A']--;
                }
            }
            System.out.println(Arrays.stream(result).map(r -> r == Type.CORRECT ? "#" : r == Type.MISPLACED ? "O" : "X").collect(Collectors.joining()));
        }
    }

    public static Type validate(char c, int index, String answer) {
        if(answer.indexOf(c) == -1) {
            return Type.WRONG;
        }
        if(answer.charAt(index) == c) {
            return Type.CORRECT;
        }
        return Type.MISPLACED;
    }
}
