package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	public static int WIDTH = 180;
	public static int HEIGTH = 160;
	public static int SCALE = 3;

	public BufferedImage layer = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_BGR);

	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public Menu menu;
	public Countdown countdown;
	public static int difficulty = 1;

	private enum STATE {
		MENU, GAME
	}

	private STATE state = STATE.MENU;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));
		this.addKeyListener(this);
		player = new Player(70, HEIGTH - 10);
		enemy = new Enemy(150, 0);
		ball = new Ball(100, HEIGTH / 2 - 1);
		menu = new Menu();
		countdown = new Countdown();
	}

	public static void main(String[] args) {
		Game game = new Game();
		frame(game);

		new Thread(game).start();
	}

	public void tick() {
		if (state == state.GAME) {
			player.tick();
			enemy.tick();
			ball.tick();
		}

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGTH);

		if (state == state.GAME) {
			player.render(g);
			enemy.render(g);
			ball.render(g);
			if(countdown.actualCount >= 0) {
				countdown.render(g);
			}
		}else {
			menu.render(g);
		}
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGTH * SCALE, null);
		bs.show();
	}

	@Override
	public void run() {
		while (true) {
			tick();
			render();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void frame(Game game) {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (state == STATE.GAME) {
			if (e.getKeyChar() == 'a') {
				player.left = true;
			}
			if (e.getKeyChar() == 'd') {
				player.right = true;
			}
		}else {
			if(e.getKeyChar() ==  '\n') {
				state = STATE.GAME;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (state == STATE.GAME) {
			if (e.getKeyChar() == 'a') {
				player.left = false;
			}
			if (e.getKeyChar() == 'd') {
				player.right = false;
			}
		}
	}

}
