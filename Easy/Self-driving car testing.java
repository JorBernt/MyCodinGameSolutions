import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


class Solver {
    private Road road;
    private Car car;
    private List<String> output;

    public Solver(){
        road = new Road();
        output = new ArrayList<>();
    }

    public void addPattern(String pattern) {
        road.addRoad(pattern);
    }

    public void addCar(String commands) {
        car = new Car(commands);
    }

    public void solve() {
        output = car.drive(road.getRoad());
    }

    public void print() {
        for(String s : output) {
            System.out.println(s);
        }
    }

    private class Car {
        private int x;
        private List<Integer> commands;

        public Car (String input) {
            String[] values = input.split(";");
            x = Integer.parseInt(values[0])-1;
            generateCommands(values);
        }

        public List<String> drive(List<String> road) {
            List<String> driveOnRoad = new ArrayList<>();
            int length = road.size();
            for(int i = 0; i < length; i++) {
                String r = road.get(i);
                x += commands.get(i);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < r.length(); j++) {
                    sb.append(j==x?"#":r.charAt(j));
                }
                driveOnRoad.add(sb.toString());
            }
            return driveOnRoad;
        }



        private void generateCommands(String[] values) {
            commands = new ArrayList<>();
            for(int i = 1; i < values.length; i++) {
                commands.addAll(parseCommand(values[i]));
            }
        }

        private List<Integer> parseCommand(String string) {
            int repeat = Integer.parseInt(string.replaceAll("[\\D]",""));
            String dir = string.replaceAll("[\\d]","");
            int d = 0;
            if(dir.equals("L")) {
                d = -1;
            }
            else if(dir.equals("R")) d = 1;
            List<Integer> com = new ArrayList<>();
            for(int i = 0; i < repeat; i++) com.add(d);
            return com;
        }
    }


    private class Road {
        private List<RoadPattern> patterns;

        public Road() {
            patterns = new ArrayList<>();
        }

        public void addRoad(String pattern) {
            patterns.add(new RoadPattern(pattern));
        }


        public List<String> getRoad() {
            List<String> road = new ArrayList<>();
            for(RoadPattern p : patterns) {
                road.addAll(p.getString());
            }
            return road;
        }
    }

    private class RoadPattern {
        private int repeats;
        private String pattern;

        public RoadPattern(String input) {
            String[] values = input.split(";");
            repeats = Integer.parseInt(values[0]);
            pattern = values[1];
        }

        public List<String> getString() {
            List<String> strings = new ArrayList<>();
            for(int i = 0; i < repeats; i++) {
                strings.add(pattern);
            }
            return strings;
        }
    }
}



class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Solver solver = new Solver();

        String xthenCommands = in.nextLine();
        solver.addCar(xthenCommands);

        for (int i = 0; i < N; i++) {
            String rthenRoadpattern = in.nextLine();
            solver.addPattern(rthenRoadpattern);
        }
        solver.solve();
        solver.print();
    }
}
