package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean left, right;
	
	public int x, y;
	
	public int width = 40, heigth = 10;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(left) {
			x--;
		}
		else if(right) {
			x++;
		}
		
		if(x + width > Game.WIDTH) {
			x = Game.WIDTH -width;
		}
		
		if(x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, heigth);
	}
}
