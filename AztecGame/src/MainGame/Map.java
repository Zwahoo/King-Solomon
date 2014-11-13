package MainGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
//Zane Laughlin- map class- holds bunch of tiles
// hello


import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	
	int width;
	int height;
	private Tile[][] tiles;
		
	//random map generator
	public Map(int width, int height, MainGame mainGame) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		String a = null;
		Tile t;
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				int i = (int) ((Math.random()) * 3);
				if (i == 0){
					a = "water";
				}
				else if (i == 1){
					a = "jungle";
				}
				else if (i == 2){
					a = "mountain";
				}
				t = new Tile(a, x, y, mainGame.getRandomMoveToEvent(a));
				tiles[x][y] = t;
				//System.out.println(x + ", " + y);
			}
		}
	}

	//map which is created from image
	//filePath is in the form assets/filename.png
	public Map(String filePath, MainGame mainGame) throws IOException{
		File imageFile = new File(filePath);
		BufferedImage image = ImageIO.read(imageFile);
		
		width = image.getWidth();
		height = image.getHeight();
		
		tiles = new Tile[image.getWidth()][image.getHeight()];
		String a = null;
		Tile t;
		
		for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int clr = image.getRGB(x, y);
                int red = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;
                
                if ((red == 255 && green == 100) && blue == 0) {
                    a = "desert";
                } else if ((red == 255 && green == 200) && blue == 0){
                    a = "oasis";
                } else if ((red == 0 && green == 255) && blue == 0){
                	a = "jungle";
                } else if ((red == 0 && green == 0) && blue == 255){
                	a = "water";
                } else if ((red == 255 && green == 255) && blue == 0){
                	a = "savannah";
                } else if ((red == 100 && green == 50) && blue == 0){
                	a = "mountain";
                } else if ((red == 50 && green == 100) && blue == 0){
                	a = "highland";
                } else if ((red == 255 && green == 255) && blue == 255){
                	a = "solomonsMines";
                } else if ((red == 100 && green == 100) && blue == 100){
                	a = "village";
                } else {
                	System.out.println("Pixel in map not recognized. Tile defaulting to jungle at " + x + ", " + y);
                	a = "jungle";
                }
            	//System.out.println(a + " " + x + " " + y);
                t = new Tile(a, x, y, mainGame.getRandomMoveToEvent(a));
                tiles[x][y] = t;
            }
        }
	}
	
	public void draw(Graphics g, View view, Player player1) {
		int newx;
		int newy;
		for (int i = 0; i < width; i++) {
			newx = view.getLocation().x + (i * 64);
			newy = view.getLocation().y + (i * 32);
			for (int b = height-1; b >=0; b--) {
				int curTileX = i;
				int curTileY = height - b - 1;
				Tile tile = getTile(curTileX, curTileY);

				// if(!view.polygonIsInView(tile.selectangle)) continue; //Skip
				// if it isn't in view

				// draws the tile
				g.drawImage(MainGame.images[tile.getImageIndex()], newx, newy, null);

				// draws player marker
				if (curTileX == player1.getX() && curTileY == player1.getY()) {
					BufferedImage loadedimage = MainGame.images[3];
					g.drawImage(loadedimage, newx + 43, newy + 12, null);
				}

				
				newx += 64;
				newy -= 32;
			}
		}
		
	}
	
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
}
