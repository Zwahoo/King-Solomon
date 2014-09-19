import java.awt.Component;
import java.awt.Point;

public class InputManager {
	
	private mouseinput mouseInput;
	
	public InputManager(Component c) {
		mouseInput = new mouseinput(c);
	}
	
	public boolean mouseDown(int mouseCode) {
		return mouseInput.isDown(mouseCode);
	}
	
	public Point getMouseLoc() {
		return new Point(mouseInput.getX(), mouseInput.getY());
	}
	
	public boolean mouseInWindow() {
		return mouseInput.checkmouseinwindow();
	}
}
