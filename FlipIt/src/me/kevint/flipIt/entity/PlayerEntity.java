package me.kevint.flipIt.entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import me.kevint.flipIt.entity.component.Component;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.entity.component.GraphicsComponent.AnimationType;
import me.kevint.flipIt.entity.component.InputComponent;
import me.kevint.flipIt.entity.component.PhysicsComponent;
import me.kevint.flipIt.input.KeyMapping;

public class PlayerEntity extends Entity{
	
	public boolean noClip = true;

	public PlayerEntity(Point pos) {
		super(new Component[] {
				new GraphicsComponent("player_sheet.png", true, new Rectangle(0,0,24,23)),
				new InputComponent(new KeyMapping[]{new KeyMapping(KeyEvent.VK_LEFT, "moveLeft", "stopMotion"),
													new KeyMapping(KeyEvent.VK_RIGHT, "moveRight", "stopMotion"),
													new KeyMapping(KeyEvent.VK_UP, "moveUp", "stopMotion"),
													new KeyMapping(KeyEvent.VK_DOWN, "moveDown", "stopMotion"),
													new KeyMapping(KeyEvent.VK_SPACE, "jump")
				}),
				new PhysicsComponent(3)
		}, pos);
		size = new Rectangle(0,0,24,23);
		bounds = new Rectangle(pos.x, pos.y, size.width, size.height);
	}
	
	public Rectangle[] getAnimation(AnimationType type) { // all tile coord relative
		switch(type) {
		case STILL:
			return new Rectangle[]{new Rectangle(0,1,0,0)};
		case MOVE:
			return new Rectangle[]{new Rectangle(8,0,0,0),
							new Rectangle(0,0,0,0),
							new Rectangle(1,0,0,0),
							new Rectangle(2,0,0,0),
							new Rectangle(3,0,0,0),
							new Rectangle(4,0,0,0),
							new Rectangle(5,0,0,0),
							new Rectangle(6,0,0,0),
							new Rectangle(7,0,0,0),
							};
		case JUMP:
			return new Rectangle[]{
					new Rectangle(1,1,0,0),
					new Rectangle(2,1,0,0),
					new Rectangle(3,1,0,0),
					new Rectangle(4,1,0,0),
					new Rectangle(5,1,0,0),
					new Rectangle(6,1,0,0),
					new Rectangle(7,1,0,0),
					new Rectangle(8,1,0,0),
					};
		}
		return null;
	}
	
	public void moveLeft() {
		this.getComponentByType(GraphicsComponent.class).setDirection(false);
		this.getComponentByType(PhysicsComponent.class).move(5, Math.toRadians(-180));
	}
	public void moveRight() {
		this.getComponentByType(GraphicsComponent.class).setDirection(true);
		this.getComponentByType(PhysicsComponent.class).move(5, Math.toRadians(0));
	}
	public void moveUp() {
		if(noClip) {
			this.getComponentByType(GraphicsComponent.class).setDirection(false);
			this.getComponentByType(PhysicsComponent.class).move(5, Math.toRadians(270));
		}
	}
	public void moveDown() {
		if(noClip) {
			this.getComponentByType(GraphicsComponent.class).setDirection(true);
			this.getComponentByType(PhysicsComponent.class).move(5, Math.toRadians(90));
		}
	}
	
	@Override
	public void move(Point deltaPos) {
		if(deltaPos.equals(getPosition())) {
			stopMotion();
			return;
		}
		if(deltaPos.x > getPosition().x) {
			this.getComponentByType(GraphicsComponent.class).setDirection(true);
		} else {
			this.getComponentByType(GraphicsComponent.class).setDirection(false);
		}
		setPosition(deltaPos);
		this.getComponentByType(GraphicsComponent.class).setAnimation(AnimationType.MOVE);
		
		//slide
		if(!noClip) {
			this.getComponentByType(PhysicsComponent.class).increaseHorizontalIntertia();
			this.getComponentByType(PhysicsComponent.class).setMotionStopped(false);
		}
	}
	
	public void stopMotion() {
		this.getComponentByType(GraphicsComponent.class).setAnimation(AnimationType.STILL);
		this.getComponentByType(PhysicsComponent.class).setMotionStopped(true);
		this.getComponentByType(PhysicsComponent.class).stop();
	}
	
	public void jump() {
		this.getComponentByType(PhysicsComponent.class).jump();
	}

}
