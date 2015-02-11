package me.kevint.flipIt.entity.component;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.kevint.flipIt.Constants;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.PlayerEntity;
import me.kevint.flipIt.math.Rect;

/**
 * Graphics Component used in Surfaces to render
 *
 * @author kevint <br>
 *			Created Feb 10, 2015 <br> <br>
 * Copyright 2015
 */
public class GraphicsComponent extends Component implements SurfaceUpdateListener{
	
	/**
	 * Animation types to be defined in the entity classes
	 *
	 * @author kevint <br>
	 *			Created Feb 10, 2015 <br> <br>
	 * Copyright 2015
	 */
	public enum AnimationType {
		/**
		 *  Still Animation (breathing for player, basically the "go-to" animation cycle
		 */
		STILL,
		/**
		 *  Entity motion
		 */
		MOVE,
		/**
		 *  Entity motion: jump
		 */
		JUMP;
	}
	
	private BufferedImage image;
	
	private BufferedImage spriteSheet;
	private Rect spriteSize;
	
	private boolean toAnimate = false;
	
	private boolean animate = false;
	
	private AnimationType currentAnim;
	private int currentFrame = 1;
	
	public GraphicsComponent(String imageName) {
		try {
			image = ImageIO.read(GraphicsComponent.class.getResource(Constants.IMAGE_PATH+imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GraphicsComponent(String imageName, boolean animated, Rect spriteSize) {
		this.toAnimate = animated;
		this.spriteSize = spriteSize;
		try {
			spriteSheet = ImageIO.read(GraphicsComponent.class.getResource(Constants.IMAGE_PATH+imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!animated) {
			image = spriteSheet.getSubimage(spriteSize.x, spriteSize.y, spriteSize.w, spriteSize.h);
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public void startAnim() {
		this.animate = true;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage img) {
		image = img;
	}
	
	public BufferedImage getSubImage(int xTile, int yTile) {
		return this.spriteSheet.getSubimage(
				xTile * this.spriteSize.w,
				yTile * this.spriteSize.h,
				this.spriteSize.w, 
				this.spriteSize.h);
	}

	@Override
	public void onUpdate() {
		if(animate) {
			if(this.getParentEntity() instanceof PlayerEntity) { //TODO fix nastiness here
				PlayerEntity parent = (PlayerEntity) this.getParentEntity();
				if(currentFrame > parent.getAnimation(currentAnim).length-1)
					currentFrame = 0;
				currentFrame++;
				switch(currentAnim) {
				case STILL:
					setImage(getSubImage(
							parent.getAnimation(currentAnim)[currentFrame].x,
							parent.getAnimation(currentAnim)[currentFrame].y));
				}
			}
		}
		
	}

}
