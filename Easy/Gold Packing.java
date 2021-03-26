import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Pack {
    int size;
    List<Integer> bars;
    
    public Pack() {
        size = 0;
        bars = new ArrayList<>();
    }
}

class Solution {
    static List<Pack> sets;
    static int max;
    static int closest;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        closest = Integer.MAX_VALUE;
        max = m;
        sets = new ArrayList<>();
        int[] bars = new int[n];
        for (int i = 0; i < n; i++) {
            bars[i] = in.nextInt();
        }
        pack(bars, 0, new Pack(), 0);

        int minSize = Integer.MAX_VALUE;
        for(Pack s : sets) {
            if(max-s.size == closest) {
                minSize = Math.min(minSize, s.bars.size());
            }
        }
        List<List<Integer>> possible = new ArrayList<>();
        for(Pack s : sets) {
            if(max-s.size == closest) {
                if(s.bars.size() == minSize) possible.add(s.bars);
            }
        }
        for(List<Integer> l : possible) {
            Collections.sort(l);
        }
        int ans = 0;
        List<Integer> ret = new ArrayList<>();
        if(possible.size() > 1) {
            for(int i = 0; i < minSize; i++) {
                int cur = possible.get(0).get(i);
                for(int j = 0; j < possible.size(); j++) {
                    if(possible.get(j).get(i) < cur) ans = j;
                }
            }
            ret = possible.get(ans);
        }
        else ret = possible.get(0);

        for(int i = 0; i < ret.size(); i++) {
            System.out.print(ret.get(i));
            if(i<ret.size()-1) System.out.print(" ");
        }

        
    }

    static void pack(int[] bars, int n, Pack pack, int size) {
        if(n==bars.length){
            if(max-pack.size == closest) {
                sets.add(pack);
            }
            return;
        }
        Pack skip = new Pack();
        skip.bars.addAll(pack.bars);
        skip.size += pack.size;
        pack(bars, n+1, skip, size);
        if(bars[n]+pack.size <= max) {
            Pack pick = new Pack();
            pick.bars.addAll(pack.bars);
            pick.bars.add(bars[n]);
            pick.size+=bars[n]+pack.size;
            closest = Math.min(closest, Math.max(max-pick.size,0));
            pack(bars, n+1, pick, size);
        }
    } 
}
