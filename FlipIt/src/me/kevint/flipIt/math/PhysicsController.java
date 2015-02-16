package me.kevint.flipIt.math;

import java.util.ArrayList;

import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.display.Screen;
import me.kevint.flipIt.display.SurfaceUpdateListener;
import me.kevint.flipIt.entity.LayoutEntity;
import me.kevint.flipIt.entity.PlayerEntity;
import me.kevint.flipIt.entity.component.PhysicsComponent;

public class PhysicsController implements SurfaceUpdateListener {

	private ArrayList<PhysicsComponent> attached = new ArrayList<PhysicsComponent>();

	private Screen screen;

	public PhysicsController(Screen screen) {
		FlipIt.surfaceUpdateListeners.add(this);
		this.screen = screen;
	}

	public void attachComponent(PhysicsComponent c) {
		if(!attached.contains(c)) {
			attached.add(c);
			//System.out.println("Added " + c.getParentEntity().getClass().getName());
		}
	}

	@Override
	public void onUpdate() {
		ArrayList<PhysicsComponent> returnObjects = new ArrayList<PhysicsComponent>();
		//refresh quadtree
		this.screen.getQuadTree().clear();
		for(PhysicsComponent comp : attached) {
			this.screen.getQuadTree().insert(comp);
			
			//collision detection
			returnObjects.clear();
			this.screen.getQuadTree().retrieve(returnObjects, comp);

			for (int x = 0; x < returnObjects.size(); x++) {
				PhysicsComponent a = returnObjects.get(x);
				PhysicsComponent b;
				try {
					b = returnObjects.get(x+1);
				} catch (IndexOutOfBoundsException e) {
					b = returnObjects.get(0);
				}
				//System.out.println("Length: " + returnObjects.size() + " Iterating at: " + x + ", " + (x+1));
				if(a.getParentEntity().getCollisionBounds().intersects(b.getParentEntity().getCollisionBounds()) || 
						b.getParentEntity().getCollisionBounds().intersects(a.getParentEntity().getCollisionBounds())) {
					if((!(a.getParentEntity() instanceof LayoutEntity) && !(b.getParentEntity() instanceof LayoutEntity)))
						System.out.println("Collision! With: " + a.getParentEntity().getClass().getName() + " and " + b.getParentEntity().getClass().getName());
					if(a.getMass() > b.getMass()) {
						b.move(0, 0);
						continue;
					} else {
						a.move(0, 0);
						continue;
					}
				}
			}
		}

	}

}
