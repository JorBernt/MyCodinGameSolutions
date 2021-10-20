import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    private int x, y, z, goalDistance;
    private boolean passable;
    private List<Node> adjacent;

    public Node(int x, int y, int z, boolean passable) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.passable = passable;
        goalDistance = -1;
    }

    public List<Node> getAdjacent(List<Node> nodes) {
        if(adjacent != null) return adjacent;
        adjacent = new ArrayList<>();
        for(Node n : nodes) {
            if(n.x == x && n.y == y && n.z == z - 1 && n.passable) adjacent.add(n);
            if(n.x == x && n.y == y && n.z == z + 1 && n.passable) adjacent.add(n);
            if(n.x == x - 1 && n.y == y && n.z == z && n.passable) adjacent.add(n);
            if(n.x == x + 1 && n.y == y && n.z == z && n.passable) adjacent.add(n);
            if(n.x == x && n.y == y - 1 && n.z == z && n.passable) adjacent.add(n);
            if(n.x == x && n.y == y + 1 && n.z == z && n.passable) adjacent.add(n);
        }
        return adjacent;
    }

    public void setGoalDistance(int distance) {
        goalDistance = distance;
    }

    public int getDistance() {
        return goalDistance;
    }

    public void setPassable() {
        passable = true;
    }

    public boolean isPassable() {
        return passable;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public int Z() {
        return z;
    }
}

class Solver {
    private int maxLevels;
    private Node start, goal;
    private List<Node> nodes;

    public Solver(int levels, int h, int w) {
        maxLevels = levels;
        nodes = new ArrayList<>();
    }

    public void addMap(String... lines) {
        int level = -1;
        int y = 0;
        for (String s : lines) {
            if (s.length() == 0) {
                y = 0;
                level++;
                continue;
            }
            for (int x = 0; x < s.length(); x++) {
                char c = s.charAt(x);
                Node node = new Node(x, y, level, c == '.');
                if (c == 'A') {
                    start = node;
                    node.setPassable();
                } else if (c == 'S') {
                    goal = node;
                    goal.setGoalDistance(0);
                    
                }
                nodes.add(node);
            }
            y++;
        }
    }

    public void solve() {
        calcDistance();
        int minPath = start.getDistance();
        System.out.println(minPath == -1 ? "NO PATH" : minPath);
    }

    private void calcDistance() {
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.addAll(goal.getAdjacent(nodes));
        start.getAdjacent(nodes);
        int distance = 1;
        while (!toVisit.isEmpty()) {
            List<Node> temp = new ArrayList<>();
            while (!toVisit.isEmpty()) {
                Node node = toVisit.poll();
                node.setGoalDistance(distance);
                for (Node n : node.getAdjacent(nodes)) {
                    if(n.getDistance() == -1 && !temp.contains(n)) temp.add(n);
                }
            }
            toVisit.addAll(temp);
            distance++;
        }
    }


}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int R = in.nextInt();
        int C = in.nextInt();
        int ln = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Solver solver = new Solver(L, R, C);
        String[] lines = new String[ln];
        for (int i = 0; i < ln; i++) {
            lines[i] = in.nextLine();
        }
        solver.addMap(lines);
        solver.solve();

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
