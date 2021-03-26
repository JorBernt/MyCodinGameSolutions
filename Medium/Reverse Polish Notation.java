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
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        boolean error = false;
        out:for (int i = 0; i < N; i++) {
            String instruction = in.next();
            String[] instructions = instruction.split(" ");
            
            for(String s : instructions) {
                int n = 0;
                try{
                    n = Integer.parseInt(s);
                    stack.add(n);
                }
                catch (Exception e){
                    switch(s) {
                        case "ADD": {
                            if(stack.size() == 1) {
                                stack.pop();
                                error = true;
                                break out;
                            }
                            sum = stack.pop()+stack.pop();
                            stack.add(sum);
                            break;    
                        }
                        case "SUB": {
                            if(stack.size() == 1) {
                                stack.pop();
                                error = true;
                                break out;
                            }
                            int b = stack.pop();
                            int a = stack.pop();
                            sum = a-b;
                            stack.add(sum);
                            break;    
                        }
                        case "MUL": {
                            if(stack.size() == 1) {
                                stack.pop();
                                error = true;
                                break out;
                            }
                            sum = stack.pop()*stack.pop();
                            stack.add(sum);
                            break;    
                        }
                        case "DIV": {
                            if(stack.size() == 1) {
                                stack.pop();
                                error = true;
                                break out;
                            }
                            int b = stack.pop();
                            int a = stack.pop();
                            if(b == 0) {
                                error = true;
                                break out;
                            }
                            sum = a/b;
                            
                            stack.add(sum);
                            break;    
                        }
                        case "MOD": {
                            if(stack.size() == 1) {
                                stack.pop();
                                error = true;
                                break out;
                            }
                            int b = stack.pop();
                            int a = stack.pop();
                            if(b == 0) {
                                error = true;
                                break out;
                            }
                            sum = a%b;
                            stack.add(sum);
                            break;    
                        }
                        case "POP": {
                            if(stack.size() < 1) {
                                error = true;
                                break out;
                            }
                            stack.pop();
                            break;    
                        }
                        case "DUP": {
                            if(stack.size() < 1) {
                                error = true;
                                break out;
                            }
                            stack.add(stack.peek());
                            break;    
                        }
                        case "SWP": {
                            if(stack.size() == 1) {
                                stack.pop();
                                error = true;
                                break out;
                            }
                            int a = stack.pop();
                            int b = stack.pop();
                            stack.add(a);
                            stack.add(b);
                            break;    
                        }
                    }
                }
                    if(s.contains("ROL")) {
                        int d = stack.pop();
                        int[] temp = new int[d];
                        for(int j = 0; j < temp.length; j++) {
                            temp[j] = stack.pop();
                        }
                        for(int j = temp.length-2; j >= 0; j--) {
                            stack.add(temp[j]);
                        }
                        stack.add(temp[temp.length-1]);
                }
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        String[] ans = new String[stack.size()+(error?1:0)];
        int u = 0;
        if(error) {
            ans[u++] = "ERROR";
        }
        while(!stack.isEmpty()) {
            ans[u++] = Integer.toString(stack.pop());
        }
        
        for(int i = ans.length-1; i >= 0; i--) {
            System.out.print(ans[i]);
            if(i>0) System.out.print(" ");
        }
       
        
    }
}
