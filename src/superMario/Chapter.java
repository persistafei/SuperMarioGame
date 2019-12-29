package superMario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Chapter {

	public static BufferedImage[] images = new BufferedImage[2];

	Background background;
	Ground ground;
	Mushroom mushroom;
	// 障碍物
	List<Block> list= new ArrayList<>();
	List<Star> starList= new ArrayList<>();

	public Chapter() {
		background = new Background();
		ground = new Ground();
		mushroom= new Mushroom(250, 480-65-22);
		
		//大小柱子
		for(int i=0; i< 6; i++){
			list.add(new Obstacle(i%2));
		}
		 
		//砖块
		int l= 160;
		for(int i=0; i<6; i++){
			list.add(new Obstacle(2, l,180));
			starList.add(new Star(l, 180-28));
			l+= 32;
		}
		
		// 按x坐标排序，（animal碰撞顺序）
		list.sort((Block o1, Block o2)->o2.x-o1.x);
		//将地面加入集合
		list.add(ground);
	}

	// 关卡生成
	 static Chapter generateChap() {
		return new Chapter();
	}

	void paintObject(Graphics g) {
		background.paintObject(g);
		ground.paintObject(g);
		for(Block o: list){
			o.paintObject(g);
		}
		for(Star o: starList){
			o.paintObject(g);
		}
		if(mushroom!= null){
			mushroom.paintObject(g);
		}
	}

}
