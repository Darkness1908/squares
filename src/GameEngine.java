public class GameEngine {
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
            checkGameStatus(currentPlayer, ga);
            currentPlayer = currentPlayer  == firstPlayer  ? secondPlayer : firstPlayer;
        }

        if (ga.getCount() == ga.getN()*ga.getN()) System.out.println("Игра окончена. Ничья");
    }
    private static void checkGameStatus(Player player, GameAttributes ga) {
        for (Player.Pair p : player.getCheckers()) {
            float dX = (float) (player.getCurrentX() - p.x) / 2;
            float dY = (float) (player.getCurrentY() - p.y) / 2;
            float cX = (float) (player.getCurrentX() + p.x) / 2;
            float cY = (float) (player.getCurrentY() + p.y) / 2;
            if ((dX*dX*4 + dY*dY*4) % 2 == 0) {
                if (player.getCheckers().contains(
                        new Player.Pair((int) (cX + (-1) * dY), (int) (cY + dX)))) {
                    if (player.getCheckers().contains(
                            new Player.Pair((int) (cX + dY), (int) (cY + (-1) * dX)))) {
                        System.out.println("Игра окончена. Игрок " + player.getPlayerColor()
                                + " одержал победу");
                        ga.exitGame();
                    }
                }
            }
        }
    }
}
