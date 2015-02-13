package me.kevint.flipIt.entity;

import me.kevint.flipIt.entity.component.Component;

public class LayoutEntity extends Entity{
	
	public static String[] TEST_LAYOUT = new String[]{"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
												      "w00000000000000000000000000000000000000000000w",
												      "w00000000000000000000000000000000000000000000w",
												      "w00000000000000000000000000000000000000000000w",
												      "w00000000000000000000000000000000000000000000w",
												      "w00000000000000000000000000000000000000000000w",
												      "w00000000000000000000000000000000000000000000w",
												      "w00000000000000000000000000000000000000000000w",
												      "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"};

	public LayoutEntity(int renderLayer, Component[] componentsToRegister) {
		super(renderLayer, componentsToRegister);
	}
	
	

}
