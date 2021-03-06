package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import Components.Button;
import Components.Textbox;

@SuppressWarnings("unused")
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
	//Play sounds on neutral/lose/win.
	public static boolean defaultPlayJingles = true;

	public boolean playJingles;

	//image properties
	BufferedImage image;
	int imageBorderSize = 5;
	Color imageBorderCol = new Color(80, 50, 50);

	//other 3 parts
	Textbox info;
	Textbox partyMembersTextbox;
	Textbox adviceBox;
	Textbox result;
	ArrayList<Button> buttons = new ArrayList<Button>();
	Button advice;
	Button exit;

	/*
	 * fractions that represent how much of the window's height and width each 
	 * section will take up.
	 * Done this way (and not hardcoded) so that if window size changes it doesn't ruin everything
	 */
	double infoTextboxWMult = 0.8;
	double infoTextboxHMult = .17;
	double partyMembersWMult = .25;
	double partyMembersHMult = .29;
	double imageWMult = .8;
	double imageHMult = .35;
	double buttonWMult = .53;
	double adviceButtonWMult = .21;
	double adviceButtonHMult = .05;
	double resultTextboxWMult = 0.8;
	double resultTextboxHMult = .49;
	double exitButtonWMult = .21;
	double exitButtonHMult = .05;
	double spacer = .015; // standard spacing between most parts of event menu
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
	int resultTextboxX;
	int resultTextboxY;
	int exitButtonX;
	int exitButtonY;

	//list of present party members
	ArrayList<PartyMember> izDaParty = new ArrayList<PartyMember>();

	/*
	 * @param	toLaunch: event being created
	 * @param	presMembers: current party members for this event
	 */
	public EventDrawer(Event toLaunch, ArrayList<PartyMember> presMembers) {
		this(toLaunch, presMembers, null);
	}

	public EventDrawer(Event toLaunch, ArrayList<PartyMember> presMembers, PartyMember toSelect) {	
		/*currently just for testing will eventually be a parameter to the event? Not sure and don't want to change
		 *MapToEvent method without consulting Jackson
		 */
		String imageLoc = "assets/sunset.png";
		playJingles = toLaunch.playJingles;
		try {
			image = ImageIO.read(new File(imageLoc));
		} catch (IOException e) {
			System.out.println("Couldn't find image: " + imageLoc + " for event. Breaking everything.");
			image = null;
		}
		setLocations();
		launchEvent(toLaunch, presMembers, toSelect);
	}

	//initializes all parts of the event menu
	public void launchEvent(Event toLaunch, ArrayList<PartyMember> presMembers, PartyMember toSelect) {

		if (toLaunch.isPartyMemberTargeted()){
			if(toSelect != null) {
				toLaunch.setAffectedPartyMember(toSelect);
			}
			else {
				boolean couldTarget = toLaunch.setAffectedPartyMemberRandomly(presMembers);
				if(!couldTarget)
				{
					return; //Can't select needed target. Don't launch event.
				}
			}
		}

		info = new Textbox(toLaunch.getIntroText(), infoTextboxX, infoTextboxY, (int)(gameframe.windowWidth*infoTextboxWMult), (int)(gameframe.windowHeight*infoTextboxHMult), MainGame.input);
		izDaParty = presMembers;
		partyMembersTextbox  = new Textbox(getPresentPartyMembers(presMembers), partyMembersX, partyMembersY, (int)(gameframe.windowWidth*partyMembersWMult), (int)((gameframe.windowHeight*partyMembersHMult) - (2*gameframe.windowHeight * spacer)), MainGame.input);
		adviceBox = new Textbox(parseAdvice(toLaunch.getAdvice(), presMembers), partyMembersX, partyMembersY, (int)(gameframe.windowWidth*partyMembersWMult), (int)((gameframe.windowHeight*partyMembersHMult) - (2*gameframe.windowHeight * spacer)), MainGame.input);
		result = new Textbox("", resultTextboxX, resultTextboxY, (int) (gameframe.windowWidth*resultTextboxWMult), 
				(int)(gameframe.windowHeight*resultTextboxHMult), MainGame.input);
		info.setVisibility(true); partyMembersTextbox.setVisibility(true);
		int totalY = upperButtonY;	//variably keeps track of where the button should be drawn based on #buttons already drawn
		//create a button for each response option for this specific event (max is 5)
		for (final ResponseOption ro : toLaunch.getResponseOptions()) {
			boolean isResponsePossible = MainGame.isResponsePossible(ro);

			if (isResponsePossible){
				Button temp = new Button(buttonX, totalY, (int)(gameframe.windowWidth * buttonWMult), buttonHeight, ro.getText(), MainGame.input) {
					@Override
					public void onClick() {
						handleResponseSelect(ro); //custom method for handling button press, currently just closes program
					}
				};
				buttons.add(temp);			
				totalY += (buttonSpace + buttonHeight);
			}
			else if (!isResponsePossible){
				Button temp = new Button(buttonX, totalY, (int)(gameframe.windowWidth * buttonWMult), buttonHeight, ro.getText(), MainGame.input) {
					@Override
					public void onClick() {
					}
				};
				Color impossCol = new Color(200, 200, 200);
				Color impossBor = new Color(100, 70, 70);
				temp.setColor(impossCol, impossBor);
				temp.setImpossible(true);
				buttons.add(temp);			
				totalY += (buttonSpace + buttonHeight);
			}
		}
		advice = new Button(adviceButtonX, adviceButtonY, (int)(gameframe.windowWidth * partyMembersWMult), (int)(gameframe.windowHeight * adviceButtonHMult), "Advice", MainGame.input) {
			@Override
			public void onClick() {
				handleAdviceResponseSelect();
			}
		};
		buttons.add(advice);	
	}

	//currently closes program, but will eventually check which button was pressed then execute the proper result based on 
	//that response option
	public void handleResponseSelect(ResponseOption r) {
		ArrayList<Integer> totalPartyStats = new ArrayList<Integer>();
		ArrayList<PartyMember> partyMembers = new ArrayList<PartyMember>();

		String[] keyArray = { PartyMember.MARKSMANSHIP_KEY, PartyMember.PERCEPTION_KEY, PartyMember.TACTICS_KEY,
				PartyMember.LOYALTY_KEY, PartyMember.AGILITY_KEY, PartyMember.STRENGTH_KEY, PartyMember.DIPLOMACY_KEY,
				PartyMember.KNOWLEDGE_KEY, };

		partyMembers.addAll(MainGame.getParty());
		for (int c = 0; c < 8; c++) {
			int stat = 0;
			for (int i = 0; i < partyMembers.size(); i++) {
				stat += partyMembers.get(i).getStat(keyArray[c]);
			}
			totalPartyStats.add(stat);
		}



		int resultNumber = EventHandler.checkResponse(r, MainGame.getStats(), totalPartyStats);
		adviceBox.setVisibility(false); partyMembersTextbox.setVisibility(false); info.setVisibility(false);
		setResultText(r, resultNumber);
		result.setVisibility(true);

		MainGame.responseEffect(resultNumber, r);
		//MainGame.closeEvent();
	}

	//toggles between party members screen and advice screen
	public void handleAdviceResponseSelect() {
		if (partyMembersTextbox.getVisibility()) {
			partyMembersTextbox.setVisibility(false);
			adviceBox.setVisibility(true);
			advice.setText("Return");
		}
		else {
			partyMembersTextbox.setVisibility(true);
			adviceBox.setVisibility(false);
			advice.setText("Advice");
		}
		//MainGame.closeEvent();
	}

	public void draw(Graphics g) {
		drawImage(g);
		info.draw(g);
		if (partyMembersTextbox.getVisibility()) {
			partyMembersTextbox.draw(g);
		}
		if (adviceBox.getVisibility()) {
			adviceBox.draw(g);
		}
		if (result.getVisibility()) {
			result.draw(g);
		}
		for (int i=0; i<buttons.size(); i++) {
			Button b = buttons.get(i);
			b.draw(g);
		}
	}

	//draws image with rectangular background like everything else is drawn
	public void drawImage(Graphics g) {
		int borderWidth = (int) (gameframe.windowWidth * imageWMult);
		int borderHeight = (int) (gameframe.windowHeight * imageHMult);
		int imageWidth = borderWidth - (imageBorderSize * 2);
		int imageHeight = borderHeight - (imageBorderSize * 2);

		g.setColor(imageBorderCol);
		g.fillRect(imageX, imageY, borderWidth, borderHeight);
		g.drawImage(image, imageX + imageBorderSize, imageY + imageBorderSize, imageWidth, imageHeight, null);

	}

	public void update() {
		info.update();
		partyMembersTextbox.update();
		adviceBox.update();
		for (Button b : buttons) {
			b.update();
		}
	}

	//initializes x and y locs based on window size
	public void setLocations() {

		int partyInfoHeight = (int)(gameframe.windowHeight * ((partyMembersHMult + adviceButtonHMult) - spacer));
		buttonHeight = partyInfoHeight/6;	//divide by six because we want space between buttons to sum to size of a 6th button
		buttonSpace = buttonHeight/4;		//divide by four because four spaces between all buttons

		int totalHeight = (int) ((gameframe.windowHeight * spacer));

		imageX = (int) (gameframe.windowWidth * sideSpacer);
		imageY = totalHeight;
		totalHeight += gameframe.windowHeight * (imageHMult + spacer);

		infoTextboxX = imageX;
		infoTextboxY = totalHeight;

		resultTextboxX = imageX;
		resultTextboxY = totalHeight;
		totalHeight += gameframe.windowHeight * (infoTextboxHMult + spacer);

		partyMembersX = imageX;
		partyMembersY = totalHeight;


		buttonX = (int) (partyMembersX + (gameframe.windowWidth * (partyMembersWMult + spacer)));
		upperButtonY = totalHeight;

		adviceButtonX = (partyMembersX);
		adviceButtonY = (int) ((partyMembersY + ((gameframe.windowHeight * partyMembersHMult) - 
				((gameframe.windowHeight * spacer) * 1.5))));
		/*
		adviceButtonX = (int) (partyMembersX + gameframe.windowWidth * spacer);
		adviceButtonY = (int) ((partyMembersY + ((gameframe.windowHeight * partyMembersHMult) - (gameframe.windowHeight * adviceButtonHMult))) - 
				(gameframe.windowHeight * spacer));
		 */

		exitButtonX = (int) (resultTextboxX + (gameframe.windowWidth*spacer));
		exitButtonY = (int) ((resultTextboxY + ((gameframe.windowHeight*resultTextboxHMult) - (gameframe.windowHeight*exitButtonHMult))) - 
				(gameframe.windowHeight*spacer));
	}

	//prints all present party members to party members section
	public String getPresentPartyMembers(ArrayList<PartyMember> partyMembers) {
		String intro = "Present Party: \n";
		for (PartyMember partyMember : partyMembers) {
			intro += partyMember.getName() + "\n";
		}

		return intro;	
	}

	//method that checks which party members are in the pres party and then printing their respective advice
	public String parseAdvice(HashMap<String, String> advice, ArrayList<PartyMember> presMembers) {
		/*String ret = "";
		ret += "Run\n";
		ret += "Hide\n";
		ret += "Set up a system of organized trade\n";
		return ret; */

		ArrayList<String> useful = new ArrayList<String>();
		ArrayList<String> notUseful = new ArrayList<String>();
		String currAdvice = "";

		for(PartyMember member : presMembers) {
			String key = member.getName();
			if(!advice.containsKey(key)) {
				key = member.getType();
			}
			if (advice.containsKey(key)) {
				currAdvice = advice.get(key);
				if (currAdvice.charAt(0) == 'U') {
					useful.add(member.getName() + " says: \"" + currAdvice.substring(1) + "\"");
				} else {
					notUseful.add(member.getName() + " says: \"" + currAdvice.substring(1) + "\"");
				}
			}
		}

		Random r = new Random();
		int selection;
		if (useful.size() > 0) {
			selection = r.nextInt(useful.size());
			return useful.get(selection);
		}
		else if(notUseful.size() > 0){
			selection = r.nextInt(notUseful.size());
			return notUseful.get(selection);
		}
		return "No Advice Available";


	}

	public void setResultText(final ResponseOption ro, final int resultNumber) {
		if (resultNumber == 0) {
			result.setText(ro.getLoseText());
		} else if (resultNumber == 1) {
			result.setText(ro.getPassText());
		} else {
			result.setText(ro.getWinText());
		}

		//Play the sound
		if(playJingles) {
			if (resultNumber == 0) {
				Sound resultSound = new Sound("assets/sounds/Bad Event Count.wav", false);
			} else if (resultNumber == 1) {
				Sound resultSound = new Sound("assets/sounds/Neutral Event Outcome.wav", false);
			} else {
				Sound resultSound = new Sound("assets/sounds/Good Event Outcome.wav", false);
			}
		}


		clearButtons();
		buttons.clear();
		exit = new Button(exitButtonX, exitButtonY, (int)(gameframe.windowWidth*exitButtonWMult), (int)(gameframe.windowHeight*exitButtonHMult), "Exit", MainGame.input) {
			@Override
			public void onClick() {
				//System.out.println("I was clicked! YAY!");
				if (resultNumber == 1){
					MainGame.closeEvent();
				}
				else if (resultNumber == 0){
					if (ro.loseFollowUp.length() > 3){
						//System.out.println("I (apparently) have a follow up");
						int index = ro.loseFollowUp.lastIndexOf("\\");
						String cutLoseFollowUp = "assets/events/" + ro.loseFollowUp.substring(index+1,ro.loseFollowUp.length());
						HashMap <String, Object> izDaMap= FileToMap.createMap(cutLoseFollowUp);
						Event izDaEvent = MapToEvent.createEvent(izDaMap);
						destroyer();

						MainGame.eventDrawer = null;
						MainGame.launchEvent(izDaEvent, izDaParty);
					}
					else{
						//System.out.println("Imma close it.");
						MainGame.closeEvent();
						//System.out.println("something went right...");
					}

				}
				else{

					if (ro.winFollowUp.length() > 3){
						//System.out.println("I (apparently) have a follow up");
						int index = ro.winFollowUp.lastIndexOf("\\");
						String cutWinFollowUp = "assets/events/" + ro.winFollowUp.substring(index+1,ro.winFollowUp.length());
						HashMap <String, Object> izDaMap= FileToMap.createMap(cutWinFollowUp);
						Event izDaEvent = MapToEvent.createEvent(izDaMap);
						destroyer();
						MainGame.eventDrawer = null;
						MainGame.launchEvent(izDaEvent, izDaParty);
					} else {
						//System.out.println("Imma close it.");
						MainGame.closeEvent();
						//System.out.println("HHHHH yay");
					}

				}
			}
		};
		buttons.add(exit);
	}

	public void clearButtons() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.START_DAY_MODE); temp.add(MainGame.MOVEMENT_MODE); temp.add(MainGame.EVENT_MODE);
		for(Button button : buttons) {
			button.removeInputManager(MainGame.input, temp);
		}
	}

	public void destroyer() {
		clearButtons();
	}
}
