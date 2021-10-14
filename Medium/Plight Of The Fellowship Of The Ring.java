import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private int numberOfSpots, numberOfOrcs, numberOfPortals;
    private List<Node> nodes;
    private List<Node> orcs;
    private Node start, goal;
    private List<Integer> finalPath;

    public Solver(int numberOfSpots, int numberOfOrcs, int numberOfPortals) {
        this.numberOfSpots = numberOfSpots;
        this.numberOfOrcs = numberOfOrcs;
        this.numberOfPortals = numberOfPortals;
        nodes = new ArrayList<>();
        finalPath = new ArrayList<>();
        orcs = new ArrayList<>();
    }

    public void addSpots(int x, int y, int index) {
        Node node = new Node(x, y, index);
        if(nodes.size() > 0) {
            Node prev = nodes.get(nodes.size()-1);
            //node.from = prev;
            prev.to = node;
        }
        nodes.add(node);
    }

    public void addPortal(int from, int to) {
        Node f = getNode(from);
        Node t = getNode(to);
        if(f == null || t == null) return;
        f.portalTo = t;
        t.portalFrom = f;
        f.portal = true;
        t.portal = true;
    }

    public void addStartAndGoal(int start, int goal) {
        this.start = getNode(start);
        this.goal = getNode(goal);
    }

    private Node getNode(int index) {
        for(Node n : nodes) {
            if(n.index == index) return n;
        }
        return null;
    }

    public void addOrcSpots(int x, int y) {
        Node node = new Node(x, y, -1);
        node.orc = true;
        orcs.add(node);
    }

    public void solve() {
        findPath(start, new ArrayList<>());
    }

    public void print() {
        if(finalPath.isEmpty()) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        for(int i = 0; i < finalPath.size(); i++) {
            System.out.print(finalPath.get(i) + (i < finalPath.size() - 1 ? " ":""));
        }
    }

    private void findPath(Node node, List<Node> path) {

        if(node == null || orcsNearby(node, path.size()) || path.contains(node)) {
            return;
        }
        path.add(node);
        if(node.equals(goal)) {
            if(path.size() < finalPath.size() || finalPath.isEmpty()) {
                finalPath.clear();
                for(Node n : path) {
                    finalPath.add(n.index);
                }
                return;
            }
        }
        findPath(node.to, new ArrayList<>(path));
        if(node.portal) {
            findPath(node.portalFrom, new ArrayList<>(path));
            findPath(node.portalTo, new ArrayList<>(path));
        }
    }

    private boolean orcsNearby(Node node, int d) {
        for(Node n : orcs) {
            double dist = distance(n, node);
            if(dist <= d) return true;
        }
        return false;
    }

    private double distance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y,2));
    }

    class Node {
        int x, y, index;
        Node from, to, portalFrom, portalTo;
        boolean orc, portal;

        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            orc = false;
            portal = false;
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int L = in.nextInt();
        Solver solver = new Solver(N, M, L);
        for (int i = 0; i < N; i++) {
            int XS = in.nextInt();
            int YS = in.nextInt();
            solver.addSpots(XS, YS, i);
        }
        for (int i = 0; i < M; i++) {
            int XO = in.nextInt();
            int YO = in.nextInt();
            solver.addOrcSpots(XO, YO);
        }
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt();
            int N2 = in.nextInt();
            solver.addPortal(N1, N2);
        }
        int S = in.nextInt();
        int E = in.nextInt();
        solver.addStartAndGoal(S, E);
        solver.solve();
        solver.print();

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
