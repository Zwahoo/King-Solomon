import java.awt.*;

public abstract class InputListener {
	
	//Stores the input manager.
	private InputManager inputManager;

	//Hooks the listener up to the input manager.
	protected void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
		inputManager.addInputListener(this);
	}
	//Called when the mouse is first pressed down.
	public void mouseDown(int mouseButton, Point mouseLoc) {};
	
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
}
