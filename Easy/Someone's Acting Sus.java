import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    int roomLength;
    String order;
    List<String> crew;

    public Solver(int l, String order) {
        roomLength = l;
        this.order = order;
        crew = new ArrayList<>();
    }

    public void addCrew(String s) {
        crew.add(s);
    }

    public void solve() {
        for(String s : crew) {
            System.out.println((!isSus(s) ? "NOT " : "") + "SUS");
        }
    }

    private boolean isSus(String s) {
        char[] movement = s.toCharArray();
        char current = ' ';
        for(char c : movement) {
            if(c != '#') {
                current = c;
                break;
            }
        }
        int lastIndex = 0;
        for(int i = 1; i < movement.length; i++) {
            if(movement[i] != '#') {
                if(!isLegal(current, lastIndex, i, movement[i])) return true;
                current = movement[i];
                lastIndex = i;
            }
        }
        return false;
    }

    private boolean isLegal(char prev, int lastIndex, int curIndex, char current) {
        if(prev == current) return true;
        int pInd = Math.max(order.indexOf(prev), order.indexOf(current));
        int cInd = Math.min(order.indexOf(prev), order.indexOf(current));
        int right = order.length() - pInd + cInd;
        int left = Math.abs(pInd-cInd);
        int movement = curIndex-lastIndex;
        return movement >= right || movement >= left;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String F = in.nextLine();
        int N = in.nextInt();
        int K = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Solver solver = new Solver(L, F);
        for (int i = 0; i < N; i++) {
            String crewmate = in.nextLine();
            solver.addCrew(crewmate);
        }
        solver.solve();
    }
}
