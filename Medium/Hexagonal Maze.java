import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Solver solver = new Solver(w, h);
        for (int i = 0; i < h; i++) {
            String row = in.nextLine();
            solver.addRow(row, i);

        }
        solver.solve();
    }
}

class Solver {
    Map map;

    public Solver(int w, int h) {
        map = new Map(w, h);
    }

    public void addRow(String row, int col) {
        map.addRow(row, col);
    }

    public void solve() {
        map.addAdjacent();
        map.solve();
        map.print();
    }
}

class Map {
    Node[][] grid;
    List<Node> nodes;
    int h, w;

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        grid = new Node[h][w];
        nodes = new ArrayList<>();
    }

    public void solve() {
        Node start = findStart();
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(start);
        Node end = null;
        while (!toVisit.isEmpty()) {
            Node n = toVisit.poll();
            if(n.end) {
                end = n;
                break;
            }
            List<Node> curPath = n.pathTo;

            n.visited = true;
            curPath.add(n);
            for(Node a : n.adjacent) {
                if(!a.pathTo.isEmpty() && a.pathTo.size() < curPath.size()) {
                    continue;
                }
                if(a.wall || a.visited)
                    continue;
                a.pathTo.clear();
                a.pathTo.addAll(new ArrayList<>(curPath));
                toVisit.add(a);
            }

        }
        for(Node n : end.pathTo) {
            n.val = '.';
        }
        start.val = 'S';
    }

    private Node findStart() {
        for (Node n : nodes)
            if (n.start)
                return n;
        return null;
    }

    public void print() {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(grid[i][j].val);
            }
            System.out.println();
        }
    }

    public void addRow(String row, int col) {
        for (int i = 0; i < row.length(); i++) {
            Node node = new Node(i, col, row.charAt(i) == '#', row.charAt(i) == 'S', row.charAt(i) == 'E', row.charAt(i));
            grid[col][i] = node;
            nodes.add(node);
        }
    }

    public void addAdjacent() {
        for (Node n : nodes)
            addAdjacent(n);
    }

    private void addAdjacent(Node node) {
        for (Node n : nodes) {
            if (n.equals(node)) continue;
            {
                int x = node.x - 1;
                if (x < 0) x += w;
                if (x == n.x && node.y == n.y)
                    node.adjacent.add(n);
            }
            {
                int x = node.x + 1;
                x %= w;
                if (x == n.x && node.y == n.y)
                    node.adjacent.add(n);
            }
            {
                int y = node.y + 1;
                y %= h;
                if (y == n.y && node.x == n.x)
                    node.adjacent.add(n);
            }
            {
                int y = node.y - 1;
                if (y < 0) y += h;
                if (y == n.y && node.x == n.x)
                    node.adjacent.add(n);
            }
            {
                if (node.y % 2 == 0) {
                    int y = node.y + 1;
                    int x = node.x - 1;
                    if (x < 0) x += w;
                    y %= h;
                    if (y == n.y && x == n.x)
                        node.adjacent.add(n);
                } else {
                    int y = node.y + 1;
                    int x = node.x + 1;
                    x %= w;
                    y %= h;
                    if (y == n.y && x == n.x)
                        node.adjacent.add(n);
                }
            }
            {
                if (node.y % 2 == 0) {
                    int y = node.y - 1;
                    if (y < 0) y += h;
                    int x = node.x - 1;
                    if (x < 0) x += w;
                    if (y == n.y && x == n.x)
                        node.adjacent.add(n);
                } else {
                    int y = node.y - 1;
                    int x = node.x + 1;
                    x %= w;
                    y %= h;
                    if (y == n.y && x == n.x)
                        node.adjacent.add(n);
                }
            }
        }
    }

    static class Node {
        int x, y;
        boolean wall, start, end, visited;
        char val;
        List<Node> adjacent;
        List<Node> pathTo;

        public Node(int x, int y, boolean wall, boolean start, boolean end, char val) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.start = start;
            this.end = end;
            this.val = val;
            visited = false;
            adjacent = new ArrayList<>();
            pathTo = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
