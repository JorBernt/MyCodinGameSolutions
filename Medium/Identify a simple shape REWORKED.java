import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    static class Corner {
        int y, x;

        public Corner(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Corner corner = (Corner) o;
            return y == corner.y && x == corner.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        boolean[][] grid = new boolean[20][20];
        int count = 0;
        for (int i = 0; i < 20; i++) {
            String line = in.nextLine().replace(" ", "");
            for (int j = 0; j < 20; j++) {
                grid[i][j] = line.charAt(j) == '#';
                if (grid[i][j])
                    count++;
            }
        }
        List<Corner> corners = new ArrayList<>();
        outer:
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (grid[i][j]) {
                    corners.add(new Corner(i, j));
                    for (int x = 19; x > j; x--) {
                        if (checkVerticalSides(grid, corners, x, i)) break;
                    }
                    for(int x = 0; x < j; x++) {
                        if (checkVerticalSides(grid, corners, x, i)) break;
                    }
                    for(int y = 19; y > i; y--) {
                        for(int x = 0; x < 20; x++) {
                            if((x == 0 || !grid[y][x-1] )&& grid[y][x]) {
                                addCorner(corners, y, x);
                            }
                            if((x == 19 || !grid[y][x+1]) && grid[y][x]) {
                                addCorner(corners, y, x);
                                break outer;
                            }
                        }
                    }
                    break outer;
                }
            }
        }
        corners.sort(Comparator.comparingInt((Corner a) -> a.x).thenComparing((Corner a) -> a.y));
        int outline = countOutline(corners);
        if (corners.size() > 2)
            System.out.print(count > outline ? "FILLED " : "EMPTY ");
        switch (corners.size()) {
            case 1:
                System.out.print("POINT ");
                break;
            case 2:
                System.out.print("LINE ");
                break;
            case 3:
                System.out.print("TRIANGLE ");
                break;
            case 4:
                System.out.print(isSquare(corners) ? "SQUARE " : "RECTANGLE ");
                break;
        }
        System.out.println(corners.stream().map(c -> String.format("(%d,%d)", c.x, c.y)).collect(Collectors.joining(" ")));
    }

    private static void addCorner(List<Corner> corners, int y, int x) {
        Corner corner = new Corner(y, x);
        if(!corners.contains(corner))
            corners.add(corner);
    }

    private static boolean checkVerticalSides(boolean[][] grid, List<Corner> corners, int x, int Y) {
        for(int y = Y; y < 20; y++) {
            if((y == 0 || !grid[y-1][x]) && grid[y][x]) {
                addCorner(corners, y, x);
            }
            if((y == 19 || !grid[y+1][x]) && grid[y][x]) {
                addCorner(corners, y, x);
                return true;
            }
        }
        return false;
    }

    private static boolean isSquare(List<Corner> corners) {
        return distance(corners.get(0), corners.get(1)) == distance(corners.get(0), corners.get(2));
    }

    private static int distance(Corner a, Corner b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private static int countOutline(List<Corner> corners) {
        int outline = 0;
        for (int i = 0; i < corners.size(); i++) {
            Corner c1 = corners.get(i);
            Corner c2 = corners.get((i + 1) % corners.size());
            if (c1.x == c2.x) {
                outline += Math.abs(c1.y - c2.y);
            } else {
                outline += Math.abs(c1.x - c2.x);
            }
        }
        return outline;
    }
}
