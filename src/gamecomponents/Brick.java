package gamecomponents;

import java.awt.*;
import java.util.Random;

import files.ImageFile;
import files.Level;

public class Brick {
	
	public static final int DESTROY = 0;

	public static int brickWidth;
	public static int brickHeight;
	public static int totalCurrentBricks;

    public int brick[][];
	public int set [][];
	private int row, col;

	private Image imageAksara;

	public Brick(int level) {
		// Random random = new Random();
		this.brick = Level.getLevelBricks(level);
		this.set = new int[Level.BRICKS_ROW][Level.BRICKS_COL];
		this.row = Level.BRICKS_ROW;
		this.col = Level.BRICKS_COL;
		
		brickWidth = 540 / col;
		brickHeight = 150 / row;

		totalCurrentBricks = getInitialTotalBrick();
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < brick.length; i++) {
			for(int j = 0; j < brick[0].length; j++) {

				// Gambar brick biasa
                if(brick[i][j] < 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j*brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
					g.drawRect(j*brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}

				// Gambar brick spesial
				if (brick[i][j] > 0) {
					g.setColor(Color.RED);
					g.fillRect(j*brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
					g.drawRect(j*brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

					imageAksara = ImageFile.getBricksImage(this.set[i][j], 5 - brick[i][j]);
					g.drawImage(imageAksara, j * brickWidth + 80, i * brickHeight + 50, null);
				}
				
			}
		}
	}

	public void brickSet(int brick[][]) {
		Random rand = new Random();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(brick[i][j] > 0) this.set[i][j] = rand.nextInt(4);
				else this.set[i][j] = -1;
			}
		}
	}
	
	public void setBickValue(int value, int row, int col) {
		brick[row][col] = value;
	}


	public boolean brickDestroyed(int i, int j) {
		if (this.brick[i][j] == 0) return true;
		return false;
	}	

	public boolean brickSpecial(int i, int j) {
		if (this.brick[i][j] > 0) return true;
		return false;
	}

	public Rectangle getBrick(int i, int j) {
		int brickX = j * Brick.brickWidth + 80;
        int brickY = i * Brick.brickHeight + 50;
		Rectangle brickRect = new Rectangle(brickX, brickY, Brick.brickWidth, Brick.brickHeight);
		
		return brickRect;
	}

	private int getInitialTotalBrick() {
		int count = 0;
		for (int[] br : brick) {
			for (int b : br) {
				if (b != 0) count++;
			}
		}
		return count;
	}
}
