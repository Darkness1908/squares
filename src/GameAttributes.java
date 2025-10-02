import java.util.HashSet;
import java.util.Set;

public class GameAttributes {
    private final char[][] matrix;
    private final int N;
    private int count;
    private char currentPlayerColor;
    private int firstPlayerCurrentX;
    private int firstPlayerCurrentY;
    private int secondPlayerCurrentX;
    private int secondPlayerCurrentY;
    private String currentPlayer;
    private final Set<Pair> firstPlayerCheckers;
    private final Set<Pair> secondPlayerCheckers;

    public GameAttributes(int N, char color) {
        matrix = new char[N][N];
        this.N = N;
        this.currentPlayer = "Первый";
        currentPlayerColor = color;
        firstPlayerCheckers = new HashSet<>();
        secondPlayerCheckers = new HashSet<>();
        count = 0;
    }

    public int getN() {
        return N;
    }
    public int getCount() {
        return count;
    }
    public void changeCurrentPlayer() {
        currentPlayer = currentPlayer.equals("Второй") ? "Первый": "Второй";
    }
    public void setMatrixValue(int x, int y) {
        if ((x < 0) || (x >= N) || (y < 0) || (y >= N)) {
            throw new IllegalArgumentException("Координаты шашки выходят за пределы поля");
        }
        if (count > 1) {
            if (currentPlayer.equals("Первый")) {
                firstPlayerCheckers.add(new Pair(firstPlayerCurrentX, firstPlayerCurrentY));
                firstPlayerCurrentX = x;
                firstPlayerCurrentY = y;
            }
            else {
                secondPlayerCheckers.add(new Pair(secondPlayerCurrentX, secondPlayerCurrentY));
                secondPlayerCurrentX = x;
                secondPlayerCurrentY = y;
            }

        }
        else {
            if (currentPlayer.equals("Первый")) {
                firstPlayerCurrentX = x;
                firstPlayerCurrentY = y;
            } else {
                secondPlayerCurrentX = x;
                secondPlayerCurrentY = y;
            }
        }
        matrix[y][x] = currentPlayerColor;
        count++;
        for (Pair p : firstPlayerCheckers) {
            System.out.println("Значения в сете 1: "  +p.x + " " + p.y);
        }
        for (Pair p : secondPlayerCheckers) {
            System.out.println("Значения в сете 2: "  +p.x + " " + p.y);
        }
    }
    public char getMatrixValue(int x, int y) {
        return matrix[y][x];
    }
    public void changeCurrentPlayerColor() {
        currentPlayerColor = currentPlayerColor == 'W' ? 'B' : 'W';
    }
    public char getCurrentPlayerColor() {
        return currentPlayerColor;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    public Set<Pair> getFirstPlayerCheckers() {
        return firstPlayerCheckers;
    }
    public Set<Pair> getSecondPlayerCheckers() {
        return secondPlayerCheckers;
    }
    public int getFirstPlayerCurrentX() {
        return firstPlayerCurrentX;
    }
    public int getFirstPlayerCurrentY() {
        return firstPlayerCurrentY;
    }
    public int getSecondPlayerCurrentX() {
        return secondPlayerCurrentX;
    }
    public int getSecondPlayerCurrentY() {
        return secondPlayerCurrentY;
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
            if (this == o) return true;              // если это тот же объект
            if (o == null || getClass() != o.getClass()) return false; // если null или другой класс
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;       // сравниваем координаты
        }

        @Override
        public int hashCode() {
            return 31 * x + y;                       // простая хэш-функция для пары
        }
    }

}
