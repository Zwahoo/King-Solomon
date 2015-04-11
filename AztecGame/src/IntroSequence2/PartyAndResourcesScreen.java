package IntroSequence2;

import java.awt.Graphics;
import java.util.ArrayList;

import Components.Button;
import Components.Textbox;
import MainGame.DrawScreen;
import MainGame.IntroSequence;
import MainGame.MainGame;
import MainGame.gameframe;

public class PartyAndResourcesScreen implements DrawScreen {

	private Textbox valuableAmt;

	private Button hireButton;
	private Button beginButton;

	private ArrayList<Textbox> partyList;
	private ArrayList<Textbox> supplies;

	private ArrayList<Button> plusButtons;
	private ArrayList<Button> minusButtons;

	private boolean finished = false;

	private int hireCounter = 0;

	private int maxPartySize = 8;

	private int initialPartyVSpacer = 100;

	private int partyVSpacer = 20;
	private int partyTHeight = 50;
	private int partyTWidth = 200;

	private int suppliesVSpacer = 20;
	private int suppliesHSpacer = 10;
	private int suppliesTHeight = 50;
	private int suppliesTWidth = 150;

	private int suppliesBHeight = suppliesTHeight;
	private int suppliesBWidth = suppliesBHeight;

	public PartyAndResourcesScreen() {
		initializeButtons();
		initializeTextboxes();
	}

	@Override
	public boolean update() {
		valuableAmt.update();
		hireButton.update();
		beginButton.update();
		for (Textbox t : partyList) {
			t.update();
		}
		for (Textbox t : supplies) {
			t.update();
		}
		for (Button b : plusButtons) {
			b.update();
		}
		for (Button b : minusButtons) {
			b.update();
		}
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		valuableAmt.draw(g);
		hireButton.draw(g);
		beginButton.draw(g);
		for (Textbox t : partyList) {
			t.draw(g);
		}
		for (Textbox t : supplies) {
			t.draw(g);
		}
		for (Button b : plusButtons) {
			b.draw(g);
		}
		for (Button b : minusButtons) {
			b.draw(g);
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);

		IntroSequence.input.removeInputListener(hireButton, modesList);
		IntroSequence.input.removeInputListener(beginButton, modesList);
		for(Button btn : plusButtons) {
			IntroSequence.input.removeInputListener(btn, modesList);
		}
		for(Button btn : minusButtons) {
			IntroSequence.input.removeInputListener(btn, modesList);
		}
	}

	private void initializeButtons() {
		hireButton = new Button(50, gameframe.windowHeight - 150, 50, 100, "Hire", IntroSequence.input){
			@Override
			public void onClick() {

			}
		};
		beginButton = new Button(gameframe.windowWidth - 150, gameframe.windowHeight - 150, 50, 100, "Begin", IntroSequence.input){
			@Override
			public void onClick() {
				finished = true;
			}
		};
		int j = 0;
		for (String e : MainGame.getStats().keySet()) {
			plusButtons.add(new Button(gameframe.windowWidth - (initialPartyVSpacer + (2 * suppliesBWidth) + suppliesHSpacer), initialPartyVSpacer + (j * partyVSpacer) + (j * partyTHeight) + suppliesVSpacer, suppliesBWidth, suppliesBHeight, "+", IntroSequence.input));
			minusButtons.add(new Button(gameframe.windowWidth - (initialPartyVSpacer + suppliesBWidth), initialPartyVSpacer + (j * partyVSpacer) + (j * partyTHeight) + suppliesVSpacer, j, j, "-", IntroSequence.input));
			j++;
		}
	}

	private void initializeTextboxes() {
		valuableAmt = new Textbox("Valuables: " + MainGame.getStats().get(MainGame.VALUABLES_KEY), 50, 50, 50, 150, IntroSequence.input);
		for (int i = 0; i < maxPartySize; i++) {
			partyList.add(new Textbox("-------------------", initialPartyVSpacer, initialPartyVSpacer + (i * partyVSpacer) + (i * partyTHeight), partyTWidth, partyTHeight, IntroSequence.input));
		}
		int j = 0;
		for (String e : MainGame.getStats().keySet()) {
			supplies.add(new Textbox(e + ": " + MainGame.getStats().get(e), gameframe.windowWidth - (initialPartyVSpacer + suppliesTWidth + (2 * suppliesVSpacer) + (2 * suppliesBWidth)), initialPartyVSpacer + (j * suppliesTHeight) + (j * suppliesVSpacer), suppliesTWidth, suppliesTHeight, IntroSequence.input));
			j++;
		}
	}

}
