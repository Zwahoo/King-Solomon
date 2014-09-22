import java.awt.*;

public class View extends InputListener{
	
	private Point loc;
	
	
	public View(InputManager input) {
		this(input, new Point(0, 0));
	}
	
	public View(InputManager input, Point loc) {
		this.loc = loc;
		setInputManager(input);
	}
	
	@Override
	void mouseDown(int mouseButton) {
		// TODO Auto-generated method stub
		
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
    			loc.x += 20;
    		}
    		else if (x > 900){
    			loc.x -= 20;
    		}
    		if (y < 100){
    			loc.y += 20;
    		}
    		if (y > 700){
    			loc.y -= 20;
    		}
    	}
    }
    
	public Point getLocation() {
		return loc;
	}
}
