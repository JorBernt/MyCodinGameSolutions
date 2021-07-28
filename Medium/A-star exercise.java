import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private Node[] nodes;
    private int S, G, N;

    public Solver(int S, int N, int G) {
        this.S = S;
        this.G = G;
        this.N = N;
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
    }

    public void solve() {
        astar(nodes[S], G);
    }

    public void astar(Node start, int goal) {
        int[] fScore = new int[N];
        PriorityQueue<Node> set = new PriorityQueue<>(Comparator.comparingInt((Node a) -> fScore[a.id]).thenComparingInt(a -> a.id));
        Map<Node, Node> cameFrom = new HashMap<>();
        int[] gScore = new int[N];
        Arrays.fill(gScore, Integer.MAX_VALUE);
        gScore[start.id] = 0;
        Arrays.fill(fScore, Integer.MAX_VALUE);
        fScore[start.id] = start.hValue + gScore[start.id];

        set.add(start);

        while (!set.isEmpty()) {
            Node current = set.poll();
            System.out.println(current.id + " " + fScore[current.id]);
            if (current.id == goal) {
                return;
            }
            for (Edge e : current.edges) {
                int tentative_gScore = gScore[current.id] + e.weight;
                if (tentative_gScore < gScore[e.b.id]) {
                    cameFrom.put(e.b, current);
                    gScore[e.b.id] = tentative_gScore;
                    fScore[e.b.id] = gScore[e.b.id] + e.b.hValue;
                    if (!set.contains(e.b)) set.add(e.b);
                }
            }
        }
    }

    public void nodeDistToGoal(int dist, int index) {
        nodes[index].setHValue(dist);
    }

    public void addEdge(int start, int end, int weight) {
        nodes[start].addEdge(nodes[end], weight);
        nodes[end].addEdge(nodes[start], weight);
    }


    class Node {
        private List<Edge> edges;
        private int id, hValue;

        public Node(int id) {
            this.id = id;
            edges = new ArrayList<>();
        }

        public void setHValue(int val) {
            hValue = val;
        }

        public void addEdge(Node end, int weight) {
            edges.add(new Edge(end, weight));
        }

    }

    class Edge {
        private Node b;
        private int weight;

        public Edge(Node b, int weight) {
            this.b = b;
            this.weight = weight;
        }

    }

}


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int E = in.nextInt();
        int S = in.nextInt();
        int G = in.nextInt();
        Solver solver = new Solver(S, N, G);
        for (int i = 0; i < N; i++) {
            int node = in.nextInt();
            solver.nodeDistToGoal(node, i);
        }
        for (int i = 0; i < E; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = in.nextInt();
            solver.addEdge(x, y, c);
        }
        solver.solve();
    }
}
