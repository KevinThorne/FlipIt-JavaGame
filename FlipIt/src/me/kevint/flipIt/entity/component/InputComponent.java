package me.kevint.flipIt.entity.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import me.kevint.flipIt.FlipIt;
import me.kevint.flipIt.input.KeyMapping;

public class InputComponent extends Component implements KeyListener{
	
	private KeyMapping[] keyMap;
	
	private boolean startCounter = false;
	private int loadCounter;

	public InputComponent(KeyMapping... keyMap) {
		this.keyMap = keyMap;
	}
	
	@Override
	public void initialize() {
		FlipIt.getJFrame().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for(KeyMapping mappedKey : keyMap) {
			if(e.getKeyCode() == mappedKey.getKey()) {
				if(!mappedKey.getWaitForRelease()) {
					try {
						this.getParentEntity().getClass().getMethod(mappedKey.getMethod()).invoke(this.getParentEntity());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(KeyMapping mappedKey : keyMap) {
			if(e.getKeyCode() == mappedKey.getKey()) {
				if(mappedKey.getReleasedMethod() != null) {
					try {
						this.getParentEntity().getClass().getMethod(mappedKey.getReleasedMethod()).invoke(this.getParentEntity());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {;}

}
