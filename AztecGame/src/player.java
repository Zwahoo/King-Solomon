import java.awt.*;
//Zane Laughlin
//player class- manages supplies, player location, etc.

public class player{

	private tile currenttile;
	private tile[] neighbortiles = new tile[4];
	private String name;
	private Point loc;
	private partymember[] partymembers  = new partymember[10];
	
	//attaches player to map
	public player(map m){
		currenttile = m.getTile(0,0);
		loc = new Point(1, 1);
	}
	
	//gets one of the neighbor tiles
	public tile getNeighbortile(int b){
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
	public tile getCurrentTile() {
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
	public void setCurrentTile(tile newTile) {
		currenttile = newTile;
	}
	public void setNeighborTile(int tileNum, tile newTile) {
		neighbortiles[tileNum % neighbortiles.length] = newTile;
	}
	
}
