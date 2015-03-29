package MainGame;

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

	private final double width = 0.2;
	private final double buttonWidth = 0.158;
	private final double buttonSpacer = 0.75 * buttonWidth;
	private final double spacer = 0.02;
	// Four above are percentages of total screen width.

	private final double topspacer = 0.05;
	private final double buttonHeight = 0.06;
	private final double totalheight = 3 * buttonHeight;
	// private final double partyPanelHeight = 3 * (buttonHeight + ((spacer *
	// gameframe.windowWidth) / 800));
	// Three above are percentages of total screen height.

	private final double iconWidthHeight = (.5 * ((spacer * gameframe.windowWidth) + (buttonHeight * gameframe.windowHeight))) / 1000;
	private final double iconSpacer = 0.070;
	// Two above are a percentage of the total screen width.

	private final double partyPanelWidth = MainGame.statsBar.partyWidth;
	private final double partyPanelHeight = MainGame.statsBar.partyHeight;

	private int verticalinset;
	private int buttonHeighti;
	private int buttonWidthi;
	private int buttonSpaceri;
	private int totalheighti;
	private int spaceri;
	private int partyPanelHeighti;

	public StartDayDrawer() {
		setLocations();
		launchStartDay();
	}

	private void setLocations() {
		spaceri = (int) (gameframe.windowWidth * spacer);
		buttonHeighti = (int) (gameframe.windowHeight * buttonHeight);
		verticalinset = gameframe.windowHeight - ((2 * MainGame.statBarHeight) + spaceri);
		buttonWidthi = (int) (gameframe.windowWidth * buttonWidth);
		buttonSpaceri = (int) (gameframe.windowWidth * buttonSpacer);
		totalheighti = (int) (gameframe.windowHeight * totalheight);
		partyPanelHeighti = (int) (gameframe.windowHeight * partyPanelHeight);
	}

	public void launchStartDay() {
		/*
		 * textbox = new Textbox("", 0, 20, buttonWidthi, totalheighti,
		 * MainGame.input);
		 */
		moveButton = new Button(spaceri, verticalinset, buttonWidthi,
				buttonHeighti, MOVE_BUTTON_TEXT, MainGame.input) {
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.MOVEMENT_MODE, 0);
			}
		};
		investigateButton = new Button(spaceri + buttonWidthi + buttonSpaceri,
				verticalinset, buttonWidthi, buttonHeighti, INV_BUTTON_TEXT,
				MainGame.input) {
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.EVENT_MODE, 1);
			}
		};
		restButton = new Button(spaceri + (2 * buttonWidthi)
				+ (2 * buttonSpaceri), verticalinset, buttonWidthi,
				buttonHeighti, REST_BUTTON_TEXT, MainGame.input) {
			@Override
			public void onClick() {
				handleResponseSelect(MainGame.EVENT_MODE, 2);
			}
		};
		for (int i = 1; i <= 7; i += 2) {
			if (MainGame.input.getPlayerMovementHandler().getNeighborTile(i) != null) {
				if (MainGame.input.getPlayerMovementHandler()
						.getNeighborTile(i).getType().hasWater()) {
					isNextToWater = true;
				}
			}
		}
		if (isNextToWater) {
			collectWaterButton = new Button(
					(int) (0.75 * gameframe.windowWidth)
					+ (int) (0.5 * (partyPanelWidth - buttonWidthi)),
					gameframe.windowHeight
					- (int) (partyPanelHeight + (2 * spaceri) + buttonHeighti),
					buttonWidthi, buttonHeighti, COLLECT_WATER_BUTTON_TEXT,
					MainGame.input) {
				@Override
				public void onClick() {
					handleResponseSelect(MainGame.EVENT_MODE, 3);
				}
			};
		} else {
			collectWaterButton = null;
			// collectWaterButton = new Button(0, verticalinset +
			// (3*buttonheighti),
			// buttonwidth, buttonheighti, COLLECT_WATER_BUTTON_TEXT,
			// MainGame.input){
			// @Override
			// public void onClick() {
			//
			// }
			// };
			// Color impossCol = new Color(200, 200, 200);
			// Color impossBor = new Color(100, 70, 70);
			// collectWaterButton.setImpossible(true);
			// collectWaterButton.setColor(impossCol, impossBor);
		}
	}

	// currently, just close the menu.
	public void handleResponseSelect(Integer mode, int startDayChoice) {
		// if (startDayChoice == 0) {
		// MainGame.closeStartDay(MainGame.MOVEMENT_MODE, 0);
		// } else if (startDayChoice == 1) {
		// MainGame.closeStartDay(MainGame.EVENT_MODE, 1);
		// } else if (startDayChoice == 2) {
		// MainGame.closeStartDay(MainGame.EVENT_MODE, 2);
		// } else if (startDayChoice == 3) {
		// MainGame.closeStartDay(MainGame.EVENT_MODE, 3);
		// }
		MainGame.closeStartDay(mode, startDayChoice);
	}

	public void draw(Graphics g) {
		// textbox.draw(g);
		moveButton.draw(g);
		investigateButton.draw(g);
		restButton.draw(g);
		if (collectWaterButton != null) {
			collectWaterButton.draw(g);
		}
	}

	public void update() {
		// textbox.update();
		moveButton.update();
		investigateButton.update();
		restButton.update();
		if (collectWaterButton != null) {
			collectWaterButton.update();
		}
	}

	public void destroyer() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.START_DAY_MODE);
		temp.add(MainGame.MOVEMENT_MODE);
		temp.add(MainGame.EVENT_MODE);
		moveButton.removeInputManager(MainGame.input, temp);
		investigateButton.removeInputManager(MainGame.input, temp);
		restButton.removeInputManager(MainGame.input, temp);
		if (collectWaterButton != null) {
			collectWaterButton.removeInputManager(MainGame.input, temp);
		}
	}

}
