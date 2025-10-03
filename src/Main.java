import java.util.Scanner;

public class Main {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            isValidCommand(args[0]);
            if (args[0].equals("GAME")) checkParametersNumber(6, args.length);
            else {
                input = sc.nextLine();
                args = input.split(" ");
                continue;
            }
            GameOptions gameOptions = new GameOptions(args);
            GameEngine.startGame(gameOptions);

            input = sc.nextLine();
            args = input.split(" ");
        }
    }

    public static void checkParametersNumber(int lenReq, int lenProv) {
        if (lenReq != lenProv) {
            throw new IllegalArgumentException("Параметров должно быть ровно " + lenReq);
        }
    }
    public static void printCommands() {
        System.out.println("Описание команд");
        System.out.println("GAME - начать новую игру");
        System.out.println("EXIT - завершить работу программы");
        System.out.println("MOVE - совершить ход");
    }
    public static void isValidCommand(String cmd) {
        if (!cmd.equals("GAME")) {
            switch (cmd) {
                case "MOVE" -> throw new IllegalArgumentException("Чтобы сделать ход, сначала начните игру");
                case "EXIT" -> System.exit(0);
                case "HELP" -> printCommands();
                default -> throw new IllegalArgumentException("Команда не определена");
            }
        }
    }
}
