import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String S = in.nextLine();
        var numbers = new PriorityQueue<Integer>();
        numbers.addAll(Arrays.stream(S.split("")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
        int cantStartWith = -1;
        long left = -1, right = -1;
        while (!numbers.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            PriorityQueue<Integer> l = new PriorityQueue<>();
            int n = 0;
            while (!numbers.isEmpty()) {
                n = numbers.poll();
                if (n > cantStartWith)
                    break;
                temp.add(n);
            }
            if (n <= cantStartWith)
                break;
            numbers.addAll(temp);
            l.add(n);
            cantStartWith = n;
            long ls;
            long rs;
            while (!numbers.isEmpty()) {
                ls = createNumber(l);
                rs = createNumber(numbers);
                if (ls != -1 && rs != -1) {
                    left = left == -1 ? Math.min(ls, rs) : Math.min(left, Math.min(ls, rs));
                    right = Math.max(right, Math.max(ls, rs));
                    break;
                }
                if (ls == 0) {
                    break;
                }
                l.add(numbers.poll());
            }
            numbers.addAll(l);
        }
        System.out.println(left + " " + right);
    }

    private static long createNumber(PriorityQueue<Integer> in) {
        PriorityQueue<Integer> numbers = new PriorityQueue<>(in);
        StringBuilder sb = new StringBuilder();
        while (!numbers.isEmpty()) {
            int k = 0;
            if (sb.length() == 0) {
                List<Integer> temp = new ArrayList<>();
                while (!numbers.isEmpty() && k == 0) {
                    k = numbers.poll();
                    if (k == 0) {
                        temp.add(k);
                    }
                }
                if (k == 0 && temp.size() > 1)
                    return -1;
                numbers.addAll(temp);
            } else k = numbers.poll();
            sb.append(k);
        }
        boolean c = new BigInteger(sb.toString()).compareTo(BigInteger.TEN.pow(18)) <= 0;
        return c ? Long.parseLong(sb.toString()) : -1;
    }
}
