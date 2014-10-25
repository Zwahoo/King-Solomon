package MainGame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class InputListener {
	
	//Stores the input manager.
	private InputManager inputManager;

	//Hooks the listener up to the input manager.
	protected void setInputManager(InputManager inputManager, ArrayList<Integer> modes) {
		this.inputManager = inputManager;
		inputManager.addInputListener(this, modes);
	}
	
	//Returns the location of the mouse
	protected Point getMouseLoc() {
		if(inputManager != null) return inputManager.getMouseLoc();
		else {
			System.out.println("Input Listener is trying to access mouse loc without setting inputManager");
			return null;
		}
	}
	
	//Will be called once every frame
	protected void update() {};
	
	//Returns whether or not the mouse is in the game window.
	protected boolean mouseInWindow() {
		return inputManager.mouseInWindow();
	}
	
	//Mouse Methods
	//Called when the mouse is first pressed down.
	public void mouseDown(int mouseButton, Point mouseLoc) {}
	public void mousePressed(int button, Point mouseLoc) {}
	public void mouseReleased(int button, Point mouseLoc) {}
	public void mouseDragged() {}
	public void mouseExited() {}
	public void mouseEntered() {}
	public void mouseMoved(Point point) {}
	
	//Keyboard Methods
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public boolean keyIsDown(int keyCode) { return inputManager.keyIsDown(keyCode); }
}
