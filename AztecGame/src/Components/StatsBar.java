package Components;

import MainGame.InputManager;

//A bar that displays the current stats of the party.
//Can be updated by using setText and passing it the parsed HashMap of stats 

public class StatsBar extends Textbox {

	public int x;
	public int y;
	public int width;
	public int height;
	
	public StatsBar(String str, int width, int height, InputManager input) {
		this(str, 0, 0, width, height, input);
	}
	
	public StatsBar(String str, int x, int y, int width, int height,
			InputManager input) {
		super(str, x, y, width, height, input);
		this.width = width;
		this.height = height;
	}

	public void setText(String stats) {
		textOrig = stats;
		lines = fitStr(stats);
		calculateFullHeight();
		checkNeedScroll();
	}
}
