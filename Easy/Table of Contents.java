import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lengthofline = in.nextInt();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        var lines = new String[N];
        for (int i = 0; i < N; i++) {
            String entry = in.nextLine();
            lines[i] = entry;
        }
        print(lines, 1, 0, 0, lengthofline);
    }

    public static int print(String[] lines, int chapter, int index, int indentation, int lengthofline) {
        for(int i = index; i < lines.length; i++) {
            int ind = 0;
            while (lines[i].charAt(ind) == '>') {
                ind++;
            }
            if(ind > indentation) {
                i = print(lines, 1, i, indentation + 1, lengthofline);
                continue;
            }
            else if(ind < indentation){
                return i - 1;
            }
            var parts = lines[i].split(" ");
            System.out.println(("    ").repeat(indentation) + chapter + " " + parts[0].replaceAll(">", "") + ".".repeat(lengthofline - (indentation * 3) - parts[0].length() - parts[1].length() - ("" + chapter).length() - 1) + parts[1]);
            chapter++;
        }
        return lines.length - 1;
    }
}
