package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public static boolean play = true;
	
	public void render(Graphics g) {
		Font font = new Font("arial", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("PONG in Java", 35, Game.HEIGTH/2);
		
		Font font2 = new Font("arial", Font.BOLD, 10);
		g.setFont(font2);
		g.setColor(Color.yellow);
		g.drawString("Press ENTER to start the game", 15, Game.HEIGTH/2 + 25);
	}
}
