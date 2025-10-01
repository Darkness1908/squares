import java.util.HashSet;
import java.util.Set;

public class GameAttributes {
    private final char[][] matrix;
    private final int nn;
    private int count;
    private char currentPlayerColor;
    int currentX;
    int currentY;
    private String currentPlayer;
    private final Set<Pair> firstPlayerCheckers;
    private final Set<Pair> secondPlayerCheckers;

    public GameAttributes(int N, char color) {
        matrix = new char[N][N];
        this.nn = N*N;
        this.currentPlayer = "Первый";
        currentPlayerColor = color;
        firstPlayerCheckers = new HashSet<>();
        secondPlayerCheckers = new HashSet<>();
        count = 0;
    }

    public int getNn() {
        return nn;
    }
    public int getCount() {
        return count;
    }
    public void incrementCount() {
        count++;
    }
    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer.equals("Второй") ? "Первый": "Второй";
        System.out.println(currentPlayer + " игрок делает ход");
    }
    public void setMatrixValue(int x, int y) {
        if (count != 0) {
            matrix[y][x] = currentPlayerColor;
            (currentPlayer.equals("Первый") ? firstPlayerCheckers : secondPlayerCheckers)
                    .add(new Pair(currentX, currentY));
        }
        currentX = x;
        currentY = y;
        count++;
    }
    public char getMatrixValue(int x, int y) {
        return matrix[y][x];
    }
    public void changeCurrentPlayerColor() {
        currentPlayerColor = currentPlayerColor == 'W' ? 'B' : 'W';
    }

    public static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
