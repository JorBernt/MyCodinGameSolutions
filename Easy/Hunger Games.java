import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Tribute {
    String name, killedBy;
    List<String> victims;

    public Tribute(String name) {
        this.name = name;
        this.victims = new ArrayList();
    }

    public void addVictim(String victim) {
        victims.add(victim);
    }
    private String victimString() {
        if(victims.size() == 0)
            return "None"; 
        StringBuilder out = new StringBuilder();
        Collections.sort(victims);
        for(int i = 0; i < victims.size(); i++) {
            out.append(victims.get(i));
            if(i < victims.size()-1) out.append(", ");
        }
        return out.toString();
    }

    public String toString(){
        return "Name: " + name+"\n"+
                "Killed: " + victimString()+"\n"+
                "Killer: " + (killedBy == null ? "Winner" : killedBy);
    }

}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<String, Tribute> tributes = new HashMap<>();
        List<String> order = new ArrayList<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < t; i++) {
            String playerName = in.nextLine();
            Tribute tribute = new Tribute(playerName);
            tributes.put(playerName, tribute);
            order.add(playerName);
        }
        Collections.sort(order);
        int turns = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < turns; i++) {
            String info = in.nextLine();
            info = info.replace(",", "");
            String[] a = info.split(" ");
            for(int j = 2; j < a.length; j++) {
                tributes.get(a[0]).addVictim(a[j]);
                tributes.get(a[j]).killedBy = tributes.get(a[0]).name;
            }
        }
        int line = tributes.size();
        for(String s : order) {
            System.out.println(tributes.get(s).toString());
            line--;
            if(line > 0) System.out.println("");
        }
    }

}
