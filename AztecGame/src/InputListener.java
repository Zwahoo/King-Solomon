import java.awt.*;

public abstract class InputListener {
	private InputManager inputManager;
	
	abstract void mouseDown(int mouseButton);
	
	protected void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}
	
	protected Point getMouseLoc() {
		if(inputManager != null) return inputManager.getMouseLoc();
		else {
			System.out.println("Input Listener is trying to access mouse loc without setting inputManager");
			return null;
		}
	}
	
	protected boolean mouseInWindow() {
		return inputManager.mouseInWindow();
	}
}
