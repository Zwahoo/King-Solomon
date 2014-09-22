import java.awt.*;

//Handles any player movement that occurs
public class PlayerMovementHandler extends InputListener {
	
	gameframe mainFrame;
	map theMap;
	player thePlayer;
	private tile dummy; //dummy tile
	
	public PlayerMovementHandler(gameframe mainFrame, map theMap, player thePlayer, InputManager input) {
		this.setInputManager(input);
		input.addInputListener(this);
		
		this.mainFrame = mainFrame;
		this.theMap = theMap;
		this.thePlayer = thePlayer;
		
		dummy = new tile("dummy", -1,-1);
		dummy.setSelectangle(-2, -3, -4, -5, -6, -7, -8, -9, -2, -3, -4, -5, -6, -7, -8, -9);
		setNeighbors();
		checkSight();
	}
	
	@Override
	public void mouseDown(int mouseButton, Point mouseLoc) {
        
        int xRelBoard = mouseLoc.x - mainFrame.getViewLoc().x;
        int yRelBoard = mouseLoc.y - mainFrame.getViewLoc().y;
        
        //checks to see if any of the tiles neighboring player were clicked- this can probably be reduced to loop
		if(thePlayer.getNeighbortile(0).checkcontains(xRelBoard, yRelBoard)){
			move(thePlayer.getNeighbortile(0));
			checkSight();
		} 
		
		else if(thePlayer.getNeighbortile(1).checkcontains(xRelBoard, yRelBoard)){
			move(thePlayer.getNeighbortile(1));
			checkSight();
		}
		
		else if(thePlayer.getNeighbortile(2).checkcontains(xRelBoard, yRelBoard)){
			move(thePlayer.getNeighbortile(2));
			checkSight();
		}
		
		else if(thePlayer.getNeighbortile(3).checkcontains(xRelBoard, yRelBoard)){
			move(thePlayer.getNeighbortile(3));
			checkSight();
		}
		
	}
	
	//sets the 4 neighbors or sets the neighbor to a dummy tile if no neighbor available
	public void setNeighbors(){
		if(thePlayer.getX() != 0){
			thePlayer.setNeighborTile(0,  theMap.getTile(thePlayer.getX()-1, thePlayer.getY()));
		}
		else if(thePlayer.getX() == 0){
			thePlayer.setNeighborTile(0,  dummy);
		}
		if(thePlayer.getY() != 0){
			thePlayer.setNeighborTile(1, theMap.getTile(thePlayer.getX(), thePlayer.getY()-1));
		}
		else if(thePlayer.getY() == 0){
			thePlayer.setNeighborTile(1, dummy);
		}
		if(thePlayer.getX() != 39){
			thePlayer.setNeighborTile(2, theMap.getTile(thePlayer.getX()+1, thePlayer.getY()));
		}
		else if(thePlayer.getX() == 39){
			thePlayer.setNeighborTile(2, dummy);
		}
		if(thePlayer.getY() != 39){
			thePlayer.setNeighborTile(3, theMap.getTile(thePlayer.getX(), thePlayer.getY()+1));
		}
		else if(thePlayer.getY() == 39){
			thePlayer.setNeighborTile(3, dummy);
		}
		
	}

	//changes the tile the player is on and sets the neighbors as well
	public void move(tile t){
		thePlayer.setLoc(t.getX(), t.getY());
		thePlayer.setCurrentTile(t);
		setNeighbors();
		t.runEvent();
	}
	

	//Updates which tiles are visible
    public void checkSight(){
    	int x = 0;
    	int y = 0;
    	for(int i = 0; i < 4; i++){
    		x = thePlayer.getNeighbortile(i).getX();
    		y = thePlayer.getNeighbortile(i).getY();
    		if(!thePlayer.getNeighbortile(i).getType().equals("dummy")){
    			theMap.getTile(x, y).reveal();
    		}
    	}
    }
}
