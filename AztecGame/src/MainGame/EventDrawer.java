package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;

import Components.Button;
import Components.Textbox;

public class EventDrawer {
	
	BufferedImage image;
	int imageBorderSize = 5;
	Color imageBorderCol = new Color(80, 50, 50);
	
	Textbox info;
	Textbox partyMembers;
	ArrayList<Button> buttons = new ArrayList<Button>();
	double infoTextboxWMult = 0.8;
	double infoTextboxHMult = .17;
	double partyMembersWMult = .25;
	double partyMembersHMult = .30;
	double imageWMult = .8;
	double imageHMult = .35;
	double buttonWMult = .53;
	double spacer = .02;
	double sideSpacer = .1;
	
	int buttonHeight;
	int buttonSpace;
	
	int infoTextboxX;
	int infoTextboxY;
	int partyMembersX;
	int partyMembersY;
	int imageX;
	int imageY;
	int buttonX;
	int upperButtonY;
	
	
	public EventDrawer(Event toLaunch, ArrayList<PartyMember> presMembers) {
		
		String imageLoc = "assets/Africa.bmp";
		
		try {
			image = ImageIO.read(new File(imageLoc));
		} catch (IOException e) {
			System.out.println("Couldn't find image: " + imageLoc + " for event. Breaking everything.");
			image = null;
		}
		setLocations();
		launchEvent(toLaunch, presMembers);

	}
	
	
	public void launchEvent(Event toLaunch, ArrayList<PartyMember> presMembers) {
		
		info = new Textbox(toLaunch.getIntroText(), infoTextboxX, infoTextboxY, (int)(gameframe.windowWidth*infoTextboxWMult), (int)(gameframe.windowHeight*infoTextboxHMult), MainGame.input);
		partyMembers  = new Textbox(getPresentPartyMembers(presMembers), partyMembersX, partyMembersY, (int)(gameframe.windowWidth*partyMembersWMult), (int)(gameframe.windowHeight*partyMembersHMult), MainGame.input);
		int totalY = upperButtonY;
		for (ResponseOption ro : toLaunch.getResponseOptions()) {
			Button temp = new Button(buttonX, totalY, (int)(gameframe.windowWidth * buttonWMult), buttonHeight, ro.getText(), MainGame.input) {
				@Override
				public void onClick() {
					handleResponseSelect();
				}
			};
			buttons.add(temp);			
			totalY += (buttonSpace + buttonHeight);
		}
	}
	
	public void handleResponseSelect() {
		MainGame.closeEvent();
	}
	
	public void draw(Graphics g) {
		drawImage(g);
		info.draw(g);
		partyMembers.draw(g);
		for (Button b : buttons)
			b.draw(g);
	}
	
	public void drawImage(Graphics g) {
		int borderWidth = (int) (gameframe.windowWidth * imageWMult);
		int borderHeight = (int) (gameframe.windowHeight * imageHMult);
		int imageWidth = borderWidth - imageBorderSize * 2;
		int imageHeight = borderHeight - imageBorderSize * 2;
		
		g.setColor(imageBorderCol);
		g.fillRect(imageX, imageY, borderWidth, borderHeight);
		g.drawImage(image, imageX + imageBorderSize, imageY + imageBorderSize, imageWidth, imageHeight, null);
		
	}
	
	public void update() {
		info.update();
		partyMembers.update();
		for (Button b : buttons)
			b.update();
	}
	
	public void setLocations() {
		
		int partyInfoHeight = (int)(gameframe.windowHeight * partyMembersHMult);
		buttonHeight = partyInfoHeight/6;
		buttonSpace = buttonHeight/4;
		
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
