package superMario;

import java.awt.image.BufferedImage;

public class Ground extends Block{
	
	
	public static BufferedImage image= Window.loadImage("ground.png");
	
	
	public Ground() {
		super(0, 480-65,640, 65);
	}

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return image;
	}

}
