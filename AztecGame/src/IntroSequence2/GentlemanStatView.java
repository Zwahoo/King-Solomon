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
	
	public int height = 50;
	public int nameWidth = 200;
	public int numBoxWidth = 50;
	
	public GentlemanStatView(String statName, int defaultVal, int x, int y, InputManager input) {
		this(statName, defaultVal, 0, 0, x, y, input);
	}
	
	public GentlemanStatView(String statName, int defaultVal, int primaryMod, int secondaryMod, int x, int y, InputManager input) {
		this.statName = statName;
		this.defaultVal = defaultVal;
		this.primaryMod = primaryMod;
		this.secondaryMod = secondaryMod;
		this.total = this.defaultVal + this.primaryMod + this.secondaryMod;
		this.input = input;
		
		setupTextboxes();
	}
	
	private void setupTextboxes() {
		int xPos = x;
		nameText = new Textbox(statName, xPos, y, nameWidth, height, input);
		xPos += nameWidth;
		defaultText = new Textbox(defaultVal + "", xPos, y, numBoxWidth, height, input);
		xPos += numBoxWidth;
		primaryText = new Textbox(defaultVal + "", xPos, y, numBoxWidth, height, input);
		xPos += numBoxWidth;
		secondaryText = new Textbox(defaultVal + "", xPos, y, numBoxWidth, height, input);
		xPos += numBoxWidth;
		totalText = new Textbox(defaultVal + "", xPos, y, numBoxWidth, height, input);
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
