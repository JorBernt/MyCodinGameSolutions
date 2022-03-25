import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Solution {
    static Map<Integer, Node> nodes = new HashMap<>();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            Node a = nodes.getOrDefault(A, new Node(A));
            Node b = nodes.getOrDefault(B, new Node(B));
            nodes.put(A, a);
            nodes.put(B, b);
            a.addAdjacent(b);
            b.addAdjacent(a);
        }
        int longestPath = 0;
        for(Node node : nodes.values()) {
            longestPath = Math.max(longestPath, findLongestPath(node));
        }
        System.out.println(longestPath / 2);
    }

    public static int findLongestPath(Node n) {
        return findLongestPath(n, new ArrayList<>());
    }

    private static int findLongestPath(Node node, List<Node> visited) {
        if(visited.contains(node)) return 0;
        visited.add(node);
        int max = 0;
        for(Node n : node.getAdjacent()) {
            max = Math.max(max, findLongestPath(n, visited));
        }
        return max + 1;
    }
}

class Node {
    private List<Node> adjacent;
    private int id;

    public Node(int id) {
        this.id = id;
        adjacent = new ArrayList<>();
    }

    public void addAdjacent(Node n) {
        adjacent.add(n);
    }

    public List<Node> getAdjacent() {
        return adjacent;
    }

}
