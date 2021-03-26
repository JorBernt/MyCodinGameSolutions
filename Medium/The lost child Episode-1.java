import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int x, y, distance;
    boolean path, visited, mother;
    List<Node> adjacent;

    public Node(int x, int y, char c) {
        this.x = x;
        this.y = y;
        path = c=='.';
        mother = false;
        adjacent = new ArrayList<>();
        distance = 0;
    }

    public void addAdjacent(Node[] nodes) {
        for(Node n:nodes) {
            if(x == n.x && y-1 == n.y) adjacent.add(n);
            if(x == n.x && y+1 == n.y) adjacent.add(n);
            if(x+1 == n.x && y == n.y) adjacent.add(n);
            if(x-1 == n.x && y == n.y) adjacent.add(n);
        }
    }
}

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Node[] nodes = new Node[100];
        int id = 0, childID = 0;
        for (int i = 0; i < 10; i++) {
            char[] row = in.nextLine().toCharArray();
            for(int j = 0; j < 10; j++) {
                Node node = new Node(j, i, row[j]);
                if(row[j]=='M') {node.mother = true;node.path=true;};
                if(row[j]=='C') childID = id;
                nodes[id] = node;
                id++;
            }
        }
        for(Node n:nodes) n.addAdjacent(nodes);
        System.out.println(bfs(nodes[childID])*10+"km");
    }

    static int bfs(Node node) {
        List<Node> toVisit = new ArrayList<>();
        Queue<Node> temp = new LinkedList<>();
        toVisit.addAll(node.adjacent);
        int distance = 1;
        while(true) {
            for(Node n:toVisit) {
                if(!n.visited && n.path) {
                    n.visited = true;
                    n.distance = distance;
                    temp.addAll(n.adjacent);
                }
                if(n.mother) return n.distance;
            }
            distance++;
            toVisit.addAll(temp);
            temp.clear();
        }
    }
}
