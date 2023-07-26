import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
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
        boolean isLine = false;
        outer:
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (grid[i][j]) {
                    int n = countSurrounding(i, j, grid);
                    if (n == 0) {
                        corners.add(new Corner(i, j));
                        break outer;
                    }
                    if (n == 1) {
                        corners.add(new Corner(i, j));
                        if (isLine) {
                            break outer;
                        }
                        isLine = true;
                        continue;
                    }
                    if (isCorner(i, j, grid))
                        corners.add(new Corner(i, j));
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

    private static int countSurrounding(int y, int x, boolean[][] grid) {
        int count = 0;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (isPoint(i, j, grid))
                    count++;
            }
        }
        return count - 1;
    }

    private static boolean isPoint(int y, int x, boolean[][] grid) {
        if (x < 0 || x == 20 || y < 0 || y == 20)
            return false;
        return grid[y][x];
    }

    private static boolean isCorner(int y, int x, boolean[][] grid) {
        int[][][] cornerTypes = {
                       {{0, 1, 2},
                        {0, 1, 1},
                        {0, 0, 0}},

                       {{0, 0, 1},
                        {0, 1, 2},
                        {0, 0, 1}},

                       {{1, 0, 0},
                        {1, 1, 0},
                        {0, 0, 0}},

                       {{0, 0, 0},
                        {0, 1, 0},
                        {0, 1, 1}},
        };
        int[][] area = getCornerArea(x, y, grid);
        for (int[][] cornerType : cornerTypes) {
            if (gridMatches(cornerType, area))
                return true;
            for (int i = 0; i < 3; i++) {
                cornerType = rotateGrid(cornerType);
                if (gridMatches(cornerType, area))
                    return true;
            }
        }
        return false;
    }

    private static boolean gridMatches(int[][] corner, int[][] area) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (corner[i][j] != area[i][j] && corner[i][j] != 2)
                    return false;
            }
        }
        return true;
    }

    private static int[][] rotateGrid(int[][] grid) {
        int[][] rotated = new int[3][3];
        for (int i = 0, k = 2; i < 3; i++, k--) {
            for (int j = 0; j < 3; j++) {
                rotated[j][k] = grid[i][j];
            }
        }
        return rotated;
    }

    private static int[][] getCornerArea(int x, int y, boolean[][] grid) {
        int[][] area = new int[3][3];
        for (int i = y - 1, k = 0; i <= y + 1; i++, k++) {
            for (int j = x - 1, q = 0; j <= x + 1; j++, q++) {
                if (i < 0 || i == 20 || j < 0 || j == 20)
                    continue;
                area[k][q] = grid[i][j] ? 1 : 0;
            }
        }
        return area;
    }
}
