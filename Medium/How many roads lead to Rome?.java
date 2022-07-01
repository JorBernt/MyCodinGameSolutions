import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, List<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            nodes.computeIfAbsent(a, l -> new ArrayList<>()).add(b);
            nodes.computeIfAbsent(b, l -> new ArrayList<>()).add(a);
        }

        int numerOfPaths = getAllPaths(nodes, new ArrayList<>(), 1);
        System.out.println(numerOfPaths);
    }

    private static int getAllPaths(Map<Integer, List<Integer>> nodes, List<Integer> visited, int node) {
        if (node == 100) {
            return 1;
        }
        if(visited.contains(node))
            return 0;
        int sum = 0;
        visited.add(node);
        for(int n : nodes.get(node)) {
            sum += getAllPaths(nodes, visited, n);
        }
        visited.remove(Integer.valueOf(node));
        return sum;
    }
}
