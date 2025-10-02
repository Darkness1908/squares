import java.util.Scanner;

public class UserPlayer extends Player {

    Scanner SC;

    public UserPlayer(char color, String order) {
        super(color, order, "user");
        SC = new Scanner(System.in);
    }

    @Override
    public void makeMove(GameAttributes ga) {
        System.out.println(playerOrder + " игрок делает ход");
        String input = SC.nextLine();
        String[] params = input.split(" ");
        checkParametersNumber(params.length);

        int x = Integer.parseInt(params[1].replace(",", ""));
        int y = Integer.parseInt(params[2]);

        if ((x < 0) || (x >= ga.getN()) || (y < 0) || (y >= ga.getN())) {
            throw new IllegalArgumentException("Координаты шашки выходят за пределы поля");
        }

        if (!ga.getPotentialMoves().contains(new Player.Pair(x, y))) {
            throw new RuntimeException("На этой клетке уже присутствует шашка");
        }

        if (ga.getCount() > 1) {
            checkers.add(new Pair(currentX, currentY));
        }

        ga.getPotentialMoves().remove(new Player.Pair(x, y));

        currentX = x;
        currentY = y;

        ga.setMatrixValue(x, y, playerColor);
        ga.increment();
    }

    private void checkParametersNumber(int lenProv) {
        if (3 != lenProv) {
            throw new RuntimeException("Параметров должно быть ровно " + 3);
        }
    }

}
