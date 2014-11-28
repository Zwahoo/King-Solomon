package MainGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashMap;

public class IntroSequence {
	
	public boolean finished = false;
	
	int stateNum = 0; //0 main screen | 1 stat select | 3 party select | 4 member info?
	
	private DrawScreen currentScreen;
	private StartScreen startScreen;
	private StatSelectScreen statSelect;
	private PartySelectScreen partySelect;
	
	Color bkgColor = Color.GRAY;
	
	public static InputManager input;
	
	public HashMap<String, Integer> gentStats = PartyMemberStats.AVERAGE_ABE_STATS;
	
	public IntroSequence(Component c) {
		input = new InputManager(c, null);
		setupStatSelect();
	}

	
	public void setupStatSelect() {
		startScreen = new StartScreen();
		currentScreen = startScreen;
	}
	
	public void launchNextState() {
		if(stateNum == 0) {
			statSelect = new StatSelectScreen();
			currentScreen = statSelect;
			startScreen.finish();
		}
		else if(stateNum == 1) {
			gentStats = statSelect.gentStats;
			partySelect = new PartySelectScreen();
			currentScreen = partySelect;
			statSelect.finish();
		} else if (stateNum == 2) {
			finished = true;
		}
		stateNum++;
	}
	
	public void update() {
		boolean finished = currentScreen.update();
		if(finished) {
			launchNextState();
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(bkgColor);
		g.fillRect(0, 0, gameframe.windowWidth, gameframe.windowHeight);
		currentScreen.draw(g);
	}

}