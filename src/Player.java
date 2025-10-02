import java.util.HashSet;
import java.util.Set;

abstract public class Player {
    protected String playerOrder;
    String playerType;
    protected char playerColor;
    protected int currentX;
    protected int currentY;
    Set<Pair> checkers;

    public Player(char playerColor, String order, String playerType) {
        this.playerColor = playerColor;
        this.playerType = playerType;
        playerOrder = order;
        checkers = new HashSet<>();
    }

    abstract public void makeMove(GameAttributes ga);

    public Set<Pair> getCheckers() {
        return checkers;
    }
    public int getCurrentX() {
        return currentX;
    }
    public int getCurrentY() {
        return currentY;
    }
    public char getPlayerColor() {
        return playerColor;
    }

    public static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player.Pair pair = (Player.Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
