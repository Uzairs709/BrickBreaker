package brickbreaker;

import java.util.ArrayList;

public class LevelThree implements Level {
    // Replace magic numbers with constants
    private static final int BOX1_START_X = 30;
    private static final int BOX1_START_Y = 20;
    private static final int BOX2_START_X = 400;
    private static final int BOX2_START_Y = 20;
    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 5;
    private static final int Y_INCREMENT = 30;

    @Override
    public void createBrickPattern(ArrayList<Brick> bricks) {
        // Create Box 1
        bricks.addAll(createBrickBox(BOX1_START_X, BOX1_START_Y, NUM_ROWS, NUM_COLS));
        // Create Box 2
        bricks.addAll(createBrickBox(BOX2_START_X, BOX2_START_Y, NUM_ROWS, NUM_COLS));
    }

    // Extracted helper method for creating a rectangular grid of bricks (a "box")
    private ArrayList<Brick> createBrickBox(int startX, int startY, int rows, int cols) {
        ArrayList<Brick> boxBricks = new ArrayList<>();
        int currentY = startY;
        for (int row = 0; row < rows; row++) {
            int currentX = startX;
            for (int col = 0; col < cols; col++) {
                Brick brick = new Brick(null);
                brick.setBounds(currentX, currentY, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                boxBricks.add(brick);
                currentX += brick.getBRICK_WIDTH();
            }
            currentY += Y_INCREMENT;
        }
        return boxBricks;
    }
}
