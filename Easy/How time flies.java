import java.util.*;
import java.io.*;
import java.math.*;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.*;
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] BEGIN = in.next().split("\\.");
        String b = BEGIN[2]+"-"+BEGIN[1]+"-"+BEGIN[0];
        String[] END = in.next().split("\\.");
        String e = END[2]+"-"+END[1]+"-"+END[0];
        LocalDate startDate = LocalDate.parse(b);
        LocalDate endDate = LocalDate.parse(e);


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int months = (int)MONTHS.between(startDate, endDate)%12;
        int year = (int)YEARS.between(startDate, endDate);
        String m = "";
        String y = "";
        if(year == 1) y = year+" year, ";
        else if(year > 1) y = year+" years, ";
        if(months == 1) m = months+" month, ";
        else if(months > 1) m = months+" months, ";

        System.out.println(y+m+"total "+DAYS.between(startDate, endDate)+" days");
    }
}
