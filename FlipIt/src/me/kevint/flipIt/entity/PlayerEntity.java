package me.kevint.flipIt.entity;

import java.awt.Point;

import me.kevint.flipIt.entity.component.Component;
import me.kevint.flipIt.entity.component.GraphicsComponent;
import me.kevint.flipIt.entity.component.GraphicsComponent.AnimationType;
import me.kevint.flipIt.math.Rect;

public class PlayerEntity extends Entity{
	
	private Rect size;

	public PlayerEntity(int renderLayer, Point pos) {
		super(renderLayer, new Component[] {
				new GraphicsComponent("player_sheet.png", false, new Rect(0,0,19,23))
		});
		setPosition(pos);
		this.size = new Rect(0,0,20,22);
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

}
