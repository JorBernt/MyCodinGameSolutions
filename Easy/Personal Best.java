import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solver {
    private List<String> gymnasts;
    private Map<String, double[]> scoring;
    private int[] categories;

    public Solver(String comps, String categories) {
        gymnasts = new ArrayList<>();
        scoring = new HashMap<>();
        addGymnasts(comps);
        addCategories(categories);
    }

    private void addGymnasts(String comps) {
        for (String name : comps.split(",")) {
            gymnasts.add(name);
        }
    }

    private void addCategories(String cat) {
        String[] cats = cat.split(",");
        categories = new int[cats.length];
        int i = 0;
        for (String s : cats) {
            switch (s) {
                case "bars":
                    categories[i] = 0;
                    break;
                case "beam":
                    categories[i] = 1;
                    break;
                case "floor":
                    categories[i]  = 2;
                    break;
            }
            i++;
        }
    }

    public void parseInput(String input) {
        String[] data = input.split(",");
        double[] score = new double[3];
        for(int i = 1; i < data.length; i++) {
            score[i-1] = Double.parseDouble(data[i]);
        }
        if(scoring.containsKey(data[0])) {
            double[] ar = scoring.get(data[0]);
            for(int i = 0; i < 3; i++) {
                score[i] = Math.max(ar[i], score[i]);
            }
        }
        scoring.put(data[0], score);
    }

    public void print() {
        for(String name : gymnasts) {
            double[] score = scoring.get(name);
            for(int i = 0; i < categories.length; i++) {
                String s = "";
                double d = score[categories[i]];
                if(d==(int)d) {
                    s = ""+(int)d;
                }
                else s = ""+d;
                System.out.print(s+(i<categories.length-1?",":""));
            }
            System.out.println();
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String gymnasts = in.nextLine();
        String categories = in.nextLine();
        int N = in.nextInt();

        Solver solver = new Solver(gymnasts, categories);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String row = in.nextLine();
            solver.parseInput(row);
        }
        solver.print();

    }
}
