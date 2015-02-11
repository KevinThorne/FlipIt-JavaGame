package me.kevint.flipIt.display;

import java.awt.Graphics2D;
import java.util.Set;
import java.util.TreeMap;

public class Screen {
	
	private TreeMap<Integer, Surface> screen;
	
	public Screen() {
		screen = new TreeMap<Integer, Surface>();
	}
	
	public TreeMap<Integer, Surface> getSurfaces() {
		return screen;
	}
	
	public boolean blit(Surface surface, int renderLayer) {
		screen.put(renderLayer, surface);
		return true;
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
