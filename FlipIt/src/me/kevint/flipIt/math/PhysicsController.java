package me.kevint.flipIt.math;

import java.util.ArrayList;

import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.component.PhysicsComponent;

public class PhysicsController implements SurfaceUpdateListener {
	
	private ArrayList<PhysicsComponent> attached = new ArrayList<PhysicsComponent>();
	
	public PhysicsController() {
		
	}
	
	public void attachComponent(PhysicsComponent c) {
		if(!attached.contains(c)) {
			attached.add(c);
		}
	}

	@Override
	public void onUpdate() {
		
		
	}

}
