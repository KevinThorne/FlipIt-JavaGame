package me.kevint.flipIt.display;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import me.kevint.flipIt.entity.Entity;
import me.kevint.flipIt.entity.component.GraphicsComponent;

public class Surface {
	
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
	
	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
	public int[] colors= new int[MAP_WIDTH * MAP_WIDTH * 4];

	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	public Rectangle tileSize;
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Surface(int width, int height) {
		this.width = width;
		this.height= height;
		
		/*for(int i=0 ; i < MAP_WIDTH * MAP_WIDTH; i++) {
			colors[i*4+0] = 0xff00ff;
			colors[i*4+1] = 0x00ffff;
			colors[i*4+2] = 0xffff00;
			colors[i*4+3] = 0xffffff;
		}*/
	}
	
	public void render(Graphics2D screen) {
		for(Entity entity : entities) {
			if(entity.hasComponentType(GraphicsComponent.class)) {
				screen.drawImage(entity.getComponentByType(GraphicsComponent.class).getImage(), entity.getX(), entity.getY(), null);
			} else {
				//TODO warn entity on surface has no graphics component
			}
		}
		/*for(int yTile = yOffset; yTile <= (yOffset + height); yTile++) {
			int yMin = yTile * tileSize.getY() - yOffset;
			int yMax = yMin + tileSize.getY();
			if(yMin < 0) yMin = 0;
			if(yMax > height) yMax = height;
			
			for(int xTile = xOffset; xTile <= (xOffset + width); xTile++) {
				int xMin = xTile * tileSize.getX() - xOffset;
				int xMax = xMin + tileSize.getX();
				if(xMin < 0) xMin = 0;
				if(xMax > height) xMax = width;
				
				int tileIndex = (xTile & (MAP_WIDTH_MASK)) + (yTile & (MAP_WIDTH_MASK)) * MAP_WIDTH;
				
				for(int y = yMin ; y < yMax; y++) {
					int sheetPixel = ((y + yOffset) & tileSize.getY()) * sheet.width 
							+ ((xMin + xOffset) & tileSize.getX());
					int tilePixel = offset + xMin + y * row;
					
					for(int x = xMin ; x < xMax; x++) { 
						int color = tileIndex * tileSize.getX()/2 + sheet.pixels[sheetPixel++];
						pixels[tilePixel++] = colors[color];
					}
				}
			}
		}*/
	}
}
