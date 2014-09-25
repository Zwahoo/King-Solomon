import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InputManager {
	
	private MouseInput mouseInput;
	private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	
	//Movement Handlers
	private PlayerMovementHandler playerMovHandler; //Moves the player around based on input
	private ViewMovementHandler viewMovHandler; //Moves the view around based on input.
	
	public InputManager(Component c, MainGame mainGame) {
		mouseInput = new MouseInput(c);
		playerMovHandler = new PlayerMovementHandler(mainGame, mainGame.getMap(), mainGame.getPlayer(), this);
		viewMovHandler = new ViewMovementHandler(mainGame.getView(), this);
	}
	
	public void update() {
		if (mouseDown(MouseEvent.BUTTON1)) {
			for(InputListener listener : inputListeners) {
				listener.mouseDown(MouseEvent.BUTTON1, getMouseLoc());
			}
    	}
		
		for(InputListener listener : inputListeners) {
			listener.update();
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
