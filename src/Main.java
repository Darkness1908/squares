import java.util.Scanner;
import java.util.Set;

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
        startGame(gameOptions);
    }

    public static void startGame(GameOptions gm) {
        System.out.println("Новая игра началась");
        Scanner sc = new Scanner(System.in);
        GameAttributes ga = new GameAttributes(gm.getN(), gm.getFirstPlayerColor());
        while (ga.getCount() < 6) {
            makeMove(sc, ga);
            ga.changeCurrentPlayer();
            ga.changeCurrentPlayerColor();
        }
        while (ga.getCount() < ga.getN()*ga.getN()) {
            makeMove(sc, ga);
            checkGameStatus(ga);
            ga.changeCurrentPlayer();
            ga.changeCurrentPlayerColor();
        }
        System.out.println("Игра окончена. Ничья");
    }

    public static void checkGameStatus(GameAttributes ga) {
        if (ga.getCurrentPlayer().equals("Первый")) {
            rotate90(ga, ga.getFirstPlayerCheckers(), ga.getFirstPlayerCurrentX(), ga.getFirstPlayerCurrentY());
        }
        else rotate90(ga, ga.getSecondPlayerCheckers(), ga.getSecondPlayerCurrentX(), ga.getSecondPlayerCurrentY());
    }

    public static void rotate90(GameAttributes ga, Set<GameAttributes.Pair> playerCheckers, int x, int y) {
        for (GameAttributes.Pair p : playerCheckers) {
            System.out.println(p.x + " " + p.y);
            float dX = (float) (x - p.x) / 2;
            float dY = (float) (y - p.y) / 2;
            float cX = (float) (x + p.x) / 2;
            float cY = (float) (y + p.y) / 2;
            if ((x-p.x)*(x-p.x) + (y-p.y)*(y-p.y) % 2 == 0) {
                if (playerCheckers.contains(
                        new GameAttributes.Pair((int) (cX+(-1)*dY), (int) (cY+dX)))) {
                    System.out.println("Первое условие");
                    if (playerCheckers.contains(
                            new GameAttributes.Pair((int) (cX+dY), (int) (cY+(-1)*dX)))) {
                        System.out.println("Игра окончена. Игрок " + ga.getCurrentPlayerColor()
                                + " одержал победу");
                        System.exit(0);
                    }
                }
            }
        }
    }

    public static void makeMove(Scanner sc, GameAttributes ga) {
        System.out.println(ga.getCurrentPlayer() + " игрок делает ход");
        String input = sc.nextLine();
        String[] params = input.split(" ");
        checkParametersNumber(3, params.length);

        int x = Integer.parseInt(params[1].replace(",", ""));
        int y = Integer.parseInt(params[2]);
        if (ga.getMatrixValue(x, y) != '\u0000') {
            throw new RuntimeException("На этой клетке уже присутствует шашка");
        }
        else ga.setMatrixValue(x, y);
    }

    public static void checkParametersNumber(int lenReq, int lenProv) {
        if (lenReq != lenProv) {
            throw new RuntimeException("Параметров должно быть ровно " + lenReq);
        }
    }
}
