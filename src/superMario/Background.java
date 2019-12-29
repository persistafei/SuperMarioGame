package superMario;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Background extends Block{
	
	//int x1= 640; //second image
	public Background() {
		super(640, 480);
	}

	public static BufferedImage image= Window.loadImage("background.png");

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	
}
