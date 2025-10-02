public class GameOptions {

    private final int N;
    private final String firstPlayerType;
    private final String secondPlayerType;
    private final char firstPlayerColor;
    private final char secondPlayerColor;

    public GameOptions(String[] args) {
        N = Integer.parseInt(args[1].replace(",", ""));
        firstPlayerType = args[2];
        firstPlayerColor = args[3].charAt(0);
        secondPlayerType = args[4];
        secondPlayerColor = args[5].charAt(0);
        isNMoreThan2();
        isTypePermit(args[2], args[4]);
        isColorPermit(firstPlayerColor, true);
        isColorPermit(secondPlayerColor, false);
        areColorsEqual();
    }

    public int getN() {
        return N;
    }
    public char getFirstPlayerColor() {
        return firstPlayerColor;
    }
    public char getSecondPlayerColor() {
        return secondPlayerColor;
    }
    public String getFirstPlayerType() {
        return firstPlayerType;
    }
    public String getSecondPlayerType() {
        return secondPlayerType;
    }

    void isTypePermit(String type1, String type2) {
        if (!(type1.equals("user") || type1.equals("comp")) ||
                !(type2.equals("user") || type2.equals("comp"))) {
            throw new IllegalArgumentException("Тип игрока может быть либо 'comp', либо 'user'");
        }
    }
    void isNMoreThan2() {
        if (N < 2) throw new IllegalArgumentException("N должно быть больше 2-х");
    }
    void isColorPermit(char color, boolean isFirstPlayer) {
        if (Character.toUpperCase(color) != 'W' &&
                Character.toUpperCase(color) != 'B' ) {
            String player = isFirstPlayer ? "первого" : "второго";
            throw new RuntimeException("Недопустимый цвет для шашек " + player + " игрока");
        }
    }
    void areColorsEqual() {
        if (firstPlayerColor == secondPlayerColor)
            throw new RuntimeException("Цвета игроков должны быть разными ");
    }
}
