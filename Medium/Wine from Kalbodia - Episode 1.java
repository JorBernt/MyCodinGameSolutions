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
            List<String> requests = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String request = in.nextLine();
                requests.add(encode(request));
            }
            List<String> crates = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String crate = in.nextLine();
                crates.add(encode(crate));
            }
            for(String r : requests) {
                for (int i = 0; i < N; i++) {
                    if(crates.get(i).equals(r)) {
                        System.out.println(i+1);
                        break;
                    }
                }
            }
        }

        static String encode(String word) {
            StringBuilder sb = new StringBuilder();
            char d = 'a';
            char f = 'a';
            char prev  = word.charAt(0);
            Map<Character, Character> map = new HashMap<>();
            for(char c : word.toCharArray()) {
                boolean changed = false;
                if(c != prev) {
                    prev = c;
                    d++;
                    changed = true;
                }
                if(map.containsKey(c)) {
                    if(changed) d--;
                    sb.append(map.get(c));
                    continue;
                }
                map.put(c, d);
                sb.append(d);
            }
            return sb.toString();
        }
    }
