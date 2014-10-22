package MainGame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;

import Components.Button;
import Components.Textbox;

public class EventDrawer {
	
	Textbox info;
	Textbox partyMembers;
	ArrayList<Button> buttons = new ArrayList<Button>();
	double infoTextboxWMult = 0.8;
	double infoTextboxHMult = .17;
	double partyMembersWMult = .25;
	double partyMembersHMult = .30;
	double imageWMult = .8;
	double imageHMult = .35;
	double buttonWMult = .50;
	double buttonHMult = .06;
	double spacer = .02;
	double buttonSpacer = .01;
	double sideSpacer = .1;
		
	int infoTextboxX;
	int infoTextboxY;
	int partyMembersX;
	int partyMembersY;
	int imageX;
	int imageY;
	int buttonX;
	int upperButtonY;
	
	
	public EventDrawer(Event toLaunch, ArrayList<PartyMember> presMembers) {
		setLocations();
		launchEvent(toLaunch, presMembers);

	}
	
	
	public void launchEvent(Event toLaunch, ArrayList<PartyMember> presMembers) {
		
		info = new Textbox(toLaunch.getIntroText(), infoTextboxX, infoTextboxY, (int)(gameframe.windowWidth*infoTextboxWMult), (int)(gameframe.windowHeight*infoTextboxHMult), MainGame.input);
		partyMembers  = new Textbox(getPresentPartyMembers(presMembers), partyMembersX, partyMembersY, (int)(gameframe.windowWidth*partyMembersWMult), (int)(gameframe.windowHeight*partyMembersHMult), MainGame.input);
		int totalY = upperButtonY;
		for (ResponseOption ro : toLaunch.getResponseOptions()) {
			Button temp = new Button(buttonX, totalY, (int)(gameframe.windowWidth * buttonWMult), (int)(gameframe.windowHeight * buttonHMult), ro.getText(), MainGame.input) {
				@Override
				public void onClick() {
					handleResponseSelect();
				}
			};
			buttons.add(temp);			
			totalY += (int)(gameframe.windowHeight * (buttonSpacer + buttonHMult));
		}
	}
	
	public void handleResponseSelect() {
		System.out.println("Clicked!!1 \n");
	}
	
	public void draw(Graphics g) {
		info.draw(g);
		partyMembers.draw(g);
		for (Button b : buttons)
			b.draw(g);
	}
	
	public void update() {
		info.update();
		partyMembers.update();
		for (Button b : buttons)
			b.update();
	}
	
	public void setLocations() {
		int totalHeight = (int) (gameframe.windowHeight * spacer + MainGame.statBarHeight);
		
		imageX = (int) (gameframe.windowWidth * sideSpacer);
		imageY = totalHeight;
		totalHeight += gameframe.windowHeight * (imageHMult + spacer);
		
		infoTextboxX = imageX;
		infoTextboxY = totalHeight;
		totalHeight += gameframe.windowHeight * (infoTextboxHMult + spacer);
		
		partyMembersX = imageX;
		partyMembersY = totalHeight;
		
		
		buttonX = (int) (partyMembersX + gameframe.windowWidth* (partyMembersWMult + spacer));
		upperButtonY = totalHeight;
	}
	
	public String getPresentPartyMembers(ArrayList<PartyMember> partyMembers) {
		String intro = "Present Party: \n";
		for (PartyMember partyMember : partyMembers) {
			intro += partyMember.getName() + "\n";
		}
		
		return intro;
		
	}
	
	
}
