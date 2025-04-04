package brickbreaker;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class CollisionHandler implements Serializable {
    private static final long COLLISION_COOLDOWN = 100;
    private final GamePanel gamePanel;

    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkCollisionsWithBase(Base base, Ball ball) {
        if (!canCollide()) {
            return;
        }

        if (isTopCollision(base, ball)) {
            ball.setyVelocity(ball.getyVelocity() * -1);
            // Example condition: if ball is on right side, reverse its x-velocity too
            if (ball.getX() > 400) {
                ball.setxVelocity(ball.getxVelocity() * -1);
            }
            updateCollisionTime();
        } else if (isLeftCollision(base, ball)) {
            ball.setxVelocity(ball.getxVelocity() * -1);
            ball.setyVelocity(ball.getyVelocity() * -1);
            updateCollisionTime();
        } else if (isRightCollision(base, ball)) {
            ball.setxVelocity(ball.getxVelocity() * -1);
            ball.setyVelocity(ball.getyVelocity() * -1);
            updateCollisionTime();
        }
    }

    public Brick checkCollisionsWithBricks(ArrayList<Brick> bricks, Ball ball) {
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            Rectangle bounds = brick.getBounds();

            if (isBrickBottomCollision(ball, bounds)) {
                System.out.println("Brick bottom collided");
                iterator.remove();
                ball.setyVelocity(ball.getyVelocity() * -1);
                return brick;
            } else if (isBrickLeftCollision(ball, bounds)) {
                System.out.println("Brick left collided");
                iterator.remove();
                ball.setxVelocity(ball.getxVelocity() * -1);
                return brick;
            } else if (isBrickRightCollision(ball, bounds)) {
                System.out.println("Brick right collided");
                iterator.remove();
                ball.setxVelocity(ball.getxVelocity() * -1);
                return brick;
            } else if (isBrickTopCollision(ball, bounds)) {
                System.out.println("Brick top collided");
                iterator.remove();
                ball.setyVelocity(ball.getyVelocity() * -1);
                return brick;
            }
        }
        return null;
    }

    // Helper method to check if the collision cooldown has passed
    private boolean canCollide() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - gamePanel.getLastCollisionTime()) > COLLISION_COOLDOWN;
    }

    // Update the last collision time
    private void updateCollisionTime() {
        gamePanel.setLastCollisionTime(System.currentTimeMillis());
    }

    // Helper methods for base collision detection

    private boolean isTopCollision(Base base, Ball ball) {
        return ball.getX() >= base.getX() &&
                ball.getX() <= base.getX() + base.getBaseWidth() &&
                ball.getY() + ball.getBallHeight() >= base.getY() &&
                ball.getY() + ball.getBallHeight() <= base.getY() + base.getBaseHeight();
    }

    private boolean isLeftCollision(Base base, Ball ball) {
        return ball.getX() + ball.getBallWidth() >= base.getX() &&
                ball.getX() + ball.getBallWidth() <= base.getX() + base.getBaseWidth() / 4 &&
                ball.getY() >= base.getY() &&
                ball.getY() <= base.getY() + base.getBaseHeight();
    }

    private boolean isRightCollision(Base base, Ball ball) {
        return ball.getX() <= base.getX() + base.getBaseWidth() &&
                ball.getX() >= base.getX() + base.getBaseWidth() - base.getBaseWidth() / 4 &&
                ball.getY() + ball.getBallHeight() >= base.getY() &&
                ball.getY() + ball.getBallHeight() <= base.getY() + base.getBaseHeight();
    }

    // Helper methods for brick collision detection

    private boolean isBrickBottomCollision(Ball ball, Rectangle bounds) {
        return ball.getX() >= bounds.x &&
                ball.getX() <= bounds.x + bounds.width &&
                ball.getY() <= bounds.y + bounds.height &&
                ball.getY() >= bounds.y;
    }

    private boolean isBrickLeftCollision(Ball ball, Rectangle bounds) {
        return ball.getX() + ball.getBallWidth() >= bounds.x &&
                ball.getX() + ball.getBallWidth() <= bounds.x + 5 &&
                ball.getY() > bounds.y &&
                ball.getY() < bounds.y + bounds.height;
    }

    private boolean isBrickRightCollision(Ball ball, Rectangle bounds) {
        return ball.getX() <= bounds.x + bounds.width &&
                ball.getX() >= bounds.x &&
                ball.getY() < bounds.y + bounds.height;
    }

    private boolean isBrickTopCollision(Ball ball, Rectangle bounds) {
        return ball.getX() >= bounds.x &&
                ball.getX() <= bounds.x + bounds.width &&
                ball.getY() + ball.getHeight() >= bounds.y &&
                ball.getY() + ball.getHeight() <= bounds.y + bounds.height;
    }
}
