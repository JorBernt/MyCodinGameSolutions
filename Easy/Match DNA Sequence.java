import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int delta = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String gene = in.nextLine();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < n; i++) {
            String chr = in.nextLine();
            var match = match(gene, chr, delta);
            if (match != null) {
                System.out.printf("%d %d %d%n", i, match.start, match.delta);
                return;
            }
        }
        System.out.println("NONE");
    }

    public static Match match(String gene, String chr, int delta) {
        outer:
        for (int i = 0; i < chr.length() && gene.length() - delta + i <= chr.length(); i++) {
            int d = delta;
            int j = 0;
            for (; j < gene.length() && i + j < chr.length(); j++) {
                if (gene.charAt(j) != chr.charAt(j + i)) {
                    d--;
                    if (d < 0) {
                        continue outer;
                    }
                }
            }
            if (j < gene.length()) {
                int diff = gene.length() - j;
                d -= diff;
                if (d < 0) {
                    continue;
                }
            }
            return new Match(i, delta - d);
        }
        return null;
    }

    static class Match {
        int start;
        int delta;

        public Match(int start, int delta) {
            this.start = start;
            this.delta = delta;
        }
    }
}
