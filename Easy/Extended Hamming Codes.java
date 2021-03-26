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
        String bits = in.nextLine();
        Map<Character, Integer> block = new HashMap<>();
        int n = 0;
        int a = 0;
        for(char c = 'a'; c <= 'p'; c++) {
            block.put(c, Character.getNumericValue(bits.charAt(n)));
            if(bits.charAt(n) == '1') a++;
            n++;
        }
        char[] bBit = {'d', 'f', 'h', 'j', 'l', 'n', 'p'};
        char[] cBit = {'d', 'g', 'h', 'k', 'l', 'o', 'p'};
        char[] eBit = {'f', 'g', 'h', 'm', 'n', 'o', 'p'};
        char[] iBit = {'j', 'k', 'l', 'm', 'n', 'o', 'p'};

        Map<Character, char[]> bitmap = new HashMap<>();
        bitmap.put('b', bBit);
        bitmap.put('c', cBit);
        bitmap.put('e', eBit);
        bitmap.put('i', iBit);

        int b = 0, c = 0, e = 0, i = 0;
        
        List<Character> flips = new ArrayList<>();
        for(int j = 0; j < 7; j++) {
            b += block.get(bBit[j]);
            c += block.get(cBit[j]);
            e += block.get(eBit[j]);
            i += block.get(iBit[j]);
            if(j == 6) {
            if(b%2 != block.get('b')) flips.add('b');
            if(c%2 != block.get('c')) flips.add('c');
            if(e%2 != block.get('e')) flips.add('e');
            if(i%2 != block.get('i')) flips.add('i');
            }
        }
        if(flips.size() == 1) {
            block.put(flips.get(0), (block.get(flips.get(0)) == 1 ? 0 : 1));
        }
        else if(flips.size() > 1) {
            out:for(int j = 0; j < 7; j++) {
                char o = bitmap.get(flips.get(0))[j];
                for(int k = 0; k < 7; k++) {
                    char p = bitmap.get(flips.get(1))[k];
                    if(o == p) {
                        block.put(o, (block.get(o) == 1 ? 0 : 1));
                        break out;
                    }
                }
            }
        }
        String ans = "";
        for(int p : block.values()) ans += p;

        if (a%2 == 0 && flips.size() > 0) {
            System.out.println("TWO ERRORS");
            return;
        }
        else if(a%2 == 0 && flips.size() == 0) System.out.println(bits);
        else if(a%2 != 0 && flips.size() > 0) System.out.println(ans);

    }
}
