import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Road {
    int x,y,group;
    boolean visited, town;
    List<Road> adjacent;
    char c;

    public Road(int y, int x, boolean town,char c) {
        this.x = x;
        this.y = y;
        this.c = c;
        visited = false;
        this.town = town;
        adjacent = new ArrayList<>();
        group = -1;
    }

    public List<Road> getAdjacent(List<Road> roads) {
        List<Road> adjacent = new ArrayList<>();
        for(Road r:roads) {
            if(r.x==x && r.y==y+1 && (r.c == (town?Character.toLowerCase(c):c) || (r.town&&Character.toLowerCase(r.c)==c))) adjacent.add(r);
            if(r.x==x && r.y==y-1 && (r.c == (town?Character.toLowerCase(c):c) || (r.town&&Character.toLowerCase(r.c)==c))) adjacent.add(r);
            if(r.x==x-1 && r.y==y && (r.c == (town?Character.toLowerCase(c):c) || (r.town&&Character.toLowerCase(r.c)==c))) adjacent.add(r);
            if(r.x==x+1 && r.y==y && (r.c == (town?Character.toLowerCase(c):c) || (r.town&&Character.toLowerCase(r.c)==c))) adjacent.add(r);
        }
        return adjacent;
    }
}

class Solution {
    static int N = 0;
    static int longest = 0;
    static char longestC = 'a';
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Road> roads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] line = in.nextLine().toCharArray();
            for(int j = 0; j < line.length;j++) {
                if(line[j] != '#') {
                    Road road = new Road(i,j,Character.isUpperCase(line[j]), line[j]);
                    roads.add(road);
                }
            }
        }
        Map<Integer, List<Road>> roadPaths = new HashMap<>();

        for(Road r : roads) if(!r.town) findAllPaths(r,roads,roadPaths);

        for(Map.Entry<Integer, List<Road>> entry : roadPaths.entrySet()) {
            for(Road r : entry.getValue()) {
                findLongest(r, roads, new ArrayList<>(), 0);
            }
        }
        if(longest < 5) {
            System.out.println(0);
            return;
        }
        System.out.println(Character.toUpperCase(longestC)+" "+longest);
    }

    static void findLongest(Road road, List<Road> roads, List<Road> visited, int length) {
        if(visited.contains(road)) {
            return;
        }
        if(!road.town)length++;
        if(length > longest) {
            longest = length;
            longestC = road.c;
        }
        visited.add(road);
        for(Road a:road.getAdjacent(roads)) {
            findLongest(a, roads, visited, length);
        }
        visited.remove(road);
    }

    static void findAllPaths(Road road, List<Road> roads,Map<Integer, List<Road>> roadPaths) {
        if(road.visited) {
            return;
        }
        N++;
        Queue<Road> toVisit = new LinkedList<>();
        toVisit.add(road);
        while(!toVisit.isEmpty()) {
            Road polled = toVisit.poll();
            if(polled.visited) continue;
            polled.visited = true;
            if(!polled.town) {
                if(!roadPaths.containsKey(N)) {
                    roadPaths.put(N, new ArrayList<>());
                }
                roadPaths.get(N).add(polled);
            }
            polled.group = N;
            toVisit.addAll(polled.getAdjacent(roads));
        }
    }
}
