import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Valley {
    int id, size;
    List<Integer> heights;

    public Valley(int id, int height) {
        heights = new ArrayList<>();
        heights.add(height);
        this.id = id;
        size = 1;
    }

    public void addHeight(int height) {
        heights.add(height);
        size++;
    }
    public int lowestPoint() {
        int min = Integer.MAX_VALUE;
        for(int i : heights) min = Math.min(min, i);
        return min;
    }
} 

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int N = in.nextInt();
        int[][] map = new int[N][N];
        int id = 1;
        Map<Integer, Valley> valleys = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int h = in.nextInt();
                if(h <= H) {
                    if(i==0 && j>0) {
                        if(map[i][j-1]==0) {
                            Valley valley = new Valley(id, h);
                            valleys.put(id, valley);
                            map[i][j] = id;
                            id++;
                        }
                        else {
                            valleys.get(map[i][j-1]).addHeight(h);
                            map[i][j] = map[i][j-1];
                        }
                    }
                    if(i > 0 && j > 0) {
                        if(map[i][j-1]==0 && map[i-1][j]==0) {
                            Valley valley = new Valley(id, h);
                            valleys.put(id, valley);
                            map[i][j] = id;
                            id++;
                        }
                        else {
                            if(map[i][j-1] != 0) {
                                valleys.get(map[i][j-1]).addHeight(h);
                                map[i][j] = map[i][j-1];
                            }
                            if(map[i-1][j] != 0) {
                                valleys.get(map[i-1][j]).addHeight(h);
                                map[i][j] = map[i-1][j];
                            } 
                        }
                    }
                }
            }
        }

        int largest = 0, min = Integer.MAX_VALUE;;
        for(int i : valleys.keySet()) {
            if(valleys.get(i).size > largest) {
                largest = valleys.get(i).size;
            }
        }
        List<Valley> equal = new ArrayList<>();
        for(int i : valleys.keySet()) {
            if(valleys.get(i).size == largest) {
                equal.add(valleys.get(i));
            }
        }
        for(Valley v : equal) {
            min = Math.min(min, v.lowestPoint());
        }
        
        System.out.println(equal.isEmpty() ? 0:min);
    }
}
