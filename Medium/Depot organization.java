import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/

class Depot {
    char[] sides;
    int id;

    public Depot(char[] sides, int id) {
        this.sides = sides;
        this.id = id;
    }

    public void rotate() {
        char[] array = new char[6];
        for (int i = 0, j = 1; i < array.length; i++, j++) {
            j %= 6;
            array[i] = sides[j];
        }
        sides = array;
    }

    public boolean validCenter() {
        char lowest = 'Z';
        for (char c : sides)
            if (c < lowest)
                lowest = c;
        return sides[2] == lowest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Depot depot = (Depot) o;
        return Arrays.equals(sides, depot.sides);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(sides);
    }
}

class Floor {
    private Depot[] depots = new Depot[7];
    private int pos = 0;
    private int[] sideNumber;
    private char[] letters;

    public boolean addDepot(Depot depot, boolean force) {
        if (!force && !valid(depot))
            return false;
        depots[pos] = depot;
        switch (pos) {
            case 0: {
                sideNumber = new int[] { 5 };
                letters = new char[] { depots[0].sides[2] };
                break;
            }
            case 1: {
                sideNumber = new int[] { 1 };
                letters = new char[] { depots[0].sides[4] };
                break;
            }
            case 2: {
                sideNumber = new int[] { 0, 1, 5 };
                letters = new char[] { depots[0].sides[3], depots[1].sides[4], depots[2].sides[2] };
                break;
            }
            case 3: {
                sideNumber = new int[] { 0, 5 };
                letters = new char[] { depots[1].sides[3], depots[3].sides[2] };
                break;
            }
            case 4: {
                sideNumber = new int[] { 0, 1 };
                letters = new char[] { depots[2].sides[3], depots[3].sides[4] };
                break;
            }
            case 5: {
                sideNumber = new int[] { 0, 1, 5 };
                letters = new char[] { depots[3].sides[3], depots[4].sides[4], depots[5].sides[2] };
                break;
            }
        }
        pos = Math.min(6, pos + 1);

        return true;
    }

    public boolean contains(Depot depot) {
        for (Depot d : depots)
            if (d == depot)
                return true;
        return false;
    }

    public boolean valid(Depot depot) {
        if (pos == 3 && !depot.validCenter())
            return false;
        if (pos == 0)
            return true;
        int i = 0;
        for (int n = 0; n < sideNumber.length; n++) {
            if (depot.sides[sideNumber[n]] != letters[i++])
                return false;
        }
        return true;
    }

    public void remove(Depot depot) {
        for (int i = 0; i < depots.length; i++) {
            if (depots[i] == depot) {
                depots[i] = null;
                pos--;
                if (pos > 0) {
                    pos--;
                    addDepot(depots[pos], true);
                }

                return;
            }
        }
    }

    public boolean done() {
        return depots[6] != null;
    }

    public void print() {
        for (int i = 0; i < 7; i++) {
            System.out.print(depots[i].id + "" + depots[i].sides[2] + (i < 6 ? " " : ""));
        }
    }

}

class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<Depot> depots = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Depot depot = new Depot(in.nextLine().replace(" ", "").toCharArray(), i);
            depots.add(depot);
        }
        Floor floor = new Floor();
        solve(depots, floor);
        floor.print();
    }

    static boolean solve(List<Depot> depots, Floor floor) {
        if (floor.done())
            return true;
        for (Depot depot : depots) {
            if (floor.contains(depot))
                continue;
            for (int i = 0; i < 6; i++) {
                if (floor.addDepot(depot, false)) {
                    if (solve(depots, floor)) {
                        return true;
                    } else {
                        floor.remove(depot);
                    }
                }
                depot.rotate();

            }
        }
        return false;
    }
}
