package Components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import MainGame.InputManager;
import MainGame.MainGame;
import MainGame.PartyMember;
import MainGame.gameframe;

//A bar that displays the current stats of the party.
//Can be updated by using setText and passing it the parsed HashMap of stats 

@SuppressWarnings("unused")
public class StatsBar extends Textbox {

	public int x;
	public int y;
	public int width;
	public int height;

	Button expandButton;
	String expandedText = "-";
	String collapsedText = "+";
	int normExpandButtonX;
	int normExpandButtonY;
	int expandButtonSize = 25;
	int tbWidth;
	public int partyWidth;
	
	ArrayList<Image> statImgs = new ArrayList<Image>();
	ArrayList<Integer> statImgLocs = new ArrayList<Integer>();

	public int partyHeight;

	PartyExpandPanel expandPanel = null;
	Textbox partyMembersTextbox;

	public StatsBar(String str, int width, int height, InputManager input) {
		this(str, 0, gameframe.windowHeight, width, height, input);
	}

	public StatsBar(String str, int x, int y, int width, int height, InputManager input) {
		super(str, x, y - (int)(height*1.38), (int)(width), height, input);
		initIcons();
		tbWidth = (int)(width);
		partyHeight = 3*height;
		partyWidth = (int)(width*0.25);
		SetFont(new Font("Arial", Font.PLAIN, 16));
		vTextBuffer -= 6;

		int partyX = gameframe.windowWidth - partyWidth - 3;
		int partyY = gameframe.windowHeight - (int)(partyHeight * 1.125);
		partyMembersTextbox = new Textbox(getPartyText(), partyX, partyY, partyWidth, partyHeight, input);


		expandButtonSize = 25;
		normExpandButtonX = (width - expandButtonSize - 10);
		normExpandButtonY = (partyY + 5);
		expandButton = new Button(normExpandButtonX, normExpandButtonY, expandButtonSize, expandButtonSize, collapsedText, input) {
			@Override 
			public void onClick() {
				showHideExpandPanel();
			}
		};
	}
	
	private void initIcons() {
		try {
			Image ammoIcon = ImageIO.read(new File("assets/Icons/ammon icon.png"));
			Image foodIcon = ImageIO.read(new File("assets/Icons/food icon.png"));
			Image morIcon = ImageIO.read(new File("assets/Icons/morale icon.png"));
			Image stamIcon = ImageIO.read(new File("assets/Icons/stamina icon.png"));
			Image valIcon = ImageIO.read(new File("assets/Icons/valuables icon.png"));
			Image waterIcon = ImageIO.read(new File("assets/Icons/water icon.png"));
			Image medIcon = ImageIO.read(new File("assets/Icons/medicine icon.png"));
			
			statImgs.add(morIcon);
			statImgs.add(stamIcon);
			statImgs.add(foodIcon);
			statImgs.add(waterIcon);
			statImgs.add(ammoIcon);
			statImgs.add(medIcon);
			statImgs.add(valIcon);
			
			int pos = 20;
			int incAmt = 107;
			for(int i = 0; i < statImgs.size(); i++) {
				statImgLocs.add(pos);
				pos += incAmt;
			}
		} catch(IOException e) {
			System.out.println("Failed to load resource icon images...");
		}
	}

	private String getPartyText() {
		String intro = "Present Party: \n";
		for (PartyMember partyMember : MainGame.party) {
			intro += partyMember.getName() + "\n";
		}

		return intro;
	}

	public void showHideExpandPanel() {
		if(expandPanel == null) {
			showExpandPanel();
		} else {
			hideExpandPanel();
		}
	}

	public void showExpandPanel() {
		int createX = 980;
		int createY = this.drawRect.y;
		if(partyMembersTextbox != null) {
			createX = partyMembersTextbox.drawRect.x + partyMembersTextbox.drawRect.width;
			createY = partyMembersTextbox.drawRect.y;
		} 
		expandPanel = new PartyExpandPanel(createX, createY);
		expandButton.setText(expandedText);
	}

	public void hideExpandPanel() {
		expandPanel = null;
		expandButton.setText(collapsedText);
	}

	@Override
	public void setText(String stats) {
		textOrig = stats;
		lines = fitStr(stats);
		calculateFullHeight();
		checkNeedScroll();
	}

	public void showHidePartyPanel() {
		if (expandPanel == null) {
			showPartyPanel();
		} else {
			hidePartyPanel();
		}
	}

	public void showPartyPanel() {
		int partyX = gameframe.windowWidth - partyWidth - 3;
		int partyY = gameframe.windowHeight - (int) (partyHeight * 1.125);
		partyMembersTextbox = new Textbox(getPartyText(), partyX, partyY,
				partyWidth, partyHeight, input);
		
		expandButton.setLocation(new Point(normExpandButtonX, normExpandButtonY));
	}

	public void hidePartyPanel() {
		int buttonX = normExpandButtonX;
		int buttonY = this.drawRect.y + 10;
		expandButton.setLocation(new Point(buttonX, buttonY));
		
		partyMembersTextbox = null;
	}

	@Override
	public void update() {
		super.update();
		expandButton.update();
		if (partyMembersTextbox != null) {
			partyMembersTextbox.update();
		}
		if(expandPanel != null) {
			expandPanel.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (partyMembersTextbox != null) {
			partyMembersTextbox.draw(g);
		}
		expandButton.draw(g);
		if(expandPanel != null) {
			expandPanel.draw(g);
		}
		for(int i=0; i<statImgs.size(); i++) {
			g.drawImage(statImgs.get(i), statImgLocs.get(i), 723, 30, 30, null);
		}
	}
}
