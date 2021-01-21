package handlers;

import java.awt.event.*;
import java.awt.*;

public class MouseHandler implements MouseListener, MouseMotionListener  {
    
    public static boolean mouseDown = false;
    public static boolean hasPressed = false;
    public static Point mousePoint = new Point(0, 0);


    // Implemented MouseListener methods
    @Override
    public void mousePressed(MouseEvent e) {
        if (!MouseHandler.hasPressed) {
            MouseHandler.mouseDown = true;
            MouseHandler.hasPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseHandler.mouseDown = false;
        MouseHandler.hasPressed = false;
    }
    
    // Implemented MouseMotionListener methods
	@Override
	public void mouseDragged(MouseEvent e) {
        MouseHandler.mouseDown = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        MouseHandler.mousePoint = e.getPoint();
    }
    

    
    @Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
