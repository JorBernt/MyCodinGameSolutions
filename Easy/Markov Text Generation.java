import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int randomSeed = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String t = in.nextLine();
        int d = in.nextInt();
        int l = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String s = in.nextLine();
        var lookup = createLookUp(t, d);
        var output = new ArrayList<>(Arrays.asList(s.split(" ")));
        int index = 0;
        while (output.size() < l) {
            var substr = new ArrayList<String>();
            for (int i = index; i < index + d; i++) {
                substr.add(output.get(i));
            }
            index++;
            var option = lookup.get(String.join(" ", substr));
            if(option == null) continue;
            output.add(option.get(pickOptionIndex(option.size())));
        }
        System.out.println(String.join(" ", output));

    }

    static int pickOptionIndex(int numberOfOptions) {
        randomSeed += 7;
        return randomSeed % numberOfOptions;
    }

    static Map<String, List<String>> createLookUp(String input, int width) {
        var lookup = new HashMap<String, List<String>>();
        int n = 0;
        var substr = new ArrayList<String>();
        for (String s : input.split(" ")) {
            if (n == width) {
                String key = String.join(" ", substr);
                if (!lookup.containsKey(key))
                    lookup.put(key, new ArrayList<>());
                lookup.get(key).add(s);
                n--;
                substr.remove(0);
            }
            substr.add(s);
            n++;
        }
        return lookup;
    }
}
