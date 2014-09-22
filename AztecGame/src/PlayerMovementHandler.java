import java.awt.*;

//Handles any player movement that occurs
public class PlayerMovementHandler extends InputListener {
	
	gameframe mainFrame;
	Map map;
	Player player;
	private Tile dummy; //dummy tile
	
	public PlayerMovementHandler(gameframe mainFrame, Map theMap, Player thePlayer, InputManager input) {
		this.setInputManager(input);
		input.addInputListener(this);
		
		this.mainFrame = mainFrame;
		this.map = theMap;
		this.player = thePlayer;
		
		dummy = new Tile("dummy", -1,-1);
		dummy.setSelectangle(-2, -3, -4, -5, -6, -7, -8, -9, -2, -3, -4, -5, -6, -7, -8, -9);
		setNeighbors();
		checkSight();
	}
	
	@Override
	public void mouseDown(int mouseButton, Point mouseLoc) {
        
        int xRelBoard = mouseLoc.x - mainFrame.getViewLoc().x;
        int yRelBoard = mouseLoc.y - mainFrame.getViewLoc().y;
        
        //checks to see if any of the tiles neighboring player were clicked- this can probably be reduced to loop
		if(player.getNeighbortile(0).checkcontains(xRelBoard, yRelBoard)){
			move(player.getNeighbortile(0));
			checkSight();
		} 
		
		else if(player.getNeighbortile(1).checkcontains(xRelBoard, yRelBoard)){
			move(player.getNeighbortile(1));
			checkSight();
		}
		
		else if(player.getNeighbortile(2).checkcontains(xRelBoard, yRelBoard)){
			move(player.getNeighbortile(2));
			checkSight();
		}
		
		else if(player.getNeighbortile(3).checkcontains(xRelBoard, yRelBoard)){
			move(player.getNeighbortile(3));
			checkSight();
		}
		
	}
	
	//sets the 4 neighbors or sets the neighbor to a dummy tile if no neighbor available
	public void setNeighbors(){
		if(player.getX() != 0){
			player.setNeighborTile(0,  map.getTile(player.getX()-1, player.getY()));
		}
		else if(player.getX() == 0){
			player.setNeighborTile(0,  dummy);
		}
		if(player.getY() != 0){
			player.setNeighborTile(1, map.getTile(player.getX(), player.getY()-1));
		}
		else if(player.getY() == 0){
			player.setNeighborTile(1, dummy);
		}
		if(player.getX() != 39){
			player.setNeighborTile(2, map.getTile(player.getX()+1, player.getY()));
		}
		else if(player.getX() == 39){
			player.setNeighborTile(2, dummy);
		}
		if(player.getY() != 39){
			player.setNeighborTile(3, map.getTile(player.getX(), player.getY()+1));
		}
		else if(player.getY() == 39){
			player.setNeighborTile(3, dummy);
		}
		
	}

	//changes the tile the player is on and sets the neighbors as well
	public void move(Tile t){
		player.setLoc(t.getX(), t.getY());
		player.setCurrentTile(t);
		setNeighbors();
		t.runEvent();
	}
	

	//Updates which tiles are visible
    public void checkSight(){
    	int x = 0;
    	int y = 0;
    	for(int i = 0; i < 4; i++){
    		x = player.getNeighbortile(i).getX();
    		y = player.getNeighbortile(i).getY();
    		if(!player.getNeighbortile(i).getType().equals("dummy")){
    			map.getTile(x, y).reveal();
    		}
    	}
    }
}
