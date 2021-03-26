import java.util.*;

import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    int x, y;
    boolean visited, path, exit;
    List<Node> adjacent;
    
    public Node(int x, int y, char c) {
        this.x = x;
        this.y = y;
        path = c=='.';
        adjacent = new ArrayList<>();
        visited = false;
    }
    public void addAdjacent(Node[] nodes) {
        for(Node n:nodes) {
            if(x == n.x && y-1 == n.y) if(n.path) adjacent.add(n);
            if(x == n.x && y+1 == n.y) if(n.path) adjacent.add(n);
            if(x-1 == n.x && y == n.y) if(n.path) adjacent.add(n);
            if(x+1 == n.x && y == n.y) if(n.path) adjacent.add(n);
        }
    }
    public void isExit() {
        exit = path;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        int X = in.nextInt();
        int Y = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Node[] nodes = new Node[H*W];
        int id = 0, startID = 0;
        for (int i = 0; i < H; i++) {
            char[] R = in.nextLine().toCharArray();
            for(int j = 0; j < W; j++) {
                Node node = new Node(j, i, R[j]);
                if(X==j && Y==i) startID = id;
                if(i==0 || i == H-1 || j == 0 || j == W-1) node.isExit();
                nodes[id] = node;
                id++;
            }
        }
        for(Node n:nodes) n.addAdjacent(nodes);
        List<Node> exits = bfs(nodes[startID]);
        System.out.print(exits.size());
        if(exits.size()!=0) {
            System.out.println();
               Collections.sort(exits, new Comparator(){
                public int compare(Object o1, Object o2){
                    Integer x1 = ((Node)o1).x;
                    Integer x2 = ((Node)o2).x;
                    int comp = x1.compareTo(x2);
                    if (comp != 0) return comp;
                    Integer y1 = ((Node)o1).y;
                    Integer y2 = ((Node)o2).y;
                    return y1.compareTo(y2);
                }});
            for(Node n:exits) {
                System.out.println(n.x + " "+n.y);
            }
        }
    }

    static List<Node> bfs(Node node) {
        Queue<Node> toVisit = new LinkedList<>();
        List<Node> exits = new ArrayList<>();
        toVisit.add(node);
        while(!toVisit.isEmpty()) {
            Node polled = toVisit.poll();
            if(!polled.visited) {
                polled.visited = true;
                if(polled.exit) exits.add(polled);
                toVisit.addAll(polled.adjacent);
            }
        }
        return exits;
    }
}
