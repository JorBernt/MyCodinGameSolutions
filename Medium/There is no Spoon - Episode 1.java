import java.util.*;
import java.io.*;
import java.math.*;

class Node {
    public int xPos, yPos;
    public boolean empty;

    public Node(int x, int y, boolean empty) {
        this.xPos = x;
        this.yPos = y;
        this.empty = empty;
    }

    public boolean exists(int x, int y) {
        if(x == this.xPos && y == this.yPos && !empty) {
            return true;
        }
        return false;
    }
}

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        List<Node> nodes = new ArrayList<>();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < height; i++) {
            char[] ca = in.nextLine().toCharArray();

            for(int j = 0; j < ca.length; j++) {
                Node node = new Node(j, i, ca[j] == '.' ? true : false);
                nodes.add(node);
            }
        }

  

        for(Node node : nodes) { 

            if(!node.empty) {
                System.out.println(node.xPos + " " + node.yPos + " " + checkNabo(node, width, height, nodes));     
            }
            
        }
    }

    static String checkNabo(Node node, int width, int height, List<Node> nodes) {
        int x2 = -1;
        int y2 = -1;
        int x3 = -1;
        int y3 = -1;
        outerloop:
        for(int i = 1; i < width; i++) {
            for(Node nabo : nodes) {
                if(nabo.exists(node.xPos+i, node.yPos)) {
                x2 = nabo.xPos;
                y2 = nabo.yPos;
                break outerloop;
                }
            }
        }
        outerloop:
        for(int i = 1; i < height; i++) {
            for(Node nabo : nodes) {
                if(nabo.exists(node.xPos, node.yPos+i)) {
                x3 = nabo.xPos;
                y3 = nabo.yPos;
                break outerloop;
                }
            }
        }
        return x2 + " " + y2 + " " + x3 + " " + y3;
    }


}
