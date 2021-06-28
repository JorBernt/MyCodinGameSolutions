import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Solver {
    private List<Planet> planets;
    private Planet alice, closestPlanet;

    public Solver() {
        planets = new ArrayList<>();
    }

    public void addPlanet(String name, String r, String m, String c) {
        if(!name.equals("Alice"))
            planets.add(new Planet(name, r, m, c));
        else
            alice = new Planet(name,r,m,c);
    }

    public void solve() {
        closestPlanet = planets.stream()
        .sorted((a,b)->(int)(a.getDistance()-b.getDistance()))
        .filter(p -> getRoche(alice, p) < p.getDistance())
        .findFirst()
        .get();
    }

    private double getRoche(Planet alice, Planet p) {
        double d = alice.getDensity()/p.getDensity();
        return Math.cbrt(d*2)*alice.getRadius();
    }

    public String toString() {
        return closestPlanet.getName();
    }
}

class Planet {
    private String name;
    private double radius, volume, density, distance;

    public Planet(String name, String r, String m, String c) {
        this.name = name;
        radius = Double.parseDouble(r);
        volume = 4/3*Math.PI*Math.pow(radius,3);
        density = Double.parseDouble(m)/volume;
        distance = Double.parseDouble(c);
    }

    public double getRadius() {
        return radius;
    }

    public double getDensity() {
        return density;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
} 
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Solver solver = new Solver();
        for (int i = 0; i < N; i++) {
            String name = in.next();
            String r = in.next();
            String m = in.next();
            String c = in.next();
            solver.addPlanet(name, r, m, c);
        }
        solver.solve();
        System.out.println(solver);

    }
}
