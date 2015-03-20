package MainGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
//Zane Laughlin- map class- holds bunch of tiles
// hello


import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Map {
	
	int width;
	int height;
	private Tile[][] tiles;
	private TileOverlay[][] tileOverlays;
	
	//map which is created from image
	//filePath is in the form assets/filename.png
	public Map(String filePath, MainGame mainGame) throws IOException{
		File imageFile = new File(filePath);
		BufferedImage image = ImageIO.read(imageFile);
		
		width = image.getWidth();
		height = image.getHeight();
		
		tiles = new Tile[image.getWidth()][image.getHeight()];
		tileOverlays = new TileOverlay[image.getWidth()][image.getHeight()];
		TileType a = null;
		Tile t;
		TileOverlay tO;
		
		for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int clr = image.getRGB(x, y);
                int red = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;
                
                if ((red == 255 && green == 100) && blue == 0) {
                    a = MainGame.tileTypes.get("desert");
                } else if ((red == 255 && green == 200) && blue == 0){
                    a = MainGame.tileTypes.get("oasis");
                } else if ((red == 0 && green == 255) && blue == 0){
                	a = MainGame.tileTypes.get("jungle");
                } else if ((red == 0 && green == 0) && blue == 255){
                	a = MainGame.tileTypes.get("water");
                } else if ((red == 255 && green == 255) && blue == 0){
                	a = MainGame.tileTypes.get("savannah");
                } else if ((red == 100 && green == 50) && blue == 0){
                	a = MainGame.tileTypes.get("mountain");
                } else if ((red == 50 && green == 100) && blue == 0){
                	a = MainGame.tileTypes.get("highland");
                } else if ((red == 255 && green == 255) && blue == 255){
                	a = MainGame.tileTypes.get("solomonsMines");
                } else if ((red == 100 && green == 100) && blue == 100){
                	a = MainGame.tileTypes.get("village");
                } else {
                	System.out.println("Pixel in map not recognized. Tile defaulting to jungle at " + x + ", " + y);
                	a = MainGame.tileTypes.get("jungle");
                }
            	//System.out.println(a + " " + x + " " + y);
                t = new Tile(a, x, y, mainGame.getRandomMoveToEvent(a), mainGame.getRandomInvestigateEvent(a), mainGame.getRandomRestEvent(a));
                tiles[x][y] = t;
                tO = new TileOverlay(x, y, "blank");
				tileOverlays[x][y] = tO;
            }
        }
	}
	
	public void draw(Graphics g, View view, Player player1) {
		int newx;
		int newy;
		for (int i = width-1; i >= 0; i--) {
			newx = view.getLocation().x + (i * 64 + (height-1) * 64);
			newy = view.getLocation().y + (i * 32 - (height-1) * 32);
			for (int b = height-1; b >= 0; b--) {
				int curTileX = i;
				int curTileY = b;
				Tile tile = getTile(curTileX, curTileY);
				TileOverlay tileOverlay = getTileOverlay(curTileX, curTileY);

				// draws the tile
				int tileImageIndex = tile.getImageIndex();
				int tileOverlayIndex = tile.getOverlayImageIndex();
				if(tileImageIndex == MainGame.GRAB_NEAREST_TILE_INDEX) {
					boolean done = false;
					for(int checkX=curTileX-1; checkX<=curTileX + 1; checkX++) {
						for(int checkY=curTileY-1; checkY<=curTileY+1; checkY++) {
							if(checkX == curTileX || checkY == curTileY || done) { continue; }
							if(checkX < 0 || checkX > width-1 || checkY < 0 || checkY > height-1) { continue; }
							
							Tile toCheck = getTile(checkX, checkY);
							if(toCheck.canBeOccupied()) {
								tile.setImageIndex(toCheck.getImageIndex(), true);
								done = true;
							}
						}
					}
					if(!done) tile.setImageIndex(MainGame.SAVANNAH_TILE_INDEX, true);
				}
				
				g.drawImage(MainGame.tileImages[tile.getImageIndex()], newx, newy, null);
				if(tile.getRevealed() && tileOverlayIndex != -1) {
					g.drawImage(MainGame.tileImages[tileOverlayIndex], newx, newy, null);
				}
				g.drawImage(MainGame.tileImages[tileOverlay.getImageIndex()], newx, newy, null);
				
				// draws player marker
				if (curTileX == player1.getX() && curTileY == player1.getY()) {
					BufferedImage loadedimage = MainGame.tileImages[3];
					g.drawImage(loadedimage, newx + 43, newy + 12, null);
				}

				
				newx -= 64;
				newy += 32;
			}
		}
		
	}
	
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
	
	public TileOverlay getTileOverlay(int x, int y){
		return tileOverlays[x][y];
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
