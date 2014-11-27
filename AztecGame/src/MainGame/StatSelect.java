package MainGame;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import Components.*;

public class StatSelect implements DrawScreen {
	
	public int windowWidth = 1000;
	public int windowHeight = 800;
	
	public static int unused = 10;
	
	Textbox titleBox;
	Textbox unusedTextboxLabel;	
	static Textbox unusedTextboxValue;
	
	int titleTextboxWidth = 350;
	int titleTextboxHeight = 50;
	
	int spacer = 50;
	
	int statsListY = titleTextboxHeight + 40;
	int statsListX = 25;
	
	int unusedTextboxHeight = 40;
	int unusedTextboxLabelWidth = (int)(windowWidth * (1.0/4.0));
	int unusedTextboxValueWidth = (int)(windowWidth * (1.0/25.0));
	int unusedTextboxX = 35;
	int unusedTextboxY = statsListY + 6 * (40 + spacer) + spacer + 30;
	
	ArrayList<StatEntryField> statsEntryItems = new ArrayList<StatEntryField>();
	
	public StatSelect() {
		titleBox = new Textbox("Select Gentleman's Stats",
				windowWidth/2 - titleTextboxWidth/2, 10,
				titleTextboxWidth, titleTextboxHeight, IntroSequence.input);
		titleBox.SetFont(new Font("Georgia", Font.PLAIN, 30));
		titleBox.textBuffer = 10;
		
		unusedTextboxLabel = new Textbox("Unused: ", unusedTextboxX, unusedTextboxY, unusedTextboxLabelWidth, unusedTextboxHeight, IntroSequence.input);
		//unusedTextbox.SetFont(new Font("Georgia", Font.PLAIN, 30));
		unusedTextboxLabel.textBuffer = 5;
		
		int yVal = statsListY;
		for(String stat : PartyMemberStats.AVERAGE_ABE_STATS.keySet()) {
			if(stat.equals("Loyalty")) continue;
			StatEntryField field = new StatEntryField(stat, PartyMemberStats.AVERAGE_ABE_STATS.get(stat), PartyMemberStats.STAT_DESCRIPTIONS.get(stat), statsListX, yVal);
			statsEntryItems.add(field);
			yVal += field.myHeight + spacer;
		}
		
	}
	
	public void draw(Graphics g) {
		titleBox.draw(g);
		unusedTextboxLabel.draw(g);
		for(StatEntryField field : statsEntryItems) {
			field.draw(g);
		}
	}
	
	public boolean update() {
		titleBox.update();
		unusedTextboxLabel.update();
		for(StatEntryField field : statsEntryItems) {
			field.update();
		}
		return false;
	}
	
	public void finish() {
		for(StatEntryField field : statsEntryItems) {
			field.dispose();
		}
	}
	
	public static void UpdateUnused(int val) {
		unused = val;
		unusedTextboxValue.setText(val + "");
	}
	
}
