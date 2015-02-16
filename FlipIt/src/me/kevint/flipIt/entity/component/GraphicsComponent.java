package me.kevint.flipIt.entity.component;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.kevint.flipIt.Constants;
import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.PlayerEntity;

/**
 * Graphics Component used in Screens to render
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
	private boolean lookDir; // if its looking right
	
	private BufferedImage spriteSheet;
	private Rectangle spriteSize;
	
	private boolean toAnimate = false;
	
	private boolean animate = true;
	private int animCounter;
	
	private AnimationType currentAnim = AnimationType.STILL;
	private int currentFrame = 0;
	
	public GraphicsComponent(String imageName) {
		try {
			image = ImageIO.read(GraphicsComponent.class.getResource(Constants.IMAGE_PATH+imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GraphicsComponent(String imageName, boolean animated, Rectangle spriteSize) {
		this.toAnimate = animated;
		this.spriteSize = spriteSize;
		try {
			spriteSheet = ImageIO.read(GraphicsComponent.class.getResource(Constants.IMAGE_PATH+imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!animated) {
			image = spriteSheet.getSubimage(spriteSize.x, spriteSize.y, spriteSize.width, spriteSize.height);
		}
	}

	@Override
	public void initialize() {
		this.getParentEntity().registerSurfaceUpdateListener(this);
	}
	
	public void setAnimation(AnimationType type) {
		this.currentAnim = type;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage img) {
		image = img;
	}
	
	public void setDirection(boolean lookingRight) {
		this.lookDir = lookingRight;
	}
	/**
	 * True if facing right (east side of screen)
	 * 
	 * @return
	 */
	public boolean getDirection() {
		return this.lookDir;
	}
	
	public BufferedImage getSubImage(int xTile, int yTile) {
		return this.spriteSheet.getSubimage(
				xTile * this.spriteSize.width,
				yTile * this.spriteSize.height,
				this.spriteSize.width, 
				this.spriteSize.height);
	}
	
	public BufferedImage flipImage(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
	}

	@Override
	public void onUpdate() {
		animCounter++;
		if(animCounter >= 100) {
			animCounter = 0;
		}
		if(animCounter % 5 != 0) {
			animate = false;
		} else {
			animate = true;
		}
		if(animate) {
			if(this.getParentEntity() instanceof PlayerEntity) { //TODO fix nastiness here
				PlayerEntity parent = (PlayerEntity) this.getParentEntity();
				currentFrame++;
				if(currentFrame >= parent.getAnimation(currentAnim).length-1)
					currentFrame = 0;
				if(lookDir) {
					setImage(getSubImage(
							parent.getAnimation(currentAnim)[currentFrame].x,
							parent.getAnimation(currentAnim)[currentFrame].y));
				} else {
					setImage(flipImage(getSubImage(
							parent.getAnimation(currentAnim)[currentFrame].x,
							parent.getAnimation(currentAnim)[currentFrame].y)));
				}
			}
		}
		
	}

}
