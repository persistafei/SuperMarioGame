package superMario;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Obstacle extends Block{

	/*
	 * location is random but:
	 * x< 2* bg.width && x>= 0  && (y== 0 || y== bg.height- ground.height)
	 */
	
	int type;
	
	/*public Obstacle() {
		super(55, 66);
		x= new Random().nextInt(5)*100;
		y= Math.random()> 0.5? 0:480-66-65;
	}*/
	
	//柱子
	public Obstacle(int type) {
		super(40, 66);
		this.type= type;
		Random random= new Random();
		
		int range= random.nextInt(180)+ random.nextInt(2)*300;
		if(type==0){
			//x= random.nextInt(70)+30+ 200*random.nextInt(3);
			y= 480-height-65;
			x= range- range%100;
		}
		if(type==1){
			height=126;
			//x= (random.nextInt(2)+1)*200;
			y= Math.random()> 0.5? 0:480-height-65;
			x= range-range%30;
		}
		if(type==2){
			height=32;
			//x= (random.nextInt(2)+1)*200;
			/*y= 200;
			x= 300;*/
		}
	}
	
	public Obstacle(int type, int x, int y) {
		// TODO Auto-generated constructor stub
		this(type);
		this.x= x;
		this.y= y;
	}
	
	public static BufferedImage[] images= new BufferedImage[3];
	
	static{
		for(int i=0; i<3; i++){
			images[i]= Window.loadImage("obstacle"+ i + ".png");
		}
	}
	
	
	
/*	public Obstacle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
*/
	//int index=0;
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
//		return images[index++%2];
		return images[type];
	}
	
}
