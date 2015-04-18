package IntroSequence2;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import Components.Button;
import Components.Textbox;
import MainGame.DrawScreen;
import MainGame.IntroSequence;
import MainGame.MainGame;
import MainGame.PartyMember;
import MainGame.gameframe;

public class PartyAndResourcesScreen implements DrawScreen {

	private Textbox valuableAmt;

	private Button beginButton;

	private ArrayList<Button> partyButtons;

	private HashMap<String, Textbox> supplyMap;

	private ArrayList<String> cannotBuyList;

	private HashMap<String, Integer> buyAmtMap;

	private HashMap<String, Button> plusButtonMap;
	private HashMap<String, Button> minusButtonMap;

	private boolean finished = false;

	//private int hireCounter = 0;
	// Probably don't need this but I'll keep it anyways for teh lolz

	private int supplyCost = 5;
	private int partyMemberCost = 10;
	private int maxPartySize = 7;

	private HashMap<String, PartyMember> selectedMembers = new HashMap<>();
	private ArrayList<PartyMember> tempMembers = new ArrayList<>(maxPartySize);



	private int initialPartyVSpacer = 150;

	private int partyVSpacer = 20;
	private int partyTHeight = 60;
	private int partyTWidth = 200;

	private int suppliesVSpacer = 20;
	private int suppliesHSpacer = 10;
	private int suppliesTHeight = 60;
	private int suppliesTWidth = 150;

	private int suppliesBHeight = suppliesTHeight / 2;
	private int suppliesBWidth = suppliesBHeight;

	int beginWidth = 200;
	int beginHeight = 40;
	int beginX = gameframe.windowWidth - beginWidth - 40;
	int beginY = gameframe.windowHeight - beginHeight - 40;

	public PartyAndResourcesScreen() {
		//partyList = new ArrayList<>();
		partyButtons = new ArrayList<>();
		plusButtonMap = new HashMap<>();
		minusButtonMap = new HashMap<>();
		supplyMap = new HashMap<>();
		buyAmtMap = new HashMap<>();
		initCannotBuyList();
		initBuyAmtMap();
		initializeButtons();
		initializeTextboxes();
		initTempMembers();
	}

	@Override
	public boolean update() {
		valuableAmt.update();
		valuableAmt.setText("Valuables: " + MainGame.getStats().get(MainGame.VALUABLES_KEY));
		if (selectedMembers.values().size() == 0) {
			beginButton.disable();
			beginButton.update();
		} else {
			beginButton.enable();
			beginButton.update();
		}
		/*for (Textbox t : partyList) {
			t.update();
		}*/
		for (Button b : partyButtons) {
			b.update();
		}
		for (Textbox t : supplyMap.values()) {
			t.update();
		}
		for (Button b : plusButtonMap.values()) {
			b.update();
		}
		for (Button b : minusButtonMap.values()) {
			b.update();
		}
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		valuableAmt.draw(g);
		beginButton.draw(g);
		/*for (Textbox t : partyList) {
			t.draw(g);
		}*/
		for (Button b : partyButtons) {
			b.draw(g);
		}
		for (Textbox t : supplyMap.values()) {
			t.draw(g);
		}
		for (Button b : plusButtonMap.values()) {
			b.draw(g);
		}
		for (Button b : minusButtonMap.values()) {
			b.draw(g);
		}
	}

	@Override
	public void finish() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);

		IntroSequence.input.removeInputListener(beginButton, modesList);
		for (Button b : partyButtons) {
			IntroSequence.input.removeInputListener(b, modesList);
		}
		for(Button btn : plusButtonMap.values()) {
			IntroSequence.input.removeInputListener(btn, modesList);
		}
		for(Button btn : minusButtonMap.values()) {
			IntroSequence.input.removeInputListener(btn, modesList);
		}
	}

	private void initializeButtons() {
		for (int i = 0; i < maxPartySize; i++) {
			//partyList.add(new Textbox("Hire Party Member\nCost: " + partyMemberCost, initialPartyVSpacer, initialPartyVSpacer + (i * partyVSpacer) + (i * partyTHeight), partyTWidth, partyTHeight, IntroSequence.input));
			partyButtons.add(new Button(initialPartyVSpacer, initialPartyVSpacer + (i * partyVSpacer) + (i * partyTHeight), partyTWidth, partyTHeight, "Hire Party Member\nCost: " + partyMemberCost, IntroSequence.input){
				@Override
				public void onClick() {
					//TODO ADD PARTY HIRING STUFF HERE
					int partyID = partyButtons.indexOf(this);
					ResourcesAndPartyHolder.switchToHire(partyID);
				}
			});
		}
		beginButton = new Button(beginX, beginY, beginWidth, beginHeight, "Begin", IntroSequence.input){
			@Override
			public void onClick() {
				finished = true;
			}
		};
		int j = 0;
		for (String e : MainGame.getStats().keySet()) {
			if (!cannotBuyList.contains(e)){
				plusButtonMap.put(e, new Button(gameframe.windowWidth - (initialPartyVSpacer + (2 * suppliesBWidth) + suppliesHSpacer), initialPartyVSpacer + (suppliesTHeight / 4) + (j * suppliesVSpacer) + (j * partyTHeight), suppliesBWidth, suppliesBHeight, "+", IntroSequence.input) {
					@Override
					public void onClick() {
						changeStat(this, true);

					}
				});
				minusButtonMap.put(e, new Button(gameframe.windowWidth - (initialPartyVSpacer + suppliesBWidth), initialPartyVSpacer + (suppliesTHeight / 4) + (j * suppliesVSpacer) + (j * partyTHeight), suppliesBWidth, suppliesBHeight, "-", IntroSequence.input) {
					@Override
					public void onClick() {
						changeStat(this, false);
					}
				});
				j++;
			}
		}
	}

	private void changeStat(Button b, boolean plus) {
		if (plus && (MainGame.getStats().get(MainGame.VALUABLES_KEY) > 0)) {
			for (String s : plusButtonMap.keySet()) {
				if (plusButtonMap.get(s).equals(b)) {
					if (MainGame.incPartyStat(s, buyAmtMap.get(s))) {
						MainGame.incPartyStat(MainGame.VALUABLES_KEY, -supplyCost);
					}
					supplyMap.get(s).setText(s + ": " + MainGame.getStats().get(s) + "\nCost: " + supplyCost);
				}
			}
		} else {
			for (String s : minusButtonMap.keySet()) {
				if (minusButtonMap.get(s).equals(b)) {
					if (MainGame.incPartyStat(s, -buyAmtMap.get(s))) {
						MainGame.incPartyStat(MainGame.VALUABLES_KEY, supplyCost);
					}
					supplyMap.get(s).setText(s + ": " + MainGame.getStats().get(s) + "\nCost: " + supplyCost);
				}
			}
		}
	}

	private void initializeTextboxes() {
		valuableAmt = new Textbox("Valuables: " + MainGame.getStats().get(MainGame.VALUABLES_KEY), 50, 20, 150, 50, IntroSequence.input);
		int j = 0;
		for (String e : MainGame.getStats().keySet()) {
			if (!cannotBuyList.contains(e)){
				supplyMap.put(e, new Textbox(e + ": " + MainGame.getStats().get(e) + "\nCost: " + supplyCost, gameframe.windowWidth - (initialPartyVSpacer + suppliesTWidth + (2 * suppliesVSpacer) + (2 * suppliesBWidth)), initialPartyVSpacer + (j * suppliesTHeight) + (j * suppliesVSpacer), suppliesTWidth, suppliesTHeight, IntroSequence.input));
				j++;
			}
		}
	}

	private void initCannotBuyList() {
		cannotBuyList = new ArrayList<>();
		cannotBuyList.add(MainGame.VALUABLES_KEY);
		cannotBuyList.add(MainGame.MORALE_KEY);
		cannotBuyList.add(MainGame.STAMINA_KEY);
	}

	private void initBuyAmtMap() {
		buyAmtMap.put(MainGame.FOOD_KEY, 10);
		buyAmtMap.put(MainGame.WATER_KEY, 10);
		buyAmtMap.put(MainGame.AMMO_KEY, 20);
		buyAmtMap.put(MainGame.MEDICINE_KEY, 5);
	}

	private void initTempMembers() {
		for(int i = 0; i < maxPartySize; i++) {
			tempMembers.add(null);
		}
	}

	public HashMap<String, PartyMember> getSelectedParty() {
		return selectedMembers;
	}

	public PartyMember getSelectedPartyMember(int index) {
		return tempMembers.get(index);
	}

}
