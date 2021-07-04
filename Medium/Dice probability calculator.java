import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/


//JS's eval() is horrible thing, except for when you need it...
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        String[] variables = expr.replaceAll("[()]", "").split("[-+*//=<>]");
        List<String> die = new ArrayList<>();
        for (String s : variables) if (s.contains("d")) die.add(s);

        Map<String, List<Integer>> map = new HashMap<>();
        for (String d : die) {
            List<Integer> nums = new ArrayList<>();
            if (map.containsKey(d)) continue;
            for (int i = 1; i <= Integer.parseInt(d.substring(1)); i++) {
                nums.add(i);
            }
            map.put(d, nums);
        }

        List<String> permutations = new ArrayList<>();
        int max = 0;
        int min = 10000;
        double total = 0;

        perm(permutations, die, map, 0, expr);
        Map<Integer, Double> ans = new HashMap<>();
        for (String p : permutations) {
            int n = eval(p);
            ans.put(n, ans.getOrDefault(n, 0d) + 1);
            min = Math.min(min, n);
            max = Math.max(max, n);
            total++;
        }

        for (int i = min; i <= max; i++) {
            if (ans.containsKey(i)) {
                double d = (ans.get(i) / total) * 100d;
                System.out.println(i + " " + String.format("%.2f", d));
            }
        }

    }

    static int eval(String s) {
        String ans = null;
        boolean bigger = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == ')') {
                        String k = s.substring(i + 1, j);
                        Queue<String> ops = new LinkedList<>();
                        ops.add("*");
                        ops.add("/");
                        ops.add("+");
                        ops.add("-");
                        int res = helper(k, ops);
                        s = s.replace(s.substring(i, j + 1), "" + res);
                        break;
                    }
                }
            }
        }
        if (s.contains(">")) {
            int ind = s.indexOf(">");
            if (ind > s.length() / 2) {
                ans = s.substring(ind + 1);
            } else {
                ans = s.substring(0, ind);
                bigger = true;
            }
        } else if (s.contains("<")) {
            int ind = s.indexOf("<");
            if (ind > s.length() / 2) {
                ans = s.substring(ind + 1);
                bigger = true;
            } else ans = s.substring(0, ind);
        }
        if (ans != null) {
            int ind = s.indexOf("<");
            if (ind == -1) ind = s.indexOf(">");
            String a = ind < s.length() / 2 ? s.substring(0, ind + 1) : s.substring(ind);
            s = s.replace(a, "");
        }
        Queue<String> ops = new LinkedList<>();
        ops.add("*");
        ops.add("/");
        ops.add("+");
        ops.add("-");
        int result = helper(s, ops);
        if (ans != null) {
            int n = Integer.parseInt(ans);
            if (bigger && n > result || !bigger && n < result) return 1;
            else return 0;
        } else return result;
    }

    static int helper(String s, Queue<String> ops) {
        boolean o = false;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (("" + s.charAt(i)).matches("[-+*/]") || i == s.length() - 1) {
                if (!o) o = true;
                else if (o) {
                    String str = s.substring(start, i + (i == s.length() - 1 ? 1 : 0));
                    if (str.contains(ops.peek())) {
                        if (str.charAt(0) == '-') return Integer.parseInt(s);
                        int res = evalExp(str, ops.peek());
                        String t = s.replace(str, "" + res);
                        return helper(t, ops);
                    } else start = i - 1;
                }
            }
        }
        ops.poll();
        if (ops.isEmpty()) return Integer.parseInt(s);
        else return helper(s, ops);
    }

    static int evalExp(String s, String op) {
        if (op.equals("+")) op = "\\+";
        if (op.equals("*")) op = "\\*";
        String[] p = s.split(op);
        int a = Integer.parseInt(p[0]);
        int b = Integer.parseInt(p[1]);
        int res = 0;
        switch (op) {
            case "\\*":
                res = a * b;
                break;
            case "/":
                res = a / b;
                break;
            case "\\+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "<":
                res = a < b ? 1 : 0;
                break;
            case ">":
                res = a > b ? 1 : 0;
                break;
        }
        return res;
    }

    static void perm(List<String> permutations, List<String> die, Map<String, List<Integer>> map, int index, String s) {
        if (index == die.size()) {
            permutations.add(s);
            return;
        }
        for (int n : map.get(die.get(index))) {
            String str = s.replaceFirst(die.get(index), "" + n);
            perm(permutations, die, map, index + 1, str);
        }

    }
}
