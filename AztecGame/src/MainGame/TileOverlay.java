package MainGame;
//Zane Laughlin- Tile Class: Holds tile info

import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class TileOverlay{
	
	public Polygon selectangle;
	private boolean revealed;
	String type; //Red, Blue, or Dark
	private int[] xar = new int[8];
	private int[] yar = new int[8];
	private int xp;
	private int yp;
	
	//constructor
	public TileOverlay(int x, int y, String type){
		
		xp = x;
		yp = y;
		revealed = false;
		this.type = type;
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
	
	public boolean checkContains(double x, double y){
		//System.out.println("ch2");
		return this.selectangle.contains(x,y);
	}
	
	public void update(){
		int playerX = MainGame.getPlayer().getX();
		int playerY = MainGame.getPlayer().getY();
		double xRelBoard = MainGame.input.getMouseLoc().getX() - MainGame.getViewLoc().x;
        double yRelBoard = MainGame.input.getMouseLoc().getY() - MainGame.getViewLoc().y;
		boolean mouseOn = checkContains(xRelBoard, yRelBoard);
		if (this.getY() == playerY + 1 || this.getY() == playerY - 1){
			if (this.getX() == playerX || this.getX() == playerX){
				if (MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && mouseOn){
					this.type = "darkBlue";
				} else if (MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && !mouseOn){
					this.type = "blue";
				} else if (!MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && mouseOn){
					this.type = "darkRed";
				} else if (!MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && !mouseOn){
					this.type = "red";
				}
			}
			else if (mouseOn){
				this.type = "dark";
			}
			else if (!mouseOn){
				this.type = "blank";
			}
		}
		else if (this.getX() == playerX + 1 || this.getX() == playerX - 1){
			if (this.getY() == playerY || this.getY() == playerY){
				if (MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && mouseOn){
					this.type = "darkBlue";
				} else if (MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && !mouseOn){
					this.type = "blue";
				} else if (!MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && mouseOn){
					this.type = "darkRed";
				} else if (!MainGame.map.getTile(getX(), getY()).getType().canBeOccupied() && !mouseOn){
					this.type = "red";
				}
			}
			else if (mouseOn){
				this.type = "dark";
			}
			else if (!mouseOn){
				this.type = "blank";
			}
		}
		else if (mouseOn){
			this.type = "dark";
		}
		else if (!mouseOn){
			this.type = "blank";
		}
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
		if (type.equalsIgnoreCase("red")){
			return 6;
		} else if (type.equalsIgnoreCase("blue")){
			return 7;
		} else if (type.equalsIgnoreCase("dark")){
			return 8;
		} else if (type.equalsIgnoreCase("blank")){
			return 9;
		} else if (type.equalsIgnoreCase("darkRed")){
			return 10;
		} else if (type.equalsIgnoreCase("darkBlue")){
			return 11;
		} else {
			return 9;
		}
	}
	
	@Override
	public String toString() {
		return "Tile Overlay: (" + xp + ", " + yp + ")";
	}
	
}
