import java.awt.*;
//Zane Laughlin
//player class- manages supplies, player location, etc.

public class Player{

	private Tile currenttile;
	private Tile[] neighbortiles = new Tile[4];
	private String name;
	private Point loc;
	private PartyMember[] partymembers  = new PartyMember[10];
	
	//attaches player to map
	public Player(Map m){
		currenttile = m.getTile(0,0);
		loc = new Point(1, 1);
	}
	
	//gets one of the neighbor tiles
	public Tile getNeighbortile(int b){
		//System.out.println("ch1");
		return neighbortiles[b];
		
	}
	
	//Getters
	public int getX(){
		return loc.x;
	}
	public int getY(){
		return loc.y;
	}
	public Point getLoc() {
		return loc;
	}
	public Tile getCurrentTile() {
		return currenttile;
	}
	//Setters
	public void setX(int val) {
		setLoc(val, loc.y);
	}
	public void setY(int val) {
		setLoc(loc.x, val);
	}
	public void setLoc(int x, int y) {
		setLoc(new Point(x, y));
	}
	public void setLoc(Point p) {
		loc = p;
	}
	public void setCurrentTile(Tile newTile) {
		currenttile = newTile;
	}
	public void setNeighborTile(int tileNum, Tile newTile) {
		neighbortiles[tileNum % neighbortiles.length] = newTile;
	}
	
}
