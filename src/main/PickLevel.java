package main;

import java.awt.*;

import files.*;
import handlers.MouseHandler;

public class PickLevel {

    private Image lockedIcon;

    public PickLevel() {
        this.lockedIcon = ImageFile.getLockedImage();
    }
    
    private Rectangle bounds[] = {
        new Rectangle(50 + 112, 200, 75, 75), new Rectangle(150 + 112, 200, 75, 75),
        new Rectangle(250 + 112, 200, 75, 75), new Rectangle(350 + 112, 200, 75, 75),
        new Rectangle(50 + 112, 300, 75, 75), new Rectangle(150 + 112, 300, 75, 75),
        new Rectangle(250 + 112, 300, 75, 75), new Rectangle(350 + 112, 300, 75, 75)
    };

    public void tick() {
        for (int i = 0; i < this.bounds.length; i++) {
            if (bounds[i].contains(MouseHandler.mousePoint) 
                    && i <= Controller.getPlayerLevel() 
                    && MouseHandler.mouseDown) {
                MouseHandler.mouseDown = false;

                Controller.switchState(Controller.STATE.GAME, i);
            }
        }
        if (oneOfBondsHover() && hoverOnUnlocked()) {
            Main.frame.setCursor(Cursor.HAND_CURSOR);
        } else {
            Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.setFont(Controller.BIG_FONT);
        g.drawString("Pilih Level", 275, 100);


        g.setFont(Controller.SMALL_FONT);
        for(int i = 0; i < bounds.length; i++) {
            g.setColor(Color.BLACK);
            g.drawRect(bounds[i].x, bounds[i].y, bounds[i].width, bounds[i].height);
            g.drawString("" + (i + 1), bounds[i].x + 30, bounds[i].y + 30);

            if (i > Controller.getPlayerLevel()) {
                g.drawImage(lockedIcon, bounds[i].x, bounds[i].y, null);
            }

            if (bounds[i].contains(MouseHandler.mousePoint) && i <= Controller.getPlayerLevel()) {
                g.setColor(new Color(255, 255, 255, 150));
                g.fillRect(bounds[i].x, bounds[i].y, bounds[i].width, bounds[i].height);
            }
        }

    }

    private boolean oneOfBondsHover() {
        if (bounds[0].contains(MouseHandler.mousePoint) 
                || bounds[1].contains(MouseHandler.mousePoint)
                || bounds[2].contains(MouseHandler.mousePoint)
                || bounds[3].contains(MouseHandler.mousePoint)
                || bounds[4].contains(MouseHandler.mousePoint)
                || bounds[5].contains(MouseHandler.mousePoint)
                || bounds[6].contains(MouseHandler.mousePoint)
                || bounds[7].contains(MouseHandler.mousePoint)) {
            return true;
        }
        return false;
    }

    private boolean hoverOnUnlocked() {
        for (int i = 0; i < bounds.length; i++) {
            if (bounds[i].contains(MouseHandler.mousePoint)
                    && i <= Controller.getPlayerLevel()) {
                return true;
            }
        }
        return false;
    }
}
