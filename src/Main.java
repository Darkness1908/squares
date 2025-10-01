import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (!args[0].equals("GAME")) {
            switch (args[0]) {
                case "MOVE" -> throw new RuntimeException("Чтобы сделать ход, сначала начините игру");
                case "EXIT" -> throw new RuntimeException("Вы не можете завершить еще не начавшуюся игру");
//                case "HELP" ->
                default -> throw new RuntimeException("Запуск программы должен начинаться с параметра 'GAME'");
            }
        }
        checkParametersNumber(6, args.length);

        GameOptions gameOptions = new GameOptions(args);

        System.out.println("Новая игра начата");
        startGame(gameOptions);
    }

    public static void startGame(GameOptions gm) {
        Scanner sc = new Scanner(System.in);
        GameAttributes ga = new GameAttributes(gm.getN(), gm.getFirstPlayerColor());
        while (ga.getCount() < 6) {
            makeMove(sc, ga);
        }
        while (ga.getCount() < ga.getNn()) {
            makeMove(sc, ga);
            checkGameStatus(ga);
        }
    }

    public static void checkGameStatus(GameAttributes ga) {

    }

    public static void makeMove(Scanner sc, GameAttributes ga) {
        String input = sc.nextLine();
        String[] params = input.split(" ");
        checkParametersNumber(3, params.length);

        int x = Integer.parseInt(params[1].replace(",", ""));
        int y = Integer.parseInt(params[2]);
        if (ga.getMatrixValue(x, y) != '\u0000') {
            throw new RuntimeException("На этой клетке уже присутствует шашка");
        }
        else ga.setMatrixValue(x, y);
        ga.changeCurrentPlayer();
        ga.changeCurrentPlayerColor();
        ga.incrementCount();
    }
    public static void checkParametersNumber(int lenReq, int lenProv) {
        if (lenReq != lenProv) {
            throw new RuntimeException("Параметров должно быть ровно " + lenReq);
        }
    }
}