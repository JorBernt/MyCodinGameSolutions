import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int longest = 0;
    static int min = Integer.MAX_VALUE;
    static Map<String, Integer> cmap = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String L = in.next();
        int N = in.nextInt();
        Map<Character, String> malph = new HashMap<>();
        malph.put('A', ".-");
        malph.put('B', "-...");
        malph.put('C', "-.-.");
        malph.put('D', "-..");
        malph.put('E', ".");
        malph.put('F', "..-.");
        malph.put('G', "--.");
        malph.put('H', "....");
        malph.put('I', "..");
        malph.put('J', ".---");
        malph.put('K', "-.-");
        malph.put('L', ".-..");
        malph.put('M', "--");
        malph.put('N', "-.");
        malph.put('O', "---");
        malph.put('P', ".--.");
        malph.put('Q', "--.-");
        malph.put('R', ".-.");
        malph.put('S', "...");
        malph.put('T', "-");
        malph.put('U', "..-");
        malph.put('V', "...-");
        malph.put('W', ".--");
        malph.put('X', "-..-");
        malph.put('Y', "-.--");
        malph.put('Z', "--..");

        HashSet<String> words = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String W = in.next();
            StringBuilder sb = new StringBuilder();
            for (char c : W.toCharArray()) sb.append(malph.get(c));
            words.add(sb.toString());
            longest = Math.max(longest, sb.length());
            min = Math.min(min, sb.length());
            cmap.put(sb.toString(), cmap.getOrDefault(sb.toString(), 0) + 1);
        }

        long[] validSentences = new long[L.length()];
        for (int i = 0; i < L.length(); i++) {
            if (i > 0 && validSentences[i - 1] == 0) continue;
            for (int j = i + min - 1; j < Math.min(L.length(), i + longest); j++) {
                String str = L.substring(i, j + 1);
                if (words.contains(str)) {
                    if (i == 0) validSentences[j] += cmap.get(str);
                    else {
                        validSentences[j] += cmap.get(str) * validSentences[i - 1];
                    }
                }
            }
        }
        System.out.println(validSentences[validSentences.length - 1]);
    }
}

