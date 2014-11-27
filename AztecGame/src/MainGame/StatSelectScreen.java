package MainGame;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import Components.*;

public class StatSelectScreen implements DrawScreen {
	
	public int windowWidth = 1000;
	public int windowHeight = 800;
	
	public static int unused = 10;
	
	Textbox titleBox;
	Textbox unusedTextboxLabel;	
	static Textbox unusedTextboxValue;
	Button nextButton;
	
	int titleTextboxWidth = 350;
	int titleTextboxHeight = 50;
	
	int spacer = 50;
	
	int statsListY = titleTextboxHeight + 40;
	int statsListX = 25;
	
	int unusedTextboxHeight = 40;
	int unusedTextboxLabelWidth = (int)(windowWidth * (1.0/4.0));
	int unusedTextboxValueWidth = (int)(windowWidth * (1.0/20.0));
	int unusedTextboxX = 35;
	int unusedTextboxY = statsListY + 6 * (40 + spacer) + spacer + 30;
	
	int nextBtnWidth = (int)(windowWidth * (1.0/4.0));
	int nextBtnHeight = unusedTextboxHeight;
	int nextBtnX = windowWidth - nextBtnWidth - 40;
	int nextBtnY = unusedTextboxY;
	
	ArrayList<StatEntryField> statsEntryItems = new ArrayList<StatEntryField>();
	
	public HashMap<String, Integer> gentStats = new HashMap<String, Integer>();
	
	private boolean finished = false;
	
	public StatSelectScreen() {
		unused = 10;
		
		titleBox = new Textbox("Select Gentleman's Stats",
				windowWidth/2 - titleTextboxWidth/2, 10,
				titleTextboxWidth, titleTextboxHeight, IntroSequence.input);
		titleBox.SetFont(new Font("Georgia", Font.PLAIN, 30));
		titleBox.textBuffer = 10;
		
		unusedTextboxLabel = new Textbox("Unused: ", unusedTextboxX, unusedTextboxY, unusedTextboxLabelWidth, unusedTextboxHeight, IntroSequence.input);
		unusedTextboxLabel.textBuffer = 5;
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
		titleBox.draw(g);
		unusedTextboxLabel.draw(g);
		unusedTextboxValue.draw(g);
		nextButton.draw(g);
		for(StatEntryField field : statsEntryItems) {
			field.draw(g);
		}
	}
	
	@Override
	public boolean update() {
		titleBox.update();
		unusedTextboxLabel.update();
		unusedTextboxValue.update();
		nextButton.update();
		for(StatEntryField field : statsEntryItems) {
			field.update();
		}
		if(finished) {
			gentStats.clear();
			for(StatEntryField field : statsEntryItems) {
				gentStats.put(field.statName, field.myVal);
			}
			gentStats.put(PartyMember.LOYALTY_KEY, 0);
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
	
	public static void UpdateUnused(int val) {
		unused = val;
		unusedTextboxValue.setText(val + "");
	}
	
	
	
}
