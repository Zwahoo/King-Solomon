package MainGame;

import java.awt.Component;
import java.awt.Graphics;

public class IntroSequence {
	
	public boolean finished = false;
	
	private DrawScreen currentScreen;
	private StatSelect statSelect;
	
	public static InputManager input;
	
	public IntroSequence(Component c) {
		input = new InputManager(c, null);
		
		setupStatSelect();
	}

	
	public void setupStatSelect() {
		statSelect = new StatSelect();
		currentScreen = statSelect;
	}
	
	public void launchNextState() {
		finished = true;
	}
	
	public void update() {
		boolean finished = currentScreen.update();
		if(finished) launchNextState();
	}
	
	public void draw(Graphics g) {
		currentScreen.draw(g);
	}

}
