package MainGame;

import java.awt.*;
import java.awt.event.KeyEvent;

//Handles any player movement that occurs
public class PlayerMovementHandler extends InputListener {
	
	MainGame mainGame;
	Map map;
	Player player;
	private Tile dummy; //dummy tile
	
	static final int NW_TILE = 0;
	static final int SW_TILE = 1;
	static final int SE_TILE = 2;
	static final int NE_TILE = 3;
	
	public boolean playerKeyMovement = false;
	public boolean playerMouseMovement = true;
	
	public PlayerMovementHandler(MainGame mainGame, Map theMap, Player thePlayer, InputManager input) {
		this.setInputManager(input);
		
		this.mainGame = mainGame;
		this.map = theMap;
		this.player = thePlayer;
		
		dummy = new Tile("dummy", -1,-1);
		dummy.setSelectangle(-2, -3, -4, -5, -6, -7, -8, -9, -2, -3, -4, -5, -6, -7, -8, -9);
		setNeighbors();
		checkSight();
	}
	
	@Override
	public void mouseDown(int mouseButton, Point mouseLoc) {
        if(!playerMouseMovement) return;
        
        int xRelBoard = mouseLoc.x - mainGame.getViewLoc().x;
        int yRelBoard = mouseLoc.y - mainGame.getViewLoc().y;
        
        //checks to see if any of the tiles neighboring player were clicked- this can probably be reduced to loop
		if(player.getNeighbortile(NW_TILE).checkcontains(xRelBoard, yRelBoard)){
			moveToTile(NW_TILE);
		} 
		
		else if(player.getNeighbortile(SW_TILE).checkcontains(xRelBoard, yRelBoard)){
			moveToTile(SW_TILE);
		}
		
		else if(player.getNeighbortile(SE_TILE).checkcontains(xRelBoard, yRelBoard)){
			moveToTile(SE_TILE);
		}
		
		else if(player.getNeighbortile(NE_TILE).checkcontains(xRelBoard, yRelBoard)){
			moveToTile(NE_TILE);
		}
		
	}
	
	//sets the 4 neighbors or sets the neighbor to a dummy tile if no neighbor available
	public void setNeighbors(){
		if(player.getX() <= 0){
			player.setNeighborTile(NW_TILE,  dummy);
		}
		else {
			player.setNeighborTile(NW_TILE,  map.getTile(player.getX()-1, player.getY()));
		}
		
		 if(player.getY() <= 0){
			player.setNeighborTile(SW_TILE, dummy);
		}
		else{
			player.setNeighborTile(SW_TILE, map.getTile(player.getX(), player.getY()-1));
		}
		 
		if(player.getX() >= (map.width - 1)){
			player.setNeighborTile(SE_TILE, dummy);
		} else {
			player.setNeighborTile(SE_TILE, map.getTile(player.getX()+1, player.getY()));
		}
		 
		if(player.getY() >= (map.height - 1)){
			player.setNeighborTile(NE_TILE, dummy);
		}
		else {
			player.setNeighborTile(NE_TILE, map.getTile(player.getX(), player.getY()+1));
		}
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!playerKeyMovement) return;
		int keyCode = e.getKeyCode();
		if(keyCode == Controls.NORTH_EAST_KEY) {
			moveToTile(NE_TILE);
		} else if(keyCode == Controls.NORTH_WEST_KEY) {
			moveToTile(NW_TILE);
		} else if(keyCode == Controls.SOUTH_EAST_KEY) {
			moveToTile(SE_TILE);
		} else if(keyCode == Controls.SOUTH_WEST_KEY) {
			moveToTile(SW_TILE);
		}
	}
	
	

	public void moveToTile(int tile) {
		move(player.getNeighbortile(tile));
		checkSight();
	}
	
	//changes the tile the player is on and sets the neighbors as well
	public void move(Tile t){
		if(!isValidTile(t)) return;
		player.setLoc(t.getX(), t.getY());
		player.setCurrentTile(t);
		setNeighbors();
		t.runEvent();
	}
	
	public boolean isValidTile(Tile t) {
		return (t != null && !t.getType().equals("dummy"));
	}
	
	//Updates which tiles are visible
    public void checkSight(){
    	int x = 0;
    	int y = 0;
    	for(int i = 0; i < 4; i++){
    		if(!player.getNeighbortile(i).getType().equals("dummy")){
        		x = player.getNeighbortile(i).getX();
    		y = player.getNeighbortile(i).getY();
    			map.getTile(x, y).reveal();
    		}
    	}
    }
}
