package IntroSequence2;

import java.awt.Graphics;

import MainGame.DrawScreen;

public class ResourcesAndPartyHolder implements DrawScreen {

	private boolean finished;

	static PartyAndResourcesScreen resScr;
	static PartyHireScreen hireScr;

	public static boolean drawRes;

	public ResourcesAndPartyHolder() {
		resScr = new PartyAndResourcesScreen();
		drawRes = true;
	}

	@Override
	public boolean update() {
		if (drawRes) {
			resScr.update();
		} else {
			hireScr.update();
		}
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		if (drawRes) {
			resScr.draw(g);
		} else {
			hireScr.draw(g);
		}
	}

	@Override
	public void finish() {
		resScr.finish();
	}

	public static void switchToHire(int partyID) {
		drawRes = false;
		hireScr = new PartyHireScreen(resScr.getSelectedPartyMember(partyID));
	}

	public PartyAndResourcesScreen getResScr() {
		return resScr;
	}

}
