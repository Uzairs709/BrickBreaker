package brickbreaker;

import brickbreaker.AudioPlayback;
import brickbreaker.Settings;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle(CONSTANTS.TITLE);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setSize(CONSTANTS.PANEL_WIDTH, CONSTANTS.PANEL_HEIGHT);
        super.setLocationRelativeTo(null);
        super.setIconImage(new ImageIcon(CONSTANTS.ICON_IMAGE).getImage());
        super.add(new GamePanel());
        
        // Game settings
        Settings gameSettings = Settings.getInstance();
        
        if (gameSettings.isBackgroundMusicOn()) {
            this.playBackgroundMusic();
        }
    }

    /**
     * Plays background music loop in a separate thread
     */
    private void playBackgroundMusic() {
        new Thread(AudioPlayback::playMusic).start();
    }
}
