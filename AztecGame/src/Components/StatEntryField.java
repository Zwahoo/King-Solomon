package Components;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import MainGame.IntroSequence;
import MainGame.StatSelectScreen;

public class StatEntryField {
	
	private int windowWidth = 1000;
	private int windowHeight = 800;
	
	public String statName;
	String statDesc;
	public int myVal = 0;
	
	Point loc;
	
	Textbox label;
	Textbox val;
	Button plus;
	Button minus;
	Textbox desc;
	
	public int myHeight = 40;
	private int spacer = 10;
	private int buttonSpacer = 3;
	private int buttonHeight = (myHeight/2 - buttonSpacer/2);
	
	private int widthOfLabel = (int)(windowWidth * (1.0/4.0));
	private int widthOfVal = (int)(windowWidth * (1.0/20.0));
	private int widthOfButtons = 25;
	private int widthOfDesc = (int)(windowWidth * (1.0/1.75));
	
	
	public StatEntryField(String statName, int initValue, String statDesc, int x, int y) {
		this.myVal = initValue;
		this.statName = statName;
		this.statDesc = statDesc;
		this.loc = new Point(x, y);
		initComponents();
	}
	
	public void initComponents() {
		int currentX = loc.x + spacer;
		label = new Textbox(statName + ":", currentX, loc.y, widthOfLabel, myHeight, IntroSequence.input);
		currentX += widthOfLabel + spacer;
		val = new Textbox(myVal + "", currentX, loc.y, widthOfVal, myHeight, IntroSequence.input);
		currentX += widthOfVal + spacer;
		
		plus = new Button(currentX, loc.y, widthOfButtons, buttonHeight, "+", IntroSequence.input) {
			@Override
			public void onClick() {
				changeStat(1);
			}
		};
		minus = new Button(currentX, loc.y + buttonHeight + buttonSpacer, widthOfButtons, buttonHeight, "-", IntroSequence.input) {
			@Override
			public void onClick() {
				changeStat(-1);
			}
		};
		
		currentX += widthOfButtons + spacer;
		
		desc = new Textbox(statDesc, currentX, loc.y, widthOfDesc, myHeight, IntroSequence.input);
	}
	
	
	public void changeStat(int changeAmt) {
		if(myVal + changeAmt < 0) return;
		if(StatSelectScreen.unused - changeAmt < 0) return;
		
		StatSelectScreen.UpdateUnused(StatSelectScreen.unused - changeAmt);
		myVal += changeAmt;
		val.setText(myVal + "");
	}
	
	
	public void dispose() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(plus,modesList);
		IntroSequence.input.removeInputListener(minus, modesList);
	}

	public void draw(Graphics g) {
		label.draw(g);
		val.draw(g);
		plus.draw(g);
		minus.draw(g);
		desc.draw(g);
	}

	public void update() {
		label.update();
		val.update();
		plus.update();
		minus.update();
		desc.update();
	}
	
	
	
}
