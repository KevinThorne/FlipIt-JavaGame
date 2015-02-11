package me.kevint.flipIt.math;

public class Rect {
	
	public int x;
	public int y;
	
	public int w;
	public int h;
	
	public Rect() {
		x = 0; y = 0; w = 0; h = 0;
	}
	
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
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
