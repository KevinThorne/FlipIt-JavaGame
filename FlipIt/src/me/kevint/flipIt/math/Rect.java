package me.kevint.flipIt.math;

import java.awt.Point;

public class Rect {
	
	public int x;
	public int y;
	
	public int x2;
	public int y2;
	
	public int w;
	public int h;
	
	public Rect() {
		x = 0; y = 0; w = 0; h = 0; x2 = 0; y2=0;
	}
	
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.y2 = y+h;
		this.x2 = x+w;
		this.w = w;
		this.h = h;
	}
	
	public Rect mult(Rect rect1) {
		x = rect1.x * this.x;
		y = rect1.y * this.y;
		w = rect1.w * this.w;
		h = rect1.h * this.h;
		return this;
		
	}
	public Rect add(Rect rect1) {
		x = rect1.x + this.x;
		y = rect1.y + this.y;
		w = rect1.w + this.w;
		h = rect1.h + this.h;
		return this;
		
	}
	
	public Rect localize(Point point) {
		x = point.x + this.x;
		y = point.y + this.y;
		return this;
	}
	
	public boolean collide(Point origin, Rect bounds1, Point rect1Origin) {
		Rect rect1 = this.localize(origin);
		Rect rect2 = bounds1.localize(rect1Origin);
		
		if (rect1.x < rect2.x + rect2.w && // rect1,x < rect2.x2
			rect1.x + rect1.w > rect2.x && // rect1.x2< rect2.x
			rect1.y < rect2.y + rect2.h && // rect1.y < rect2.y2
			rect1.h + rect1.y > rect2.y) { // rect1.y2< rect2.y
				    return true;
				}
		
		return false;
	}
	
	public static Rect mult(Rect rect1, Rect rect2) {
		return new Rect(
				rect1.x * rect2.x,
				rect1.y * rect2.y,
				rect1.w * rect2.w,
				rect1.h * rect2.h);
		
	}
	public static Rect add(Rect rect1, Rect rect2) {
		return new Rect(
				rect1.x + rect2.x,
				rect1.y + rect2.y,
				rect1.w + rect2.w,
				rect1.h + rect2.h);
		
	}
}
