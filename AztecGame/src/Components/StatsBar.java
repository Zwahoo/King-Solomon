package Components;

import MainGame.InputManager;

//A bar that displays the current stats of the party.
//Can be updated by using setText and passing it the parsed HashMap of stats 

public class StatsBar extends Textbox {

	public StatsBar(String str, int x, int y, int width, int height,
			InputManager input) {
		super(str, x, y, width, height, input);
		
	}

	public void setText(String stats) {
		textOrig = stats;
		lines = fitStr(stats);
		calculateFullHeight();
		checkNeedScroll();
	}
}
