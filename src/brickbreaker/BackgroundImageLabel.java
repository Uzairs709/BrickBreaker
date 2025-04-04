
package brickbreaker;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class BackgroundImageLabel extends JLabel {
    public BackgroundImageLabel() {
        super.setPreferredSize(new Dimension(CONSTANTS.PANEL_WIDTH, CONSTANTS.PANEL_HEIGHT));
        super.setBounds(0, 0, CONSTANTS.PANEL_WIDTH, CONSTANTS.PANEL_HEIGHT);
        
        // Load background image
        Image backgroundImage;
        try {
            backgroundImage = ImageIO.read(new File(CONSTANTS.BACKGROUND_IMAGE));
            super.setIcon(new ImageIcon(backgroundImage));
        } catch (IOException ex) {
            System.out.println("[ERROR] Unable to load background image");
        }
        
    }
}
