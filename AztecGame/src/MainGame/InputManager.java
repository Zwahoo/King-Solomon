package MainGame;
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
		mouseInput = new MouseInput(c, this);
		playerMovHandler = new PlayerMovementHandler(mainGame, mainGame.getMap(), mainGame.getPlayer(), this);
		viewMovHandler = new ViewMovementHandler(mainGame.getView(), this);
	}
	
	public void update() {
		
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
	
	public boolean isMouseDown(int mouseCode) {
		return mouseInput.isDown(mouseCode);
	}
	
	public Point getMouseLoc() {
		return new Point(mouseInput.getX(), mouseInput.getY());
	}
	
	public boolean mouseInWindow() {
		return mouseInput.checkmouseinwindow();
	}

	//Mouse Override Methods
	public void mouseClicked(int button) {
		for(InputListener listener : inputListeners) {
			listener.mouseDown(button, getMouseLoc());
		}
	}
	public void mouseMoved(Point point) {
		for(InputListener listener : inputListeners) {
			listener.mouseMoved(point);
		}
	}
	public void mouseEntered() {
		for(InputListener listener : inputListeners) {
			listener.mouseEntered();
		}
	}
	public void mouseExited() {
		for(InputListener listener : inputListeners) {
			listener.mouseExited();
		}
	}
	public void mousePressed(int button) {
		for(InputListener listener : inputListeners) {
			listener.mousePressed(button, getMouseLoc());
		}
	}
	public void mouseReleased(int button) {
		for(InputListener listener : inputListeners) {
			listener.mouseReleased(button, getMouseLoc());
		}
	}
	public void mouseDragged() {
		for(InputListener listener : inputListeners) {
			listener.mouseDragged();
		}
	}
}
