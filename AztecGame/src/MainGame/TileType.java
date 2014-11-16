package MainGame;

import java.awt.Color;

public class TileType {
	public String name;
	public boolean blockSight;
	public boolean canBeOccupied;
	public int tileImageIndex;
	Color mapCol;
	public boolean canSeeAll = false;

	public TileType(String name, boolean blockSight, boolean canBeOccupied, int tileImageIndex, Color mapCol) {
		this.name = name;
		this.blockSight = blockSight;
		this.canBeOccupied = canBeOccupied;
		this.tileImageIndex = tileImageIndex;
		this.mapCol = mapCol;
	}
	
	public boolean colorMatch(int r, int g, int b) {
		return (r == mapCol.getRed() && g == mapCol.getGreen() && b == mapCol.getBlue());
	}
	
	public String getName(){
		return name;
	}
	
	public boolean canBeOccupied(){
		return canBeOccupied;
	}
}
