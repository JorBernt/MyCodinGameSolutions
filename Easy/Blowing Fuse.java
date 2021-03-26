import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Device {
    int a;
    boolean on;

    public Device(int a, boolean on) {
        this.a = a;
        this.on = on;
    }

    public void turn(boolean on) {
        this.on = on;
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        Map<Integer, Device> devices = new HashMap<>();
        int cur = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            Device device = new Device(in.nextInt(), false);
            devices.put(i, device);
        }
        for (int i = 0; i < m; i++) {
            int mx = in.nextInt()-1;
            if(devices.get(mx) != null) {
                if(!devices.get(mx).on) {
                    cur += devices.get(mx).a;
                    devices.get(mx).turn(true);
                }
                else if(devices.get(mx).on) {
                    cur -= devices.get(mx).a;
                    devices.get(mx).turn(false);
                }
                if(cur > max) max = cur;
                if(cur > c) {
                    System.out.println("Fuse was blown.");
                    return;
                }
                
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("Fuse was not blown.");
        System.out.println("Maximal consumed current was "+max+" A.");
    }
}
