import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int x,y,d;
    boolean path,exit;
    Node down, right;

    public Node(int x, int y, int val, boolean exit) {
        this.x = x;
        this.y = y;
        path = val==0;
        this.exit = exit;
    }

    public void addPath(Node[] nodes) {
        for(Node n:nodes) {
            if(n.path) {
                if(x == n.x && y+1 == n.y) down = n;
                if(x+1 == n.x && y == n.y) right = n;
            }

        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        Node[] nodes = new Node[M*N];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int id = 0;
        for (int i = 0; i < M; i++) {
            String ROW = in.nextLine();
            for(int j = 0; j < N; j++) {
                Node node = new Node(j, i, Character.getNumericValue(ROW.charAt(j)), false);
                if(i==M-1 && j==N-1) node.exit = true;
                nodes[id++] = node;
            }
        }
        for(Node n:nodes) n.addPath(nodes);
        traverse(nodes[0]);
        System.out.println(nodes[id-1].d);
    }

    static void traverse(Node node) {
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(node);
        while(!toVisit.isEmpty()) {
            Node current = toVisit.poll();
            if(current.right!=null)
            toVisit.add(current.right);
            if(current.down!=null)
            toVisit.add(current.down);
            if(current.exit) current.d++;
        }
    }
}
