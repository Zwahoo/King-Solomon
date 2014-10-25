package MainGame;

import java.awt.Graphics;

import Components.Button;
import Components.Textbox;

public class StartDayDrawer {
	
	private Textbox textbox;
	private Button moveButton;
	private Button investigateButton;
	private Button restButton;
	
	public final String MOVE_BUTTON_TEXT = "MOVE";
	public final String INV_BUTTON_TEXT = "INVESTIGATE";
	public final String REST_BUTTON_TEXT = "REST";
	
	private final double topspacer = 0.1;
	private final double leftspacer = 0.05;
	private final double buttonheight = 0.06;
	private final double buttonspacer = 0.01;
	private final double width = 0.3;
	private final double totalheight = (3*buttonheight + 2*buttonspacer);
	
	private int verticalinset;
	private int horizontalinset;
	private int buttonheighti;
	private int buttonspaceri;
	private int buttonwidth;
	private int totalheighti;
	
	public StartDayDrawer() {
		setLocations();
		launchStartDay();
	}
	
	private void setLocations() {
		verticalinset = (int) (gameframe.windowHeight * topspacer);
		horizontalinset = (int) (gameframe.windowWidth * leftspacer);
		buttonheighti = (int) (gameframe.windowHeight * buttonheight);
		buttonspaceri = (int) (gameframe.windowHeight * buttonspacer);
		buttonwidth = (int) (gameframe.windowWidth * width);
		totalheighti = (int) (gameframe.windowHeight * totalheight);
	}
	
	public void launchStartDay() {
		textbox = new Textbox("", horizontalinset, verticalinset, 
				buttonwidth, totalheighti, MainGame.input);
		moveButton = new Button(horizontalinset, verticalinset, buttonwidth, 
				buttonheighti, MOVE_BUTTON_TEXT, MainGame.input) {
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.MOVEMENT_MODE);
			}
		};
		investigateButton = new Button(horizontalinset, verticalinset + buttonheighti + buttonspaceri,
				buttonwidth, buttonheighti, INV_BUTTON_TEXT, MainGame.input){
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.EVENT_MODE);
			}
		};
		restButton = new Button(horizontalinset, verticalinset + 2*buttonheighti + 2*buttonspaceri,
				buttonwidth, buttonheighti, REST_BUTTON_TEXT, MainGame.input){
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.EVENT_MODE);
			}
		};
	}
	
	// currently, just close the menu.
	public void handleResponseSelect(Integer mode) {
		MainGame.closeStartDay(MainGame.MOVEMENT_MODE);
	}
	
	public void draw(Graphics g) {
		textbox.draw(g);
		moveButton.draw(g);
		investigateButton.draw(g);
		restButton.draw(g);
	}
	
	public void update() {
		textbox.update();
		moveButton.update();
		investigateButton.update();
		restButton.update();
	}

}
