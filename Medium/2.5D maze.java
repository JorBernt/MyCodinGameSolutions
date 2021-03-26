import java.util.*;
import java.io.*;
import java.math.*;

class Node {
    int x, y, id;
    char type;
    boolean start, exit, visited, chosen;
    List<Node> adj;
    List<Node> pathNodes;
    public Node(int x, int y, char type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
        start = false;
        exit = false;
        visited = false;
        adj = new ArrayList<>();
        pathNodes = new ArrayList<>();
        chosen = false;
    }

    public void addChildren(Node[] nodes) {
        for(Node n: nodes) {
                if(n.x == x && n.y == y-1){
                    if(n.type == '+' && (type == '+' || type == '|'))
                        adj.add(n);
                    if(n.type == '.' && (type == '.' || type == '|' || type == 'X'))
                        adj.add(n);
                    if(n.type == '|' || n.type == 'X')
                        adj.add(n);
                    if(n.type == 'O' && (type == '.' || type == '|'))    
                        adj.add(n);

                }
                if(n.x == x && n.y == y+1){
                    if(n.type == '+' && (type == '+' || type == '|'))
                        adj.add(n);
                    if(n.type == '.' && (type == '.' || type == '|' || type == 'X'))
                        adj.add(n);
                    if(n.type == '|' || n.type == 'X')
                        adj.add(n);
                    if(n.type == 'O' && (type == '.' || type == '|'))    
                        adj.add(n);     
                }
                if(n.x == x-1 && n.y == y) {
                    if(n.type == '+' && (type == '+' || type == '-'))
                        adj.add(n);
                    if(n.type == '.' && (type == '.' || type == '-'|| type == 'X'))
                        adj.add(n);
                    if(n.type == '-' || n.type == 'X')
                        adj.add(n);
                    if(n.type == 'O' && (type == '.' || type == '-'))    
                        adj.add(n);    
                }
                if(n.x == x+1 && n.y == y) {
                    if(n.type == '+' && (type == '+' || type == '-'))
                        adj.add(n);
                    if(n.type == '.' && (type == '.' || type == '-'|| type == 'X'))
                        adj.add(n);
                    if(n.type == '-' || n.type == 'X')
                        adj.add(n);
                    if(n.type == 'O' && (type == '.' || type == '-'))    
                        adj.add(n);
                }
            }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int starty = in.nextInt();
        int startx = in.nextInt();
        int endy = in.nextInt();
        int endx = in.nextInt();
        int h = in.nextInt();
        int w = in.nextInt();
        int[][] map = new int[h][w];
        Node[] nodes = new Node[h*w];
        int id = 0, startID=0, exitID=0;
        for (int i = 0; i < h; i++) {
            String line = in.next();
            for(int j = 0; j < w; j++) {
                Node node = new Node(j, i, line.charAt(j), id);
                if(i == starty && j == startx){
                     node.start = true;
                     startID = id;
                }
                if(i == endy && j == endx) {
                    node.exit = true;
                    exitID = id;
                }
                nodes[id] = node;
                map[i][j] = id;
                id++;
            }
        }
        
        for(Node n:nodes) {
            n.addChildren(nodes);
        }
        
        List<Node> shortestPath = findDistance(nodes[startID]);
        System.out.println(shortestPath.size()+1);
        for(Node n:shortestPath) {
            nodes[n.id].chosen = true;
        }

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.err.print(nodes[map[i][j]].chosen?"*":nodes[map[i][j]].type);
            }
            System.err.println();
        }
        
    }

   

    static List<Node> findDistance(Node start) {
        List<Node> toVisit = new ArrayList<>();
        Stack<Node> temp = new Stack<>();
        toVisit.addAll(start.adj);
        while(true) {
            for(Node n:toVisit){
                if(!n.visited) {
                    n.visited = true;
                    for(Node nn:n.adj) {
                        if(nn.pathNodes.isEmpty()) {
                        nn.pathNodes.addAll(n.pathNodes);
                        nn.pathNodes.add(n);
                        }
                        temp.add(nn);
                    }
                if(n.exit) return n.pathNodes;

                }
            }
            while(!temp.isEmpty()) {
                toVisit.add(temp.pop());
            }
        }

    }   
}
