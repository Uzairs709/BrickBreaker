package brickbreaker;

import java.util.ArrayList;

public class LevelFour implements Level {
    // Replace magic numbers with constants
    private static final int INITIAL_X = 120;
    private static final int INITIAL_Y = 20;
    private static final int INITIAL_BRICKS_COUNT = 8;
    private static final int X_INCREMENT = 35;
    private static final int Y_INCREMENT = 30;

    @Override
    public ArrayList<Brick> createBrickPattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        int currentY = INITIAL_Y;

        // Use a loop with a counter representing bricks in the row
        for (int bricksInRow = INITIAL_BRICKS_COUNT, nextRowStartX = INITIAL_X; bricksInRow > 0; bricksInRow--) {
            bricks.addAll(createRow(nextRowStartX, currentY, bricksInRow));
            nextRowStartX += X_INCREMENT;
            currentY += Y_INCREMENT;
        }
        return bricks;
    }

    // Extracted method to create a row of bricks
    private ArrayList<Brick> createRow(int startX, int y, int bricksInRow) {
        ArrayList<Brick> rowBricks = new ArrayList<>();
        int currentX = startX;
        for (int i = 0; i < bricksInRow; i++) {
            Brick brick = new Brick(null);
            brick.setBounds(currentX, y, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
            rowBricks.add(brick);
            currentX += brick.getBRICK_WIDTH();
        }
        return rowBricks;
    }
}
