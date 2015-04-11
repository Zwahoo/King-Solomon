package IntroSequence2;

import java.awt.Graphics;

import Components.*;
import MainGame.InputManager;

public class GentlemanStatView {
	
	Textbox nameText;
	Textbox defaultText;
	Textbox primaryText;
	Textbox secondaryText;
	Textbox totalText;
	
	InputManager input;
	
	String statName;
	int defaultVal, primaryMod, secondaryMod;
	public int total;
	int x, y;
	
	public int height = 40;
	public int nameWidth = 200;
	public int numBoxWidth = 70;
	public int xSpacer = 5;
	
	public GentlemanStatView(String statName, int defaultVal, int x, int y, InputManager input) {
		this(statName, defaultVal, 0, 0, x, y, 40, input);
	}
	public GentlemanStatView(String statName, int defaultVal, int primaryMod, int secondaryMod, int x, int y, InputManager input) {
		this(statName, defaultVal, 0, 0, x, y, 40, input);
	}
	public GentlemanStatView(String statName, int defaultVal, int primaryMod, int secondaryMod, int x, int y, int height, InputManager input) {
		this.statName = statName;
		this.defaultVal = defaultVal;
		this.primaryMod = primaryMod;
		this.secondaryMod = secondaryMod;
		this.height = height;
		this.total = this.defaultVal + this.primaryMod + this.secondaryMod;
		this.x = x;
		this.y = y;
		this.input = input;
		
		setupTextboxes();
	}
	
	private void setupTextboxes() {
		int xPos = x;
		nameText = new Textbox(statName, xPos, y, nameWidth, height, input);
		xPos += nameWidth + xSpacer;
		defaultText = new Textbox(defaultVal + "", xPos, y, numBoxWidth, height, input);
		xPos += numBoxWidth + xSpacer;
		primaryText = new Textbox(primaryMod + "", xPos, y, numBoxWidth, height, input);
		xPos += numBoxWidth + xSpacer;
		secondaryText = new Textbox(secondaryMod + "", xPos, y, numBoxWidth, height, input);
		xPos += numBoxWidth + xSpacer;
		totalText = new Textbox(total + "", xPos, y, numBoxWidth, height, input);
	}
	
	public void update() {
		nameText.update();
		defaultText.update();
		primaryText.update();
		secondaryText.update();
		totalText.update();
	}
	
	public void draw(Graphics g) {
		nameText.draw(g);
		defaultText.draw(g);
		primaryText.draw(g);
		secondaryText.draw(g);
		totalText.draw(g);
	}

	public void setDefault(int val) {
		defaultVal = val;
		updateTotal();
	}
	
	public void setPrimary(int val) {
		primaryMod = val;
		updateTotal();
	}

	public void setSecondary(int val) {
		secondaryMod = val;
		updateTotal();
	}
	
	public void updateTotal() {
		total = defaultVal + primaryMod + secondaryMod;
		refreshTextboxes();
	}
	
	public void refreshTextboxes() {
		nameText.setText(statName);
		primaryText.setText(primaryMod + "");
		secondaryText.setText(secondaryMod + "");
		totalText.setText(total + "");
	}
}
