package me.kevint.flipIt.entity;

import java.awt.Point;
import java.awt.event.KeyEvent;

import me.kevint.flipIt.display.Screen;
import me.kevint.flipIt.entity.component.Component;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.entity.component.InputComponent;
import me.kevint.flipIt.entity.component.PhysicsComponent;
import me.kevint.flipIt.input.KeyMapping;
import me.kevint.flipIt.layout.LevelBuilder;
import me.kevint.flipIt.math.Rect;

public class LayoutEntity extends Entity{
	
	private Screen screen;
	
	public LayoutEntity(LevelBuilder layoutManager, Point pos, String imageName) {
		super(new Component[]{
				new GraphicsComponent(imageName),
				new PhysicsComponent(0),
				new InputComponent(new KeyMapping[]{new KeyMapping(KeyEvent.VK_A, "moveLeft"),
													new KeyMapping(KeyEvent.VK_D, "moveRight"),
													new KeyMapping(KeyEvent.VK_W, "moveUp"),
													new KeyMapping(KeyEvent.VK_S, "moveDown")
				})
		}, pos);
		this.screen = layoutManager.getScreen();
		size = new Rect(0,0,32,32);
		collisionBounds = size;
	}
	
	public void moveLeft() {
		this.screen.setCameraPos(new Point(this.screen.getCameraPos().x-1,this.screen.getCameraPos().y));
	}
	public void moveRight() {
		this.screen.setCameraPos(new Point(this.screen.getCameraPos().x+1,this.screen.getCameraPos().y));
	}
	public void moveUp() {
		this.screen.setCameraPos(new Point(this.screen.getCameraPos().x,this.screen.getCameraPos().y-1));
	}
	public void moveDown() {
		this.screen.setCameraPos(new Point(this.screen.getCameraPos().x,this.screen.getCameraPos().y+1));
	}

}
