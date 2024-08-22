package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    String dir;
    int score = 0;
    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_X) {
            score++;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(dir != "down") {
            if (code == KeyEvent.VK_W) dir = "up";
        }
        if(dir != "left") {
            if (code == KeyEvent.VK_A) dir = "right";
        }
        if(dir != "up") {
            if (code == KeyEvent.VK_S) dir = "down";
        }
        if(dir != "right") {
            if (code == KeyEvent.VK_D) dir = "left";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
