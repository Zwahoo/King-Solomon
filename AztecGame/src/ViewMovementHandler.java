



public class ViewMovementHandler extends InputListener {
	
	View view;
	
	public ViewMovementHandler(View view, InputManager input) {
		this.view = view;
		setInputManager(input);
	}
	
	public void update() {
		checkScroll();
	}

	//checks to see if the player activated the scrolling function
    public void checkScroll(){
    	//checks to see if mouse is at the edge of the frame
    	if (mouseInWindow()){
    		int x = getMouseLoc().x;
    		int y = getMouseLoc().y;
    	
    		if (x < 100){
    			view.getLocation().x += 20;
    		}
    		else if (x > 900){
    			view.getLocation().x -= 20;
    		}
    		if (y < 100){
    			view.getLocation().y += 20;
    		}
    		if (y > 700){
    			view.getLocation().y -= 20;
    		}
    	}
    }
}
