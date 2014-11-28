package Components;

import java.awt.Graphics;
import java.util.ArrayList;

import MainGame.*;

public class PartySelectButton {
	
	public PartyMember myMember;
	int btnXLoc;
	int btnYLoc;
	int btnWidth;
	int btnHeight;
	public boolean hired = false;
	
	Button mainButton;
	
	boolean paymentCharBefore = true;
	String paymentChar = "$";
	
	public PartySelectButton(PartyMember member, int btnXLoc, int btnYLoc, int btnWidth, int btnHeight) {
		this.myMember = member;
		this.btnXLoc = btnXLoc;
		this.btnYLoc = btnYLoc;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
		
		mainButton = new Button(btnXLoc, btnYLoc, btnWidth, btnHeight, makeButtonString(), IntroSequence.input) {
			@Override
			public void onClick() {
				if(hired) fire();
				else hire();
			}
		};
	}

	public void update() {
		mainButton.update();
	}

	public void draw(Graphics g) {
		mainButton.draw(g);
	}
		
	public void hire() {
		if(!hired && PartySelectScreen.money - myMember.getPay() >= 0) { 
			hired = true;
			PartySelectScreen.money -= myMember.getPay();
			mainButton.setText(makeButtonString());
		}
	}
	
	public void fire() {
		if(hired) {
			hired = false;
			PartySelectScreen.money += myMember.getPay();
			mainButton.setText(makeButtonString());
		}
	}
	
	public String makeButtonString() {
		String cost = (myMember.getPay() + "");
		if(paymentCharBefore) {
			cost = paymentChar + cost;
		} else {
			cost += paymentChar;
		}
		String note = "";
		if(hired) note = " (Hired)";
		return (myMember.getName() + note + "\n" + myMember.getType() + "\n" + cost);
	}
	
	public void destroy() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(mainButton, modesList);
	}
}
