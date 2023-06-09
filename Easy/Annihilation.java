import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int W = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            String line = in.nextLine();
            for (int j = 0; j < W; j++) {
                if (line.charAt(j) == '>' || line.charAt(j) == '<' || line.charAt(j) == '^' || line.charAt(j) == 'v') {
                    points.add(new Point(i, j, line.charAt(j), H, W));
                }
            }
        }

        int rounds = 0;
        while (!points.isEmpty()) {
            rounds++;
            List<Point> toDestroy = new ArrayList<>();
            for (Point point : points) {
                point.move();
            }
            for (Point point : points) {
                for (Point other : points) {
                    if (point != other && point.x == other.x && point.y == other.y) {
                        toDestroy.add(point);
                    }
                }
            }
            if (toDestroy.size() > 0) {
                for (Point point : toDestroy) {
                    points.remove(point);
                }
            }
        }
        System.out.println(rounds);
    }

    static class Point {
        int x = 0;
        int y = 0;
        int H = 0;
        int W = 0;

        Direction dir = Direction.RIGHT;

        public Point(int x, int y, char dir, int H, int W) {
            this.x = x;
            this.y = y;
            this.H = H;
            this.W = W;
            this.dir = dir == '>' ? Direction.RIGHT : dir == '<' ? Direction.LEFT : dir == '^' ? Direction.UP : Direction.DOWN;
        }

        public void move() {
            switch (dir) {
                case UP:
                    x--;
                    if (x < 0)
                        x = H - 1;
                    break;
                case DOWN:
                    x++;
                    if (x >= H)
                        x = 0;
                    break;
                case LEFT:
                    y--;
                    if (y < 0)
                        y = W - 1;
                    break;
                case RIGHT:
                    y++;
                    if (y >= W)
                        y = 0;
                    break;
            }
        }

        enum Direction {
            UP, DOWN, LEFT, RIGHT
        }
    }
}
