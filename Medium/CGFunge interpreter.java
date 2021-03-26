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
        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i]= in.nextLine().toCharArray();
        }
        Stack<Integer> stack = new Stack<>();
        int x=0,y=0,dir=1;
        boolean sMode = false;
        out:while(true) {
            int s = 0;
            char c = map[y][x];
            if(Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            }
            if(sMode && c !='"') {
                stack.push((int)c);
            }
            if(c=='"') sMode = sMode?false:true;
            if(!sMode) {
                switch(c) {
                    case '>' : dir=1;break;
                    case '<' : dir=3;break;
                    case '^' : dir=0;break;
                    case 'v' : dir=2;break;
                    case '+' : {
                        int a = stack.pop();
                        int b = stack.pop();
                        int d = a+b;
                        stack.push(d);
                        break;
                    }
                    case '-' : {
                        int a = stack.pop();
                        int b = stack.pop();
                        int d = b-a;
                        stack.push(d);
                        break;
                    }
                    case '*' : {
                        int a = stack.pop();
                        int b = stack.pop();
                        int d = a*b;
                        stack.push(d);
                        break;
                    }
                    case '_' : {
                        int a = stack.pop();
                        dir = a==0?1:3;
                        break;
                    }
                    case '|' : {
                        int a = stack.pop();
                        dir = a==0?2:0;
                        break;
                    }
                    case 'X': {
                        int a = stack.pop();
                        int b = stack.pop();
                        stack.push(a);
                        stack.push(b);
                        break;
                    }
                    case 'P' : stack.pop();break;
                    case 'D' : {
                        int a = stack.peek();
                        stack.push(a);
                        break;
                    }
                    case 'I' : System.out.print(stack.pop());break;
                    case 'S' : s=1;break;
                    case 'E' : break out;
                    case 'C' : {
                        int a = stack.pop();
                        System.out.print((char)a);
                        break;
                    }
                }
            }
            switch(dir) {
                case 0:y=y-1-s;break;
                case 1:x+=1+s;break;
                case 2:y+=1+s;break;
                case 3:x=x-1-s;break;
            }

        }

    }
}
