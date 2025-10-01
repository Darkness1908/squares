public class GameOptions {

    private final int N;
    private final boolean isFirstPlayerUser;
    private final boolean isSecondPlayerUser;
    private final char firstPlayerColor;
    private final char secondPlayerColor;

    public GameOptions(String[] args) {
        N = Integer.parseInt(args[1].replace(",", ""));
        isFirstPlayerUser = args[2].equals("user");
        firstPlayerColor = args[3].charAt(0);
        isSecondPlayerUser = args[4].equals("user");
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
