import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Equipment {
    String name;
    Map<String, Boolean> series;
    Map<String, Boolean> parallel;
    public Equipment(String name) {
        this.name = name;
        series = new HashMap<>();
        parallel = new HashMap<>();
    }
    public void addSeries(String name) {
        series.put(name, false);
    }
    public void addParallel(String name) {
        parallel.put(name, false);
    }
    public void changeSwitch(String name) {
        if(series.containsKey(name)) series.put(name, series.get(name) ? false : true);
        if(parallel.containsKey(name)) parallel.put(name, parallel.get(name) ? false : true);
    }
    public boolean checkSeries() {
        for(String s : series.keySet()) {
            if(!series.get(s)) return false;
        }
        return true;
    }
    public boolean checkParallel() {
        if(parallel.size() == 0) return true;
        for(String s : parallel.keySet()) {
            if(parallel.get(s)) return true;
        }
        return false;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Equipment> devices = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            String WIRING = in.nextLine();
            String[] parse = WIRING.split(" ");
            Equipment equipment = new Equipment(parse[0]);
            int n = 1;
            while(n < parse.length-1) {
            if(parse[n].equals("-")) {
                n++;
                while(!parse[n].equals("=")) {
                    if(parse[n].equals("-")) break;
                    equipment.addSeries(parse[n]);
                    if(n == parse.length-1) break;
                    n++;
                }
            }
            if(parse[n].equals("=")) {
                n++;
                while(!parse[n].equals("-")) {
                    if(parse[n].equals("=")) break;
                    equipment.addParallel(parse[n]);
                    if(n == parse.length-1) break;
                    n++;
                }
            }
            }
            devices.add(equipment);

        }
        int A = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < A; i++) {
            String SWITCH = in.nextLine();
            for(Equipment device : devices) {
                device.changeSwitch(SWITCH);
            }
        }

        for(Equipment device : devices) {
            if(device.checkSeries() && device.checkParallel()) {
                System.out.println(device.name + " is ON");
            }
            else System.out.println(device.name + " is OFF");
        }
    }
}
