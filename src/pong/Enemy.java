package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x,y;
	public int width = 40, heigth = 10;
	public static double enemyLevel = 0.05;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		x += (Game.ball.x - x - 6) * enemyLevel;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, heigth);
	}
}
