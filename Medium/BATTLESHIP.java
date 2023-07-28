import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


class GameBoard {
    List<Ship> ships;
    int[] allowedShipLengths = {0, 0, 1, 2, 1, 1};
    boolean validBoard = true;

    public GameBoard(char[][] board) {
        ships = new ArrayList<>();
        addShips(board);
    }

    private void addShips(char[][] board) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (board[y][x] != '.') {
                    Ship ship = createShip(y, x, board);
                    if (ship.length() > 5 || allowedShipLengths[ship.length()] - 1 < 0) {
                        validBoard = false;
                        return;
                    }
                    ships.add(ship);
                    allowedShipLengths[ship.length()]--;
                }
            }
        }
        validBoard = Arrays.stream(allowedShipLengths).allMatch(i -> i == 0);
    }

    public void play(String attack) {
        if (!validBoard || !validateShips()) {
            System.out.println("INVALID");
            return;
        }
        int x = attack.charAt(0) - 'A';
        int y = Integer.parseInt(attack.substring(1)) - 1;
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            System.out.println("MISSED");
            return;
        }
        List<String> answer = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                answer.add("TOUCHE");
                if (ship.isSunk()) {
                    answer.add("COULE");
                    answer.add(Integer.toString(ship.length()));
                }
                break;
            }
        }
        if (answer.isEmpty())
            answer.add("MISSED");
        if (gameLost()) {
            answer.add("THEN LOSE");
        }
        System.out.println(String.join(" ", answer));
    }

    private boolean gameLost() {
        for (Ship ship : ships) {
            if (!ship.isSunk())
                return false;
        }
        return true;
    }

    private boolean validateShips() {
        for (Ship s1 : ships) {
            for (Ship s2 : ships) {
                if (s1 != s2 && s1.isAdjacentTo(s2))
                    return false;
            }
        }
        return true;
    }

    private Ship createShip(int y, int x, char[][] board) {
        Ship ship = new Ship();
        if (x > 0 && board[y][x - 1] != '.') {
            for (int i = x; i >= 0; i--) {
                if (board[y][i] == '.')
                    break;
                ship.addPart(y, i, board[y][i] == '_');
                board[y][i] = '.';
            }
            return ship;
        } else if (y > 0 && board[y - 1][x] != '.') {
            for (int i = y; i >= 0; i--) {
                if (board[i][x] == '.')
                    break;
                ship.addPart(i, x, board[i][x] == '_');
                board[i][x] = '.';
            }
        } else if (x < 9 && board[y][x + 1] != '.') {
            for (int i = x; i < 10; i++) {
                if (board[y][i] == '.')
                    break;
                ship.addPart(y, i, board[y][i] == '_');
                board[y][i] = '.';
            }
        } else if (y < 9 && board[y + 1][x] != '.') {
            for (int i = y; i < 10; i++) {
                if (board[i][x] == '.')
                    break;
                ship.addPart(i, x, board[i][x] == '_');
                board[i][x] = '.';
            }
        }
        return ship;
    }

    static class Ship {
        List<ShipPart> parts;

        public Ship() {
            parts = new ArrayList<>();
        }

        public boolean isHit(int x, int y) {
            for (ShipPart part : parts) {
                if (part.x == x && part.y == y) {
                    if (part.isHit)
                        return false;
                    part.isHit = true;
                    return true;
                }
            }
            return false;
        }

        public boolean isSunk() {
            for (ShipPart part : parts) {
                if (!part.isHit)
                    return false;
            }
            return true;
        }

        public int length() {
            return parts.size();
        }

        public void addPart(int y, int x, boolean isHit) {
            parts.add(new ShipPart(y, x, isHit));
        }

        public boolean isAdjacentTo(Ship b) {
            for (ShipPart aPart : parts) {
                for (ShipPart bPart : b.parts) {
                    if (aPart.x == bPart.x && Math.abs(aPart.y - bPart.y) == 1)
                        return true;
                    if (aPart.y == bPart.y && Math.abs(aPart.x - bPart.x) == 1)
                        return true;
                    if (Math.abs(aPart.x - bPart.x) == 1 && Math.abs(aPart.y - bPart.y) == 1)
                        return true;
                }
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ship ship = (Ship) o;
            return Objects.equals(parts, ship.parts);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parts);
        }

        static class ShipPart {
            int x;
            int y;
            boolean isHit;

            public ShipPart(int y, int x, boolean isHit) {
                this.x = x;
                this.y = y;
                this.isHit = isHit;
            }
        }
    }

}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String SHOT = in.next();
        char[][] board = new char[10][10];
        for (int i = 0; i < 10; i++) {
            String LINE = in.next();
            for (int j = 0; j < 10; j++) {
                board[i][j] = LINE.charAt(j);
            }
        }
        GameBoard gameBoard = new GameBoard(board);
        gameBoard.play(SHOT);
    }
}
