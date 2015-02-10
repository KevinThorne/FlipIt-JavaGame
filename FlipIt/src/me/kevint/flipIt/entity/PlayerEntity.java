package me.kevint.flipIt.entity;

import me.kevint.flipIt.entity.component.Component;

public class PlayerEntity extends Entity{

	public PlayerEntity(int renderLayer, Component[] componentsToRegister) {
		super(renderLayer, componentsToRegister);
	}

}
