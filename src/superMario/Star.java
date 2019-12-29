package superMario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Star extends Block{
	
	public Star(int x, int y) {
		super(27, 27);
		this.x = x;
		this.y = y;
	}

	public static BufferedImage image = Window.loadImage("star.png");

	public BufferedImage getImage() {
		return image;
	}

	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
}
