package MainGame;
//Zane Laughlin- Tile Class: Holds tile info

import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Tile{
	
	private TileType type;
	public Polygon selectangle;
	private boolean revealed;
	private int[] xar = new int[8];
	private int[] yar = new int[8];
	private int xp;
	private int yp;
	private Event moveToEvent;
	private Event investigateEvent;
	private Event restEvent;
	
	//constructor
	public Tile(TileType t, int  x, int y, Event moveTo, Event investigate, Event rest){
		
		this.type = t;
		xp = x;
		yp = y;
		revealed = false;
		this.moveToEvent = moveTo;
		this.investigateEvent = investigate;
		this.restEvent = rest;
	}
	
	
	//gets type of tile
	public TileType getType(){
		return type;
	}
	
	//reveals tile
	public void reveal(){
		revealed = true;
	}
	
	public void setSelectangle(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int y1, int y2, int y3, int y4, int y5, int y6, int y7, int y8){
		//x array
		//System.out.println("STAR WOLF");
		xar[0] = x1;
		xar[1] = x2;
		xar[2] = x3;
		xar[3] = x4;
		xar[4] = x5;
		xar[5] = x6;
		xar[6] = x7;
		xar[7] = x8;
		//y array
		yar[0] = y1;
		yar[1] = y2;
		yar[2] = y3;
		yar[3] = y4;
		yar[4] = y5;
		yar[5] = y6;
		yar[6] = y7;
		yar[7] = y8;
		selectangle = new Polygon(xar, yar, 8);
		//System.out.println(selectangle);
		
	}
	
	public void moveSelectangle(int x, int y){
		selectangle.translate(x,y);
	}
	
	public boolean checkcontains(int x, int y){
		//System.out.println("ch2");
		return this.selectangle.contains(x,y);
	}
	
	public boolean checkcontains(Point pnt){
		return this.selectangle.contains(pnt);
	}
	
	public void update(){
		
	}
	
	public boolean getRevealed(){
		return revealed;
	}
	
	public int getX(){
		return xp;
	}
	
	public int getY(){
		return yp;
	}
	
	public int getImageIndex() {
		if(!revealed) return 5;
		return getType().tileImageIndex;
	}
	
	public void runEvent(ArrayList<PartyMember> presMembers){
		if(moveToEvent == null) return;
		MainGame.launchEvent(moveToEvent, presMembers);
	
	}
	
	@Override
	public String toString() {
		return "Tile: (" + xp + ", " + yp + ") : " + getType().name;
	}
	
	public Event getInvestigateEvent() {
		return investigateEvent;
	}
	
	public Event getRestEvent() {
		return restEvent;
	}
	
}
