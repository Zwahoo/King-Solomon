package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Components.Button;
import Components.Textbox;

public class StartDayDrawer {

	private Textbox textbox;
	private Button moveButton;
	private Button investigateButton;
	private Button restButton;
	private Button collectWaterButton;

	public final String MOVE_BUTTON_TEXT = "MOVE";
	public final String INV_BUTTON_TEXT = "INVESTIGATE";
	public final String REST_BUTTON_TEXT = "REST";
	public final String COLLECT_WATER_BUTTON_TEXT = "COLLECT WATER";

	private boolean isNextToWater = false;

	private final double topspacer = 0.05;
	private final double buttonheight = 0.06;
	private final double width = 0.2;
	private final double totalheight = 3*buttonheight;

	private int verticalinset;
	private int buttonheighti;
	private int buttonwidth;
	private int totalheighti;

	public StartDayDrawer() {
		setLocations();
		launchStartDay();
	}

	private void setLocations() {
		verticalinset = (int) (gameframe.windowHeight * topspacer);
		buttonheighti = (int) (gameframe.windowHeight * buttonheight);
		buttonwidth = (int) (gameframe.windowWidth * width);
		totalheighti = (int) (gameframe.windowHeight * totalheight);
	}

	public void launchStartDay() {
		textbox = new Textbox("", 0, verticalinset, 
				buttonwidth, totalheighti, MainGame.input);
		moveButton = new Button(0, verticalinset, buttonwidth, 
				buttonheighti, MOVE_BUTTON_TEXT, MainGame.input) {
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.MOVEMENT_MODE, 0);
			}
		};
		investigateButton = new Button(0, verticalinset + buttonheighti,
				buttonwidth, buttonheighti, INV_BUTTON_TEXT, MainGame.input){
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.EVENT_MODE, 1);
			}
		};
		restButton = new Button(0, verticalinset + (2*buttonheighti),
				buttonwidth, buttonheighti, REST_BUTTON_TEXT, MainGame.input){
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.EVENT_MODE, 2);
			}
		};
		for (int i = 1; i <= 7 ; i+=2){
			if (MainGame.input.getPlayerMovementHandler().getNeighborTile(i) != null){
				if (MainGame.input.getPlayerMovementHandler().getNeighborTile(i).getType().getName()
						.equalsIgnoreCase("water")
						|| MainGame.input.getPlayerMovementHandler().getNeighborTile(i).getType().getName()
								.equalsIgnoreCase("oasis")) {
					isNextToWater = true;
				}
			}
		}
		if (isNextToWater){
			collectWaterButton = new Button(0, verticalinset + (3*buttonheighti), 
					buttonwidth, buttonheighti, COLLECT_WATER_BUTTON_TEXT, MainGame.input){
				@Override
				public void onClick() {
					handleResponseSelect(MainGame.EVENT_MODE, 3);
				}
			};
		}
		else {
			collectWaterButton = new Button(0, verticalinset + (3*buttonheighti), 
					buttonwidth, buttonheighti, COLLECT_WATER_BUTTON_TEXT, MainGame.input){
				@Override
				public void onClick() {

				}
			};
			Color impossCol = new Color(200, 200, 200);
			Color impossBor = new Color(100, 70, 70);
			collectWaterButton.setImpossible(true);
			collectWaterButton.setColor(impossCol, impossBor);
		}
	}

	// currently, just close the menu.
	public void handleResponseSelect(Integer mode, int startDayChoice) {
		if (startDayChoice == 0) {
			MainGame.closeStartDay(MainGame.MOVEMENT_MODE, 0);
		} else if (startDayChoice == 1) {
			MainGame.closeStartDay(MainGame.EVENT_MODE, 1);
		} else if (startDayChoice == 2) {
			MainGame.closeStartDay(MainGame.EVENT_MODE, 2);
		} else if (startDayChoice == 3){
			MainGame.closeStartDay(MainGame.EVENT_MODE, 3);
		}
	}

	public void draw(Graphics g) {
		textbox.draw(g);
		moveButton.draw(g);
		investigateButton.draw(g);
		restButton.draw(g);
		collectWaterButton.draw(g);
	}

	public void update() {
		textbox.update();
		moveButton.update();
		investigateButton.update();
		restButton.update();
		collectWaterButton.update();
	}

	public void destroyer() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.START_DAY_MODE); temp.add(MainGame.MOVEMENT_MODE); temp.add(MainGame.EVENT_MODE);
		moveButton.removeInputManager(MainGame.input, temp);
		investigateButton.removeInputManager(MainGame.input, temp);
		restButton.removeInputManager(MainGame.input, temp);
		collectWaterButton.removeInputManager(MainGame.input, temp);
	}

}
