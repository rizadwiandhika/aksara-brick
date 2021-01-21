package files;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageFile {
	private static Image lockedImage = Toolkit.getDefaultToolkit().getImage("image/assetGame/locked.png");
	private static Image bgMenuImage = Toolkit.getDefaultToolkit().getImage("image/BrickBreakerForeground.png");
	private static Image [][] imageAksara = new Image[4][5];

	
	public static Image getBricksImage (int set, int iter) {
        fillImageAksara();
		return imageAksara[set][iter];
	}
	
	public static Image getLockedImage() {
		return lockedImage;
	}

	public static Image getBgMenuImage() {
		return bgMenuImage;
	}
	
	private static void fillImageAksara() {
		imageAksara[0] = imageAksara0;
		imageAksara[1] = imageAksara1;
		imageAksara[2] = imageAksara2;
		imageAksara[3] = imageAksara3;
    }
	
	private static Image[] imageAksara0 = {
			Toolkit.getDefaultToolkit().getImage("image/aksara/img1/1_1.png"),
			Toolkit.getDefaultToolkit().getImage("image/aksara/img1/1_2.png"),
			Toolkit.getDefaultToolkit().getImage("image/aksara/img1/1_3.png"),
			Toolkit.getDefaultToolkit().getImage("image/aksara/img1/1_4.png"),
			Toolkit.getDefaultToolkit().getImage("image/aksara/img1/1_5.png"),
	    };

	    private static Image[] imageAksara1 = {
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img2/2_1.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img2/2_2.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img2/2_3.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img2/2_4.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img2/2_5.png"),
				
	    };

	    private static Image[] imageAksara2 = {
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img3/3_1.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img3/3_2.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img3/3_3.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img3/3_4.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img3/3_5.png"),
				
	    };

	    private static Image[] imageAksara3 = {
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img4/4_1.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img4/4_2.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img4/4_3.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img4/4_4.png"),
	    	Toolkit.getDefaultToolkit().getImage("image/aksara/img4/4_5.png"),
				
	    };
}
