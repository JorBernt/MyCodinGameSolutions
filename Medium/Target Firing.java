import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Enemy {
    String type;
    int HP, armor, damage, TTK;

    public Enemy(String type, int HP, int armor, int damage) {
        this.type = type;
        this.HP = HP;
        this.armor = armor;
        this.damage = damage;
        TTK = 0;
    }

    public void predictDamage() {
        int tempHP = HP;
        while(tempHP > 0) {
            tempHP -= Math.max((type.equals("FIGHTER") ? 20 : 10) - armor, 1);
            TTK++;
        }
    }

    public double damageToKill(Map<Integer, Enemy> enemies) {
        return (double)damage/TTK;
    }

    public void takeDamage() {
        HP -= Math.max((type.equals("FIGHTER") ? 20 : 10) - armor, 1);
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Map<Integer, Enemy> enemies = new HashMap<>();
        int shield = 5000;
        for (int i = 0; i < N; i++) {
            String SHIP = in.next();
            int HP = in.nextInt();
            int ARMOR = in.nextInt();
            int DAMAGE = in.nextInt();
            Enemy enemy = new Enemy(SHIP, HP, ARMOR, DAMAGE);
            enemy.predictDamage();
            enemies.put(i, enemy);

        }
        Map<Integer, Double> damageList = new HashMap<>();
        int totalTTK = 0;
        for(int i : enemies.keySet()) {
            totalTTK += enemies.get(i).TTK;
        }
        for(int j = 0; j < totalTTK; j++) {
            for(int i : enemies.keySet()) {
                damageList.put(i, enemies.get(i).damageToKill(enemies));
            }
        }

        while(shield > 0) {
            int priority = 0;
            double maxDamage = 0;
            for(int i : damageList.keySet()) {
                double dmg = damageList.get(i);
               
               if(dmg > maxDamage) {
                   maxDamage = dmg;
                   priority = i;
               }
               
            }
            while(true) {
                for(int i : enemies.keySet()) {
                    shield-=enemies.get(i).damage;
                }
                enemies.get(priority).takeDamage();
                if(enemies.get(priority).HP <= 0) {
                    enemies.remove(priority);
                    damageList.remove(priority);
                    break;
                }
            }
            
            if(enemies.isEmpty()) break;
        }

        System.out.println(shield>=0?shield:"FLEE");
    }
}
