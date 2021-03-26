import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Piece {
    int x, y;
    char color;
    boolean empty;
    List<Piece> adjacent;

    public Piece(int x, int y, char color, boolean empty) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.empty = empty;
        adjacent = new ArrayList<>();
    }

    public void addAdjacent(Piece[] pieces) {
        for(Piece p : pieces) {
            if(x == p.x && y+1 == p.y) adjacent.add(p);
            if(x == p.x && y-1 == p.y) adjacent.add(p);
            if(x+1 == p.x && y == p.y) adjacent.add(p);
            if(x-1 == p.x && y == p.y) adjacent.add(p); 
        }
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Piece[][] map = new Piece[L][L];
        Piece[] pieces = new Piece[L*L];
        int id  = 0;
        for (int i = 0; i < L; i++) {
            char[] ROW = in.nextLine().toCharArray();
            for(int j = 0; j < ROW.length; j++) {
                Piece piece = new Piece(j, i, ROW[j], ROW[j]=='.');
                pieces[id++] = piece;
                map[i][j] = piece;
            }
        }
        
        for(Piece p:pieces) p.addAdjacent(pieces);

        for(int i = 0; i < L; i++) {
            for(int j = 0; j < L; j++) {
                Piece p = map[i][j];
                if(!p.empty) {
                    for(Piece adj:p.adjacent) {
                        if(adj.empty) {
                            fill(adj, pieces, p.color);
                        }
                    }
                }
            }
        }

        double w = 0;
        int b = 0;
        for(int i = 0; i < L; i++) {
            for(int j = 0; j < L; j++) {
                if(map[i][j].color == 'W') w++;
                if(map[i][j].color == 'B') b++;
                System.err.print(map[i][j].color);
            }
            System.err.println();
        }
        w+=6.5;
        System.out.println("BLACK : " + b);
        System.out.println("WHITE : " + w);
        System.out.println((w>b?"WHITE":"BLACK") +" WINS");

    }

    static void fill(Piece piece, Piece[] pieces, char color) {
        Queue<Piece> toVisit = new LinkedList<>();
        List<Piece> toFill = new ArrayList<>();
        toFill.add(piece);
        toVisit.add(piece);
        boolean f = true;
        while(!toVisit.isEmpty()){
            Piece polled = toVisit.poll();
            if(polled.empty) {
                toFill.add(polled);
                for(Piece adj:polled.adjacent) {
                    if(adj.empty && !toFill.contains(adj)) toVisit.add(adj);
                    if(!adj.empty && adj.color != color) {
                        f = false;
                    }
                }
            }
        }
        if(f)
            for(Piece p : toFill) {
                p.color = color;
                p.empty = false;
            }

    }
}
