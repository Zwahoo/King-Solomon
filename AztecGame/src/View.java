import java.awt.*;

public class View extends InputListener {
	
	private Point loc;
	int width;
	int height;
	
	
	public View(InputManager input, int width, int height) {
		this(input, new Point(0, 0), width, height);
	}
	
	public View(InputManager input, Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;
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
	
	public boolean pointIsInView(Point pnt) {
		return pointIsInView(pnt, 10);
	}
	public boolean pointIsInView(Point pnt, int buffer) {
		if(pnt.x < (getLocation().x - buffer) || pnt.y < (getLocation().y - buffer)) return false;
		if(pnt.x > (getLocation().x + width + buffer) || pnt.y > (getLocation().y + width + buffer)) return false;
		return true;
	}
	

	public boolean polygonIsInView(Polygon poly) {
		return polygonIsInView(poly, 10);
	}
	public boolean polygonIsInView(Polygon poly, int buffer) {
		for(int x=0; x < poly.xpoints.length; x++) {
			for(int y=0; y<poly.ypoints.length; y++) {
				if(pointIsInView(new Point(poly.xpoints[x], poly.ypoints[y]), buffer)) return true;
			}
		}
		return false;
	}
}
