package me.kevint.flipIt.entity.component;

import java.awt.Point;
import java.awt.Rectangle;

import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.LayoutEntity;
import me.kevint.flipIt.entity.PlayerEntity;

/**
 * Adds <b>linear</b> physical properties to the entity.
 * 
 * Parabolic physical properties will come later
 *
 * @author kevint <br>
 *			Created Feb 13, 2015 <br> <br>
 * Copyright 2015
 */
public class PhysicsComponent extends Component implements SurfaceUpdateListener {
	
	private double verticalVelocity = 0;
	private double horizontalVelocity = 0;
	private int mass;
	private final float GRAVITY = 9.8f;
	
	private float horizontalInertia = 0;
	private float horizontalMaxInertia;
	private float friction = 0.1f;
	
	private boolean motionStopped = true;
	
	public boolean allowMotion = false;
	
	private boolean inAir = false;

	public PhysicsComponent(int mass) {
		this.mass = mass;
		this.horizontalMaxInertia = mass + (mass * 0.00025f) - 1f;
	}
 	
	@Override
	public void initialize() {
		this.getParentEntity().registerSurfaceUpdateListener(this);
		FlipIt.getPhysicsControl().attachComponent(this);
	}
	
	public void move(float speed, double angleInRadians) {
		this.horizontalVelocity = (speed * (Math.cos(angleInRadians)));
		this.verticalVelocity = (speed * (Math.sin(angleInRadians)));
		
	}
	
	public void stop() {
		move(0,0);
	}
	
	//slide
	public void increaseHorizontalIntertia() {
		if(this.horizontalInertia <= this.horizontalMaxInertia)
			this.horizontalInertia++;
	}
	public void resetHorizontalInertia() {
		this.horizontalInertia = 0;
	}
	
	
	public void setMotionStopped(boolean bool) {
		this.motionStopped = bool;
	}
	
	//jump
	public void jump() {
		if(!inAir ) {
			move(10, Math.toRadians(90));
			inAir = true;
		}
	}
	
	public void collision(PhysicsComponent who, Rectangle intersection) {
		if(this.getParentEntity() instanceof PlayerEntity )  { //TODO fix nastiness
			if(who.getParentEntity() instanceof LayoutEntity) {
				//this.getParentEntity().move(new Point((this.getParentEntity().getPosition().x - ((int) intersection.x)), 
				//		  					(this.getParentEntity().getPosition().y - ((int) intersection.y))));
				stop();
			}
		}
	}
	
	@Override
	public void onUpdate() { 
		//if((int) horizontalVelocity != 0 || (int) verticalVelocity != 0)
			this.getParentEntity().move(new Point((this.getParentEntity().getPosition().x + ((int) horizontalVelocity)), 
												  (this.getParentEntity().getPosition().y + ((int) verticalVelocity))));
		if(inAir) {
			this.verticalVelocity = this.verticalVelocity - mass;
			
		}
		
		//slide
		if(motionStopped) {
			if(this.horizontalInertia != 0)
				if(this.getParentEntity().getComponentByType(GraphicsComponent.class) != null) {
					if(this.getParentEntity().getComponentByType(GraphicsComponent.class).getDirection()) {
						this.getParentEntity().bump(new Point((int) horizontalInertia, 0));
					} else {
						this.getParentEntity().bump(new Point( - (int) horizontalInertia, 0));
					}
				}
		
			if(this.horizontalInertia > 0) {
				this.horizontalInertia = this.horizontalInertia - friction;
			}
			if(this.horizontalInertia <= 0) {
				this.resetHorizontalInertia();
			}
		}
	}
	
	public int getMass() {
		return mass;
	}
}
