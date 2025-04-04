package brickbreaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private final Base base;
    private final Ball ball;
    private final ArrayList<Brick> bricks = new ArrayList<>();
    private final BackgroundImageLabel backgroundImageLabel;
    private final Timer timer;
    private final Settings gameSettings;
    private int levelNumber=1;
    private long lastCollisionTime = 0;

    public GamePanel() {

        super.setPreferredSize(new Dimension(CONSTANTS.PANEL_WIDTH, CONSTANTS.PANEL_HEIGHT));
        super.setLayout(new BorderLayout());

        backgroundImageLabel = new BackgroundImageLabel();
        base = new Base();
        ball = new Ball();
        backgroundImageLabel.add(ball);
        backgroundImageLabel.add(base);
        for (Brick brick : createBrickPattern()) {
            bricks.add(brick);
            backgroundImageLabel.add(brick);
        }
        super.add(backgroundImageLabel, BorderLayout.NORTH);

        // Game settings
        gameSettings = Settings.getInstance();

        // Start game loop
        timer = new Timer(50, this);
        timer.start();
    }
    /**
     * Main game-loop method. It repaints the game after every 16 milliseconds
     * (60 FPS)
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.repaint();

        base.move();
        ball.moveBall();
        checkCollisionsWithBase(base, ball);
        Brick collidedBrick = checkCollisionsWithBricks(bricks, ball);
        if (collidedBrick != null) {
            if (gameSettings.isSoundEffectsOn()) {
                AudioPlayback.playSoundEffect();
            }
            backgroundImageLabel.remove(collidedBrick);
        }

        if (bricks.isEmpty()){
            levelNumber++;
            for (Brick brick : createBrickPattern()) {
                bricks.add(brick);
                backgroundImageLabel.add(brick);
            }
        }

        if (ball.getY() >= 500) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Gameover");
        }
    }

    private ArrayList<Brick> createBrickPattern(){
        Level currentLevel = LevelFactory.getLevel(levelNumber);
        return currentLevel.createBrickPattern();
    }

    private void checkCollisionsWithBase(Base base, Ball ball) {
        // Using time base collision checks to avoid some collision glitches
        // like, multiple collision detections at a time
        long currentTime = System.currentTimeMillis();
        // CHECK COLLISION WITH TOP SIDE OF THE BASE
        long COLLISION_COOLDOWN = 100;
        if ((currentTime - lastCollisionTime) > COLLISION_COOLDOWN) {
            if (ball.getX() >= base.getX() && ball.getX() <= base.getX() + base.getBaseWidth() && ball.getY() + ball.getBallHeight() >= base.getY() && ball.getY() + ball.getBallHeight() <= base.getY() + base.getBaseHeight()) {
                ball.setyVelocity(ball.getyVelocity() * -1);

                if (ball.getX() > 400) {
                    ball.setxVelocity(ball.getxVelocity() * -1);
                }
                lastCollisionTime = currentTime;
            } // CHECK COLLISIONS WITH LEFT SIDE OF THE BASE
            else if (ball.getX() + ball.getBallWidth() >= base.getX() && ball.getX() + ball.getBallWidth() <= base.getX() + base.getBaseWidth() / 4 && ball.getY() >= base.getY() && ball.getY() <= base.getY() + base.getBaseHeight()) {
                ball.setxVelocity(ball.getxVelocity() * -1);
                ball.setyVelocity(ball.getyVelocity() * -1);
                lastCollisionTime = currentTime;
            } // CHECK COLLISIONS WITH RIGHT SIDE OF THE BASE (!!! Not tested yet !!!)
            else if (ball.getX() <= base.getX() + base.getBaseWidth() && ball.getX() >= base.getX() + base.getBaseWidth() - base.getBaseWidth() / 4 && ball.getY() + ball.getBallHeight() >= base.getY() && ball.getY() + ball.getBallHeight() <= base.getY() + base.getBaseHeight()) {
                ball.setxVelocity(ball.getxVelocity() * -1);
                ball.setyVelocity(ball.getyVelocity() * -1);
                lastCollisionTime = currentTime;
            }
        }
    }

    private Brick checkCollisionsWithBricks(ArrayList<Brick> bricks, Ball ball) {
        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            Rectangle bounds = brick.getBounds();
            if (ball.getX() >= bounds.x && ball.getX() <= bounds.x + bounds.width && ball.getY() <= bounds.y + bounds.height && ball.getY() >= bounds.y) {
                System.out.println("Brick bottom collided");
                iterator.remove();
                ball.setyVelocity(ball.getyVelocity() * -1);
                return brick;
            } // BALL'S RIGHT SIDE --> BRICK'S LEFT SIDE
            else if (ball.getX() + ball.getBallWidth() >= bounds.x && ball.getX() + ball.getBallWidth() <= bounds.x + 5 && ball.getY() > bounds.y && ball.getY() < bounds.y + bounds.height) {
                System.out.println("Brick left collided");
                iterator.remove();
                ball.setxVelocity(ball.getxVelocity() * -1);                return brick;
            } // BALL'S LEFT SIDE --> BRICK'S RIGHT SIDE
            else if (ball.getX() <= bounds.x + bounds.width && ball.getY() >= bounds.y && ball.getX() >= bounds.x && ball.getY() < bounds.y + bounds.height) {
                System.out.println("Brick right collided");
                iterator.remove();
                ball.setxVelocity(ball.getxVelocity() * -1);
                return brick;
            } // BALL'S BOTTOM SIDE --> BRICK'S TOP SIDE
            else if (ball.getX() >= bounds.x && ball.getX() <= bounds.x + bounds.width && ball.getY() + ball.getHeight() >= bounds.y && ball.getY() + ball.getHeight() <= bounds.y + bounds.height) {
                System.out.println("Brick top collided");
                iterator.remove();
                ball.setyVelocity(ball.getyVelocity() * -1);
                return brick;
            }
        }
        return null;
    }

}
