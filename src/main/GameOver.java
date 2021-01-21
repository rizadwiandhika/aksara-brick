package main;

import java.awt.*;
import handlers.*;

public class GameOver {

    private Game lastGame;
    private Rectangle bounds = new Rectangle(243, 290, 230, 40);
    private static final Color BG_COLOR = new Color(0,0,0,191);
    private static final Color HOVER_COLOR = new Color(255,255,255,64);

    public GameOver(Game lastGame) {
        this.lastGame = lastGame;
    }

    public void tick() {
        if (KeyHandler.ENTER) {
            int currentPlayingLevel = Controller.getCurrentPlayingLevel();
            Controller.switchState(Controller.STATE.GAME, currentPlayingLevel);
        } 

        if (bounds.contains(MouseHandler.mousePoint)) {
            Main.frame.setCursor(Cursor.HAND_CURSOR);
            if (MouseHandler.mouseDown) {
                MouseHandler.mouseDown = false;
                Controller.switchState(Controller.STATE.PICKLEVEL);
            }
        } else {
            Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
        }
    }
    
    public void render(Graphics g) {
        lastGame.render(g);

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.YELLOW);
        g.setFont(Controller.MEDIUM_FONT);
        g.drawString("Press Enter to Restart" , 230, 225);

        g.setColor(Color.WHITE);
        g.drawString("Back to pick level", 255, 320);

        g.setFont(Controller.BIG_FONT);
        if (lastGame.isPlayerWin()) {
            g.setColor(Color.GREEN);
			g.drawString("Congratss... You Won!!!", 180, 175);
        } else {
            g.setColor(Color.RED);
            g.drawString("Game Over, Scores: " + lastGame.getScore() , 180, 175);
        }

        g.setColor(Color.WHITE);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        if (bounds.contains(MouseHandler.mousePoint)) {
            g.setColor(HOVER_COLOR);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

}
