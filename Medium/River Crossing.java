import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
     static List<String> shortestPath = new ArrayList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char[] INITIAL = in.nextLine().replace(" ", "").toCharArray();
        char[] TARGET = in.nextLine().replace(" ", "").toCharArray();
        rec(INITIAL, TARGET, new ArrayList<>());
        for (String s : shortestPath) {
            System.out.println(String.join(" ", s.split("")));
        }
    }

    static void rec(char[] init, char[] target, List<String> path) {
        String str = String.valueOf(init);
        if (path.contains(str) || (shortestPath.size() != 0 && path.size() > shortestPath.size()) || !valid(init))
            return;
        path.add(String.valueOf(init));
        if (Arrays.equals(target, init)) {
            if (path.size() < shortestPath.size() || shortestPath.size() == 0)
                shortestPath = path;
            else if (path.size() == shortestPath.size()) {
                for (int i = 0; i < path.size(); i++)
                    if (path.get(i).compareTo(shortestPath.get(i)) > 0)
                        return;
                shortestPath = path;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (init[i] == init[0]) {
                init[i] = init[i] == 'L' ? 'R' : 'L';
                if(i > 0)
                    init[0] = init[0] == 'L' ? 'R' : 'L';

                rec(init, target, new ArrayList<>(path));

                init[i] = init[i] == 'L' ? 'R' : 'L';
                if(i > 0)
                    init[0] = init[0] == 'L' ? 'R' : 'L';
            }
        }
    }

    static boolean valid(char[] ca) {
        return (ca[1] != ca[2] || ca[0] == ca[1]) &&
                (ca[2] != ca[3] || ca[0] == ca[2]);
    }
}
