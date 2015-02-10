package me.kevint.flipIt;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import me.kevint.flipIt.display.SpriteSheet;
import me.kevint.flipIt.display.Surface;
import me.kevint.flipIt.math.Vector2i;

/**
 * Insert type description here
 *
 * @author kevint <br>
 *			Created Feb 9, 2015 <br> <br>
 * Copyright 2015
 */
public class FlipIt extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -264467231219868010L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final int FPS = 60;
	public static final String NAME = "FlipIt2d";
	
	private JFrame frame;

	private boolean running = false;
	private int tickCounter = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private SpriteSheet spriteSheet = new SpriteSheet("/player_sheet.png");
	
	private Surface surface;
	
	public FlipIt() {
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void init() {
		surface = new Surface(WIDTH, HEIGHT, new Vector2i(8,8), new SpriteSheet("/player_sheet.png"));
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	public synchronized void stop() {
		running = false;
	}
	

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			boolean forceRender = true;
			
			while (delta >= 1) {
				ticks++;
				update();
				delta -= 1;
				forceRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(forceRender) {
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				//System.out.println(frames+ ", " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void update() { // game tick
		
		tickCounter++;
		surface.xOffset++;
		
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		surface.render(pixels, 0, WIDTH);
		
		Graphics g = bs.getDrawGraphics();
		
		//g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new FlipIt().start();
	}
	
	
}
