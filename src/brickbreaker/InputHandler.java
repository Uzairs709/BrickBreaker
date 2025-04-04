package brickbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public record InputHandler(Base base) implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                base.setLeftKeyPressed(true);
                break;
            case KeyEvent.VK_RIGHT:
                base.setRightKeyPressed(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                base.setLeftKeyPressed(false);
                break;
            case KeyEvent.VK_RIGHT:
                base.setRightKeyPressed(false);
                break;
            default:
                break;
        }
    }
}
