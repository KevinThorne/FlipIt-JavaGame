package me.kevint.flipIt.display;

import java.awt.Graphics2D;
import java.util.ArrayList;

import me.kevint.flipIt.entity.Entity;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.math.Rect;

public class Surface {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	private Screen parent;
	
	public Surface(Screen parent) {
		this.parent = parent;
	}
	
	public void blit(Entity entity) {
		if(!entities.contains(entity)) {
			entities.add(entity);
		}
	}
	public void remove(Entity entity) {
		if(entities.contains(entity)) {
			entities.remove(entity);
		}
	}
	
	public Graphics2D render(Graphics2D screen) {
		for(Entity entity : entities) {
			if(entity.hasComponentType(GraphicsComponent.class)) {
				screen.drawImage(entity.getComponentByType(GraphicsComponent.class).getImage(), entity.getPosition().x - parent.getCameraPos().x,
						entity.getPosition().y - parent.getCameraPos().y, null);
			} else {
				//TODO warn entity on surface has no graphics component
			}
		}
		return screen;
	}
}
