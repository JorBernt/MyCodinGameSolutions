import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Range {
    int min;
    int max;
    String type;

    Range(int min, int max, String type) {
        this.min = min;
        this.max = max;
        this.type = type;
    }

    boolean isInRange(int value) {
        return value >= min && value <= max;
    }

    void update(int value) {
        min = Math.min(min, value);
        max = Math.max(max, value);
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Range> male = new ArrayList<>();
        List<Range> female = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            var answer = in.nextLine().split(" ");
            var ranges = answer[1].equals("male") ? male : female;
            int age = Integer.parseInt(answer[0]);
            if (answer.length > 2) {
                String genre = answer[2];
                var range = ranges.stream().filter(a -> a.type.equals(genre)).findFirst().orElseGet(() -> new Range(age, age, genre));
                range.update(age);
                ranges.add(range);
            } else {
                var type = ranges.stream().filter(a -> a.isInRange(age)).findFirst().map(a -> a.type).orElse("None");
                System.out.println(type);
            }
        }
    }
}
