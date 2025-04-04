package brickbreaker;

public class LevelFactory {
    public static Level getLevel(int levelNumber) {
        return switch (levelNumber) {
            case 2 -> new LevelTwo();
            case 3 -> new LevelThree();
            case 4 -> new LevelFour();
            case 5 -> new LevelFive();
            default -> new LevelOne();
        };
    }
}
