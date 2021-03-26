import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Piece {
    String pos;
    boolean enemy;
    public Piece(String pos, boolean enemy) {
        this.pos = pos;
        this.enemy = enemy;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String rookPosition = in.next();
        int nbPieces = in.nextInt();
        List<String> ans = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < nbPieces; i++) {
            int colour = in.nextInt();
            String onePiece = in.next();
            Piece piece = new Piece(onePiece, (colour == 1 ? true : false));
            pieces.add(piece);
            
        }
        for(char c = rookPosition.charAt(0); c <= 'h'; c++) {
            String s = c+""+rookPosition.charAt(1);
            if(!ans.contains(s) && !s.equals(rookPosition))
            if(possible(pieces, s) == 1) ans.add("-"+s);
            else if(possible(pieces, s) == 2) {
                ans.add("x"+s);
                break;
            }
            else break;   
        }
        for(char c = rookPosition.charAt(0); c >= 'a'; c--) {
            String s = c+""+rookPosition.charAt(1);
            if(!ans.contains(s) && !s.equals(rookPosition))
            if(possible(pieces, s) == 1) ans.add("-"+s);
            else if(possible(pieces, s) == 2) {
                ans.add("x"+s);
                break;
            }
            else break;  
        }
        for(int i = Character.getNumericValue(rookPosition.charAt(1)); i <= 8; i++) {
            String s = rookPosition.charAt(0)+""+i;
            if(!ans.contains(s) && !s.equals(rookPosition))
            if(possible(pieces, s) == 1) ans.add("-"+s);
            else if(possible(pieces, s) == 2) {
                ans.add("x"+s);
                break;
            }
            else break;
        }
        for(int i = Character.getNumericValue(rookPosition.charAt(1)); i > 0; i--) {
            String s = rookPosition.charAt(0)+""+i;
            if(!ans.contains(s) && !s.equals(rookPosition))
            if(possible(pieces, s) == 1) ans.add("-"+s);
            else if(possible(pieces, s) == 2) {
                ans.add("x"+s);
                break;
            }
            else break;
        }
        Collections.sort(ans);
        for(String s : ans) {
            System.out.println("R"+rookPosition+s);
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }

    static int possible(List<Piece> pieces, String s) {
        for(Piece p : pieces) {
            if(s.equals(p.pos) && !p.enemy) {
                return -1;
            }
            if(s.equals(p.pos) && p.enemy) {
                return 2;
            }
        }
        return 1;
    }
}
