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
        String intext = in.nextLine().toLowerCase();
        char[] ca = intext.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ca.length; i++) {
            if(i == 0 && ca[i] != ' ' && ca[i] != '.' && ca[i] != ',')
                sb.append(Character.toUpperCase(ca[i]));
            if(i > 0) {
                if(ca[i] == ' ') {
                    if(sb.charAt(sb.length()-1) != ' ' && i != ca.length-2) {
                        for(int j = i; j < ca.length; j++) {
                            if(ca[j] != ' ') {
                                if(ca[j] != ',' && ca[j] != '.') {
                                    sb.append(ca[i]);
                                    break;
                                }
                                else break;

                            }
                        }
                    }
                }
                else if(ca[i] == ',') {
                    if(sb.charAt(sb.length()-1) != ',' && sb.charAt(sb.length()-1) != ' ') sb.append(ca[i]);
                    if(sb.charAt(sb.length()-1) != ' ') sb.append(' ');
                }
                else if(ca[i] == '.') {
                    if(sb.charAt(sb.length()-1) != '.' && sb.charAt(sb.length()-1) != ' ') sb.append(ca[i]);
                    if(sb.charAt(sb.length()-1) != ' ' && i < ca.length-1) sb.append(' ');
                    
                }
               
                else if(ca[i] != ' ' && ca[i] != '.' && ca[i] != ',') {
                    if(i>1) {
                    if(sb.charAt(sb.length()-2) == '.') {
                        sb.append(Character.toUpperCase(ca[i]));
                        continue;
                        }
                    }
                    sb.append(ca[i]);
                }
            }    
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(sb.toString());
    }
}
