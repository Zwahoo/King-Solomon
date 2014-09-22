import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InputManager {
	
	private mouseinput mouseInput;
	private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	
	public InputManager(Component c) {
		mouseInput = new mouseinput(c);
	}
	
	public void update() {
		if (mouseDown(MouseEvent.BUTTON1)) 
    	{
			for(InputListener listener : inputListeners) {
				listener.mouseDown(MouseEvent.BUTTON1, getMouseLoc());
			}
    	}
	}
	
	public void addInputListener(InputListener toAdd) {
		if(!inputListeners.contains(toAdd)) inputListeners.add(toAdd);
	}
	
	public void removeInputListener(InputListener toAdd) {
		if(inputListeners.contains(toAdd)) inputListeners.remove(toAdd);
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
