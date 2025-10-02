
public class Main {
    public static void main(String[] args) {
        if (!args[0].equals("GAME")) {
            switch (args[0]) {
                case "MOVE" -> throw new RuntimeException("Чтобы сделать ход, сначала начините игру");
                case "EXIT" -> throw new RuntimeException("Вы не можете завершить еще не начавшуюся игру");
//                case "HELP" ->
                default -> throw new RuntimeException("Запуск программы должен начинаться с команды 'GAME'");
            }
        }
        checkParametersNumber(6, args.length);
        GameOptions gameOptions = new GameOptions(args);
        startGame(gameOptions);
    }

    public static void startGame(GameOptions go) {
        System.out.println("Новая игра началась");

        GameAttributes ga = new GameAttributes(go.getN());

        Player firstPlayer = PlayerFactory.create(go.getFirstPlayerType(),
                go.getFirstPlayerColor(), "Первый");
        Player secondPlayer = PlayerFactory.create(go.getSecondPlayerType(),
                go.getSecondPlayerColor(), "Второй");
        Player currentPlayer = firstPlayer;

        while (ga.getCount() < 6) {
            currentPlayer.makeMove(ga);
            currentPlayer = currentPlayer  == firstPlayer  ? secondPlayer : firstPlayer;
        }

        while (ga.getCount() < ga.getN()*ga.getN()) {
            currentPlayer.makeMove(ga);
            rotateAndCheckGameStatus(currentPlayer);
            currentPlayer = currentPlayer  == firstPlayer  ? secondPlayer : firstPlayer;
        }
        System.out.println("Игра окончена. Ничья");
    }

    public static void rotateAndCheckGameStatus(Player player) {
        for (Player.Pair p : player.getCheckers()) {
            float dX = (float) (player.getCurrentX() - p.x) / 2;
            float dY = (float) (player.getCurrentY() - p.y) / 2;
            float cX = (float) (player.getCurrentX() + p.x) / 2;
            float cY = (float) (player.getCurrentY() + p.y) / 2;
            if ((dX*dX*4 + dY*dY*4) % 2 == 0) {
                if (player.getCheckers().contains(
                        new Player.Pair((int) (cX+(-1)*dY), (int) (cY+dX)))) {
                    if (player.getCheckers().contains(
                            new Player.Pair((int) (cX+dY), (int) (cY+(-1)*dX)))) {
                        System.out.println("Игра окончена. Игрок " + player.getPlayerColor()
                                + " одержал победу");
                        System.exit(0);
                    }
                }
            }
        }
    }

    public static void checkParametersNumber(int lenReq, int lenProv) {
        if (lenReq != lenProv) {
            throw new RuntimeException("Параметров должно быть ровно " + lenReq);
        }
    }
}
