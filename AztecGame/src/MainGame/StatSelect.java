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
	
	int titleTextboxWidth = 350;
	int titleTextboxHeight = 50;
	
	int spacer = 50;
	
	int statsListY = titleTextboxHeight + 40;
	int statsListX = 5;
	
	ArrayList<StatEntryField> statsEntryItems = new ArrayList<StatEntryField>();
	
	public StatSelect() {
		titleBox = new Textbox("Select Gentleman's Stats",
				windowWidth/2 - titleTextboxWidth/2, 10,
				titleTextboxWidth, titleTextboxHeight, IntroSequence.input);
		titleBox.SetFont(new Font("Georgia", Font.PLAIN, 30));
		titleBox.textBuffer = 10;
		
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
		for(StatEntryField field : statsEntryItems) {
			field.draw(g);
		}
	}
	
	public boolean update() {
		titleBox.update();
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
	
}
