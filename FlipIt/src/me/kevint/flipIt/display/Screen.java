package me.kevint.flipIt.display;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Set;
import java.util.TreeMap;

public class Screen {
	
	private TreeMap<Integer, Surface> screen;
	
	private Point cameraPos;
	
	public Screen() {
		screen = new TreeMap<Integer, Surface>();
		cameraPos = new Point(0,0);
	}
	
	public Point getCameraPos() {
		return cameraPos;
	}
	
	public TreeMap<Integer, Surface> getSurfaces() {
		return screen;
	}
	
	public boolean blit(Surface surface, int renderLayer) {
		screen.put(renderLayer, surface);
		return true;
	}
	
	public void setCameraPos(Point newPos) {
		this.cameraPos = newPos;
	}
	
	public Graphics2D render(Graphics2D g2) {
		Set<Integer> keySet = screen.keySet();
        for(Integer i : keySet)
        {
        	Surface surface = screen.get(i);
            surface.render(g2);
        }
        
        return g2;
	}

}
