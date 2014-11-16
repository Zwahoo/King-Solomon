package Components;

import java.awt.Point;
import java.util.ArrayList;

import MainGame.IntroSequence;

public class StatEntryField {
	
	private int windowWidth = 1000;
	private int windowHeight = 800;
	
	String statName;
	String statDesc;
	int statVal;
	
	Point loc;
	
	Textbox label;
	Textbox val;
	Button plus;
	Button minus;
	Textbox desc;
	
	private int myHeight = 50;
	private int buttonHeight = (myHeight/2 - 5);
	
	private int widthOfLabel = windowWidth * (1/4);
	private int widthOfVal = windowWidth * (1/20);
	private int widthOfPlus = 10;
	private int widthOfMinus = 10;
	private int widthOfDesc = windowWidth * (1/3);
	
	public StatEntryField(String statName, int initValue, String statDesc, int x, int y) {
		this.statVal = initValue;
		this.statName = statName;
		this.statDesc = statDesc;
		this.loc = new Point(x, y);
		initComponents();
	}
	
	
	
	public void initComponents() {
		label = new Textbox(statName, loc.x, loc.y, widthOfLabel, myHeight, IntroSequence.input);
		
	}
	
	public void dispose() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(plus,modesList);
		IntroSequence.input.removeInputListener(minus, modesList);
	}
	
	
	
}
