import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


class Duck {
    int x, y, id, offScreen, dirX, dirY;

    public Duck(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setDirection(int x, int y,int h, int w) {
            dirX = x-this.x;
            dirY = y-this.y;
            move(1);
            numberOfTurnsUntilOffscreen(h,w);
    }

    public void numberOfTurnsUntilOffscreen(int h, int w) {
        int tempX = x, tempY = y;
        while(tempX >= 0 && tempX < w && tempY >= 0 && tempY < h) {
            if(dirX == 0 && dirY == 0) {
                offScreen  = 100;
                return;
            }
            tempX += dirX;
            tempY += dirY;
            offScreen++;
        }
    }

    public void move(int n) {
        x += dirX * n;
        y += dirY * n;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Duck> ducks = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            char[] row = in.nextLine().toCharArray();
            for(int j = 0; j < row.length; j++) {
                if(row[j] != '.') {
                    int id = Character.getNumericValue(row[j]);
                    Duck duck = new Duck(j, i, id);
                    ducks.add(duck);
                }
            }
        }
        for (int i = 0; i < h; i++) {
            char[] row = in.nextLine().toCharArray();
            for(int j = 0; j < row.length; j++) {
                if(row[j] != '.') {
                    int id = Character.getNumericValue(row[j]);
                    for(Duck d : ducks) {
                        if(d.id == id) {
                            d.setDirection(j, i, h, w);
                            d.move(1);
                            break;
                        }
                    }
                }
            }
        }
        Collections.sort(ducks, new Comparator<Duck>(){
            @Override
            public int compare(Duck d1, Duck d2) {
                return Integer.compare(d1.offScreen, d2.offScreen);
            }
        });

        int rounds = 0;
        for(Duck d : ducks) {
            d.move(rounds);
            if(d.x >= 0 && d.x < w && d.y >= 0 && d.y < h) {
            System.out.println(d.id + " " + d.x + " " + d.y);
            rounds++;
            }
        }
    }
}
