package IntroSequence2;

import java.awt.Graphics;

import MainGame.DrawScreen;
import MainGame.PartyMember;

public class ResourcesAndPartyHolder implements DrawScreen {

	public static boolean finished;

	static PartyAndResourcesScreen resScr;
	static PartyHireScreen hireScr;

	public static boolean drawRes;

	public static int currPartyID;

	public ResourcesAndPartyHolder() {
		resScr = new PartyAndResourcesScreen();
		drawRes = true;
	}

	@Override
	public boolean update() {
		if (drawRes) {
			if(resScr != null) {
				resScr.update();
			}
		} else {
			if(hireScr != null) {
				hireScr.update();
			}
		}
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		if (drawRes) {
			if(resScr != null) {
				resScr.draw(g);
			}
		} else {
			if(hireScr != null) {
				hireScr.draw(g);
			}
		}
	}

	@Override
	public void finish() {
		resScr.finish();
	}

	public static void switchToResources(PartyMember pMember) {
		if (pMember == null) {
			if (resScr.getSelectedPartyMember(currPartyID) != null) {
				resScr.removePartyMember(currPartyID);
			} else {
				resScr.cancelPartyMember();
			}
		} else {
			resScr.addPartyMember(pMember, currPartyID);
		}
		resScr.enableAllButtons();
		drawRes = true;
		hireScr.finish();
		hireScr = null;
	}

	public static void switchToHire(int partyID) {
		resScr.disableAllButtons();
		drawRes = false;
		hireScr = new PartyHireScreen(resScr.getSelectedPartyMember(partyID));
		currPartyID = partyID;
	}

	public PartyAndResourcesScreen getResScr() {
		return resScr;
	}

}
