package superMario;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.omg.CORBA.INTERNAL;

public class Mario extends Rectangle{
/*
 * 说明 ： (临界区)Vy*t< 4
 */

	int xSpeed= 4;
	double ySpeed;
	final double jumpInitSpeed = -10;
	double t = 0.3;
	double g= 0.5;  //gravity
	boolean forward= true;
	boolean bigSize= false;
	
	public Mario(int x, int y) {
		super(28, 46);
		this.x = x;
		this.y = y;
	}

	public static BufferedImage[] images = new BufferedImage[4];

	static {
		for (int i = 0; i <4; i++) {
			images[i] = Window.loadImage("mario" + i + ".gif");
		}
	}

	void moveRight() {
		forward= true;
		x += xSpeed * t;
	}

	void moveLeft() {
		forward= false;
		x -= xSpeed * t;
	}
	
	boolean isJumping = false;

	void jump() {
		
		//开始起跳
		if (isJumping == false){
			ySpeed = jumpInitSpeed;
			isJumping = true;
		}

		//继续跳
		y = (int) (y + ySpeed * t + g * t * t/2);
		ySpeed += g * t;

	}
	
	//初速度为0 降落
	void fall(){
		y+=3;
	}

	public void growBig(){
		if(!bigSize){
			
			System.out.println("马里奥变大了");
			width=44;
			height= 73;
			bigSize= true;
		}
	}
	
	public void backToSmall(){
		if(bigSize){
			
			System.out.println("马里奥回到原來大小");
			width=28;
			height= 46;
			bigSize= false;
		}
	}
	
	
	int p;
	BufferedImage getImage() {
		p= bigSize? 2:0;
		if(forward){
			return images[0+p];
		}else{
			return images[1+p];
		}
	}

	void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

}
