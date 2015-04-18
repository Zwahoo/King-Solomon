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
	private int valuables = 100;

	private Button beginButton;

	private ArrayList<Button> partyButtons;

	private HashMap<String, Textbox> supplyTextboxMap;
	private HashMap<String, Integer> supplyMap;

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
		supplyTextboxMap = new HashMap<>();
		supplyMap = new HashMap<>();
		buyAmtMap = new HashMap<>();
		initSupplyBases();
		initCannotBuyList();
		initBuyAmtMap();
		initializeButtons();
		initializeTextboxes();
		initTempMembers();
	}

	@Override
	public boolean update() {
		valuableAmt.update();
		valuableAmt.setText("Valuables: " + valuables);
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
		for (Textbox t : supplyTextboxMap.values()) {
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
		for (Textbox t : supplyTextboxMap.values()) {
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
					int partyID = partyButtons.indexOf(this);
					if (valuables > 0){
						if (tempMembers.get(partyID) == null) {
							valuables -= partyMemberCost;
						}
						ResourcesAndPartyHolder.switchToHire(partyID);
					} else if (tempMembers.get(partyID) != null) {
						ResourcesAndPartyHolder.switchToHire(partyID);
					}
				}
			});
		}
		beginButton = new Button(beginX, beginY, beginWidth, beginHeight, "Begin", IntroSequence.input){
			@Override
			public void onClick() {
				ResourcesAndPartyHolder.finished = true;
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
		if (plus && (valuables > 0)) {
			for (String s : plusButtonMap.keySet()) {
				if (plusButtonMap.get(s).equals(b)) {
					supplyMap.put(s, supplyMap.get(s) + buyAmtMap.get(s));
					valuables -= supplyCost;
					supplyTextboxMap.get(s).setText(s + ": " + supplyMap.get(s) + "\nCost: " + supplyCost);
				}
			}
		} else {
			for (String s : minusButtonMap.keySet()) {
				if (minusButtonMap.get(s).equals(b)) {
					supplyMap.put(s, supplyMap.get(s) - buyAmtMap.get(s));
					valuables += supplyCost;
					supplyTextboxMap.get(s).setText(s + ": " + supplyMap.get(s) + "\nCost: " + supplyCost);
				}
			}
		}
	}

	private void initializeTextboxes() {
		valuableAmt = new Textbox("Valuables: " + valuables, 50, 20, 150, 50, IntroSequence.input);
		int j = 0;
		for (String e : MainGame.getStats().keySet()) {
			if (!cannotBuyList.contains(e)){
				supplyTextboxMap.put(e, new Textbox(e + ": " + supplyMap.get(e) + "\nCost: " + supplyCost, gameframe.windowWidth - (initialPartyVSpacer + suppliesTWidth + (2 * suppliesVSpacer) + (2 * suppliesBWidth)), initialPartyVSpacer + (j * suppliesTHeight) + (j * suppliesVSpacer), suppliesTWidth, suppliesTHeight, IntroSequence.input));
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

	private void initSupplyBases() {
		supplyMap.put(MainGame.FOOD_KEY, 10);
		supplyMap.put(MainGame.WATER_KEY, 10);
		supplyMap.put(MainGame.AMMO_KEY, 20);
		supplyMap.put(MainGame.MEDICINE_KEY, 5);
	}

	public void addPartyMember(PartyMember pMember, int partyID) {
		if (tempMembers.get(partyID) != null) {
			selectedMembers.remove(tempMembers.get(partyID).getName());
		}
		tempMembers.set(partyID, pMember);
		partyButtons.get(partyID).setText(pMember.getName() + "\n" + pMember.getType() + "\nCost: " + partyMemberCost);
		selectedMembers.put(pMember.getName(), pMember);
	}

	public HashMap<String, PartyMember> getSelectedParty() {
		return selectedMembers;
	}

	public PartyMember getSelectedPartyMember(int index) {
		return tempMembers.get(index);
	}

	public void disableAllButtons() {
		beginButton.disable();
		for (Button b : partyButtons) {
			b.disable();
		}
		for (Button b : plusButtonMap.values()) {
			b.disable();
		}
		for (Button b : minusButtonMap.values()) {
			b.disable();
		}
	}

	public void enableAllButtons() {
		beginButton.enable();
		for (Button b : partyButtons) {
			b.enable();
		}
		for (Button b : plusButtonMap.values()) {
			b.enable();
		}
		for (Button b : minusButtonMap.values()) {
			b.enable();
		}
	}

	public void removePartyMember(int partyID) {
		selectedMembers.remove(tempMembers.get(partyID).getName());
		tempMembers.set(partyID, null);
		partyButtons.get(partyID).setText("Fired!\nCost: " + partyMemberCost);
		valuables += partyMemberCost;
	}

	public void cancelPartyMember() {
		valuables += partyMemberCost;
	}

	public HashMap<String, Integer> createStatsMap() {
		HashMap<String, Integer> resources = new HashMap<>();
		resources.put(MainGame.MORALE_KEY, 100);
		resources.put(MainGame.STAMINA_KEY, 100);
		resources.put(MainGame.VALUABLES_KEY, valuables);
		for (String s : supplyMap.keySet()) {
			resources.put(s, supplyMap.get(s));
		}
		return resources;
	}
}
