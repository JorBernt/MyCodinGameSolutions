import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solver {
    private int N, C, P, maxJoy;
    private List<Person> persons;

    public Solver(int N, int C, int P) {
        this.N = N;
        this.C = C;
        this.P = P;
        persons = new ArrayList<>();
    }

    public void addPerson(int budget, int joy) {
        persons.add(new Person(budget, joy));
    }


    public void solve() {
        PriorityQueue<Person> chosen = new PriorityQueue<>(Comparator.comparingInt(a -> a.budget));
        chosen.addAll(persons);
        while(!chosen.isEmpty()) {
            if(valid(chosen)) {
                maxJoy = Math.max(maxJoy, getJoy(chosen));
            }
            if (chosen.peek().budget < totalBudget(chosen.size())) {
                chosen.poll();
            }
            else {
                PriorityQueue<Person> pq = new PriorityQueue<>((a,b)->a.joy-b.joy);
                pq.addAll(chosen);
                while (!pq.isEmpty()) {
                    Person p = pq.poll();
                    if(valid(pq)) {
                        maxJoy = Math.max(maxJoy, getJoy(pq));
                    }
                    else {
                        PriorityQueue<Person> temp = new PriorityQueue<>((a,b)->a.budget-b.budget);
                        temp.addAll(pq);
                        temp.add(p);
                        while (!temp.isEmpty()) {
                            temp.poll();
                            if(valid(temp)) {
                                maxJoy = Math.max(maxJoy, getJoy(temp));
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    public String toString() {
        return "" + maxJoy;
    }

    private int getJoy(List<Person> persons) {
        int joy = 0;
        for (Person p : persons)
            joy += p.joy;
        return joy;
    }

    private int getJoy(PriorityQueue<Person> pq) {
        List<Person> list = new ArrayList<>(pq);
        return getJoy(list);
    }

    private boolean valid(List<Person> chosen) {
        int total = totalBudget(chosen.size());
        for (Person p : chosen) {
            if (p.budget < total)
                return false;
        }
        return true;
    }

    private boolean valid(PriorityQueue<Person> pq) {
        List<Person> list = new ArrayList<>(pq);
        return valid(list);
    }

    private int totalBudget(int size) {
        size++;
        double total = C + size * P;
        total /= size;
        return (int) Math.ceil(total);

    }

    private class Person {
        private int budget, joy;

        public Person(int budget, int joy) {
            this.budget = budget;
            this.joy = joy;
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int C = in.nextInt();
        int P = in.nextInt();
        Solver solver = new Solver(N, C, P);
        for (int i = 0; i < N; i++) {
            int budget = in.nextInt();
            int joy = in.nextInt();
            solver.addPerson(budget, joy);
        }
        solver.solve();
        System.out.println(solver);
    }
}
