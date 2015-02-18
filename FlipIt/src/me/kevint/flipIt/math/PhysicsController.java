package me.kevint.flipIt.math;

import java.awt.Rectangle;
import java.util.ArrayList;

import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.display.Screen;
import me.kevint.flipIt.display.SurfaceUpdateListener;
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

			for (PhysicsComponent a : returnObjects) {
				for(int i=returnObjects.indexOf(a)+1; i<returnObjects.size(); i++) {
					PhysicsComponent b = returnObjects.get(i);
					Rectangle intersection = new Rectangle();
					if(MathUtil.intersection(a.getParentEntity().getCollisionBounds(), b.getParentEntity().getCollisionBounds(), intersection)
							|| MathUtil.intersection(b.getParentEntity().getCollisionBounds(), a.getParentEntity().getCollisionBounds(), intersection)) {
						//if((!(a.getParentEntity() instanceof LayoutEntity) && !(b.getParentEntity() instanceof LayoutEntity)))
							//System.out.println("Collision! With: " + a.getParentEntity().getClass().getName() + " and " + b.getParentEntity().getClass().getName());
						a.collision(b, intersection);
						b.collision(a, intersection);
					}
				}
			}
		}

	}

}
