package me.kevint.flipIt.layout;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import me.kevint.flipIt.display.Screen;
import me.kevint.flipIt.entity.Entity;
import me.kevint.flipIt.entity.LayoutEntity;

public class LevelBuilder {

	public static String[] TEST_LAYOUT = new String[]{
		"wwwwwwwwwwwwwww",
		"w             w",
		"w p           w",
		"wwwwwwwwww    w",
		"w             w",
		"wwwwwwwwwwwwwww"};

	private String currentLevel;
	private Screen screen;
	
	private ArrayList<Entity> objects = new ArrayList<Entity>();
	
	private Point spawnPoint;

	private int scale = 32;

	public LevelBuilder(Screen screenToBlit) {
		this.screen = screenToBlit;
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
		this.screen.initQuadTree(new Rectangle(0,0,levelMap[0].length()*scale, levelMap.length*scale));
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
			screen.blit(ob, 0);
		} else if(w.equals("p")) {
			spawnPoint = pos;
			System.out.println("Spawn point at: " + pos.getX() + ", " + pos.getY());
		}
		else {;}

	}
	
	public Point getSpawnPoint() {
		return spawnPoint;
	}


}
