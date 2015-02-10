package me.kevint.flipIt.entity;

import me.kevint.flipIt.entity.component.Component;

public class LayoutEntity extends Entity{

	public LayoutEntity(int renderLayer, Component[] componentsToRegister) {
		super(renderLayer, componentsToRegister);
	}

}
