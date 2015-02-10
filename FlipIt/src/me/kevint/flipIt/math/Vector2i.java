package me.kevint.flipIt.math;

public class Vector2i {
	
	private int x;
	private int y;
	
	public Vector2i() {
		x=0; y=0;
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public static Vector2i add(Vector2i a, Vector2i b) {
		return new Vector2i(a.getX() + b.getX(), a.getY() + b.getY());
	}
	public static Vector2i mult(Vector2i a, Vector2i b) {
		return new Vector2i(a.getX() * b.getX(), a.getY() * b.getY());
	}

}
