import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {

    private List<BingoCard> cards;
    private int[] calls;
    private boolean line, bingo;
    List<String> memo = new ArrayList<>();

    public Solver() {
        cards = new ArrayList<>();
        line = false;
        bingo = false;
    }

    public void addCalls(String input) {
        calls = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public void solve() {
        int callCount = 1;
        for (int call : calls) {
            for (BingoCard card : checkAndGetUpdatedCards(call)) {
                if (!line && card.hasLine()) {
                    System.out.println(callCount);
                    line = true;
                }
                if (!bingo && card.hasBingo()) {
                    System.out.println(callCount);
                    bingo = true;
                }
                if(bingo && line) return;
            }
            callCount++;
        }
    }

    private List<BingoCard> checkAndGetUpdatedCards(int n) {
        List<BingoCard> updated = new ArrayList<>();
        for (BingoCard card : cards) {
            if (card.check(n)) updated.add(card);
        }
        return updated;
    }

    public void addCards(String row) {
        memo.add(row);
        if (memo.size() == 5) {
            cards.add(new BingoCard(memo));
            memo.clear();
        }
    }

    class BingoCard {
        int[][] card;

        public BingoCard(List<String> rows) {
            card = new int[5][5];
            for (int i = 0; i < 5; i++) {
                card[i] = Arrays.stream(rows.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }

        public boolean check(int n) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (card[i][j] == n) {
                        card[i][j] = 0;
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean hasBingo() {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += Arrays.stream(card[i]).reduce((a, b) -> a + b).getAsInt();
            }
            return sum == 0;
        }
        public boolean hasLine() {
            for (int i = 0; i < 5; i++) {
                if (Arrays.stream(card[i]).reduce((a, b) -> a + b).getAsInt() == 0) return true;
            }

            int sum = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    sum += card[j][i];
                }
                if (sum == 0) return true;
                sum = 0;
            }

            for (int i = 0, j = 0; i < 5; i++, j++) {
                sum += card[i][j];
            }
            if (sum == 0) return true;

            sum = 0;
            for (int i = 4, j = 4; i >= 0; i--, j--) {
                sum += card[i][j];
            }
            return sum == 0;
        }
    }
}


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Solver solver = new Solver();
        for (int i = 0; i < n*5; i++) {
            String row = in.nextLine();
            solver.addCards(row);
        }
        String calls = in.nextLine();
        solver.addCalls(calls);
        solver.solve();
    }
}
