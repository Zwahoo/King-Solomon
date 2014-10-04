package Components;

import MainGame.InputManager;

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
