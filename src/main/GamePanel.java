package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // general
    boolean gameLoop = true;
    int FPS = 2;
    int score = 0;
    final int maxScreenRow = 12;
    final int maxScreenCol = 16;
    static final int originalTileSize = 16;
    static final int scale = 3;
    static final int tileSize = scale * originalTileSize;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    // entities
    Snake snake = new Snake(this, keyH);
    Food food = new Food(this);



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println("Game thread started!");
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                System.out.println("Head pos: " + snake.x + " " + snake.y + "FPS: " + FPS);
                delta = 0;
            }
        }

    }
    public void update(){
        snake.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // columns
        for (int i = 0; i < maxScreenCol; i++) {
            g.drawLine(i * tileSize, screenHeight, i * tileSize, 0);
        }
        //rows
        for (int i = 0; i < maxScreenRow; i++) {
            g.drawLine(0, i * tileSize, screenWidth, i * tileSize);
        }
        snake.draw(g);
        food.draw(g);
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(String.valueOf(score), 20, 30);
        if(gameLoop == false) {
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Game Over!", 20, 30);
        }
        g.dispose();
    }

}


