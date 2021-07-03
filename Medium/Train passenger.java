import java.util.*;
import java.io.*;
import java.math.*;
import java.util.stream.*;
import static java.util.Collections.reverse;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solver {
    private Map<String, Station> stations;

    public Solver() {
        stations = new HashMap<>();
    }

    public void addStation(String input) {
        String[] in = input.split(" ");
        Station a = stations.getOrDefault(in[0], new Station(in[0]));
        Station b = stations.getOrDefault(in[1], new Station(in[1]));
        stations.put(a.name, a);
        stations.put(b.name, b);
        a.addConnection(b);
        b.addConnection(a);
    }

    public void solve(String start, String end) {
        System.out.println(findShortestPath(stations.get(start), stations.get(end)));
    }

    public String findShortestPath(Station start, Station end) {
        Queue<Station> toVisit = new LinkedList<>();
        List<Station> visited = new ArrayList<>();
        toVisit.add(start);
        Map<Station, List<Station>> paths = new HashMap<>();
        out: while (!toVisit.isEmpty()) {
            Station polled = toVisit.poll();
            if (visited.contains(polled))
                continue;
            visited.add(polled);
            List<Station> adj = polled.getConnections();
            for (Station a : adj) {
                List<Station> list = new ArrayList<>();
                list.add(polled);
                if (paths.containsKey(polled))
                    list.addAll(paths.get(polled));
                paths.put(a, list);
                if (paths.containsKey(end)) {
                    break out;
                }
            }
            toVisit.addAll(adj);
        }
        reverse(paths.get(end));
        return paths.get(end).stream().map(Station::toString).collect(Collectors.joining(" > ")) + " > " + end.name;
    }

    private class Station {

        private String name;
        private Set<Station> connections;

        public Station(String name) {
            this.name = name;
            connections = new HashSet<>();
        }

        public void addConnection(Station station) {
            connections.add(station);
        }

        public List<Station> getConnections() {
            return new ArrayList<>(connections);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Station station = (Station) o;
            return name.equals(station.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String start = in.nextLine();
        String end = in.nextLine();

        Solver solver = new Solver();

        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < n; i++) {
            String link = in.nextLine();
            solver.addStation(link);
        }

        solver.solve(start, end);
    }
}
