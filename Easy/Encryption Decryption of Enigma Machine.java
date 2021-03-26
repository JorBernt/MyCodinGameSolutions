import java.util.*;

import javax.crypto.Cipher;

import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String operation = in.nextLine();
        int pseudoRandomNumber = in.nextInt();
        List<Map<Integer, Character>> rotors = new ArrayList<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < 3; i++) {
            char[] rotor = in.nextLine().toCharArray();
            Map<Integer, Character> rotorMap = new HashMap<>();
            for(int j = 0; j < rotor.length; j++) {
                rotorMap.put(j, rotor[j]);
            }
            rotors.add(rotorMap);
        }
        String message = in.nextLine();

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println((operation.equals("ENCODE") ? encode(message, pseudoRandomNumber, rotors) : decode(message, pseudoRandomNumber, rotors)));
    }

    static String encode(String message, int cS, List<Map<Integer, Character>> rotors) {
        Map<Character, Integer> alphabet = new HashMap<>();
        int p = 0;
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            alphabet.put(ch, p++);
        }
        char[] ca = cipher(message, cS, alphabet).toCharArray();

        for(int i = 0; i < rotors.size(); i++) {
            for(int j = 0; j< ca.length; j++) {
                ca[j] = rotors.get(i).get(alphabet.get(ca[j]));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : ca) {
            sb.append(c);
        }
        return sb.toString();
    }

    static String decode(String message, int cS, List<Map<Integer, Character>> rotors) {
        Map<Character, Integer> alphabet = new HashMap<>();
        int p = 0;
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            alphabet.put(ch, p++);
        }
        char[] ca = message.toCharArray();

        for(int i = rotors.size()-1; i >= 0 ; i--) {
            out: for(int j = 0; j< ca.length; j++) {
                for(int n : rotors.get(i).keySet()) {
                    if(rotors.get(i).get(n) == ca[j]) {
                        for(char c : alphabet.keySet()) {
                            if(alphabet.get(c) == n) {
                                ca[j] = c;
                                continue out;
                            }
                        }
                    }
                }
                            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : ca) {
            sb.append(c);
        }
        
        
        return decipher(sb.toString(), cS, alphabet);
    }

    static String cipher(String msg, int cS, Map<Character, Integer> alphabet){
        int[] a = new int[msg.length()];
        for(int i = 0; i < a.length; i++) {
            a[i] = alphabet.get(msg.charAt(i));
        }
        for(int i = 0; i < a.length; i++) {
            a[i] += cS +i;
            if(a[i] > 25) a[i] = a[i]%26;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++) {
            for(char c : alphabet.keySet()) {
                if(alphabet.get(c) == a[i])
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    static String decipher(String msg, int cS, Map<Character, Integer> alphabet){
        int[] a = new int[msg.length()];
        for(int i = 0; i < a.length; i++) {
            a[i] = alphabet.get(msg.charAt(i));
        }
        for(int i = a.length-1; i >= 0; i--) {
            a[i] = a[i] -cS - i;
            if(a[i] < 0) a[i] = (26+a[i]%26)%26;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++) {
            for(char c : alphabet.keySet()) {
                if(alphabet.get(c) == a[i])
                    sb.append(c);
            }
        }
        return sb.toString();
    }    
}
