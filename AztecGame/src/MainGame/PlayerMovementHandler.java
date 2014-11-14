package MainGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//Handles any player movement that occurs
public class PlayerMovementHandler extends InputListener {
	
	MainGame mainGame;
	Map map;
	Player player;
	
	static final int NUM_TILES = 12;
	static final int N_TILE = 0;
	static final int NE_TILE = 1;
	static final int E_TILE = 2;
	static final int SE_TILE = 3;
	static final int S_TILE = 4;
	static final int SW_TILE = 5;
	static final int W_TILE = 6;
	static final int NW_TILE = 7;
	static final int NE2_TILE = 8;
	static final int NW2_TILE = 9;
	static final int SE2_TILE = 10;
	static final int SW2_TILE = 11;
	
	
	private ArrayList<Integer> validMoveTiles;
	
	public boolean playerKeyMovement = false;
	public boolean playerMouseMovement = true;
	
	public PlayerMovementHandler(MainGame mainGame, Map theMap, Player thePlayer, InputManager input) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.MOVEMENT_MODE);
		this.setInputManager(input, temp);
		
		this.mainGame = mainGame;
		this.map = theMap;
		this.player = thePlayer;
		
		validMoveTiles = new ArrayList<Integer>();
		//Can only move nw, ne, sw, se.
		validMoveTiles.add(NW_TILE);
		validMoveTiles.add(NE_TILE);
		validMoveTiles.add(SW_TILE);
		validMoveTiles.add(SE_TILE);
		
		checkSight();
	}
	
	@Override
	public void mouseDown(int mouseButton, Point mouseLoc) {
        if(!playerMouseMovement || MainGame.currentMode != MainGame.MOVEMENT_MODE) return;
        
        int xRelBoard = mouseLoc.x - mainGame.getViewLoc().x;
        int yRelBoard = mouseLoc.y - mainGame.getViewLoc().y;
        
        //checks to see if any of the tiles neighboring player were clicked- this can probably be reduced to loop
		for(int tileNum : validMoveTiles) {
			Tile tile = getNeighborTile(tileNum);
			if(tile != null) {
				if(tile.checkcontains(xRelBoard, yRelBoard)) {
					moveToTile(tileNum);
					break;
				}
			}
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
		move(getNeighborTile(tile));
	}
	
	//changes the tile the player is on and sets the neighbors as well
	public void move(Tile t){
		if(!isValidTile(t)) return;
		if(!t.getType().canBeOccupied) return;
		player.setLoc(t.getX(), t.getY());
		player.setCurrentTile(t);
		t.runEvent(mainGame.party);
		mainGame.handleMoveStatChanges();
		checkSight();
	}
	
	public boolean isValidTile(Tile t) {
		return (t != null && !t.getType().equals("dummy"));
	}
	
	//Updates which tiles are visible
    public void checkSight(){
    	revealNonconditoinalTiles();
    	revealConditionalTiles();
    }
    
    //Reveals the tiles that are unconditionally revealed upon move.
    public void revealNonconditoinalTiles() {
    	revealTile(NW_TILE);
    	revealTile(NE_TILE);
    	revealTile(SW_TILE);
    	revealTile(SE_TILE);
    }
    
    //Reveals tiles that are conditionally revealed.
    public void revealConditionalTiles() {
    	
    	//Check if the tile overrides all sight inhibitions.
    	if(player.getCurrentTile().getType().canSeeAll) {
			revealTile(NE2_TILE);
			revealTile(NW2_TILE);
			revealTile(SE2_TILE);
			revealTile(SW2_TILE);
			revealTile(N_TILE);
			revealTile(S_TILE);
			revealTile(E_TILE);
			revealTile(W_TILE);
			return;
    	}
    	
    	Tile neTile = getNeighborTile(NE_TILE);
    	Tile nwTile = getNeighborTile(NW_TILE);
    	Tile seTile = getNeighborTile(SE_TILE);
    	Tile swTile = getNeighborTile(SW_TILE);
    	
    	boolean neBlock = true;
    	boolean nwBlock = true;
    	boolean seBlock = true;
    	boolean swBlock = true;
    	
    	if(neTile != null) neBlock = neTile.getType().blockSight;
    	if(nwTile != null) nwBlock = nwTile.getType().blockSight;
    	if(seTile != null) seBlock = seTile.getType().blockSight;
    	if(swTile != null) swBlock = swTile.getType().blockSight;
    	
    	if(!neBlock) {
    		revealTile(NE2_TILE);
    	}
    	if(!nwBlock) {
    		revealTile(NW2_TILE);
    	}
    	if(!seBlock) {
    		revealTile(SE2_TILE);
    	}
    	if(!swBlock) {
    		revealTile(SW2_TILE);
    	}
    	
    	if(!(neBlock && nwBlock)) {
    		revealTile(N_TILE);
    	}
    	if(!(seBlock && swBlock)) {
    		revealTile(S_TILE);
    	}
    	if(!(neBlock && seBlock)) {
    		revealTile(E_TILE);
    	}
    	if(!(nwBlock && swBlock)) {
    		revealTile(W_TILE);
    	}
    }
    
    public void revealTile(int tileNum) {
    	Tile toReveal = getNeighborTile(tileNum);
		if(toReveal != null){
			toReveal.reveal();
		}
    }
    
	public Tile getNeighborTile(int tile) {
		int x = player.getX();
		int y = player.getY();
		switch(tile) {
		case(N_TILE):
			if(!pointIsInMap(x - 1, y + 1)) return null;
			return map.getTile(x - 1, y + 1);
		case(NE_TILE):
			if(!pointIsInMap(x, y + 1)) return null;
			return map.getTile(x, y + 1);
		case(E_TILE):
			if(!pointIsInMap(x + 1, y + 1)) return null;
			return map.getTile(x + 1, y + 1);
		case(SE_TILE):
			if(!pointIsInMap(x + 1, y)) return null;
			return map.getTile(x + 1, y);
		case(S_TILE):
			if(!pointIsInMap(x + 1, y - 1)) return null;
			return map.getTile(x + 1, y - 1);
		case(SW_TILE):
			if(!pointIsInMap(x, y - 1)) return null;
			return map.getTile(x, y - 1);
		case(W_TILE):
			if(!pointIsInMap(x - 1, y - 1)) return null;
			return map.getTile(x - 1, y - 1);
		case(NW_TILE):
			if(!pointIsInMap(x - 1, y)) return null;
			return map.getTile(x-1, y);
		case(NE2_TILE):
			if(!pointIsInMap(x, y + 2)) return null;
			return map.getTile(x, y + 2);
		case(NW2_TILE):
			if(!pointIsInMap(x - 2, y)) return null;
			return map.getTile(x - 2, y);
		case(SE2_TILE):
			if(!pointIsInMap(x + 2, y)) return null;
			return map.getTile(x + 2, y);
		case(SW2_TILE):
			if(!pointIsInMap(x, y-2)) return null;
			return map.getTile(x, y-2);
		default:
			System.out.println("Unrecognized tile move request: " + tile);
			return null;
		}
	}
	
	public boolean pointIsInMap(int x, int y){
		if(x < 0 || y < 0) return false;
		if(x >= map.width || y >= map.height) return false;
		return true;
	}
}