package MainGame;

import java.awt.event.KeyEvent;

public class ViewMovementHandler extends InputListener {
	
	View view;
	
	public boolean viewKeyMovement = true;
	public boolean viewMouseMovement = false;
	
	int movSpeed = 20;
	
	public ViewMovementHandler(View view, InputManager input) {
		this.view = view;
		setInputManager(input);
	}
	
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
    	if (viewMouseMovement && mouseInWindow()) {
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
		if(!viewKeyMovement) return;
		
		int xChange = 0;
		int yChange = 0;
		
		if(keyIsDown(Controls.VIEW_UP_KEY)) {
			yChange += movSpeed;
		}
		if(keyIsDown(Controls.VIEW_DOWN_KEY)) {
			yChange -= movSpeed;
		}
		if(keyIsDown(Controls.VIEW_LEFT_KEY)) {
			xChange += movSpeed;
		}
		if(keyIsDown(Controls.VIEW_RIGHT_KEY)) {
			xChange -= movSpeed;
		}
		
		moveView(xChange, yChange);
	}
	
	public void moveView(int xChange, int yChange) {
		view.getLocation().x += xChange;
		view.getLocation().y += yChange;
	}
}
