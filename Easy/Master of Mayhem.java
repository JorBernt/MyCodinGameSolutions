import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Cyborg {
    String name, hat, neckWear, companion, catchPhrase;
    int score;

    public Cyborg(String name) {
        this.name = name;
    }
}


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int cyborgCount = in.nextInt();
        Cyborg[] cyborgs = new Cyborg[cyborgCount];
        Map<String, Cyborg> cyborgMap = new HashMap<>();
        for (int i = 0; i < cyborgCount; i++) {
            String cyborgName = in.next();
            Cyborg cyborg = new Cyborg(cyborgName);
            cyborgs[i] = cyborg;
            cyborgMap.put(cyborgName, cyborg);
        }
        int mayhemReportCount = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Cyborg mayhem = new Cyborg("Mayhem");
        for (int i = 0; i < mayhemReportCount; i++) {
            String mayhemReport = in.nextLine();
            for(int j = 1; j < mayhemReport.length(); j++) {
                if(Character.isUpperCase(mayhemReport.charAt(j))) {
                    if(mayhemReport.contains("word")) {
                        String phrase = mayhemReport.split("\"")[1];
                        mayhem.catchPhrase = phrase;
                    }
                    else {
                        String item = mayhemReport.substring(j);
                        if(mayhemReport.contains("hat")) mayhem.hat = item;
                        else if(mayhemReport.contains("neckwear")) mayhem.neckWear = item;
                        else if(mayhemReport.contains("companion")) mayhem.companion = item;
                    }
                    break;
                }
            }
        }
            
            
        int cyborgReportCount = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < cyborgReportCount; i++) {
            String[] cyborgReport = in.nextLine().split("'s");
            for(int j = 1; j < cyborgReport[1].length(); j++) {
                if(Character.isUpperCase(cyborgReport[1].charAt(j))) {
                    if(cyborgReport[1].contains("catchphrase")) {
                        String phrase = cyborgReport[1].split("\"")[1];
                        cyborgMap.get(cyborgReport[0]).catchPhrase = phrase;
                    }
                    else {
                        String item = cyborgReport[1].substring(j);
                        if(cyborgReport[1].contains("hat")) cyborgMap.get(cyborgReport[0]).hat = item;
                        else if(cyborgReport[1].contains("neckwear")) cyborgMap.get(cyborgReport[0]).neckWear = item;
                        else if(cyborgReport[1].contains("companion")) cyborgMap.get(cyborgReport[0]).companion = item;
                    }
                    break;
                }
            }

        }
        int max = 0;
        for(Cyborg c : cyborgs) {
            if(mayhem.hat != null && (c.hat != null && !c.hat.equals(mayhem.hat))) {
                c.score = 0;
                continue;
            }
            else c.score++;
            if(mayhem.neckWear != null && (c.neckWear != null && !c.neckWear.equals(mayhem.neckWear))) {
                c.score = 0;
                continue;
            }
            else c.score++;
            if(mayhem.catchPhrase != null && (c.catchPhrase != null && !c.catchPhrase.contains(mayhem.catchPhrase))) {
                c.score = 0;
                continue;
            }
            else c.score++;
            if(mayhem.companion != null && (c.companion != null && !c.companion.equals(mayhem.companion))) {
                c.score = 0;
                continue;
            }
            else c.score++;
            max = Math.max(max, c.score);
        }
        if(max == 0) {
            System.out.println("MISSING");
            return;
        }

        String ans = null;
        for(Cyborg c : cyborgs) {
            if(c.score == max) {
                if(ans != null) {
                    ans = "INDETERMINATE";
                    break;
                }
                ans = c.name;
            }
        }
        System.out.println(ans);


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
