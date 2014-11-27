package MainGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashMap;

public class IntroSequence {
	
	public boolean finished = false;
	
	int stateNum = 1; //0 main screen | 1 stat select | 3 party select | 4 member info?
	
	private DrawScreen currentScreen;
	private StatSelectScreen statSelect;
	
	Color bkgColor = Color.GRAY;
	
	public static InputManager input;
	
	public HashMap<String, Integer> gentStats = PartyMemberStats.AVERAGE_ABE_STATS;
	
	public IntroSequence(Component c) {
		input = new InputManager(c, null);
		setupStatSelect();
	}

	
	public void setupStatSelect() {
		statSelect = new StatSelectScreen();
		currentScreen = statSelect;
	}
	
	public void launchNextState() {
		if(stateNum == 1) {
			gentStats = statSelect.gentStats;
		}
		currentScreen.finish();
		stateNum++;
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