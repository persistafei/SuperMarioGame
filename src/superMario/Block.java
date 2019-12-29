package superMario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Block extends Rectangle{
	
	
	public Block(int width, int height) {
		super(width,height);
	}
	
	public Block(int x, int y,int width, int height){
		super(x, y,width,height);
	}
	
	public abstract BufferedImage getImage();
		
	public void paintObject(Graphics g){
		g.drawImage(getImage(), x, y, null);
	}
	
}
