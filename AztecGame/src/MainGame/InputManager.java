package MainGame;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("unused")
public class InputManager {
		
	private MouseInput mouseInput;
	private KeyboardInput keyInput;
	//private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	private HashMap<Integer, ArrayList<InputListener>> inputListenersMap = new HashMap<Integer, ArrayList<InputListener>>();
	
	//Movement Handlers
	private PlayerMovementHandler playerMovHandler; //Moves the player around based on input
	private ViewMovementHandler viewMovHandler; //Moves the view around based on input.
	
	public InputManager(Component c, MainGame mainGame) {
		mouseInput = new MouseInput(c, this);
		keyInput = new KeyboardInput(c, this);
		if(mainGame != null) {
			playerMovHandler = new PlayerMovementHandler(mainGame, mainGame.getMap(), mainGame.getPlayer(), this);
			viewMovHandler = new ViewMovementHandler(mainGame.getView(), this);
		}
	}
	
	public void update() {
		// update everything regardless of mode
		ArrayList<ArrayList<InputListener>> copy = new ArrayList<ArrayList<InputListener>>(inputListenersMap.values());
		for (ArrayList<InputListener> temp : copy) {
			ArrayList<InputListener> copy2 = new ArrayList<InputListener>(temp);
			for(InputListener listener : copy2) {
				listener.update();
			}
		}
	} 
	
	/*public void addInputListener(InputListener toAdd) {
		if(!inputListeners.contains(toAdd)) inputListeners.add(toAdd);
	}*/
	
	public void addInputListener(InputListener toAdd, ArrayList<Integer> modes) {
		for (Integer mode : modes) {
			if (!inputListenersMap.containsKey(mode)) {
				inputListenersMap.put(mode, new ArrayList<InputListener>());
			}
			if (!inputListenersMap.get(mode).contains(toAdd))
				inputListenersMap.get(mode).add(toAdd);
		}
	}
	
	public void removeInputListener(InputListener toAdd, ArrayList<Integer> modes) {
		for (Integer mode : modes) {
			if (inputListenersMap.containsKey(mode)){
				inputListenersMap.get(mode).remove(toAdd);
			}
		}
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
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		ArrayList<InputListener> temp = new ArrayList<InputListener>(inputListenersMap.get(MainGame.currentMode));
		for(InputListener listener : temp) {
			listener.mouseDown(button, getMouseLoc());
		}
	}
	public void mouseMoved(Point point) {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		ArrayList<InputListener> temp = new ArrayList<InputListener>(inputListenersMap.get(MainGame.currentMode));
		for(InputListener listener : temp) {
			listener.mouseMoved(point);
		}
	}
	public void mouseEntered() {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.mouseEntered();
		}
	}
	public void mouseExited() {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.mouseExited();
		}
	}
	public void mousePressed(int button) {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.mousePressed(button, getMouseLoc());
		}
	}
	public void mouseReleased(int button) {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		ArrayList<InputListener> temp = new ArrayList<InputListener>(inputListenersMap.get(MainGame.currentMode));
		for(InputListener listener : temp) {
			listener.mouseReleased(button, getMouseLoc());
		}
	}
	public void mouseDragged() {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.mouseDragged();
		}
	}

	
	//Keyboard Events
	public void keyTyped(KeyEvent e) {
		if(gameframe.DEBUG && (e.getKeyChar() == 'r') && MainGame.map != null) {
			for(int i=0; i < MainGame.map.width; i++) {
				for(int j=0; j < MainGame.map.height; j++) {
					MainGame.map.getTile(i, j).reveal();
				}	
			}
		}
		
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.keyTyped(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.keyReleased(e);
		}	
	}
	public void keyPressed(KeyEvent e) {
		if(!inputListenersMap.containsKey(MainGame.currentMode)) return;
		for(InputListener listener : inputListenersMap.get(MainGame.currentMode)) {
			listener.keyPressed(e);
		}
	}
	public boolean keyIsDown(int keyCode) {
		return keyInput.keyIsDown(keyCode);
	}
}
