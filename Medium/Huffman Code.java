import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int val;
    Node left, right;

    public Node(int val) {
        this.val = val;
    }
}

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PriorityQueue<Node> nodes = new PriorityQueue<>((a,b)->Integer.compare(a.val,b.val));
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(in.nextInt()));
        }
        while(true) {
            if(nodes.size() == 1) break;
            Node a = nodes.poll();
            Node b = nodes.poll();
            Node parent = new Node(a.val+b.val);
            parent.left = a;
            parent.right = b;
            nodes.add(parent);
        }
        Node root = nodes.poll();
        System.out.println(sumBits(root,0));

    }

    static int sumBits(Node node, int sum) {
        if(node == null) return 0;
        if(node.left == null && node.right == null) {
            sum = Math.max(1,sum);
            sum*=node.val;
            return sum;
        }
        return sumBits(node.left, sum+1) + sumBits(node.right, sum+1);
    }
}
