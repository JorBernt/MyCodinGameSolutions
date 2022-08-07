import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int[] number = {1, 100};
        for (int i = 0; i < R; i++) {
            String line = in.nextLine();
            if (!validateAnswer(line, number)) {
                System.out.printf("Alice cheated in round %s\n", i + 1);
                return;
            }
        }
        System.out.println("No evidence of cheating");
    }

    private static boolean validateAnswer(String line, int[] number) {
        String[] parts = line.split(" ");
        int guessedNumber = Integer.parseInt(parts[0]);
        if(number[0] == number[1] && parts[2].equals("on")) {
            return guessedNumber == number[0];
        }
        boolean res;
        switch (parts[2]) {
            case "high": {
                res = guessedNumber >= number[0];
                number[1] = Math.min(guessedNumber - 1, number[1]);
                break;
            }
            case "low": {
                res = guessedNumber <= number[1];
                number[0] = Math.max(guessedNumber + 1, number[0]);
                break;
            }
            default: {
                res = number[1] >= guessedNumber && number[0] <= guessedNumber;
            }
        }
        if(number[0] == number[1] && !res) {
            return parts[2].equals("on");
        }
        if(number[1] < number[0])
            return false;
        return res;
    }
}
