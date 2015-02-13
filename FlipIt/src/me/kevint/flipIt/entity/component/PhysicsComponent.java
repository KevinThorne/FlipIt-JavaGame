package me.kevint.flipIt.entity.component;

import me.kevint.flipIt.display.SurfaceUpdateListener;

public class PhysicsComponent extends Component implements SurfaceUpdateListener{
	
	private float upwardVelocity;
	private int mass;
	
	private float horizontalVelocity = 0;
	private float horizontalMaxVelocity = 1.75f;
	
	private boolean motionStopped = true;

	public PhysicsComponent(int mass) {
		this.mass = mass;
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
	
	public void increaseAngularVelocity() {
		if(this.horizontalVelocity <= this.horizontalMaxVelocity)
			this.horizontalVelocity++;
	}
	public void resetAngularVelocity() {
		this.horizontalVelocity = 0;
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
			if(this.horizontalVelocity != 0)
				if(this.getParentEntity().getComponentByType(GraphicsComponent.class) != null) {
					if(this.getParentEntity().getComponentByType(GraphicsComponent.class).getDirection()) {
						System.out.println("Sliding - " + this.horizontalVelocity);
						this.getParentEntity().slideHorz(horizontalVelocity);
					} else {
						System.out.println("Sliding - " + this.horizontalVelocity);
						this.getParentEntity().slideHorz(-horizontalVelocity);
					}
				}
		
			if(this.horizontalVelocity > 0) {
				this.horizontalVelocity = this.horizontalVelocity - 0.1f;
			}
			if(this.horizontalVelocity <= 0) {
				this.resetAngularVelocity();
			}
		}
	}

}
