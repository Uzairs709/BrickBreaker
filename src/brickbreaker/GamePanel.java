package brickbreaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private final CollisionHandler collisionHandler = new CollisionHandler(this);
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
        addBricksToScreen();
        super.add(backgroundImageLabel, BorderLayout.NORTH);

        // Game settings
        gameSettings = Settings.getInstance();

        // Start game loop
        timer = new Timer(50, this);
        timer.start();
    }
    private void addBricksToScreen() {
        createBrickPattern();
        for (Brick brick : bricks) {
            backgroundImageLabel.add(brick);
        }
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
        collisionHandler.checkCollisionsWithBase(base, ball);
        Brick collidedBrick = collisionHandler.checkCollisionsWithBricks(bricks, ball);
        if (collidedBrick != null) {
            if (gameSettings.isSoundEffectsOn()) {
                AudioPlayback.playSoundEffect();
            }
            backgroundImageLabel.remove(collidedBrick);
        }

        if (bricks.isEmpty()){
            levelNumber++;
           addBricksToScreen();
        }

        if (ball.getY() >= 500) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Gameover");
        }
    }

    private void createBrickPattern(){
        Level currentLevel = LevelFactory.getLevel(levelNumber);
        currentLevel.createBrickPattern(bricks);
    }

    public long getLastCollisionTime() {
        return lastCollisionTime;
    }

    public void setLastCollisionTime(long currentTime) {
    lastCollisionTime=currentTime;
    }
}
