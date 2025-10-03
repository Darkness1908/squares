import java.util.HashSet;
import java.util.Set;

public class GameAttributes {
    private final int N;
    private final Set<Player.Pair> potentialMoves;
    private final char[][] matrix;
    private int count;

    public void exitGame() {
        count = N*N+1;}
    public GameAttributes(int N) {
        matrix = new char[N][N];
        this.N = N;
        count = 0;
        potentialMoves = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                potentialMoves.add(new Player.Pair(i, j));
            }
        }
    }
    public Set<Player.Pair> getPotentialMoves() {
        return potentialMoves;
    }
    public void increment() {
        count++;
    }
    public int getN() {
        return N;
    }
    public int getCount() {
        return count;
    }
    public void setMatrixValue(int x, int y, char color) {
        matrix[y][x] = color;
    }
    public char getMatrixValues(int x, int y) {
        return matrix[y][x];
    }
}
