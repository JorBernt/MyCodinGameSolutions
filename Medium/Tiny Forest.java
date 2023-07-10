import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        List<Tree> trees = new ArrayList<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        boolean[][] map = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            for (int j = 0; j < ROW.length(); j++) {
                if (ROW.charAt(j) == 'Y') {
                    Tree tree = new Tree(j, i, W, H);
                    tree.age = 10;
                    trees.add(tree);
                    map[i][j] = true;
                }
            }
        }

        int max = 0;
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (map[y][x])
                    continue;
                var mapCopy = copyMap(map);
                mapCopy[y][x] = true;
                var tree = new Tree(x, y, W, H);
                trees.add(tree);
                int cur = calc(new ArrayList<>(trees), mapCopy, 0);
                trees.remove(tree);
                max = Math.max(max, cur);
            }
        }
        System.out.println(max);
    }

    private static int calc(List<Tree> trees, boolean[][] map,  int age) {
        int numberOfTrees = (int) trees.stream().filter(Tree::isTree).count();
        if (age == 32) {
            return numberOfTrees;
        }
        List<Tree> newSeeds = new ArrayList<>();
        for (Tree tree : trees) {
            var seeds = tree.plant(map);
            if (seeds == null)
                continue;
            newSeeds.addAll(seeds);
        }
        trees.addAll(newSeeds);
        return calc(trees, map, age + 1);
    }

    private static boolean[][] copyMap(boolean[][] original) {
        boolean[][] copy = new boolean[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[0].length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    static class Tree {
        int x, y, W, H, age;

        public Tree(int x, int y, int W, int H) {
            this.x = x;
            this.y = y;
            this.W = W;
            this.H = H;
            age = 0;
        }

        public List<Tree> plant(boolean[][] map) {
            age++;
            if (age < 10) {
                return null;
            }
            List<Tree> seeds = new ArrayList<>();
            if (x > 0 && !map[y][x - 1]) {
                seeds.add(new Tree(x - 1, y, W, H));
                map[y][x - 1] = true;
            }
            if (x < W - 1 && !map[y][x + 1]) {
                seeds.add(new Tree(x + 1, y, W, H));
                map[y][x + 1] = true;
            }
            if (y > 0 && !map[y - 1][x]) {
                seeds.add(new Tree(x, y - 1, W, H));
                map[y - 1][x] = true;
            }
            if (y < H - 1 && !map[y + 1][x]) {
                seeds.add(new Tree(x, y + 1, W, H));
                map[y + 1][x] = true;
            }
            return seeds;
        }

        public boolean isTree() {
            return age >= 10;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tree tree = (Tree) o;
            return x == tree.x && y == tree.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
