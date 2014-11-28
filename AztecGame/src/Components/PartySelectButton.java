package Components;

import java.awt.Graphics;
import java.util.ArrayList;

import MainGame.*;

public class PartySelectButton {
	
	PartyMember myMember;
	int btnXLoc;
	int btnYLoc;
	int btnWidth;
	int btnHeight;
	public boolean selected = false;
	
	Button mainButton;
	
	String paymentChar = "$";
	
	public PartySelectButton(PartyMember member, int btnXLoc, int btnYLoc, int btnWidth, int btnHeight) {
		this.myMember = member;
		this.btnXLoc = btnXLoc;
		this.btnYLoc = btnYLoc;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
		
		mainButton = new Button(btnXLoc, btnYLoc, btnWidth, btnHeight, makeButtonString(), IntroSequence.input);
	}

	public void update() {
		mainButton.update();
	}

	public void draw(Graphics g) {
		mainButton.draw(g);
	}
	
	public void hire() {
		if(!selected && PartySelectScreen.money - myMember.getPay() >= 0) { 
			selected = true;
			PartySelectScreen.money -= myMember.getPay();
		}
	}
	
	public void fire() {
		if(selected) {
			selected = false;
			PartySelectScreen.money += myMember.getPay();
		}
	}
	
	public String makeButtonString() {
		 return (myMember.getName() + "\n" + myMember.getType() + "\n" + paymentChar + "" + myMember.getPay());
	}
	
	public void destroy() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(mainButton, modesList);
	}
}
