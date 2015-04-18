package MainGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.HashMap;

import IntroSequence2.GentlemanEditScreen;
import IntroSequence2.ResourcesAndPartyHolder;

/**
 * The intro sequence displays one intro scene after another,
 * starting with the title screen, finishing with party selection.
 * It shows one, until that scene says it's finished, then it moves on to the next.
 * Once all the scenes have finished, it flags itself as finished.
 * Moving on to MainGame is handled in gameframe, not here.
 */
public class IntroSequence {

	//Have all scenes been completed?
	public boolean finished = false;

	//Which scene are we showing?
	int stateNum = 0; //0 main screen | 1 stat select | 2 party select

	//The current scene being shown.
	private DrawScreen currentScreen;
	//The three different scene options.
	private GentlemanEditScreen gentEditScreen;
	private StartScreen startScreen;
	private ResourcesAndPartyHolder partySelect;

	//The background color for scenes with no background of their own.
	Color bkgColor = Color.GRAY;

	//Need an input manager for buttons. Everything is done in mode -1.
	public static InputManager input;

	//The stats chosen for the gentleman.
	public HashMap<String, Integer> gentStats = PartyMemberStats.AVERAGE_ABE_STATS;
	//Chosen Party.
	public HashMap<String, PartyMember> party = new HashMap<String, PartyMember>();
	public HashMap<String, Integer> resources = new HashMap<>();

	/**
	 * Initializes the intro sequence and launches first scene.
	 */
	public IntroSequence(Component c) {
		MainGame.setCurrentMode(-1);
		input = new InputManager(c, null);
		setupInitialScene();
	}

	/**
	 * Launches the initial scene.
	 */
	public void setupInitialScene() {
		startScreen = new StartScreen();
		currentScreen = startScreen;
	}

	/**
	 * Progresses the scenes on to the next state.
	 * Finishes the current scene, and, if there's another scene, starts it, otherwise flags as finished.
	 */
	public void launchNextState() {
		//Start Screen Completed.
		if(stateNum == 0) {
			gentEditScreen = new GentlemanEditScreen();
			currentScreen = gentEditScreen;
			startScreen.finish();
		}
		//Stat Select Screen Completed.
		else if(stateNum == 1) {
			gentStats = gentEditScreen.getGentStats();
			gentEditScreen.finish();
			partySelect = new ResourcesAndPartyHolder();
			currentScreen = partySelect;
			//statSelect.finish();
		}
		//Party Select Screen Completed.
		else if (stateNum == 2) {
			party = partySelect.getResScr().getSelectedParty();
			resources = partySelect.getResScr().createStatsMap();
			partySelect.finish();
			finished = true;
		}
		stateNum++;
	}

	public void update() {
		//Scenes have their update return true if they are finished.
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

	public static void removeInputManager() {
		if(input != null) {
			input.closeMe();
			input = null;
		}
	}

}