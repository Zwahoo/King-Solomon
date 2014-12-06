package MainGame;
import java.awt.*;
//Zane Laughlin
//player class- manages supplies, player location, etc.
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Player{

	private Tile currenttile;
	private String name;
	private Point loc;
	
	//attaches player to map
	public Player(Map m){
		int xStart = m.width - 2;
		int yStart = 1;
		currenttile = m.getTile(xStart, yStart);
		loc = new Point(xStart, yStart);
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
}
