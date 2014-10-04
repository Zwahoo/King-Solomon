package MainGame;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardInput implements KeyListener {

	InputManager input;
	
	ArrayList<Integer> downKeys = new ArrayList<Integer>();
	
	public KeyboardInput(Component c, InputManager input) {
		c.addKeyListener(this);
		this.input = input;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!downKeys.contains(e.getKeyCode())) {
			downKeys.add(e.getKeyCode());
		}
		input.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(downKeys.contains(e.getKeyCode())) {
			downKeys.remove((Object)e.getKeyCode());
		}
		input.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		input.keyTyped(e);
	}
	
	public boolean keyIsDown(int keyCode) {
		return downKeys.contains(keyCode);
	}
}
