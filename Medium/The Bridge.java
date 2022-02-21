import java.util.*;

class Bike {
    private int x, y, speed;
    private boolean alive, jumping, done;

    public Bike(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        alive = true;
        jumping = false;
        done = false;
    }

    private Bike(int x, int y, int speed, boolean alive, boolean jumping, boolean done) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.alive = alive;
        this.jumping = jumping;
        this.done = done;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Bike copy() {
        return new Bike(x, y, speed, alive, jumping, done);
    }

    public void reset(Bike b) {
        setX(b.getX());
        setY(b.getY());
        setSpeed(b.getSpeed());
        setAlive(b.isAlive());
        setJumping(b.isJumping());
        setDone(b.isDone());
    }
}

class Player {
    static Queue<String> commands = new LinkedList<>();
    static String[] commandList = {"SPEED","WAIT", "JUMP", "UP", "DOWN","SLOW"};
    static int MIN_BIKES;
    static Stack<Bike>[] bikeMemo;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt(); // the amount of motorbikes to control
        MIN_BIKES = in.nextInt(); // the minimum amount of motorbikes that must survive
        char[][] map = new char[4][];
        for (int i = 0; i < 4; i++) {
            map[i] = in.next().toCharArray();
        }
        List<Bike> bikes = new ArrayList<>();
        bikeMemo = new Stack[M];
        int S = in.nextInt(); // the motorbikes' speed
        for (int i = 0; i < M; i++) {
            int X = in.nextInt(); // x coordinate of the motorbike
            int Y = in.nextInt(); // y coordinate of the motorbike
            int A = in.nextInt(); // indicates whether the motorbike is activated "1" or detroyed "0"
            Bike bike = new Bike(X, Y, S);
            bikes.add(bike);
            bikeMemo[i] = new Stack<>();
            bikeMemo[i].add(bike);
        }

        solver(bikes, map, new ArrayList<>());
        while (!commands.isEmpty()) {
            System.out.println(commands.poll());
        }
        while(true) {
            System.out.println("SPEED");
        }

    }

    static boolean solver(List<Bike> bikes, char[][] map, List<String> com) {
        if (!enoughBikes(bikes)) return false;
        if (done(bikes)) {
            if(commands.isEmpty())
            commands.addAll(com);
            return true;
        }
        for(int i = 0; i < bikes.size(); i++) bikeMemo[i].add(bikes.get(i).copy());
        outer: for (String s : commandList) {
            switch (s) {
                case "WAIT": {
                    for(Bike b : bikes) {
                        if(b.getSpeed() <= 0) continue outer;
                    }
                    break;
                }
                case "SPEED":
                    bikes.forEach(a -> a.setSpeed(a.getSpeed() + 1));
                    break;
                case "SLOW":
                    for(Bike b : bikes) {
                        if(b.getSpeed() == 1) continue outer;
                    }
                    bikes.forEach(a -> a.setSpeed(a.getSpeed() - 1));
                    break;
                case "JUMP":
                    bikes.forEach(a -> a.setJumping(true));
                    break;
            }
            com.add(s);
            move(bikes, map, s);
            if (solver(bikes, map, com)) {
                return true;
            }
            com.remove(com.size()-1);
            resetBikes(bikes);
        }
        for(int i = 0; i < bikes.size(); i++) bikeMemo[i].pop();
        return false;
    }

    static void resetBikes(List<Bike> bikes) {
        for(int i = 0; i < bikes.size(); i++) {
            bikes.get(i).reset(bikeMemo[i].peek());
        }
    }

    static boolean enoughBikes(List<Bike> bikes) {
        int n = 0;
        for (Bike b : bikes) {
            if(b.getSpeed() < 0) return false;
            if (b.isAlive()) n++;
        }
        return n >= MIN_BIKES;
    }

    static boolean done(List<Bike> bikes) {
        for (Bike b : bikes) {
            if (b.isDone()) return true;
        }
        return false;
    }

    static void move(List<Bike> bikes, char[][] map, String s) {

        outer:
        for (Bike b : bikes) {
            switch (s) {
                case "UP": {
                    int x = 0;
                    for (int i = b.getY(); i >= b.getY() - 1; i--) {
                        for (int j = b.getX() + 1; j < b.getX() + b.getSpeed() + x; j++) {
                            if (j >= map[0].length - 1) {
                                b.setDone(true);
                                continue outer;
                            }
                            if (i < 0 || i >= map.length || map[i][j] == '0' && !b.isJumping()) {
                                b.setAlive(false);
                                continue outer;
                            }
                        }
                        x++;

                    }
                    b.setX(b.getX() + b.getSpeed());
                    b.setY(b.getY() - 1);
                    break;
                }
                case "DOWN": {
                    int x = 0;
                    for (int i = b.getY(); i <= b.getY() + 1; i++) {
                        for (int j = b.getX() + 1; j < b.getX() + b.getSpeed() + x; j++) {
                            if (j >= map[0].length - 1) {
                                b.setDone(true);
                                continue outer;
                            }
                            if (i < 0 || i >= map.length || map[i][j] == '0' && !b.isJumping()) {
                                b.setAlive(false);
                                continue outer;
                            }
                        }
                        x++;

                    }
                    b.setX(b.getX() + b.getSpeed());
                    b.setY(b.getY() + 1);
                    break;
                }
                default: {
                    for (int i = b.getX(); i <= b.getX() + b.getSpeed(); i++) {
                        if (i >= map[0].length - 1) {
                            b.setDone(true);
                            continue outer;
                        }
                        if (map[b.getY()][i] == '0' && !b.isJumping()) {

                            b.setAlive(false);
                            continue outer;
                        }
                    }

                    b.setX(b.getX() + b.getSpeed());
                }
            }
            if(map[b.getY()][b.getX()] == '0') {
                b.setAlive(false);
            }
            b.setJumping(false);
        }
    }


}
