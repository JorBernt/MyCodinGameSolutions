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
        int leapYear = in.nextInt();
        String sourceDayOfWeek = in.next();
        String sourceMonth = in.next();
        int sourceDayOfMonth = in.nextInt();
        String targetMonth = in.next();
        int targetDayOfMonth = in.nextInt();
        
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        int sNumDays=numDays(sourceMonth,sourceDayOfMonth, leapYear),tNumDays=numDays(targetMonth,targetDayOfMonth, leapYear);
        int day = 0;
        for(int i = 0; i < days.length; i++) {
            if(days[i].equals(sourceDayOfWeek)) {
                day = i;
                break;
            }
        }
        while(sNumDays != tNumDays) {
            if(sNumDays < tNumDays) {
                day++;
                day%=7;
                sNumDays++;
            }
            else {
                day--;
                if(day<0) day = 7+day;
                sNumDays--;

            }
        }
        System.out.println(days[day]);
    }

    static int numDays(String month, int day, int leap) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int sum = 0;
        if(month.equals("Jan")) return day;
        for(int i = 0;;i++) {
            switch(months[i]) {
                case "Jan":
                case "Mar":
                case "May":
                case "Jul":
                case "Aug":
                case "Oct":
                case "Dec":
                    sum+=31;break;
                case "Apr":
                case "Jun":
                case "Sep":
                case "Nov":
                    sum+=30;break;
                case "Feb": {
                    sum+=leap==1?29:28;
                    break;
                }
            }
            if(months[i+1].equals(month)) {
                break;
            }
        }
        return sum+day;
    }
}
