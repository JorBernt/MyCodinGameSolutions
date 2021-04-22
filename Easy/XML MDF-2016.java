import java.util.*;
import java.io.*;
import java.math.*;

//This solution is a little much, but was made under the assumption that there could be multiple opening tags of the same type in a row.
class Tag {
    char c;
    int nest;
    public Tag(char c, int nest) {
        this.c = c;
        this.nest = nest;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char[] sq = in.nextLine().toCharArray();
        Map<Character, Queue<Tag>> map = new HashMap<>();
        float[] sums = new float[26];
        int nest = 1;
        for(int i = 0; i < sq.length; i++) {
            if(sq[i] == '-') continue;
            if(!map.containsKey(sq[i])) {
                Queue<Tag> q = new LinkedList<>();
                q.add(new Tag(sq[i], nest));
                map.put(sq[i], q);
                nest++;
            }
            else {
                if(map.get(sq[i]).size() > 0) {
                    if(sq[i-1] == '-') {
                        sums[sq[i]-'a']+= (float)1/(map.get(sq[i]).poll().nest);
                        nest--;
                    }
                    else map.get(sq[i]).add(new Tag(sq[i], nest));
                }
                else map.get(sq[i]).add(new Tag(sq[i], nest));
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int ans = 0;
        float max = 0;
        for(int i = 0; i < 26; i++) {
            if(sums[i] == max && i > ans) {
                ans = i;
            }
            if(sums[i] > max) {
                max = sums[i];
                ans = i;
            }
        }
        System.out.println((char)(ans+'a'));
    }
}
