package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x, y;
	public int width = 5, height = 5;

	public int leftAngle;
	public int rightAngle;
	public double dx, dy, speed = 1.55;
	
	public static boolean isFinished = false;
	public boolean isWin = false;

	public Ball(int x, int y) {
		
		switch (Game.difficulty) {
		case 2: {
			 Enemy.enemyLevel = 0.06;
			break;
		}
		case 3: {
			Enemy.enemyLevel = 0.08;
			break;
		}
		case 4: {
			Enemy.enemyLevel = 0.10;
			break;
		}
		case 5:{
			Enemy.enemyLevel = 0.12;
		}
		case 6:{
			Enemy.enemyLevel = 0.15;
		}
		default:
			break;	
		}
		
		this.x = x;
		this.y = y;

		int angle = new Random().nextInt(160 - 60) + 60;
		dx = Math.cos(Math.toRadians(angle)) * speed;
		dy = Math.sin(Math.toRadians(angle)) * speed;
	}

	public void tick() {
		y += dy * speed;
		x += dx * speed;
		if (x + width > Game.WIDTH)
			dx *= -1;
		else if (x + width < 0)
			dx *= -1;

		if (y + height > Game.HEIGTH) {			
			isFinished = true;
			Game.difficulty = 1;
			Enemy.enemyLevel = 0.05;
			new Game();
		}
		if (y + height < 0) {	
			isFinished = true;
			isWin = true;
			Game.difficulty++;
			new Game();
		}

		// Collision
		Rectangle boundsBall = new Rectangle((int) x, (int) y, width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.heigth);
		Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width,
				Game.enemy.heigth);

		if (boundsBall.intersects(boundsPlayer)) {
			Rectangle leftSide = new Rectangle(Game.player.x, Game.player.y, Game.player.width / 2, Game.player.heigth);
			dy *= -1;
			
			if (boundsBall.intersects(leftSide)) {
				leftAngle = new Random().nextInt(150 - 135) + 135;
				dx = Math.cos(Math.toRadians(leftAngle)) * speed;
			}
			else {		
				rightAngle = new Random().nextInt(45 - 30) + 30;
				dx = Math.cos(Math.toRadians(rightAngle)) * speed;
			}
		}
		if (boundsBall.intersects(boundsEnemy)) {
			Rectangle leftSideEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width / 2,
					Game.enemy.heigth);
			dy *= -1;
			if (boundsBall.intersects(leftSideEnemy)) {
				leftAngle = new Random().nextInt(150 - 135) + 135;
				dx = Math.cos(Math.toRadians(leftAngle)) * speed;
			}
			else {		
				rightAngle = new Random().nextInt(45 - 30) + 30;
				dx = Math.cos(Math.toRadians(rightAngle)) * speed;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	public void renderFinishGame(Graphics g) {
		Font font = new Font("arial", Font.BOLD, 10);
		g.setFont(font);
		g.setColor(Color.yellow);
		if(isWin)
			g.drawString("You win!!", 70, Game.HEIGTH/2);
		else
			g.drawString("You lose :(", 70, Game.HEIGTH/2);
	}
}
