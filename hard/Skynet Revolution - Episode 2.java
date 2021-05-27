import java.util.*;
import java.io.*;
import java.math.*;

class Node {
    boolean gateway;
    List<Node> adj;
    Map<Node, Integer> distToNodes;
    int index;

    public Node(int index) {
        adj = new ArrayList<>();
        this.index = index;
        distToNodes = new HashMap<>();
    }

    public void link(Node n) {
        adj.add(n);
    }

    public int getLinks() {
        int links = 0;
        for(Node n : adj) if(n.gateway) links++;
        return links;
    }
}


class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        Node[] nodes = new Node[N];
        for(int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            nodes[N1].link(nodes[N2]);
            nodes[N2].link(nodes[N1]);
        }
        Node[] gateways = new Node[E];
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            nodes[EI].gateway = true;
            gateways[i] = nodes[EI];
        }
        for(Node n : nodes) {
            calcDist(n);
            n.distToNodes.put(n,0);
        }
        for(Node g : gateways) {
            for(Node a : g.adj) {
                a.distToNodes.put(g,1);
            }
        }
        // game loop
        out:while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            Node cur = nodes[SI];
            for(Node a : cur.adj) { // If there is a direct link to a gateway, sever it.
                if(a.gateway) {
                    System.out.println(a.index+" "+cur.index);
                    cur.adj.remove(a);
                    a.adj.remove(cur);
                    continue out;
                }
            }
            Map<Integer, Node[]> possible = new HashMap<>();
            int closest = Integer.MAX_VALUE;
            int maxlinks = 0;
            for(Node g : gateways) { 
                for(Node a : g.adj) {
                    int links = a.getLinks();
                    List<Node> path = getPath(cur, a, new ArrayList<>());
                    int d = 0;
                    for(Node p : path) {
                        if(p.getLinks() == 0) d++;
                    }
                    if(d <= links && links >= maxlinks) {
                        int dist = a.distToNodes.get(cur);
                        if(d == links-1) dist = -1;
                        possible.put(dist, new Node[]{g,a});
                        closest = Math.min(closest, dist);
                        maxlinks = links;
                    }
                }
            }
            if(possible.isEmpty()) {
                for(Node g : gateways) {
                    if(!g.adj.isEmpty()) {
                        for(Node a : g.adj) {
                            System.out.println(g.index + " "+a.index);
                            a.adj.remove(g);
                            g.adj.remove(a);
                            continue out;
                        }
                    }
                }
            }
            Node[] a = possible.get(closest);
            System.out.println(a[0].index + " "+a[1].index);
            a[1].adj.remove(a[0]);
            a[0].adj.remove(a[1]);
        }
    }

    static void calcDist(Node root) {
        Queue<Node> toVisit = new LinkedList<>();
        List<Node> visited = new ArrayList<>();
        visited.add(root);
        root.distToNodes.put(root,0);
        toVisit.add(root);
        int dist = 1;
        while(!toVisit.isEmpty()) {
            List<Node> adj = new ArrayList<>();
            while(!toVisit.isEmpty()) {
                Node polled = toVisit.poll();
                for(Node a : polled.adj) {
                    if(!visited.contains(a) && !adj.contains(a) && !a.gateway) {
                        adj.add(a);
                    }
                }
            }
            for(Node n : adj) {
                root.distToNodes.put(n,dist);
                visited.add(n);
                toVisit.add(n);
            }
            dist++;
        }
    }

    static List<Node> getPath(Node start, Node end, List<Node> path) {
        if(path.isEmpty())
            path.add(start);

        if(start == end) return path;
        Node temp = null;
        int dist = Integer.MAX_VALUE;
        for(Node a : start.adj) {
            if(a != end && a.gateway) continue;
            int n = a.distToNodes.get(end);
            if(n < dist) {
                dist = n;
                temp = a;
            }
        }
        path.add(temp);
        return getPath(temp, end, path);
    }
}
