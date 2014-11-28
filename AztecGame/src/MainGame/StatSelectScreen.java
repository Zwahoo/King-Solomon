package MainGame;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Components.*;

public class StatSelectScreen implements DrawScreen {
	
	//The number of unused points remianing to distribute.
	public static int unused = 10;
	
	//The background image with title.
	BufferedImage bkgImg;
	//The textbox saying "Unused:"
	Textbox unusedTextboxLabel;	
	//The textbox containing the actual unused value.
	static Textbox unusedTextboxValue;
	//The button to progress to the next scene.
	Button nextButton;
	
	//Vertical Spacing between Rows.
	int spacer = 50;
	
	//Initial location for top row.
	int statsListY = 90;
	int statsListX = 25;
	
	//Dimensions for the unused textboxes.
	int unusedTextboxHeight = 40;
	int unusedTextboxLabelWidth = (int)(gameframe.windowWidth * (1.0/4.0));
	int unusedTextboxValueWidth = (int)(gameframe.windowWidth * (1.0/20.0));
	int unusedTextboxX = 35;
	int unusedTextboxY = statsListY + 6 * (40 + spacer) + spacer + 30;
	
	//Dimensions for the next button.
	int nextBtnWidth = (int)(gameframe.windowWidth * (1.0/4.0));
	int nextBtnHeight = unusedTextboxHeight;
	int nextBtnX = gameframe.windowWidth - nextBtnWidth - 40;
	int nextBtnY = unusedTextboxY;
	
	//List of stat entry rows.
	ArrayList<StatEntryField> statsEntryItems = new ArrayList<StatEntryField>();
	
	//Has the scene finished?
	private boolean finished = false;
	
	public StatSelectScreen() {
		unused = 10;
		
		try {
			bkgImg = ImageIO.read(new File("assets/SelectStatsScreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		unusedTextboxLabel = new Textbox("Unused: ", unusedTextboxX, unusedTextboxY, unusedTextboxLabelWidth, unusedTextboxHeight, IntroSequence.input);
		unusedTextboxLabel.hTextBuffer = 3;
		int txtValX = unusedTextboxX + unusedTextboxLabelWidth + 10;
		unusedTextboxValue = new Textbox(unused + "", txtValX, unusedTextboxY, unusedTextboxValueWidth, unusedTextboxHeight, IntroSequence.input);
		
		nextButton = new Button(nextBtnX, nextBtnY, nextBtnWidth, nextBtnHeight, "Next", IntroSequence.input) {
			@Override
			public void onClick() {
				finished = true;
			}
		};
		
		int yVal = statsListY;
		for(String stat : PartyMemberStats.AVERAGE_ABE_STATS.keySet()) {
			if(stat.equals("Loyalty")) continue;
			StatEntryField field = new StatEntryField(stat, PartyMemberStats.AVERAGE_ABE_STATS.get(stat), PartyMemberStats.STAT_DESCRIPTIONS.get(stat), statsListX, yVal);
			statsEntryItems.add(field);
			yVal += field.myHeight + spacer;
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(bkgImg, 0, 0, gameframe.windowWidth, gameframe.windowHeight, null);
		unusedTextboxLabel.draw(g);
		unusedTextboxValue.draw(g);
		nextButton.draw(g);
		for(StatEntryField field : statsEntryItems) {
			field.draw(g);
		}
	}
	
	@Override
	public boolean update() {
		unusedTextboxLabel.update();
		unusedTextboxValue.update();
		nextButton.update();
		for(StatEntryField field : statsEntryItems) {
			field.update();
		}
		
		return finished;
	}
	
	@Override
	public void finish() {
		for(StatEntryField field : statsEntryItems) {
			field.dispose();
		}
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(nextButton, modesList);
	}
	
	/**
	 * Updates the value of unusued, and the corresponding textbox.
	 * @param val: The new integer value for unused.
	 */
	public static void UpdateUnused(int val) {
		unused = val;
		unusedTextboxValue.setText(val + "");
	}

	/**
	 * Fills a valid stat list for the gentleman from user entered values.
	 */
	public HashMap<String, Integer> getGentStats() {
		HashMap<String, Integer> gentStats = new HashMap<String, Integer>();
		for(StatEntryField field : statsEntryItems) {
			gentStats.put(field.statName, field.myVal);
		}
		//Gentleman loyalty = 0 so as not to mess with event modifiers.
		//Note this is never displayed in game.
		gentStats.put(PartyMember.LOYALTY_KEY, 0);
		return gentStats;
	}
	
	
	
}
