package superMario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JPanel {

	public static final int WIDTH = 640; // 窗口的宽
	public static final int HEIGHT = 480; // 窗口的高

	Chapter chap;
	List<Chapter> chapters;
	Mario mario;
	Mushroom mushroom;

	int chapterIndex;
	int direction;
	boolean jumpState;
	//得分情況：
	int life=3;
	int score= 0;
	//boolean gameOver;
	
	void newGame(){
		chapters= new ArrayList<Chapter>();
		chap = Chapter.generateChap();
		life=5;
		score=0;
		chapterIndex=0;
		chapters.add(chap);
		mario = new Mario(0, HEIGHT-66-46);
		mushroom= chap.mushroom;
	}
	
	//下一关
	public void nextChapter(){
		//System.out.println(chapterIndex+",,,,,,,,");
		if(mario.x>= WIDTH){
			if(chapters.size()> ++chapterIndex){
				chap= chapters.get(chapterIndex);
			}else{
			
			chap= Chapter.generateChap();
			chapters.add(chap);
			}
			mario.setLocation(0, HEIGHT-66-mario.height);
			mushroom= chap.mushroom;
			
		}
	}
	
	//回到上一关
	public void previousChapter(){
		if(mario.x<= -mario.width){
			
			chap= chapters.get(--chapterIndex);
			mario.setLocation(WIDTH-mario.width, HEIGHT-66-mario.height);
			mushroom= chap.mushroom;
		}	
	}
	
	//马里奥移动
	void marioMoveAction() {
		//首先判断是否碰到了障碍物
		//跳跃落到障碍物上
		if(side==-2 && mario.ySpeed> 0){
			//System.out.println(mario.y+ mario.height- chap.ground.y);
			mario.isJumping= false;
			jumpState=false;
			mario.ySpeed=0;
		}
		
		
		if (direction > 0 && side!= -1) {
			mario.moveRight();
			
		}
		if (direction < 0 && side!= 1) {
			mario.moveLeft();
			/*if(!jumpState &&side==0 ){
				mario.fall();
			}*/
		}
		
		if(!jumpState &&side==0 ){
			mario.fall();
		}
		
		if (jumpState == true ) {
			mario.jump();
		}
		
		if(side==2){
			mario.fall();
		}
		
		if(mario.y> HEIGHT-65-mario.height+6){
			mario.y= HEIGHT-65-mario.height;
			mario.isJumping= false;
		}
		
		//消除五星得分
		Iterator<Star> i= chap.starList.iterator();
		while(i.hasNext()){
			Star s= i.next();
			if(s.intersects(mario)){
				i.remove();
				score++;
			}
		}
		
		nextChapter();
		previousChapter();
	}

	//小蘑菇移动
	public void mushroomMove(){
		mushroom.move();
		//和柱子碰撞
		for (Block o : chap.list) {
			if (o != chap.ground) {
				if (mushroom.intersects(o)) {
					mushroom.xSpeed *= -1;
					break;
				}
			}
		}
		
		//和马里奥碰撞
		int marioSide= 0;
		if(mushroom.intersects(mario)){
			marioSide= mushroom.hitSide(mario);
			if(marioSide==-2){
				if(!mario.bigSize){
					mario.growBig();
				}else{
					life++;
				}
				mushroom= null;
				chap.mushroom=null; 
			}else{
				if(mario.bigSize){
					mario.backToSmall();
				}else{
					life--;
					mario.setLocation(0, HEIGHT-66-mario.height);
				}
			}
		}
	}
	
	//碰撞的位置： 上 -2， 左 -1， 右 1.
	int side= 0;
	
	int hitSide(Mario r){
		for(Block o: chap.list){
			boolean b= r.intersects(o);
			if(b== true){
				int xd=  o.x- r.x;
				int yd=  o.y- r.y;
				
				if(yd>= r.height-4 ) {
					return -2;
				}
				/*if(yd<= -o.width+4){
					return 2;
				}*/
				if(xd<0 ){
					return 1;
				}else{
					return -1;
				}
			}
		}
		return 0;
	}

	public void action() throws InterruptedException {
		KeyListener l = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_RIGHT) {
					direction = 1;
				}
				if (key == KeyEvent.VK_LEFT) {
					direction = -1;
				}
				if (key == KeyEvent.VK_SHIFT) {
					if (jumpState == false) {
						jumpState = true;
					}
				}
				Window.this.repaint();
			}
			public void keyReleased(KeyEvent e) {
				direction = 0;
			}
		};
		this.addKeyListener(l);
		this.setFocusable(true);
		this.requestFocus();
		
		while (true) {
			if(life<=0){
				repaint();
				Thread.sleep(3000);
				newGame();
			}
			side= hitSide(mario);
			marioMoveAction();
			if(mushroom!= null){
				mushroomMove();
			}
			//System.out.println(chap);
			repaint();
			Thread.sleep(5);
		}
		
	 }	 

	public void paint(Graphics g) {
		//System.out.println(chap);
		
		chap.paintObject(g);
		mario.paintObject(g);
		g.setFont( new Font("黑体", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("关卡: "+ (1+chapterIndex), 500, 20);
		g.drawString("生命值: "+ life, 500, 40);
		g.drawString("得分: "+score,500,60);
		
		
		if(life<= 0){
			g.drawImage(loadImage("gameOver.png"), 0, 0, null);
		}
	}

	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Window.class.getResource(fileName)); // 同包下读图片
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame(); // 创建窗口对象
		Window window = new Window(); // 创建面板对象
		frame.add(window); // 将面板添加到窗口中
		frame.setSize(WIDTH, HEIGHT); // 设置窗口的大小
		frame.setLocationRelativeTo(null); // 设置窗口居中显示
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭时退出程序
		frame.setVisible(true); // 1)设置窗口可见 2)尽快调用paint()方法
		window.newGame();
		window.action(); // 启动程序的执行
		
	}
}
