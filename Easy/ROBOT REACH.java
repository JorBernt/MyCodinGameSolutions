import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Point{
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int C = in.nextInt();
        int T = in.nextInt();


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        List<Point> list = new ArrayList<>();
        visitCells(new Point(0,0), R, C, T, list);
        System.out.println(list.size());
    }

    private static void visitCells(Point robot, int h, int w, int threshold, List<Point> visitedCells) {
        if (visitedCells.contains(robot))
            return;
        if (robot.x() < 0 || robot.x() >= w || robot.y() < 0 || robot.y() >= h)
            return;
        if (getCellValue(robot) > threshold)
            return;
        visitedCells.add(new Point(robot.x(), robot.y()));
        visitCells(new Point(robot.x() + 1, robot.y()), h, w, threshold, visitedCells);
        visitCells(new Point(robot.x() - 1, robot.y()), h, w, threshold, visitedCells);
        visitCells(new Point(robot.x(), robot.y() + 1), h, w, threshold, visitedCells);
        visitCells(new Point(robot.x(), robot.y() - 1), h, w, threshold, visitedCells);
    }

    private static int getCellValue(Point p) {
        return getDigitSum(p.x()) + getDigitSum(p.y());
    }

    private static int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
