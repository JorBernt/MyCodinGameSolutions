import java.util.Scanner;

class Solution {
    static int bombs;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int w = in.nextInt();
        bombs = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        var map = new int[h][w];
        for (int i = 0; i < h; i++) {
            var ca = in.nextLine().toCharArray();
            for (int j = 0; j < w; j++) {
                char c = ca[j];
                if (Character.isDigit(c)) {
                    map[i][j] = Character.getNumericValue(c);
                } else {
                    map[i][j] = c == '?' ? -1 : 0;
                }
            }
        }
        solve(map, 0, 0, bombs, false);
        if(bombs > 0 && bombCount(map) != bombs) {
            solve(map, 0, 0, bombs, true);
        }
        print(map);
    }

    static int bombCount(int[][] map) {
        int count = 0;
        for (int j = 0; j < map[0].length; j++) {
            for (int i = 0; i < map.length; i++) {
                if(map[i][j] == -1) count++;
            }
        }
        return count;
    }

    static void print(int[][] map) {
        for (int j = 0; j < map[0].length; j++) {
            for (int i = 0; i < map.length; i++) {
                if (map[i][j] == -2 || (bombs > 0 && map[i][j] == -1)) System.out.println(j + " " + i);
            }
        }
    }

    static boolean solve(int[][] map, int x, int y, int b, boolean d) {
        if (!valid(map, x, y) || b < 0) return false;
        for (int i = y; i < map.length; i++) {
            for (int j = x; j < map[0].length; j++) {
                if (map[i][j] == -1 && (d || !noBomb(map, j, i))) {
                    map[i][j] = -2;
                    if (solve(map, j, i, b - 1, d)) {
                        return true;
                    }
                    map[i][j] = -1;

                }
            }
            x = 0;
        }
        bombs = b;
        return wholeMapValid(map);
    }

    static boolean wholeMapValid(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0 && count(map, j, i) != map[i][j]) return false;
            }
        }
        return true;
    }


    static boolean noBomb(int[][] map, int x, int y) {
        for (int i = y - 1; i < y + 2; i++) {
            for (int j = x - 1; j < x + 2; j++) {
                if (i < 0 || j < 0 || i >= map.length || j >= map[0].length) continue;
                if (map[i][j] > 0) return false;
            }
        }
        return true;
    }

    static boolean valid(int[][] map, int x, int y) {
        for (int i = 0; i < y - 1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0 && count(map, j, i) != map[i][j])
                    return false;
            }
        }
        return true;
    }

    static int count(int[][] map, int x, int y) {
        int count = 0;
        for (int i = y - 1; i < y + 2; i++) {
            for (int j = x - 1; j < x + 2; j++) {
                if (i < 0 || j < 0 || i >= map.length || j >= map[0].length) continue;
                if (map[i][j] == -2) count++;
            }
        }
        return count;
    }
}
