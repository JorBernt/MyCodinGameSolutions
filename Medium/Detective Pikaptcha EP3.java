import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    static class Picachu {
        int x, y, startX, startY, w, h;
        int[][] map;
        Direction direction;
        Direction side;

        public Picachu(int y, int x, String dir, char facingDir, int[][] map) {
            this.x = x;
            this.y = y;
            startX = x;
            startY = y;
            this.map = map;
            this.w = map[0].length;
            this.h = map.length;
            this.side = setSide(dir);
            this.direction = setDir(facingDir);
        }

        private Direction setSide(String dir) {
            return dir.equals("L") ? Direction.LEFT : Direction.RIGHT;
        }

        private Direction setDir(char side) {
            switch (side) {
                case '^':
                    return Direction.UP;
                case 'v':
                    return Direction.DOWN;
                case '<':
                    return Direction.LEFT;
                case '>':
                    return Direction.RIGHT;
            }
            return null;
        }

        public boolean finished() {
            return x == startX && y == startY;
        }

        private void rotate() {
            switch (direction) {
                case UP: {
                    if (side == Direction.RIGHT) {
                        direction = Direction.RIGHT;
                    } else if (side == Direction.LEFT) {
                        direction = Direction.LEFT;
                    }
                    break;
                }
                case DOWN: {
                    if (side == Direction.RIGHT) {
                        direction = Direction.LEFT;
                    } else if (side == Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                    break;
                }
                case LEFT: {
                    if (side == Direction.RIGHT) {
                        direction = Direction.UP;
                    } else if (side == Direction.LEFT) {
                        direction = Direction.DOWN;
                    }
                    break;
                }
                case RIGHT: {
                    if (side == Direction.RIGHT) {
                        direction = Direction.DOWN;
                    } else if (side == Direction.LEFT) {
                        direction = Direction.UP;

                    }
                    break;
                }
            }
        }

        public void move() {
            tryRotate();
            int[] pos;
            switch (direction) {
                case UP:
                    pos = getPosition(y - 1, x);
                    break;
                case DOWN:
                    pos = getPosition(y + 1, x);
                    break;
                case LEFT:
                    pos = getPosition(y, x - 1);
                    break;
                case RIGHT:
                    pos = getPosition(y, x + 1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
            y = pos[0];
            x = pos[1];
            map[y][x]++;
        }

        private int[] getPosition(int y, int x) {
            if (y < 0) {
                y = h - 1;
                int mid = w / 2;
                if (x > mid) {
                    x = x - mid;
                } else {
                    x = w - (mid - x);
                }
            } else if (y >= h) {
                y = 0;
                int mid = w / 2;
                if (x > mid) {
                    x = x - mid;
                } else {
                    x = w - (mid - x);
                }
            } else if (x < 0) {
                x = w - 1;
            } else if (x >= w) {
                x = 0;
            }
            return new int[]{y, x};
        }

        private boolean isWall(int y, int x) {
            int[] pos = getPosition(y, x);
            return map[pos[0]][pos[1]] == -1;
        }

        private boolean clearAhead() {
            switch (direction) {
                case UP: {
                    return !isWall(y - 1, x);
                }
                case DOWN: {
                    return !isWall(y + 1, x);
                }
                case LEFT: {
                    return !isWall(y, x - 1);
                }
                case RIGHT: {
                    return !isWall(y, x + 1);
                }
            }
            return false;
        }

        private boolean sideIsWall() {
            switch (direction) {
                case UP: {
                    return side == Direction.LEFT ? isWall(y, x - 1) : isWall(y, x + 1);
                }
                case DOWN: {
                    return side == Direction.LEFT ? isWall(y, x + 1) : isWall(y, x - 1);
                }
                case LEFT: {
                    return side == Direction.LEFT ? isWall(y + 1, x) : isWall(y - 1, x);
                }
                case RIGHT: {
                    return side == Direction.LEFT ? isWall(y - 1, x) : isWall(y + 1, x);
                }
            }
            return false;
        }

        public void tryRotate() {
            if (!sideIsWall()) {
                rotate();
                return;
            }
            if (!clearAhead()) {
                do {
                    rotate();
                } while (!clearAhead() || !sideIsWall());
            }
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        System.err.println(width);
        System.err.println(height);
        int[][] map = new int[height][width];
        int startX = 0;
        int startY = 0;
        char pDir = 'a';
        for (int i = 0; i < height; i++) {
            char[] line = in.next().toCharArray();
            System.err.println(new String(line));
            for (int j = 0; j < width; j++) {
                char c = line[j];
                if (c == '<' || c == '>' || c == 'v' || c == '^') {
                    startX = j;
                    startY = i;
                    pDir = c;
                    c = '0';
                }
                map[i][j] = c == '#' ? -1 : 0;
            }
        }
        String side = in.next();
        System.err.println(side);
        Picachu picachu = new Picachu(startY, startX, side, pDir, map);
        while (true) {
            picachu.move();
            if (picachu.finished())
                break;
        }
        for (int[] row : map) {
            for (int n : row) {
                System.out.print(n == -1 ? "#" : n);
            }
            System.out.println();
        }
    }
}
