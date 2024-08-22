package main;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;

public class Food extends Entity{
    GamePanel gp;

    Food(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
    }
    public void setDefaultValues() {
        x = 10;
        y = 10;
    }
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x * gp.tileSize, y * gp.tileSize, gp.tileSize, gp.tileSize);
    }
}
