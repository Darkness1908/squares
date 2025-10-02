public class PlayerFactory {
    public static Player create(String type, char color, String order) {
        return switch (type) {
            case "user" -> new UserPlayer(color, order);
            case "comp" -> new ComputerPlayer(color, order);
            default -> throw new IllegalArgumentException("Неизвестный тип: " + type);
        };
    }
}
