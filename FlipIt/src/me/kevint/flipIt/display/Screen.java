package me.kevint.flipIt.display;

import java.util.TreeMap;

public class Screen {
	
	private TreeMap<Integer, Surface> screen = new TreeMap<Integer, Surface>();
	
	public Screen() {
		
	}
	
	public TreeMap<Integer, Surface> getSurfaces() {
		return screen;
	}
	
	public boolean blit(Surface surface, int renderLayer) {
		screen.put(renderLayer, surface);
		return true;
	}
	

}
