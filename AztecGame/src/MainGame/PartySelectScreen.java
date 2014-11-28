package MainGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Components.Button;
import Components.PartySelectButton;

public class PartySelectScreen implements DrawScreen {
	
	public static int money;

	int hSpacer = 20;
	int vSpacer = 20;
	
	//Row and column number of party members
	int numRows;
	int numCols;
	
	//Widths and heights of the party member buttons.
	int selectBtnWidth;
	int selectBtnHeight;
	
	boolean finished = false;
	
	public ArrayList<PartySelectButton> partySelectors = new ArrayList<PartySelectButton>();
	
	BufferedImage bkgImg;
	
	Button finishButton;

	int hBorders = 50;
	
	int finishWidth = 200;
	int finishHeight = 40;
	int finishX = gameframe.windowWidth - finishWidth - hBorders;
	int finishY = gameframe.windowHeight - finishHeight - hBorders;
	
	int upperBorder = 90;
	int lowerBorder = gameframe.windowHeight - (finishY - vSpacer); //Need to leave room for "next" button and funds.
	
	
	public PartySelectScreen() {
		money = 100;
		
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
	
	public void initializeSelectors() {
		int numMembers = PartyMemberStats.possibleParty.size();
		
		//Try and make a square grid of buttons.
		numRows = (int) Math.floor(Math.sqrt(numMembers));
		numCols = (int) Math.ceil((double)numMembers/(double)numRows);
		
		int totalW = gameframe.windowWidth - 2*hBorders - hSpacer*(numCols - 1);
		int totalH = gameframe.windowHeight - upperBorder - lowerBorder - vSpacer*(numRows - 1);
		selectBtnWidth = (int)Math.floor(totalW/numCols);
		selectBtnHeight = (int)Math.floor(totalH/numRows);
		
		
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
