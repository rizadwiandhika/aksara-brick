package gamecomponents;

import java.awt.*;
import java.util.Random;

import main.Game;

public class Ball {
    
    public final int RADIUS;
    private int x;
    private int y;
	public int speedX;
    private int speedY;
    private final Color COLOR = Color.YELLOW;
    
    public Ball (int speed, int deg) {
        Random random = new Random();
        this.RADIUS = 8;
        this.x = random.nextInt((Game.MAX_X - RADIUS) - (Game.MIN_X + RADIUS) + 1)  
                                + (Game.MIN_X + RADIUS);
        this.y = 350;
        this.speedX = (int) ((double)speed * Math.cos(Math.toRadians(deg)));
        this.speedY = (int) ((double)speed * Math.sin(Math.toRadians(deg)));
        if (this.speedY > 0) this.speedY *= -1;
    }

    public void draw (Graphics g) {
        g.setColor(COLOR);
        g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }

    public boolean intersectBrick(Rectangle brickRect) {
        Rectangle ballRect = new Rectangle(x - RADIUS, y - RADIUS, getDiameter(), getDiameter());
        return brickRect.intersects(ballRect);
    }
    public boolean intersectLeftRightBrick(Rectangle b) {
        if (y >= b.y && y <= b.y + b.height) {
            if (x + RADIUS >= b.x || x - RADIUS <= b.x + b.width) {
                return true;
            }
        }
        return false;
    }

    public boolean intersectUpDownBrick(Rectangle b) {
        if (x >= b.x && x <= b.x + b.width) {
            if (y + RADIUS >= b.y || y - RADIUS <= b.y + b.height) {
                return true;
            }
        }
        return false;
    }

    public void moveX() {
        this.x += this.speedX;
    }
    public void moveY() {
        this.y += this.speedY;
    }

    public void reverseSpeedX() {
        this.speedX = -this.speedX;
    }
    public void reverseSpeedY() {
        this.speedY = -this.speedY;
    }

    public void stop() {
        this.speedX = this.speedY = 0;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDiameter() {
        return 2 * RADIUS;
    }

}
