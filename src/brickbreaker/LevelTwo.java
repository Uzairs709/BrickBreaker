package brickbreaker;

import java.util.ArrayList;

public class LevelTwo implements Level {
    // Replace magic numbers with named constants
    private static final int START_X = 70;
    private static final int START_Y = 50;
    private static final int NUM_ROWS = 7;
    private static final int NUM_COLS = 7;
    private static final int Y_INCREMENT = 30;

    @Override
    public void createBrickPattern(ArrayList<Brick> bricks) {
        int yPos = START_Y;
        for (int row = 1; row <= NUM_ROWS; row++) {
            // Extract method for creating a row of bricks
            bricks.addAll(createBricksForRow(row, yPos));
            yPos += Y_INCREMENT;
        }
    }

    // Extracted method for creating bricks in a single row
    private ArrayList<Brick> createBricksForRow(int row, int yPos) {
        ArrayList<Brick> rowBricks = new ArrayList<>();
        int xPos = START_X;
        for (int col = 1; col <= NUM_COLS; col++) {
            if (shouldCreateBrick(row, col)) {
                Brick brick = new Brick(null);
                brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                rowBricks.add(brick);
            }
            // Update xPos by brick width (assumes brick width is constant)
            xPos += new Brick(null).getBRICK_WIDTH();
        }
        return rowBricks;
    }

    // Extracted method to encapsulate the brick creation condition
    private boolean shouldCreateBrick(int row, int col) {
        if (row % 2 == 0) {
            // For every even row, create brick only if the column is even
            return col % 2 == 0;
        } else {
            // For every odd row, create brick only if the column is odd
            return col % 2 != 0;
        }
    }
}
