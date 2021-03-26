import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Person {
    String name, location;
    List<String> alibi;
    boolean alone;

    public Person(String name, String location, List<String> alibi, boolean alone) {
        this.name = name;
        this.location = location;
        this.alibi = alibi;
        this.alone = alone;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        Map<String, Person> persons = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            String name = line.split(":")[0];
            String[] a = line.split(" ");
            String location = a[5].replace(",", "");
            List<String> alibi = new ArrayList<>();
            boolean alone = false;
            if(line.contains("alone")) {
                alone = true;
            }
            else {
                for(int j = 7; j < a.length; j++) {
                    if(!a[j].equals("and")) {
                        if(a[j].contains("."))
                            a[j] = a[j].replace(".", "");
                            a[j] = a[j].replace(" ", "");
                        alibi.add(a[j]);
                    }
                }
            }
            Person person = new Person(name, location, alibi, alone);
            persons.put(name, person);
        }

        List<Person> potentialKillers = new ArrayList<>();
        for(String s : persons.keySet()) {
            Person p1 = persons.get(s);
            int check = 0;
            for(String l : p1.alibi) {
                if(persons.get(l).alibi.contains(p1.name)) {
                   check++; 
                }
            }
            if(p1.alone) {
                for(String l : persons.keySet()) {
                    if(!p1.name.equals(l)) {
                        if(p1.location.equals(persons.get(l).location))
                            potentialKillers.add(p1);
                            break;
                    }
                }
            }
            else if(check != p1.alibi.size()) potentialKillers.add(p1);
        }

        if(potentialKillers.size() > 1) {
            out:for(Person p : potentialKillers) {
                if(p.alone) {
                    for(Person p2 : potentialKillers) {
                        if(p != p2) {
                            if(p.location.equals(p2.location))
                                potentialKillers.remove(p);
                                break out;
                        }
                    }
                }
            }
        }

        if(potentialKillers.size() > 0)
        System.out.println(potentialKillers.get(0).name + " did it!");
        else
        System.out.println("It was me!");
    }
}
