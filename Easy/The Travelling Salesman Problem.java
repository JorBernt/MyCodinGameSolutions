import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int x, y;
    Node nearest;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node nearest(List<Node> nodes) {
        double min = 100000;
        for(Node n: nodes) {
            if(distance(n) < min) {
            min = distance(n);
            nearest = n;
            }
        }
        return nearest;
    }

    public double distance(Node n) {
        return Math.sqrt(Math.pow(x - n.x, 2)+Math.pow(y-n.y, 2));
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            Node node = new Node(X, Y);
            nodes.add(node);
        }
        Node home = nodes.get(0);
        Node curNode = home;
        double ans = 0;
        while (nodes.size() > 0) {
            ans += curNode.distance(curNode.nearest(nodes));
            curNode = curNode.nearest(nodes);
            nodes.remove(curNode);
        }
        ans += curNode.distance(home);

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println((int)Math.round(ans));
    }
}
