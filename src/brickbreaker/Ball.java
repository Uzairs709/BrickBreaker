package brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ball extends JButton {

    private static final int BALL_WIDTH = 20;
    private static final int BALL_HEIGHT = 15;
    private static final int RIGHT_BOUND = 790;
    private static final int LEFT_BOUND = 0;
    private static final int TOP_BOUND = 0;

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    private int xVelocity = 4;
    private int yVelocity = -4;
    private int x = 325;
    private int y = 350;

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public Ball() {
        setPreferredSize(new Dimension(BALL_WIDTH, BALL_HEIGHT));
        setBounds(x, y, BALL_WIDTH, BALL_HEIGHT);
        setBackground(new Color(255, 255, 255, 0));
        setBorderPainted(false);
        setFocusable(false);
        loadBallImage();
    }

    // Extracted method for loading the ball image.
    private void loadBallImage() {
        try {
            Image ballImage = ImageIO.read(new File("assets/ball.png"))
                    .getScaledInstance(BALL_WIDTH, BALL_HEIGHT, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(ballImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load ball image");
        }
    }

    public void moveBall() {
        // Reflect velocity based on boundaries.
        xVelocity = BallMovementPhysics.reflectHorizontal(x, BALL_WIDTH, xVelocity, LEFT_BOUND, RIGHT_BOUND);
        yVelocity = BallMovementPhysics.reflectVertical(y, yVelocity, TOP_BOUND);

        // Update position.
        x += xVelocity;
        y += yVelocity;
        setBounds(x, y, BALL_WIDTH, BALL_HEIGHT);
    }




    public int getBallWidth() {
        return BALL_WIDTH;
    }

    public int getBallHeight() {
        return BALL_HEIGHT;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
