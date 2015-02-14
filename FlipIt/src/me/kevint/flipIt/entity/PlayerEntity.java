package me.kevint.flipIt.entity;

import java.awt.Point;
import java.awt.event.KeyEvent;

import me.kevint.flipIt.entity.component.Component;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.entity.component.GraphicsComponent.AnimationType;
import me.kevint.flipIt.entity.component.PhysicsComponent;
import me.kevint.flipIt.entity.component.InputComponent;
import me.kevint.flipIt.input.KeyMapping;
import me.kevint.flipIt.math.Rect;

public class PlayerEntity extends Entity{
	

	public PlayerEntity(Point pos) {
		super(new Component[] {
				new GraphicsComponent("player_sheet.png", true, new Rect(0,0,24,23)),
				new InputComponent(new KeyMapping[]{new KeyMapping(KeyEvent.VK_LEFT, "moveLeft", "stopMotion"),
													new KeyMapping(KeyEvent.VK_RIGHT, "moveRight", "stopMotion"),
													new KeyMapping(KeyEvent.VK_SPACE, "jump")
				}),
				new PhysicsComponent(3)
		}, pos);
		size = new Rect(0,0,24,23);
		collisionBounds = size;
	}
	
	public Rect[] getAnimation(AnimationType type) { // all tile coord relative
		switch(type) {
		case STILL:
			return new Rect[]{new Rect(0,1,0,0)};
		case MOVE:
			return new Rect[]{new Rect(8,0,0,0),
							new Rect(0,0,0,0),
							new Rect(1,0,0,0),
							new Rect(2,0,0,0),
							new Rect(3,0,0,0),
							new Rect(4,0,0,0),
							new Rect(5,0,0,0),
							new Rect(6,0,0,0),
							new Rect(7,0,0,0),
							};
		case JUMP:
			return new Rect[]{
					new Rect(1,1,0,0),
					new Rect(2,1,0,0),
					new Rect(3,1,0,0),
					new Rect(4,1,0,0),
					new Rect(5,1,0,0),
					new Rect(6,1,0,0),
					new Rect(7,1,0,0),
					new Rect(8,1,0,0),
					};
		}
		return null;
	}
	
	public void moveLeft() {
		this.getComponentByType(PhysicsComponent.class).move(10, Math.toRadians(-180));
	}
	public void moveRight() {
		this.getComponentByType(PhysicsComponent.class).move(10, Math.toRadians(0));
	}
	
	@Override
	public void move(Point deltaPos) {
		if(deltaPos == getPosition())
			return;
		setPosition(deltaPos);
		this.getComponentByType(GraphicsComponent.class).setAnimation(AnimationType.MOVE);
		this.getComponentByType(GraphicsComponent.class).setDirection(false);
		
		//slide
		this.getComponentByType(PhysicsComponent.class).increaseHorizontalIntertia();
		this.getComponentByType(PhysicsComponent.class).setMotionStopped(false);
	}
	
	public void stopMotion() {
		this.getComponentByType(PhysicsComponent.class).move(0, 0);
		this.getComponentByType(GraphicsComponent.class).setAnimation(AnimationType.STILL);
		this.getComponentByType(PhysicsComponent.class).setMotionStopped(true);
	}
	
	public void jump() {
		this.getComponentByType(PhysicsComponent.class).jump();
	}

}
