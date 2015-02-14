package me.kevint.flipIt.entity.component;

import me.kevint.flipIt.display.SurfaceUpdateListener;

/**
 * Adds <b>linear</b> physical properties to the entity.
 * 
 * Parabolic physical properties will come later
 *
 * @author kevint <br>
 *			Created Feb 13, 2015 <br> <br>
 * Copyright 2015
 */
public class PhysicsComponent extends Component implements SurfaceUpdateListener{
	
	private float upwardVelocity;
	private int mass;
	
	private float horizontalInertia = 0;
	private float horizontalMaxInertia;
	private float friction = 0.1f;
	
	private boolean motionStopped = true;

	public PhysicsComponent(int mass) {
		this.mass = mass;
		this.horizontalMaxInertia = mass + 0.75f;
	}
 	
	@Override
	public void initialize() {
		this.getParentEntity().registerSurfaceUpdateListener(this);
	}

	public void setVelocity(int newVelocity) {
		this.upwardVelocity = newVelocity;
	}
	
	public float getVelocity() {
		return this.upwardVelocity;
	}
	
	public void jump() {
		upwardVelocity = 10;
	}
	
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
	@Override
	public void onUpdate() {
		if(upwardVelocity != 0)
			this.getParentEntity().moveVert(upwardVelocity);
		if(upwardVelocity > 0) {
			upwardVelocity = upwardVelocity - mass;
		}
		if(motionStopped) {
			if(this.horizontalInertia != 0)
				if(this.getParentEntity().getComponentByType(GraphicsComponent.class) != null) {
					if(this.getParentEntity().getComponentByType(GraphicsComponent.class).getDirection()) {
						this.getParentEntity().slideHorz(horizontalInertia);
					} else {
						this.getParentEntity().slideHorz(-horizontalInertia);
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

}
