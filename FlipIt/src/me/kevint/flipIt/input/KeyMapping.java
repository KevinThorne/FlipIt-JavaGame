package me.kevint.flipIt.input;

import java.lang.reflect.Method;

public class KeyMapping {
	
	private int key;
	private String method;
	private boolean waitForRelease;
	private String methodOnRelease;
	
	public KeyMapping(int key, String method) {
		this.key = key;
		this.method = method;
	}
	
	public KeyMapping(int key, String method, String methodRelease) {
		this.key = key;
		this.method = method;
		this.methodOnRelease = methodRelease;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public String getMethod() {
		return method;
	}
	public String getReleasedMethod() {
		return methodOnRelease;
	}
	
	public boolean getWaitForRelease() {
		return waitForRelease;
	}

}
