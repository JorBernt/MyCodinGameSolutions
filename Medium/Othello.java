import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int id = 1;
        Map<Integer, Character> tokens = new HashMap<>();
        int[][] map = new int[8][8];
        for (int i = 0; i < 8; i++) {
            String row = in.nextLine();
            int j = 0;
            for(char c:row.toCharArray()) {
                if(c != '-') {
                    map[i][j] = id;
                    tokens.put(id, c);
                    id++;
                }
                j++;
            }
        }

        


        String color = in.next();
        String move = in.next();
        if(map[num(move.charAt(1))][num(move.charAt(0))] != 0) {
            System.out.print("NOPE");
            return;
        }
        map[num(move.charAt(1))][num(move.charAt(0))] = id;
        tokens.put(id, color.charAt(0));

        int w1 = 0, b1 = 0;

        for(int i : tokens.keySet()) {
            if(tokens.get(i) == 'W') w1++;
            else b1++;
        }

        
        
        tokens = play(map, tokens, color.charAt(0), num(move.charAt(0)), num(move.charAt(1)));
    

        for( int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                if(tokens.get(map[i][j]) != null)
                System.err.print(tokens.get(map[i][j]));
                else System.err.print("-");
            }
                
            System.err.println();
        }

        int w = 0, b = 0;

        for(int i : tokens.keySet()) {
            if(tokens.get(i) == 'W') w++;
            else b++;
        }
        if(w1 == w && b1 == b) {
            System.out.print("NULL");
            return;
        }
        System.out.print(w + " "+b);
    }

    static Map<Integer, Character> play(int[][] map, Map<Integer, Character> tokens, char token, int x, int y) {
        List<Integer> allTokens = new ArrayList<>();
        Stack<Integer> temp = new Stack<>();
        //RIGHT
        boolean valid = true;
        for(int i = x+1; i < 8; i++) {
            if(tokens.get(map[y][i]) != null)
            if(tokens.get(map[y][i]) != token && valid) {
                temp.add(map[y][i]);
            }
            if(map[y][i] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[y][i]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //DOWN-RIGHT
        for(int i = x+1, j = y+1; i < 8 && j < 8; i++, j++) {
            if(tokens.get(map[j][i]) != null)
            if(tokens.get(map[j][i]) != token && valid) {
                temp.add(map[j][i]);
            }
            if(map[j][i] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[j][i]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //DOWN
        for(int i = y+1; i < 8; i++) {
            if(tokens.get(map[i][x]) != null)
            if(tokens.get(map[i][x]) != token && valid) {
                temp.add(map[i][x]);
            }
            if(map[i][x] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[i][x]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //DOWN-LEFT
        for(int i = x-1, j = y+1; i >= 0 && j < 8; i--, j++) {
            if(tokens.get(map[j][i]) != null)
            if(tokens.get(map[j][i]) != token && valid) {
                temp.add(map[j][i]);
            }
            if(map[j][i] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[j][i]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //LEFT
        for(int i = x-1; i >= 0; i--) {
            if(tokens.get(map[y][i]) != null)
            if(tokens.get(map[y][i]) != token && valid) {
                temp.add(map[y][i]);
            }
            if(map[y][i] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[y][i]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //UP-LEFT
        for(int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--) {
            if(tokens.get(map[j][i]) != null)
            if(tokens.get(map[j][i]) != token && valid) {
                temp.add(map[j][i]);
            }
            if(map[j][i] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[j][i]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //UP
        for(int i = y-1; i >= 0; i--) {
            if(tokens.get(map[i][x]) != null)
            if(tokens.get(map[i][x]) != token && valid) {
                temp.add(map[i][x]);
            }
            if(map[i][x] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[i][x]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        //UP-RIGHT
        for(int i = x+1, j = y-1; i < 8 && j >= 0; i++, j--) {
            if(tokens.get(map[j][i]) != null)
            if(tokens.get(map[j][i]) != token && valid) {
                temp.add(map[j][i]);
            }
            if(map[j][i] == 0) {
                temp.removeAllElements();
                valid = false;
            }
            else if(tokens.get(map[j][i]) == token) {
                allTokens.addAll(temp);
                temp.removeAllElements();
                valid = false;
            }
        }
        temp.removeAllElements();
        valid = true;
        if(!allTokens.isEmpty()) {
            for(int i : allTokens) {
                tokens.put(i, tokens.get(i) == 'W' ? 'B':'W');
                
            }
            
        }
        return tokens;
    }


    static int num(char c) {
        if(Character.isDigit(c)) return Character.getNumericValue(c)-1;
        else return (int)(c -'a');
    }

    static int[] getXY(int[][] map, int id) {
        for( int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(map[i][j] == id) {
                    return new int[]{j, i};
                }   
            }
        }
        return null; 
    }
}

