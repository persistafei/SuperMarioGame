package superMario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.w3c.dom.css.Rect;

public class Mushroom extends Block{
	int xSpeed= 1;
	
	public Mushroom(int x, int y) {
		super(36, 22);
		this.x = x;
		this.y = y;
	}

	public static BufferedImage image = Window.loadImage("animal.png");

	void move() {
		x += xSpeed;
	}

	
	int hitSide(Rectangle r){
		int yd = y - r.y;
		if (yd >= r.height - 3) {
			return -2;
		}
		return 0;
	}
	

	public BufferedImage getImage() {
		return image;
	}

	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

}
