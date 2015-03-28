package MainGame;

import java.awt.Color;

public class TileType {
	public String name;
	public boolean blockSight;
	public boolean canBeOccupied;
	public boolean alwaysHaveEvent;
	public int tileImageIndex;
	public int tileImageOverlay;
	Color mapCol;
	public boolean canSeeAll = false;

	public boolean hasWater;

	public TileType(String name, boolean blockSight, boolean canBeOccupied, boolean alwaysHaveEvent, int tileImageIndex, Color mapCol) {
		this.name = name;
		this.blockSight = blockSight;
		this.canBeOccupied = canBeOccupied;
		this.alwaysHaveEvent = alwaysHaveEvent;
		this.tileImageIndex = tileImageIndex;
		this.mapCol = mapCol;
		tileImageOverlay = -1;
	}

	/**
	 * Set an overlay image for a tile.
	 * @param index The index of the overlay in the MainGame's TileImage list
	 */
	public void setOverlay(int index) {
		tileImageOverlay = index;
	}

	public boolean colorMatch(int r, int g, int b) {
		return ((r == mapCol.getRed()) && (g == mapCol.getGreen()) && (b == mapCol.getBlue()));
	}

	public String getName(){
		return name;
	}

	public boolean canBeOccupied(){
		return canBeOccupied;
	}

	public void setImageIndex(int imageIndex) {
		tileImageIndex = imageIndex;
	}

	public void setWater(boolean hasWater)
	{
		this.hasWater = hasWater;
	}

	public boolean hasWater()
	{
		return hasWater;
	}

	@Override
	public String toString() {
		return "Type: " + name;
	}
}
