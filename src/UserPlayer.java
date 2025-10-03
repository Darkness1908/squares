import java.util.Scanner;

public class UserPlayer extends Player {

    private final Scanner SC;

    public UserPlayer(char color, String order) {
        super(color, order);
        SC = new Scanner(System.in);
    }

    @Override
    public void makeMove(GameAttributes ga) {
        System.out.println(playerOrder + " игрок делает ход");

        boolean in = true;
        int x = 0;
        int y = 0;
        String input;
        String[] params;

        while (in) {
            input = SC.nextLine();
            params = input.split(" ");

            if (!params[0].equals("MOVE")) {
                System.out.println("Вам допустима только команда MOVE");
                continue;
            }
            try {
                Main.checkParametersNumber(3, params.length);
                x = Integer.parseInt(params[1].replace(",", ""));
                y = Integer.parseInt(params[2]);
            }
            catch (NumberFormatException e) {
                System.out.println("Неверный формат числа");
                continue;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if ((x < 0) || (x >= ga.getN()) || (y < 0) || (y >= ga.getN())) {
                System.out.println("Координаты шашки выходят за пределы поля");
                continue;
            }
            if (!ga.getPotentialMoves().contains(new Pair(x, y))) {
                System.out.println("На этой клетке уже присутствует шашка");
                continue;
            }
            in = false;
        }

        if (ga.getCount() > 1) {
            checkers.add(new Pair(currentX, currentY));
        }

        ga.getPotentialMoves().remove(new Pair(x, y));

        currentX = x;
        currentY = y;

        System.out.println(playerColor + " (" + x + ", " + y + ")");

        ga.setMatrixValue(x, y, playerColor);
        ga.increment();
    }
}
