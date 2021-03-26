import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int value, parent, left, right;
    String type;
    public Node(int value, int parent, String type) {
        this.value = value;
        this.parent = parent;
        this.type = type;
    }
} 
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int V = in.nextInt();
        int M = in.nextInt();
        List<Node> nodes = new ArrayList<>();
        int root = 0;
        for (int i = 0; i < M; i++) {
            int P = in.nextInt();
            int L = in.nextInt();
            int R = in.nextInt();
            if(i == 0) root = P;
            Node left = new Node(L, P, "Left");
            Node right = new Node(R, P, "Right");
            nodes.add(left);
            nodes.add(right);
        }
        if(V == root) {
            System.out.print("Root");
            return;
        }
        List<String> path = new ArrayList<>();
        while(V != root) {
            Node node = findNode(nodes, V);
            path.add(node.type);
            V = node.parent;
        }

        Collections.reverse(path);
        System.out.println(String.join(" ", path));
    }
    public static Node findNode(List<Node> nodes, int value) {
        for(Node n : nodes) {
            if (n.value == value) return n;
        }
        return null;
    }
}
