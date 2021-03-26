import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int val;
    List<Node> children;

    public Node(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}
class Solution {
    static int max;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence
        max = 0;
        Map<Integer, Node> nodes = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();
            Node X = null;
            Node Y = null;
            if(!nodes.containsKey(x)) {
                X = new Node(x);
                nodes.put(x, X);
            }
            else {
                X = nodes.get(x);
            }
            if(!nodes.containsKey(y)) {
                Y = new Node(y);
                nodes.put(y, Y);
            }
            else {
                Y = nodes.get(y);
            }
            X.children.add(Y);
        }

        for(Map.Entry<Integer, Node> node : nodes.entrySet()) {
            traverse(node.getValue(), 1);
        }

        System.out.println(max);
    }

    static void traverse(Node node, int path) {
        if(node.children.isEmpty()) {
            max = Math.max(max, path);
            return;
        }
        for(Node n:node.children) {
            traverse(n, path+1);
        }
    }
}
