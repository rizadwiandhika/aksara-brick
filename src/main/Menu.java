package main;

import java.awt.*;
import javax.swing.*;

import files.ImageFile;  

import handlers.MouseHandler;

public class Menu {
    
    private final Color RECT_COLOR;
    private Rectangle bounds[] = {  new Rectangle(100+10, 355+9, 265, 50),
                                    new Rectangle(100+10, 415+7, 265, 50)};

    
    private Image bgImage;
    private Game game;

    public Menu () {
        this.RECT_COLOR = new Color(240, 181, 105);
        this.bgImage = ImageFile.getBgMenuImage();
        this.game = new Game();
    }
    
    public void tick() {
        for (int  i = 0; i < bounds.length; i++ ) {
            if (bounds[i].contains(MouseHandler.mousePoint) && MouseHandler.mouseDown) {
                MouseHandler.mouseDown = false;
                MouseHandler.hasPressed = false;

                if (i == 0) {
                    Controller.switchState(Controller.STATE.PICKLEVEL);
                } else {
                    int choose = JOptionPane.showConfirmDialog(
                        null, "Do you really want to exit game ?", "Confirm", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    if (choose == JOptionPane.YES_OPTION) {
                        Main.frame.dispose();
                        Controller.stopRunning();
                    }
                }
            }
        }

        if (oneOfBondsHover()) {
            Main.frame.setCursor(Cursor.HAND_CURSOR);
        } else {
            Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    public void render(Graphics g) {

        // Passive game
        game.renderMenu(g);

		for(int i = 0; i < bounds.length; i++) {
            g.setColor(RECT_COLOR);
            g.fillRect(bounds[i].x, bounds[i].y, bounds[i].width, bounds[i].height);
            
            if (bounds[i].contains(MouseHandler.mousePoint)) {
                g.setColor(new Color(255, 255, 255, 150));
                g.fillRect(bounds[i].x, bounds[i].y, bounds[i].width, bounds[i].height);
            }
        }

        // Background Image
        g.drawImage(bgImage, 0, 0, null);
        
    }

    private boolean oneOfBondsHover() {
        if (bounds[0].contains(MouseHandler.mousePoint) 
                || bounds[1].contains(MouseHandler.mousePoint)) {
            return true;
        }
        return false;
    }
}
