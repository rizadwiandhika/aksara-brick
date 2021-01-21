package main;

import handlers.*;
import java.awt.*;
import javax.swing.*;


public class Controller extends JPanel implements Runnable  {
    
    // Enums
    public enum STATE {
        MENU,
		PICKLEVEL,
		GAME,
		GAMEOVER,
    }

    // thread dan graphics
    private Thread thread;
    // private BufferedImage image;
    // private Graphics2D g;

    // Attributes
    private static STATE state;
    // aplikasi pages
    private static Menu menu;
    private static Game game;
    private static GameOver gameOver;
    private PickLevel pickLevel;

    public static final Font BIG_FONT = new Font("Comic Sans MS", Font.BOLD, 32);
    public static final Font MEDIUM_FONT = new Font("Comic Sans MS", Font.BOLD, 24);
    public static final Font SMALL_FONT = new Font("Comic Sans MS", Font.BOLD, 18);

    // running aplikasi
    private static boolean running;
	private static final long serialVersionUID = 1L;
    private static int playerLevel;
    private static int currentPlayingLevel;
    public static int score;


    // Constructor
    public Controller() {
        super();
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        setFocusable(true);
        requestFocus(true);

        init();
        startThread();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        switch(Controller.state) {
            case MENU :
                menu.tick();
                menu.render(g);
                break;

            case PICKLEVEL :
                pickLevel.tick();
                pickLevel.render(g);
                break;

            case GAME :
                game.tick();
                game.render(g);
                break;

            case GAMEOVER :
                gameOver.tick();
                gameOver.render(g);
                break;

            default :
                break;
        }
    }

    private void init() {
        this.addKeyListener(new KeyHandler());
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());

        state = STATE.MENU;
        playerLevel = 0;
        running = true;

        menu = new Menu();
        pickLevel = new PickLevel();
    }

    // Starting point
    public void startThread() {
        thread = new Thread(this);
        thread.start(); // Menjalankan method run()
    }

    @Override
    public void run() {
        while (running) {
            repaint(); // Intinya disini

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {/* do nothing */}
        }
        System.out.println("END");
    }

    public static void switchState(STATE state) {
        if (state == STATE.GAMEOVER) {
            gameOver = new GameOver(game);
        }

        Controller.state = state;
    }

    public static void switchState(STATE state, int selectedLevel) {
        if(state == STATE.GAME) {
			Controller.score = 0;
			game = new Game(selectedLevel);
        }
        
        Controller.state = state;
        Controller.currentPlayingLevel = selectedLevel;
    }

    public static STATE getState() {
        return state;
    }

    public static int getCurrentPlayingLevel() {
        return currentPlayingLevel;
    }

    public static int getPlayerLevel() {
        return Controller.playerLevel;
    }

    public static void requestUpgradePlayerLevel() {
        if (currentPlayingLevel == Controller.playerLevel) {
            Controller.playerLevel += 1;
        }
    }

    public static void stopRunning() {
        running = false;
    }

}
