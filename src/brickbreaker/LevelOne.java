package brickbreaker;

import java.util.ArrayList;

public class LevelOne implements Level {
    // Replace magic numbers with descriptive constants
    private static final int TOTAL_BRICKS = 28;
    private static final int BRICKS_PER_ROW = 7;
    private static final int INITIAL_Y = 50;
    private static final int GAP = 20;

    @Override
    public ArrayList<Brick> createBrickPattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        int currentX = 0;
        int currentY = INITIAL_Y;
        // Create a prototype brick to retrieve brick dimensions
        Brick prototypeBrick = new Brick(null);
        int brickWidth = prototypeBrick.getBRICK_WIDTH();
        int brickHeight = prototypeBrick.getBRICK_HEIGHT();

        for (int i = 0; i < TOTAL_BRICKS; i++) {
            // Start a new row when needed
            if (i % BRICKS_PER_ROW == 0 && i != 0) {
                currentX = 0;
                currentY += brickHeight + GAP;
            }
            Brick brick = new Brick(null);
            brick.setBounds(currentX, currentY, brickWidth, brickHeight);
            bricks.add(brick);
            currentX += brickWidth + GAP;
        }
        return bricks;
    }
}
