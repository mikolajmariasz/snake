package main;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
public class Snake extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    ArrayList<Point> body;
    int snakeSize;
    Random rand = new Random();
    Snake(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        body = new ArrayList<>();
        setDefaultValues();
    }
    public void setDefaultValues() {
        x = 2;
        y = 2;
        body.clear();
        snakeSize = 2;
        keyH.dir = "left";
    }
    public void update() {
        if (gp.gameLoop == true) {
            updateBody();
            if (keyH.dir == "left") x++;
            if (keyH.dir == "right") x--;
            if (keyH.dir == "up") y--;
            if (keyH.dir == "down") y++;
            }
        checkCollision();
        }

    public void checkCollision() {
        for (Point segment : body) {
            if (x == segment.x && y == segment.y) {
                gp.gameLoop = false;
            }
            if (x == gp.food.x && y == gp.food.y) {
                snakeSize++;
                gp.score++;
                gp.food.x = rand.nextInt(gp.maxScreenCol);
                gp.food.y = rand.nextInt(gp.maxScreenRow);
                gp.FPS++;
            }
            if (x == gp.maxScreenCol) x = -1;
            if (y == gp.maxScreenRow) y = -1;
            if (x == -2) x = gp.maxScreenCol;
            if (y == -2) y = gp.maxScreenRow;
        }
    }
    private void updateBody() {
        body.add(0, new Point(x, y));

        if (body.size() > snakeSize) {
            body.remove(snakeSize);
        }
    }

    public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            for (Point segment : body) {
                g.fillOval(segment.x * gp.tileSize, segment.y * gp.tileSize, gp.tileSize, gp.tileSize);
            }
            g.setColor(Color.RED);
            g.fillOval(x * gp.tileSize, y * gp.tileSize, gp.tileSize, gp.tileSize);
    }

}
