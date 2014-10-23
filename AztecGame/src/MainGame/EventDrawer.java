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
	
	/*
	 * All events have four parts: Image, Event Info, Current Party Members, and Response Options
	 * Layout for event menu:			
	 * 										---------Image---------
	 * 										---------Image---------
	 * 										---------Image---------
	 * 										---------Image---------
	 * 										---------Image---------
	 * 										-------Event Info------
	 * 										-------Event Info------
	 * 										-------Event Info------
	 * 										-Cur Party--Response 1-
	 * 										-Cur Party--Response 2-
	 * 										-Cur Party--Response 3-
	 * 										-Cur Party--Response 4-
	 * 										-Cur Party--Response 5-
	 */
	
	//image properties
	BufferedImage image;
	int imageBorderSize = 5;
	Color imageBorderCol = new Color(80, 50, 50);
	
	//other 3 parts
	Textbox info;
	Textbox partyMembers;
	ArrayList<Button> buttons = new ArrayList<Button>();
	Button advice;
	
	/*
	 * fractions that represent how much of the window's height and width each 
	 * section will take up.
	 * Done this way (and not hardcoded) so that if window size changes it doesn't ruin everything
	 */
	double infoTextboxWMult = 0.8;
	double infoTextboxHMult = .17;
	double partyMembersWMult = .25;
	double partyMembersHMult = .30;
	double imageWMult = .8;
	double imageHMult = .35;
	double buttonWMult = .53;
	double adviceButtonWMult = .21;
	double adviceButtonHMult = .05;
	double spacer = .02;				//standard spacing between most parts of event menu
	double sideSpacer = .1;				//vertical spacing between edge of window and event menu (left and right side)
	
	int buttonHeight;
	int buttonSpace;					//special small spacing between the five buttons
	
	//x and y locs for all parts of event menu
	int infoTextboxX;
	int infoTextboxY;
	int partyMembersX;
	int partyMembersY;
	int imageX;
	int imageY;
	int buttonX;
	int upperButtonY;
	int adviceButtonX;
	int adviceButtonY;
	
	/*
	 * @param	toLaunch: event being created
	 * @param	presMembers: current party members for this event
	 */
	public EventDrawer(Event toLaunch, ArrayList<PartyMember> presMembers) {
		
		/*currently just for testing will eventually be a parameter to the event? Not sure and don't want to change
		*MapToEvent method without consulting Jackson
		*/
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
	
	//initializes all parts of the event menu
	public void launchEvent(Event toLaunch, ArrayList<PartyMember> presMembers) {
		info = new Textbox(toLaunch.getIntroText(), infoTextboxX, infoTextboxY, (int)(gameframe.windowWidth*infoTextboxWMult), (int)(gameframe.windowHeight*infoTextboxHMult), MainGame.input);
		partyMembers  = new Textbox(getPresentPartyMembers(presMembers), partyMembersX, partyMembersY, (int)(gameframe.windowWidth*partyMembersWMult), (int)(gameframe.windowHeight*partyMembersHMult), MainGame.input);
		int totalY = upperButtonY;	//variably keeps track of where the button should be drawn based on #buttons already drawn
		//create a button for each response option for this specific event (max is 5)
		for (ResponseOption ro : toLaunch.getResponseOptions()) {
			Button temp = new Button(buttonX, totalY, (int)(gameframe.windowWidth * buttonWMult), buttonHeight, ro.getText(), MainGame.input) {
				@Override
				public void onClick() {
					handleResponseSelect(); //custom method for handling button press, currently just closes program
				}
			};
			buttons.add(temp);			
			totalY += (buttonSpace + buttonHeight);
		}
		//I will/someone can tweak this later so that the button shows up in the right spot! Just wanted to get it out there
		advice = new Button(adviceButtonX, adviceButtonY, (int)(gameframe.windowWidth * adviceButtonWMult), (int)(gameframe.windowHeight * adviceButtonHMult), "Advice", MainGame.input) {
			@Override
			public void onClick() {
				handleAdviceResponseSelect();
			}
		};
		buttons.add(advice);
	}
	
	//currently closes program, but will eventually check which button was pressed then execute the proper result based on 
	//that response option
	public void handleResponseSelect() {
		MainGame.closeEvent();
	}
	
	//currently closes program as well
	public void handleAdviceResponseSelect() {
		MainGame.closeEvent();
	}
	
	public void draw(Graphics g) {
		drawImage(g);
		info.draw(g);
		partyMembers.draw(g);
		for (Button b : buttons)
			b.draw(g);
	}
	
	//draws image with rectangular background like everything else is drawn
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
	
	//initializes x and y locs based on window size
	public void setLocations() {
		
		int partyInfoHeight = (int)(gameframe.windowHeight * partyMembersHMult);
		buttonHeight = partyInfoHeight/6;	//divide by six because we want space between buttons to sum to size of a 6th button
		buttonSpace = buttonHeight/4;		//divide by four because four spaces between all buttons
		
		int totalHeight = (int) (gameframe.windowHeight * spacer + MainGame.statBarHeight);
		
		imageX = (int) (gameframe.windowWidth * sideSpacer);
		imageY = totalHeight;
		totalHeight += gameframe.windowHeight * (imageHMult + spacer);
		
		infoTextboxX = imageX;
		infoTextboxY = totalHeight;
		totalHeight += gameframe.windowHeight * (infoTextboxHMult + spacer);
		
		partyMembersX = imageX;
		partyMembersY = totalHeight;
		
		
		buttonX = (int) (partyMembersX + gameframe.windowWidth * (partyMembersWMult + spacer));
		upperButtonY = totalHeight;
		
		adviceButtonX = (int) (partyMembersX + gameframe.windowWidth * spacer);
		adviceButtonY = (int) ((partyMembersY + ((gameframe.windowHeight * partyMembersHMult) - (gameframe.windowHeight * adviceButtonHMult))) - 
				(gameframe.windowHeight * spacer));
	}
	
	//prints all present party members to party members section
	public String getPresentPartyMembers(ArrayList<PartyMember> partyMembers) {
		String intro = "Present Party: \n";
		for (PartyMember partyMember : partyMembers) {
			intro += partyMember.getName() + "\n";
		}
		
		return intro;	
	}
	
	
}
