package me.kevint.flipIt.display;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import me.kevint.flipIt.entity.Entity;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.math.QuadTree;

public class Screen  {
	
	private TreeMap<Integer, ArrayList<Entity>> screen;
	private QuadTree quad;
	
	private Point cameraPos;
	
	public Screen() {
		screen = new TreeMap<Integer, ArrayList<Entity>>();
		cameraPos = new Point(0,0);
	}
	
	public void initQuadTree(Rectangle size) {
		quad = new QuadTree(0, size);
	}
	
	public Point getCameraPos() {
		return cameraPos;
	}
	
	public QuadTree getQuadTree() {
		return quad;
	}
	
	public TreeMap<Integer, ArrayList<Entity>> getSurfaces() {
		return screen;
	}
	
	public boolean blit(final Entity entity, int renderLayer) {
		if(screen.containsKey(renderLayer)) {
			screen.get(renderLayer).add(entity);
			return true;
		}
		screen.put(renderLayer, new ArrayList<Entity>(){{add(entity);}});
		return true;
	}
	
	public void setCameraPos(Point newPos) {
		this.cameraPos = newPos;
	}
	
	public Graphics2D render(Graphics2D g2) {
		Set<Integer> keySet = screen.keySet();
        for(Integer i : keySet)
        {
        	ArrayList<Entity> entities = screen.get(i);
        	for(Entity entity : entities) {
				if(entity.hasComponentType(GraphicsComponent.class)) {
					g2.drawImage(entity.getComponentByType(GraphicsComponent.class).getImage(), entity.getPosition().x - getCameraPos().x,
							entity.getPosition().y - getCameraPos().y, null);
				} else {
					//TODO warn entity on surface has no graphics component
				}
        	}
        }
        
        return g2;
	}

}
