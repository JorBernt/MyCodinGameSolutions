import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String> invalid = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String ISBN = in.nextLine();
            if(ISBN.length() != 10 && ISBN.length() != 13) {
                if(!invalid.contains(ISBN))
                invalid.add(ISBN);
            }
            else if(ISBN.length() == 10) {
                try {
                    Integer.parseInt(ISBN.substring(0, 4));
                    Integer.parseInt(ISBN.substring(5, 9));
                }
                catch (Exception e) {
                    if(!invalid.contains(ISBN))
                    invalid.add(ISBN);
                }

                int sum = 0;
                int w = 10;
                for(int j = 0; j < ISBN.length()-1; j++) {
                    sum += Character.getNumericValue(ISBN.charAt(j))*w;
                    w--;
                }
                if(sum == 0) {
                        if(!invalid.contains(ISBN))
                        invalid.add(ISBN);
                    }

                int c = 0;
                if(sum%11 != 0) c = 11-sum%11;
                if(c != Character.getNumericValue(ISBN.charAt(ISBN.length()-1))) {
                    if(c != 10 && ISBN.charAt(ISBN.length()-1) != 'X') {
                        if(!invalid.contains(ISBN))
                        invalid.add(ISBN);
                    }
                    else if(ISBN.charAt(ISBN.length()-1) == 'X' && c != 10) {
                        if(!invalid.contains(ISBN))
                        invalid.add(ISBN);
                    }
                }
                
            }
            else if(ISBN.length() == 13) {
                try {
                    Integer.parseInt(ISBN.substring(0, 5));
                    Integer.parseInt(ISBN.substring(6, 12));
                }
                catch (Exception e) {
                    if(!invalid.contains(ISBN))
                    invalid.add(ISBN);
                }

                if(ISBN.charAt(ISBN.length()-1) == 'X') {
                    if(!invalid.contains(ISBN))
                    invalid.add(ISBN);
                }
                else {
                    int sum = 0;
                    int w = 3;
                    for(int j = 0; j < ISBN.length()-1; j++) {
                        if(w == 3) w = 1;
                        else w = 3;
                        sum+= Character.getNumericValue(ISBN.charAt(j))*w;
                    }
                    if(sum == 0) {
                        if(!invalid.contains(ISBN))
                        invalid.add(ISBN);
                    }
                    
                    int c = 0;
                    if(sum%10 != 0) c = 10-sum%10;
                    if(c != Character.getNumericValue(ISBN.charAt(ISBN.length()-1))) {
                        if(!invalid.contains(ISBN))
                        invalid.add(ISBN);
                    }
                }
            
            }
            
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(invalid.size() + " invalid:");
        for(String s : invalid) {
            System.out.println(s);
        }
    }
}
