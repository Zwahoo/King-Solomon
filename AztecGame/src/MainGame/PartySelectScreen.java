package MainGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Components.Button;
import Components.PartySelectButton;

public class PartySelectScreen implements DrawScreen {
	
	//How much money the player has left to spend on hiring people.
	public static int money;

	//Spacing between buttons.
	int hSpacer = 20;
	int vSpacer = 20;
	
	//Row and column number of party members
	int numRows;
	int numCols;
	
	//Widths and heights of the party member buttons.
	int selectBtnWidth;
	int selectBtnHeight;
	
	//Has the finish button been clicked?
	boolean finished = false;
	
	//List of the party selection buttons.
	public ArrayList<PartySelectButton> partySelectors = new ArrayList<PartySelectButton>();
	
	//Background image for the scene.
	BufferedImage bkgImg;
	
	//Button to finish selection.
	Button finishButton;

	//The border on either side of the window before the buttons start/end.
	int hBorders = 50;
	
	//Dimensions for the finish button.
	int finishWidth = 200;
	int finishHeight = 40;
	int finishX = gameframe.windowWidth - finishWidth - hBorders;
	int finishY = gameframe.windowHeight - finishHeight - hBorders;
	
	//The top and bottom borders in which to fit party select buttons.
	int upperBorder = 90;
	int lowerBorder = gameframe.windowHeight - (finishY - vSpacer); //Need to leave room for "next" button and funds.
	
	/**
	 * Sets the money, loads in background image, initializes all controls.
	 */
	public PartySelectScreen() {
		money = 600;
		
		try{
			bkgImg = ImageIO.read(new File("assets/SelectPartyScreen.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		initializeSelectors();
		finishButton = new Button(finishX, finishY, finishWidth, finishHeight, "Finish", IntroSequence.input) {
			@Override
			public void onClick() {
				finished = true;
			}
		};
	}
	
	/**
	 * Calculates the best fit size and arrangement of party selection buttons.
	 * Then creates the buttons and adds them to the list.
	 */
	public void initializeSelectors() {
		int numMembers = PartyMemberStats.possibleParty.size();
		
		//Try and make a square grid of buttons.
		numRows = (int) Math.floor(Math.sqrt(numMembers));
		numCols = (int) Math.ceil((double)numMembers/(double)numRows);
		
		//TotalW and TotalH are the amount of space to fill with buttons.
		int totalW = gameframe.windowWidth - 2*hBorders - hSpacer*(numCols - 1);
		int totalH = gameframe.windowHeight - upperBorder - lowerBorder - vSpacer*(numRows - 1);
		//Calculate optimal sizing.
		selectBtnWidth = (int)Math.floor(totalW/numCols);
		selectBtnHeight = (int)Math.floor(totalH/numRows);
		
		//Add the member selectors.
		int curX = hBorders;
		int curY = upperBorder;
		for(PartyMember member : PartyMemberStats.possibleParty.values()) {
			PartySelectButton selector = new PartySelectButton(member, curX, curY, selectBtnWidth, selectBtnHeight);
			partySelectors.add(selector);
			curX += selectBtnWidth + hSpacer;
			if((curX + selectBtnWidth) > (gameframe.windowWidth - hBorders)) {
				curY += selectBtnHeight + vSpacer;
				curX = hBorders;
			}
		}
	}
	
	public HashMap<String, PartyMember> getSelectedParty() {
		HashMap<String, PartyMember> toRet = new HashMap<String, PartyMember>();
		for(PartySelectButton selector : partySelectors) {
			if(selector.hired) {
				toRet.put(selector.myMember.getName(), selector.myMember);
			}
		}
		return toRet;
	}
	
	@Override
	public boolean update() {
		finishButton.update();
		for(PartySelectButton selector : partySelectors) {
			selector.update();
		}
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(bkgImg, 0, 0, gameframe.windowWidth, gameframe.windowHeight, null);
		finishButton.draw(g);
		for(PartySelectButton selector : partySelectors) {
			selector.draw(g);
		}
	}

	@Override
	public void finish() {
		for(PartySelectButton selector : partySelectors) {
			selector.destroy();
		}
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(finishButton, modesList);
	}

}
