package Components;

import java.awt.Graphics;

import MainGame.InputManager;
import MainGame.MainGame;

//A bar that displays the current stats of the party.
//Can be updated by using setText and passing it the parsed HashMap of stats 

public class StatsBar extends Textbox {

	public int x;
	public int y;
	public int width;
	public int height;
	
	Button expandButton;
	String expandedText = "-";
	String collapsedText = "+";
	int expandButtonSize = 25;
	int expandButtonLeftBuffer = 5;
	int expandButtonUpBuffer = 5;
	
	PartyExpandPanel expandPanel = null;
	
	public StatsBar(String str, int width, int height, InputManager input) {
		this(str, 0, 0, width, height, input);
	}
	
	public StatsBar(String str, int x, int y, int width, int height, InputManager input) {
		super(str, x, y, width, height, input);
		this.width = width;
		this.height = height;
		
		int buttonX = x+width-expandButtonSize-expandButtonLeftBuffer;
		int buttonY = y + height/2 - expandButtonSize/2;
		expandButton = new Button(buttonX, buttonY, expandButtonSize, expandButtonSize, collapsedText, input) {
			@Override 
			public void onClick() {
				showHideExpandPanel();
			}
		};
	}
	
	public void showHideExpandPanel() {
		if(expandPanel == null) {
			showExpandPanel();
		} else {
			hideExpandPanel();
		}
	}
	
	public void showExpandPanel() {
		expandPanel = new PartyExpandPanel(width - expandPanel.defaultWidth, height);
		expandButton.setText(expandedText);
	}
	
	public void hideExpandPanel() {
		expandPanel = null;
		expandButton.setText(collapsedText);
	}
	
	public void setText(String stats) {
		textOrig = stats;
		lines = fitStr(stats);
		calculateFullHeight();
		checkNeedScroll();
	}
	
	@Override
	public void update() {
		super.update();
		expandButton.update();
		if(expandPanel != null) {
			expandPanel.update();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		expandButton.draw(g);
		if(expandPanel != null) {
			expandPanel.draw(g);
		}
	}
}
