import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    Node up, down, left, right;
    int x, y;
    char type;
    boolean visited;

    public Node(char type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        visited = false;
    }
    public void addNeighbours(List<Node> nodes, int w, int h) {
        for(Node n : nodes) {
            if(x+1 == n.x && y == n.y) right = n;
            else if(x-1 == n.x && y == n.y) left = n;
            else if(y+1 == n.y && x == n.x) down = n;
            else if(y-1 == n.y && x == n.x) up = n;
            
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int startRow = in.nextInt();
        int startCol = in.nextInt();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<List<Node>> maps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Node> map = new ArrayList<>();
            for (int j = 0; j < h; j++) {
                String mapRow = in.nextLine();
                int x = 0;
                for(char c : mapRow.toCharArray()) {
                    Node node = new Node(c, x++, j);
                    map.add(node);
                }
            }
            maps.add(map);
        }
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> distances = new HashMap<>();
        int k = 0;
        for(List<Node> map : maps) {
            Node start = null;
            for(Node node : map) {
                node.addNeighbours(map, w, h);
                if(node.x == startCol && node.y == startRow) {
                start = node;
                }
            }
            int dist = traverse(start, 0);
            min = Math.min(min, dist); 
            distances.put(k++, dist);
        }
        if(min == Integer.MAX_VALUE) {
            System.out.println("TRAP");
            return;
        }
  
        for(int i : distances.keySet()) {
            if(distances.get(i) == min) {
                System.out.println(i);
            }
        }
    }

    public static int traverse(Node node, int distance) {
        distance++;
        node.visited = true;
        if(node.type == 'T') return distance;

        if(node.type == '^') {
            if(node.up != null && !node.up.visited)
            return traverse(node.up, distance);
        }
        if(node.type == '>') {
            if(node.right != null && !node.right.visited)
            return traverse(node.right, distance);
        }
        if(node.type == 'v') {
            if(node.down != null && !node.down.visited)
            return traverse(node.down, distance);
        }
        if(node.type == '<') {
            if(node.left != null && !node.left.visited)
            return traverse(node.left, distance);
        }
 
    return Integer.MAX_VALUE;
    }
}
