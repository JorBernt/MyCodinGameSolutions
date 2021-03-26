import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/



class Person {
    String name;
    int depth;
    List<Person> children;
    Person parent;

    
    public Person(String name, Person parent, int depth) {
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
        this.depth = depth;
    }
}

class Solution {

    static List<List<String>> families;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        List<Person> parents = new ArrayList<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        families = new ArrayList<>();
        Person parent = null;
        for (int i = 0; i < count; i++) {
            String line = in.nextLine();
            if(line.charAt(0) != '.') {
                parent = new Person(line, null, 0);
                parents.add(parent);
                continue;
            }
            int depth = (int)line.chars().filter(ch -> ch == '.').count();
            while(parent.depth != depth-1) {
                parent = parent.parent;
            }
            if(parent.depth == depth-1) {
                Person person = new Person(line.replace(".", ""), parent, depth);
                parent.children.add(person);
                parent = person;
            }

        }

        for(Person p : parents) {
            printFamily(p, new ArrayList<>());
        }

        for(List<String> list : families) {
            System.out.println(String.join(" > ", list));
        }
    }

    static void printFamily(Person p, List<String> path) {
        if(p.children.size() == 0) {
            path.add(p.name);
            families.add(path);
            return;
        }
        else {
            path.add(p.name);
            for(Person child : p.children) {
                printFamily(child, new ArrayList<>(path));
            }
        }
    }
}
