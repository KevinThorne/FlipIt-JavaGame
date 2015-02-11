package me.kevint.flipIt.entity.component;

import java.awt.image.BufferedImage;

import me.kevint.flipIt.display.SurfaceUpdateListener;

public class GraphicsComponent extends Component implements SurfaceUpdateListener{
	
	BufferedImage image;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
