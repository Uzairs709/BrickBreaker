package brickbreaker;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Base extends JButton {
    private static final int BASE_WIDTH = 130;
    private static final int BASE_HEIGHT = 15;
    private static final int MOVE_STEP = 8;
    private static final int LEFT_BOUND = 5;
    private static final int RIGHT_BOUND = 775;

    private int x = 325;
    private int y = 400;
    private boolean isRightKeyPressed = false;
    private boolean isLeftKeyPressed = false;

    public void setRightKeyPressed(boolean rightKeyPressed) {
        isRightKeyPressed = rightKeyPressed;
    }

    public void setLeftKeyPressed(boolean leftKeyPressed) {
        isLeftKeyPressed = leftKeyPressed;
    }

    private InputHandler inputHandler;
    public Base() {
        setPreferredSize(new Dimension(BASE_WIDTH, BASE_HEIGHT));
        setBounds(x, y, BASE_WIDTH, BASE_HEIGHT);
        setBorderPainted(false);
        setFocusable(true);
        setBaseImage();
        inputHandler=new InputHandler(this);
        addKeyListener(inputHandler);
    }

    private void setBaseImage() {
        try {
            Image baseImage = ImageIO.read(new File(CONSTANTS.BASE_IMAGE_PATH))
                    .getScaledInstance(BASE_WIDTH, BASE_HEIGHT, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(baseImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load base image");
        }
    }

    public void move() {
        // Calculate new x position based on key presses.
        int newX = x;
        if (isRightKeyPressed) {
            newX += MOVE_STEP;
        }
        else if (isLeftKeyPressed) {
            newX -= MOVE_STEP;
        }

        // Clamp newX within the allowed bounds.
        newX = Math.max(LEFT_BOUND, Math.min(newX, RIGHT_BOUND - BASE_WIDTH));

        // Update x and bounds.
        x = newX;
        setBounds(x, y, BASE_WIDTH, BASE_HEIGHT);
    }

    public int getBaseWidth() {
        return BASE_WIDTH;
    }

    public int getBaseHeight() {
        return BASE_HEIGHT;
    }


}

