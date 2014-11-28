package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import MainGame.*;

public class PartySelectButton {
	
	//The member that this selector hires.
	public PartyMember myMember;
	
	//General Positioning Info
	int hBorder = PartySelectScreen.hBorders;
	int upperBorder = PartySelectScreen.upperBorder;
	int lowerBorder = PartySelectScreen.lowerBorder;
	
	int vSpacer = 10;
	int hSpacer = 10;
	
	//Don't let the player finish (used when displaying a member's info).
	public boolean blockFinish = false;
	
	//Display the more info stuff?
	public boolean dispMoreInfo = false;
	
	//Positioning and dimensions of the main button.
	int btnXLoc;
	int btnYLoc;
	int btnWidth;
	int btnHeight;
	
	//Has the member been hired?
	public boolean hired = false;
	
	//The button displayed in the main PartyStatSelectScreen (Umbopa, Hunter, $100).
	Button mainButton;
	
	Textbox nameBox;
	Textbox descBox;
	Textbox statsBox;
	Button hireButton;
	Button closeButton;
	BufferedImage memberImage;
	
	//Image Dimension Info
	Color imageBorderCol = new Color(80, 50, 50);
	int imageTotalWidth = (int)Math.floor(gameframe.windowWidth * (1.0/3.0));
	int imageTotalHeight = (int)Math.floor(gameframe.windowHeight * (1.0/3.0));
	int imageX = gameframe.windowWidth - hBorder - imageTotalWidth;
	int imageY = upperBorder;
	int imageBorderSize = 5;

	//Name Texbox Dimensions
	int nameWidth = gameframe.windowWidth - hBorder * 2 - hSpacer - imageTotalWidth;
	int nameHeight = 50;
	int nameX = hBorder;
	int nameY = upperBorder;

	//Member Stats Textbox Dimensions
	int statsWidth = imageTotalWidth;
	int statsHeight = gameframe.windowHeight - upperBorder - lowerBorder - vSpacer - imageTotalHeight;
	int statsX = imageX;
	int statsY = imageY + imageTotalHeight + vSpacer;
	
	//Close Button Dimensions
	int closeBtnWidth = 200;
	int closeBtnHeight = 40;
	int closeBtnX = hBorder;
	int closeBtnY = gameframe.windowHeight - lowerBorder - closeBtnHeight;
	
	//Member Description Textbox Dimensions
	int descWidth = gameframe.windowWidth - hBorder * 2 - hSpacer - imageTotalWidth;
	int descHeight = gameframe.windowHeight - lowerBorder - upperBorder - vSpacer*2 - closeBtnHeight - nameHeight;
	int descX = hBorder;
	int descY = upperBorder + nameHeight + vSpacer;

	//Hire Button Dimensions
	int hireBtnWidth = closeBtnWidth;
	int hireBtnHeight = closeBtnHeight;
	int hireBtnX = descX + descWidth - hireBtnWidth;
	int hireBtnY = closeBtnY;


	
	/**
	 * Sets up the main button.
	 */
	public PartySelectButton(PartyMember member, int btnXLoc, int btnYLoc, int btnWidth, int btnHeight) {
		this.myMember = member;
		this.btnXLoc = btnXLoc;
		this.btnYLoc = btnYLoc;
		this.btnWidth = btnWidth;
		this.btnHeight = btnHeight;
		memberImage = member.getImage();
		
		mainButton = new Button(btnXLoc, btnYLoc, btnWidth, btnHeight, makeButtonString(), IntroSequence.input) {
			@Override
			public void onClick() {
				if(!dispMoreInfo) launchMoreInfo();
			}
		};
		
		//Initialize textboxes now, only draw and update when displaying more info.
		nameBox = new Textbox(member.getName(), nameX, nameY, nameWidth, nameHeight, IntroSequence.input);
		descBox = new Textbox(member.getBackgroundInfo(), descX, descY, descWidth, descHeight, IntroSequence.input);
		statsBox = new Textbox(member.generateStatsString("\n"), statsX, statsY, statsWidth, statsHeight, IntroSequence.input);
	}
	
	public void launchMoreInfo() {
		blockFinish = true;
		PartySelectScreen.drawSelectorButtons = false;
		PartySelectScreen.setAllSelectorsEnabled(false);
		hireButton = new Button(hireBtnX, hireBtnY, hireBtnWidth, hireBtnHeight, getHireText(), IntroSequence.input) {
			@Override
			public void onClick() {
				toggleHire();
			}
		};
		closeButton = new Button(closeBtnX, closeBtnY, closeBtnWidth, closeBtnHeight, "Close", IntroSequence.input) {
			@Override
			public void onClick() {
				closeMoreInfo();
			}
		};
		dispMoreInfo = true;
	}
	
	public void closeMoreInfo() {
		dispMoreInfo = false;
		destroyMoreInfoSection();
		PartySelectScreen.setAllSelectorsEnabled(true);
		PartySelectScreen.drawSelectorButtons = true;
		blockFinish = false;
	}
	
	public String getHireText() {
		String before = "Hire (-";
		if(hired) before = "Fire (+";
		String after = ")";
		if(PartySelectScreen.currencyLabelBefore) {
			before += " " + PartySelectScreen.currencyLabel;
		} else {
			after = PartySelectScreen.currencyLabel + after;
		}
		return (before + myMember.getPay() + after);
	}
	
	public void toggleHire() {
		if(hired) fire();
		else hire();
	}
	
	public void update() {
		mainButton.update();
		if(dispMoreInfo) {
			nameBox.update();
			descBox.update();
			statsBox.update();
			hireButton.update();
			closeButton.update();
		}
	}

	public void draw(Graphics g) {
		if(PartySelectScreen.drawSelectorButtons) {
			mainButton.draw(g);
		}
		if(dispMoreInfo) {
			nameBox.draw(g);
			descBox.draw(g);
			statsBox.draw(g);
			hireButton.draw(g);
			closeButton.draw(g);
			drawImage(g);
		}
	}
	
	/**
	 * Hires the member.
	 */
	public void hire() {
		if(!hired && PartySelectScreen.getMoney() - myMember.getPay() >= 0) { 
			hired = true;
			PartySelectScreen.incMoney(-1*myMember.getPay());
			mainButton.setText(makeButtonString());
			if(hireButton != null) hireButton.setText(getHireText());
		}
	}
	
	/**
	 * Fires the member.
	 */
	public void fire() {
		if(hired) {
			hired = false;
			PartySelectScreen.incMoney(myMember.getPay());
			mainButton.setText(makeButtonString());
			if(hireButton != null) hireButton.setText(getHireText());
		}
	}
	
	/**
	 * Makes the string for the main button (Umbopa (Hired), Hunter, $100).
	 */
	public String makeButtonString() {
		String cost = (myMember.getPay() + "");
		if(PartySelectScreen.currencyLabelBefore) {
			cost = PartySelectScreen.currencyLabel + cost;
		} else {
			cost += PartySelectScreen.currencyLabel;
		}
		String note = "";
		if(hired) note = " (Hired)";
		return (myMember.getName() + note + "\n" + myMember.getType() + "\n" + cost);
	}
	
	//draws image with rectangular background like everything else is drawn
	public void drawImage(Graphics g) {
		int innerWidth = imageTotalWidth - imageBorderSize * 2;
		int innerHeight = imageTotalHeight - imageBorderSize * 2;
		
		g.setColor(imageBorderCol);
		g.fillRect(imageX, imageY, imageTotalWidth, imageTotalHeight);
		g.drawImage(memberImage, imageX + imageBorderSize, imageY + imageBorderSize, innerWidth, innerHeight, null);
	}
	
	
	private void destroyMoreInfoSection() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		if(hireButton != null) IntroSequence.input.removeInputListener(hireButton, modesList);
		if(closeButton != null) IntroSequence.input.removeInputListener(closeButton, modesList);
		hireButton = null;
		closeButton = null;
	}
	
	/**
	 * Destroy all components.
	 */
	public void destroy() {
		destroyMoreInfoSection();
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(mainButton, modesList);
	}

	public void setMainButtonEnabled(boolean enabled) {
		mainButton.setEnabled(enabled);
	}
}
