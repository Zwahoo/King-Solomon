package MainGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class IntroSequence {
	
	public boolean finished = false;
	
	private DrawScreen currentScreen;
	private StatSelect statSelect;
	
	Color bkgColor = Color.GRAY;
	
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
		g.setColor(bkgColor);
		g.fillRect(0, 0, gameframe.windowWidth, gameframe.windowHeight);
		currentScreen.draw(g);
	}

}