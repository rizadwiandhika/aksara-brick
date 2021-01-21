package gamecomponents;

import java.awt.*;

import main.Game;
import main.Main;

public class Paddle {
    
    private int x;
    protected final int Y;
    protected final int WIDTH;
    protected final int HEIGHT;
    public final int SLIDE_SPEED;
    protected final Color COLOR = Color.GREEN;

    public Paddle(int slideSpeed) {
        this.WIDTH = 100;
        this.HEIGHT = 8;
        this.Y = Game.MAX_Y;
        this.SLIDE_SPEED = Math.abs(slideSpeed);
        
        this.x =  Main.WIDTH / 2;
    }

    public void draw(Graphics g) {
        g.setColor(COLOR);
		g.fillRect(x - (int) WIDTH / 2, Y - (int) HEIGHT / 2, WIDTH, HEIGHT);
    }

    public void moveLeft() {
        x -= SLIDE_SPEED;
    }

    public void moveRight() {
        x += SLIDE_SPEED;
    }

    public boolean intersect(Ball b) {
        if (b.getY() + b.RADIUS >= this.Y - this.HEIGHT / 2) {

            if (b.getX() >= this.getLeftX() && b.getX() <= this.getRightX()) {
                return true;
            }
        }

        return false;
    }

    public int getX() {
        return x;
    }

    public int getLeftX() {
        return x - (int) WIDTH / 2;
    }
    public int getRightX() {
        return x + (int) WIDTH / 2;
    }

}
