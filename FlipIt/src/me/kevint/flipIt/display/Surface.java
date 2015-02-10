package me.kevint.flipIt.display;

import me.kevint.flipIt.math.Vector2i;

public class Surface {
	
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
	
	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
	public int[] colors= new int[MAP_WIDTH * MAP_WIDTH * 4];

	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	public Vector2i tileSize;
	
	public SpriteSheet sheet;
	
	public Surface(int width, int height, Vector2i sprite_size, SpriteSheet sheet) {
		this.width = width;
		this.height= height;
		this.tileSize = sprite_size;
		this.sheet = sheet;
		
		/*for(int i=0 ; i < MAP_WIDTH * MAP_WIDTH; i++) {
			colors[i*4+0] = 0xff00ff;
			colors[i*4+1] = 0x00ffff;
			colors[i*4+2] = 0xffff00;
			colors[i*4+3] = 0xffffff;
		}*/
	}
	
	public void render(int[] pixels, int offset, int row) {
		for(int yTile = yOffset; yTile <= (yOffset + height); yTile++) {
			int yMin = yTile * tileSize.getY() - yOffset;
			int yMax = yMin + tileSize.getY();
			if(yMin < 0) yMin = 0;
			if(yMax > height) yMax = height;
			
			for(int xTile = xOffset; xTile <= (xOffset + width); xTile++) {
				int xMin = xTile * tileSize.getY() - xOffset;
				int xMax = xMin + tileSize.getY();
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
		}
	}
}
