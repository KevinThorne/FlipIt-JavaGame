package me.kevint.flipIt;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import me.kevint.flipIt.display.Screen;
import me.kevint.flipIt.display.Surface;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.PlayerEntity;
import me.kevint.flipIt.layout.LevelBuilder;

/**
 * FlipIt2D Game Object Class
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
	public static final String NAME = "FlipIt2D";
	
	public static ArrayList<SurfaceUpdateListener> updateListeners = new ArrayList<SurfaceUpdateListener>();
	
	private static JFrame frame;

	private boolean running = false;
	private int tickCounter = 0;
	
	private Screen screen;
	
	
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
	
	public static JFrame getJFrame() {
		return frame;
	}
	
	public void init() {
		screen = new Screen();
		Surface surface = new Surface(screen);
		LevelBuilder builder = new LevelBuilder(screen, surface);
		builder.loadLevel(null);
		PlayerEntity player = new PlayerEntity(new Point(150,150));
		surface.blit(player);
		screen.blit(surface, 0);
		
		//TODO load level
	}
	
	public synchronized void start() {
		running = true;
		Thread th = new Thread(this);
		th.setDaemon(true);
		th.start();
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
				render(screen);
			}
			
			if(System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println("Ticks: "+ticks+ ", Frames: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void update() { // game tick
		
		tickCounter++;
		
		for(SurfaceUpdateListener listener : updateListeners) {
			listener.onUpdate();
		}
		
		
		
	}
	
	public void render(Screen screen) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		screen.render(g2);
		
		//g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g2.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new FlipIt().start();
	}
	
	
}
