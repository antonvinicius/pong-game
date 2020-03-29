package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Countdown {
	public static int actualCount = 3;
	
	public Countdown() {
		actualCount = 3;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGTH);
		Font font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.yellow);
		
		g.drawString(actualCount + "", 70, Game.HEIGTH/2 + 20);
		
		double lastTime = System.currentTimeMillis();
		double delta = 0;
		while(delta != 1000){
			delta = System.currentTimeMillis() - lastTime;
		}
		lastTime = System.currentTimeMillis();
		delta = 0;
		actualCount--;
	}
}
