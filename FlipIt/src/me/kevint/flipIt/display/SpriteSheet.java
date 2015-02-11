package me.kevint.flipIt.display;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

@Deprecated
public class SpriteSheet {
	
	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public SpriteSheet(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if ( image == null ) {
			return;
		}
		
		this.path = path;
		this.width = image.getWidth();
		this.height= image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, pixels, 0, width); //returns pixels into pixel int[]
		
		for(int i = 0; i<pixels.length ; i++) {
			pixels[i] = (pixels[i] & 0xff); //clear alpha channels
		}
		
		
	}

}
