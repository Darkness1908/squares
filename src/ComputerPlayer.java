import java.util.Iterator;
import java.util.Random;

public class ComputerPlayer extends Player {

    private final Random RANDOM;

    public ComputerPlayer(char color, String order) {
        super(color, order, "computer");
        RANDOM = new Random();
    }

    @Override
    public void makeMove(GameAttributes ga) {
        System.out.println(playerOrder + " игрок делает ход");
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Возникло прерывание потока");
        }

        int randomIndex = RANDOM.nextInt(ga.getPotentialMoves().size());
        Iterator<Pair> it = ga.getPotentialMoves().iterator();

        for (int i = 0; i < randomIndex; i++) {
            it.next();
        }

        Pair move = it.next();
        it.remove();

        if (ga.getCount() > 1) {
            checkers.add(new Pair(currentX, currentY));
        }

        ga.getPotentialMoves().remove(move);

        currentX = move.x;
        currentY = move.y;

        System.out.println(playerColor + " (" + move.x + ", " + move.y + ")");

        ga.setMatrixValue(move.x, move.y, playerColor);
        ga.increment();
    }
}
