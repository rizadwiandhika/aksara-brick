package handlers;

import java.awt.event.*;

public class KeyHandler implements KeyListener  {
    
	public static boolean ENTER = false;
	public static boolean SPACE = false;
	public static boolean UP = false;
    public static boolean LEFT = false;
	public static boolean RIGHT = false;
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) ENTER = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) SPACE = true;

		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) UP = true;
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) LEFT = true;
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) RIGHT = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) ENTER = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) SPACE = false;

		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) UP = false;
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) LEFT = false;
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) RIGHT = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
