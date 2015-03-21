package MainGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Components.Button;
import Components.PartySelectButton;
import Components.Textbox;

public class PartySelectScreen implements DrawScreen {
	
	//The minimum number of hired party members to play.
	public static int reqNumPartyMembers = 1;
	
	//How much money the player has left to spend on hiring people.
	private static int money;
	
	//Draw the selector boxes
	public static boolean drawSelectorButtons = true;
	
	//Number of selected members
	private static int numSelectedMembers = 0;

	//Currency display information.
	public static final String currencyLabel = "$";
	public static final boolean currencyLabelBefore = true;
	
	//Spacing between buttons.
	static int hSpacer = 20;
	static int vSpacer = 20;
	
	//Row and column number of party members
	int numRows;
	int numCols;
	
	//Widths and heights of the party member buttons.
	int selectBtnWidth;
	int selectBtnHeight;
	
	//Has the finish button been clicked?
	boolean finished = false;
	
	//List of the party selection buttons.
	public static ArrayList<PartySelectButton> partySelectors = new ArrayList<PartySelectButton>();
	
	//Background image for the scene.
	BufferedImage bkgImg;
	
	//Button to finish selection.
	static Button finishButton;
	static Textbox fundsTextbox;
	static String fundsLabel = "Funds: ";

	//The border on either side of the window before the buttons start/end.
	public static int hBorders = 50;
	
	//Dimensions for the finish button.
	static int finishWidth = 300;
	static int finishHeight = 40;
	static int finishX = gameframe.windowWidth - finishWidth - hBorders;
	static int finishY = gameframe.windowHeight - finishHeight - hBorders;
	
	//Dimensions for the funds textbox.
	int fundsWidth = 150;
	int fundsHeight = 40;
	int fundsTextHBuffer = 30;
	int fundsTextVBuffer = 5;
	int fundsX = hBorders;
	int fundsY = gameframe.windowHeight - finishHeight - hBorders;
	
	//The top and bottom borders in which to fit party select buttons.
	public static int upperBorder = 90;
	public static int lowerBorder = gameframe.windowHeight - (finishY - vSpacer); //Need to leave room for "next" button and funds.
	
	/**
	 * Sets the money, loads in background image, initializes all controls.
	 */
	public PartySelectScreen() {
		partySelectors.clear();
		
		money = 600;
		numSelectedMembers = 0;
		
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
		
		fundsTextbox = new Textbox(makeFundsString(), fundsX, fundsY, fundsWidth, fundsHeight, IntroSequence.input);
		fundsTextbox.hTextBuffer = fundsTextHBuffer;
		fundsTextbox.vTextBuffer = fundsTextVBuffer;
		setFinishButtonEnabled(false);
	}
	
	public static String makeFinishString() {
		String plur = "s";
		if(reqNumPartyMembers == 1) {
			plur = "";
		}
		if(numSelectedMembers < reqNumPartyMembers) {
			return "Finish (Need at Least " + reqNumPartyMembers + " Party Member" + plur + ")";
		} else {
			return "Finish";
		}
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
		int totalW = gameframe.windowWidth - (2*hBorders) - (hSpacer*(numCols - 1));
		int totalH = gameframe.windowHeight - upperBorder - lowerBorder - (vSpacer*(numRows - 1));
		//Calculate optimal sizing.
		selectBtnWidth = (int)Math.floor(totalW/numCols);
		selectBtnHeight = (int)Math.floor(totalH/numRows);
		
		//Add the member selectors.
		int curX = hBorders;
		int curY = upperBorder;
		for(PartyMember member : PartyMemberStats.possibleParty.values()) {
			PartySelectButton selector = new PartySelectButton(member, curX, curY, selectBtnWidth, selectBtnHeight, IntroSequence.input);
			partySelectors.add(selector);
			curX += selectBtnWidth + hSpacer;
			if((curX + selectBtnWidth) > (gameframe.windowWidth - hBorders)) {
				curY += selectBtnHeight + vSpacer;
				curX = hBorders;
			}
		}
	}
	
	/**
	 * Creates a HashMap of all selected members (excluding The Gentleman).
	 */
	public HashMap<String, PartyMember> getSelectedParty() {
		HashMap<String, PartyMember> toRet = new HashMap<String, PartyMember>();
		for(PartySelectButton selector : partySelectors) {
			if(selector.hired) {
				toRet.put(selector.myMember.getName(), selector.myMember);
			}
		}
		return toRet;
	}
	
	public static String makeFundsString() {
		String before = fundsLabel;
		String after = "";
		if(currencyLabelBefore) {
		 	before += currencyLabel;
		}
		else {
			after = currencyLabel;
		}
		return (before + money + after);
	}
	

	public static void incMoney(int amt) {
		setMoney(money + amt);
	}
	public static void setMoney(int newVal) {
		money = newVal;
		fundsTextbox.setText(makeFundsString());
	}
	public static int getMoney() {
		return money;
	}
	
	public static void incNumSelectedMembers(int amt) {
		setNumSelectedMembers(numSelectedMembers + amt);
	}
	public static void setNumSelectedMembers(int newVal) {
		numSelectedMembers = newVal;
		finishButton.setText(makeFinishString());
	}
	public static int getNumSelectedMembers() {
		return numSelectedMembers;
	}
	
	@Override
	public boolean update() {
		finishButton.update();
		fundsTextbox.update();
		
		boolean blockFinish = false;
		for(PartySelectButton selector : partySelectors) {
			selector.update();
			if(selector.blockFinish) {
				blockFinish = true;
			}
		}
		if(!finishButton.isImpossible && (blockFinish || (numSelectedMembers < reqNumPartyMembers))) {
			setFinishButtonEnabled(false);
		}
		else if(finishButton.isImpossible && !blockFinish && (numSelectedMembers >= reqNumPartyMembers)) {
			setFinishButtonEnabled(true);
		}
		
		
		return finished;
	}
	
	public void setFinishButtonEnabled(boolean enable) {
		if(enable) {
			finishButton.enable();
		} else {
			finishButton.disable();
		}
		finishButton.setText(makeFinishString());
	}
	
	public static void setAllSelectorsEnabled(boolean enabled) {
		for(PartySelectButton selector : partySelectors) {
			selector.setMainButtonEnabled(enabled);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(bkgImg, 0, 0, gameframe.windowWidth, gameframe.windowHeight, null);
		finishButton.draw(g);
		fundsTextbox.draw(g);
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
