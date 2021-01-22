package main;

import java.awt.*;

import gamecomponents.*;
import java.util.Random;

import files.*;
import handlers.KeyHandler;

public class Game {

	private final Color BG_MENU_COLOR;
	private final Color BG_PLAY_COLOR;
	private final Color BG_PAUSED_COLOR;
	private final Image BG_GAME_IMAGE;

	public static final int MIN_X = 7;
	public static final int MIN_Y = 5;
	public static final int MAX_X = 680;
	public static final int MAX_Y = 560;
    
	private Ball ball;
    private Brick bricks;
    private Paddle paddle;

	private int score;
	private boolean play;
	private boolean menu;
	private boolean paused;
	private boolean gameWin;
	private boolean gameOver;

    
    public Game(int selectedLevel) {
		final int MAX_SPEED = 6;
		final int MIN_SPEED = 4;
		final int MAX_ANGLE = 55;
		final int MIN_ANGLE = 45;

        Random random = new Random();
		int speed = random.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED;
		int degree = random.nextInt(MAX_ANGLE - MIN_ANGLE + 1) + MIN_ANGLE;

		BG_MENU_COLOR = null;
		BG_PLAY_COLOR = new Color(20, 20, 20);
		BG_PAUSED_COLOR = new Color(0, 0, 0, 191);
		BG_GAME_IMAGE = ImageFile.getbgGameImage();

		this.bricks = new Brick(selectedLevel);
		this.paddle = new Paddle((int) ((double)speed * Math.cos(Math.toRadians(degree))) + 1);
		this.ball = new Ball(speed, degree);

		this.score = 0;
		this.play = true;
		this.paused = false;
		this.gameWin = false;
		this.gameOver = false;
	}
	
	public Game() {
		final int MAX_SPEED = 6;
		final int MIN_SPEED = 4;
		final int MAX_ANGLE = 55;
		final int MIN_ANGLE = 45;

        Random random = new Random();
		int speed = random.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED;
		int degree = random.nextInt(MAX_ANGLE - MIN_ANGLE + 1) + MIN_ANGLE;
		
		BG_MENU_COLOR = new Color(79, 51, 15);
		BG_PLAY_COLOR = null;
		BG_PAUSED_COLOR = null;
		BG_GAME_IMAGE = null;

		this.menu = true;
		this.bricks = new Brick(6);
		this.paddle = new Paddle((int) ((double)speed * Math.cos(Math.toRadians(degree))) + 1);
		this.ball = new Ball(speed, degree);
	}

    public void tick() {
		if (gameOver) {
			if (gameWin) Controller.requestUpgradePlayerLevel();
			Controller.switchState(Controller.STATE.GAMEOVER);
		}

		if (play && (KeyHandler.LEFT || KeyHandler.RIGHT)) {
			if (KeyHandler.LEFT && paddle.getLeftX() > Game.MIN_X)  paddle.moveLeft();
			if (KeyHandler.RIGHT && paddle.getRightX() < Game.MAX_X)  paddle.moveRight();
		}

		if (KeyHandler.SPACE) {
			KeyHandler.SPACE = false;
			toogleGamePause();
		}
		
		if (play) updateGame();

		Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
    }
    
    public void tickMenu() {
    	if(ball.getX() > paddle.getWidth()/2 
    			&& ball.getX() < Main.WIDTH-paddle.getWidth()/2)
    	paddle.setX(ball.getX());
    	
		if (menu) updateGame();

		Main.frame.setCursor(Cursor.DEFAULT_CURSOR);
    }

    public void updateGame() {

		// Setiap brick akan dicek apakan terjadi tabrakan / tidak
		for ( int i = 0; i < bricks.brick.length; i++ ) {
			for ( int j = 0; j < bricks.brick[0].length; j++ ) {
				Rectangle currentBrick = bricks.getBrick(i, j);

				// Kalo  bola menabrak brick & brick belum hancur
				if ( ball.intersectBrick(currentBrick) 
						&& !bricks.brickDestroyed(i, j)
						&& ( ball.intersectLeftRightBrick(currentBrick)
								|| ball.intersectUpDownBrick(currentBrick) ) ){

					// Cek sisi tabrakan bola dengan brick, lalu ubah arah kecepatan bola
					if (ball.intersectLeftRightBrick(currentBrick)) {
						ball.reverseSpeedX();
					} 
					if (ball.intersectUpDownBrick(currentBrick)) {
						ball.reverseSpeedY();
					}

					// Penghancuran brick
					if (bricks.brickSpecial(i, j)) {
						int val = bricks.brick[i][j] - 1;
						bricks.setBickValue(val, i, j);

						if (val == Brick.DESTROY) {
							Brick.totalCurrentBricks -= 1;
							score += 20;
						}
					} else {
						bricks.setBickValue(Brick.DESTROY, i, j); // menghancurkan brick
						Brick.totalCurrentBricks -= 1;
						score += 5;
					}
					break;
				}
			}
		}

		// Cek apakah bola menabrak batas window
		if(ball.getX() - ball.RADIUS < Game.MIN_X || ball.getX() + ball.RADIUS > Game.MAX_X)
			ball.reverseSpeedX();

		if(ball.getY() - ball.RADIUS < Game.MIN_Y || paddle.intersect(ball)) 
			ball.reverseSpeedY();

		ball.moveX();
		ball.moveY();

		// Cek apakah player menang / kalah
		if(Brick.totalCurrentBricks <= 0 || ball.getY() > Game.MAX_Y - 8) {
			if (Brick.totalCurrentBricks <= 0) gameWin = true;

			play = false;
			gameOver = true;
		}
    }

    public void render(Graphics g) {
		// update
    	// 
    	
    	
		// borders
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        // background
		g.setColor(BG_PLAY_COLOR);
		g.fillRect(5, 5, 675, 600);
		
		g.drawImage(BG_GAME_IMAGE, 0, 0, null);

		// scores
		g.setColor(Color.WHITE);
		g.setFont(Controller.MEDIUM_FONT);
		g.drawString("" + score, 592, 30);
		
		// bricks, paddle, and ball
		this.bricks.draw((Graphics2D) g);
		this.paddle.draw(g);
		this.ball.draw(g);

		if ( paused ) {
			final int PAUSED_TEXT_WIDTH = 265;
			final int PAUSED_TEXT_X = (Main.WIDTH - PAUSED_TEXT_WIDTH) / 2;
			final int PAUSED_TEXT_Y = 200;

			g.setColor(BG_PAUSED_COLOR);
			g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

			g.setColor(Color.ORANGE);
			g.setFont(Controller.BIG_FONT);
			g.drawString("Game is PAUSED", PAUSED_TEXT_X, PAUSED_TEXT_Y);
		}
	}

	public void renderMenu(Graphics g) {
		// borders
		g.setColor(BG_MENU_COLOR);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		// bricks, paddle, and ball
		this.bricks.draw((Graphics2D) g);
		this.paddle.draw(g);
		this.ball.draw(g);
	}

	public void toogleGamePause() {
		if (play) {
			play = false;
			paused = true;
			System.out.println("game paused");
		} else {
			play = true;
			paused = false;
			System.out.println("game continue");
		}
	}
	
	public boolean isPlayerWin() {
		return gameWin;
	}

	public int getScore() {
		return score;
	}

}
