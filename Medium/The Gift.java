import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static final String IMPOSSIBLE_SOLUTION = "IMPOSSIBLE";

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int C = in.nextInt();

        PriorityQueue<Integer> budgets = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int B = in.nextInt();
            budgets.offer(Integer.valueOf(B));
        }

        if(isGiftFeasible(C, budgets)) {
            calculateContributions(C, budgets).stream().forEach(System.out::println);
        }else {
            System.out.println(IMPOSSIBLE_SOLUTION);
        }
    }

    private static boolean isGiftFeasible(int giftCost, PriorityQueue<Integer> budgets) {
        return budgets.stream().mapToInt(Integer::intValue).sum() >= giftCost;
    }

    private static List<Integer> calculateContributions(int giftCost, PriorityQueue<Integer> budgets) {

        List<Integer> contributions = new ArrayList<Integer>(budgets.size());
        while(!budgets.isEmpty()) {
            Integer fairContribution = giftCost/budgets.size();
            Integer budget = budgets.poll();
            if(budget <= fairContribution) {
                contributions.add(budget);
                giftCost -= budget;
            }
            else {
                contributions.add(fairContribution);
                giftCost -= fairContribution;
            }
        }
        return contributions;
    }
}
