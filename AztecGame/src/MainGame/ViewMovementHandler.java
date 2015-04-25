package MainGame;

import java.util.ArrayList;

public class ViewMovementHandler extends InputListener {

	View view;

	public boolean viewKeyMovement = true; //Can the view be moved via the mouse?
	public boolean viewMouseMovement = false; //Can the view be moved via the keyboard?

	int movSpeed = 20;

	private int xMax, yMax, xMin, yMin;


	public ViewMovementHandler(View view, InputManager input) {
		this.view = view;

		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.START_DAY_MODE);
		temp.add(MainGame.MOVEMENT_MODE);

		setInputManager(input, temp);

		xMax = 1250 - view.width;
		xMin = -4000 + view.width;
		yMax = -1250 + view.height;
		yMin = 2000 - view.height;
	}

	@Override
	public void update() {
		checkScroll();
	}

	//checks to see if the player activated the scrolling function
	public void checkScroll() {
		if(viewKeyMovement) {
			checkKeyMovement();
		}
		if(viewMouseMovement) {
			checkMouseMovement();
		}
	}

	public void checkMouseMovement() {
		//checks to see if mouse is at the edge of the frame
		if (viewMouseMovement && mouseInWindow() 
				&& (MainGame.getCurrentMode() != MainGame.EVENT_MODE)) {
			int x = getMouseLoc().x;
			int y = getMouseLoc().y;

			int xChange = 0;
			int yChange = 0;

			if (x < 100){
				xChange += movSpeed;
			}
			if (x > 900){
				xChange -= movSpeed;
			}
			if (y < 100){
				yChange += movSpeed;
			}
			if (y > 700){
				yChange -= movSpeed;
			}
			moveView(xChange, yChange);
		}
	}

	public void checkKeyMovement() {
		if(!viewKeyMovement 
				|| (MainGame.getCurrentMode() == MainGame.EVENT_MODE)) {
			return;
		}

		int xChange = 0;
		int yChange = 0;

		if(keyIsDown(Controls.VIEW_UP_KEY) && (view.getLocation().y < yMin)) {
			yChange += movSpeed;
		}
		if(keyIsDown(Controls.VIEW_DOWN_KEY) && (view.getLocation().y > yMax)) {
			yChange -= movSpeed;
		}
		if(keyIsDown(Controls.VIEW_LEFT_KEY) && (view.getLocation().x < xMax)) {
			xChange += movSpeed;
		}
		if(keyIsDown(Controls.VIEW_RIGHT_KEY) && (view.getLocation().x > xMin)) {
			xChange -= movSpeed;
		}

		moveView(xChange, yChange);
	}

	public void moveView(int xChange, int yChange) {
		view.getLocation().x += xChange;
		view.getLocation().y += yChange;
	}
}
