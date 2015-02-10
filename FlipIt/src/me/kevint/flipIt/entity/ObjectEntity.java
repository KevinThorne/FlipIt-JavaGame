package me.kevint.flipIt.entity;

import me.kevint.flipIt.entity.component.Component;

public class ObjectEntity extends Entity{

	public ObjectEntity(int renderLayer, Component[] componentsToRegister) {
		super(renderLayer, componentsToRegister);
	}

}
