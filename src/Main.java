public class Main {
    public static void main(String[] args) {

        if (args.length != 6) {
            throw new RuntimeException("Параметров должно быть ровно 6");
        }

        if (args[0].equals("GAME")) {
            int N = Integer.parseInt(String.valueOf(args[1].charAt(0)));
            boolean isFirstPlayerUser = args[2].equals("user");
            boolean isSecondPlayerUser = args[4].equals("user");

            char firstPlayerColor = args[3].charAt(0);
            isColorPermit(firstPlayerColor, true);

            char secondPlayerColor = args[5].charAt(0);
            isColorPermit(secondPlayerColor, false);

            areColorsEqual(firstPlayerColor, secondPlayerColor);

            char[][] matrix = new char[N][N];
        }
    }









    static void isColorPermit(char ch, boolean isFirstPlayer) {
        if (Character.toUpperCase(ch) != 'W' &&
                Character.toUpperCase(ch) != 'B' ) {
            String player = isFirstPlayer ? "первого" : "второго";
            throw new RuntimeException("Недопустимый цвет для шашек " + player + " игрока");
        }
    }

    static void areColorsEqual(char firstPlayerColor, char secondPlayerColor) {
        if (firstPlayerColor == secondPlayerColor) {
            System.out.println("Цвета одинаковые");
            throw new RuntimeException("Цвета игроков должны быть разными ");
        }
    }
}