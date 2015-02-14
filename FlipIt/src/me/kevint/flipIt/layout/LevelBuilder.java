package me.kevint.flipIt.layout;

import java.awt.Point;
import java.util.ArrayList;

import me.kevint.flipIt.display.Screen;
import me.kevint.flipIt.display.Surface;
import me.kevint.flipIt.entity.Entity;
import me.kevint.flipIt.entity.LayoutEntity;

public class LevelBuilder {

	public static String[] TEST_LAYOUT = new String[]{
		"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w                                            w",
		"w p                                          w",
		"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"};

	private String currentLevel;
	private Screen screen;
	private Surface surface;
	
	private ArrayList<Entity> objects = new ArrayList<Entity>();

	private int scale = 32;

	public LevelBuilder(Screen screenToBlit, Surface surfaceToApply) {
		this.screen = screenToBlit;
		this.surface = surfaceToApply;
	}
	public Screen getScreen() {
		return screen;
	}
	
	public void loadLevel(String levelName) {
		if(levelName == null) {
			levelName = "TEST_LAYOUT";
		}
		this.currentLevel = levelName;
		String[] levelMap = null;
		if(levelName.equalsIgnoreCase("TEST_LAYOUT")) {
			levelMap = TEST_LAYOUT;
		}
		System.out.println("Making map (" + levelMap[0].length() + ", " + levelMap.length + ")");
		for(int y = 0; y < levelMap.length ; y++) {
			for(int x = 0; x < levelMap[0].length() ; x++) {
				placeObjectAt(Character.toString(levelMap[y].charAt(x)), new Point(x,y));
			}
		}
	}

	private void placeObjectAt(String w, Point pos) {
		pos = new Point(pos.x*scale, pos.y*scale);
		if (w.equals("w")) {
			LayoutEntity ob = new LayoutEntity(this, pos, "wall.png");
			objects.add(ob);
			surface.blit(ob);
		} else {;}

	}


}
