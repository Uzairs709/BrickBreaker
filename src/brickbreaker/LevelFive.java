package brickbreaker;

import java.util.ArrayList;

public class LevelFive implements Level {
    // Constants for triangle one
    private static final int TRIANGLE_ONE_START_X = 150;
    private static final int TRIANGLE_ONE_START_Y = 50;

    // Constants for triangle two
    private static final int TRIANGLE_TWO_START_X = 550;
    private static final int TRIANGLE_TWO_START_Y = 50;

    // Constants for both triangles
    private static final int MAX_ROWS = 5;
    private static final int X_DECREMENT = 35;
    private static final int Y_INCREMENT = 30;

    @Override
    public ArrayList<Brick> createBrickPattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        // Create two triangle patterns using the extracted method
        bricks.addAll(createTrianglePattern(TRIANGLE_ONE_START_X, TRIANGLE_ONE_START_Y));
        bricks.addAll(createTrianglePattern(TRIANGLE_TWO_START_X, TRIANGLE_TWO_START_Y));
        return bricks;
    }

    // Extracted method to create a triangle pattern of bricks
    private ArrayList<Brick> createTrianglePattern(int startX, int startY) {
        ArrayList<Brick> triangleBricks = new ArrayList<>();
        int currentX = startX;
        int currentY = startY;
        int nextRowStartX = startX;
        int currentRowBrickCount = 1;

        while (currentRowBrickCount <= MAX_ROWS) {
            for (int i = 0; i < currentRowBrickCount; i++) {
                Brick brick = new Brick(null);
                brick.setBounds(currentX, currentY, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                triangleBricks.add(brick);
                currentX += brick.getBRICK_WIDTH();
            }
            currentRowBrickCount++;
            nextRowStartX -= X_DECREMENT;
            currentX = nextRowStartX;
            currentY += Y_INCREMENT;
        }

        return triangleBricks;
    }
}
