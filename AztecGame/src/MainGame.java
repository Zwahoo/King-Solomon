import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainGame {

	private Map map; //The map containing all the tiles making up the world
	private Player player1; //The player
	private View view; //The "camera." What the player is seeing. Has a location, can move around.
	private InputManager input; // This registers all the mouse and keyboard

	//Death Row (To Be Deleted)
	private BufferedImage loadedimage;
	private BufferedImage[] images = new BufferedImage[10];
	
	//Starts the game, takes in the window frame, width, and height.
	public MainGame(gameframe frame, int width, int height) {
		//Create map
		map = new Map(10, 10);
		// Creates the player
		player1 = new Player(map);
		// Creates a view
		view = new View(width, height);
		// sets up mouse input stuff (INPUT MUST BE INSTANTIATED LAST)
		input = new InputManager(frame, this);
		
		
		// preloads images used for drawing dem sweet sweet grayfixs
		try {
			images[0] = ImageIO.read(new File("assets/water.png"));
			images[1] = ImageIO.read(new File("assets/jungle.png"));
			images[2] = ImageIO.read(new File("assets/mountain.png"));
			images[3] = ImageIO.read(new File("assets/marker.png"));
			images[4] = ImageIO.read(new File("assets/statusbar.png"));
			images[5] = ImageIO.read(new File("assets/unknown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initSelectangles();
	}
	
	//Sets up the selectangles for the map.
	private void initSelectangles() {
		// sets up initial tile selectangles
		int newx = 0;
		int newy = 0;
		for (int i = 0; i < map.width; i++) {
			newx = (i * 64);
			newy = (i * 32);
			for (int b = 0; b < map.height; b++) {
				map.getTile(i, b).setSelectangle(newx, newx, newx + 62,
						newx + 63, newx + 125, newx + 125, newx + 63,
						newx + 62, newy + 32, newy + 31, newy, newy, newy + 31,
						newy + 32, newy + 63, newy + 63);
				newx += 64;
				newy -= 32;
			}
		}
	}
	
	//Update all the various bits and pieces of the game world.
	public void update() {
		input.update();
	}
	
	//Draw any drawable objects in the game world.
	public void draw(Graphics g) {
		int newx;
		int newy;
		// loop for drawing each tile
		for (int i = 0; i < map.width; i++) {
			newx = view.getLocation().x + (i * 64);
			newy = view.getLocation().y + (i * 32);
			for (int b = 0; b < map.height; b++) {
				Tile tile = map.getTile(i, b);

				// if(!view.polygonIsInView(tile.selectangle)) continue; //Skip
				// if it isn't in view

				String tiletype = tile.getType();

				// draws the tile
				g.drawImage(images[tile.getImageIndex()], newx, newy, null);

				// draws player marker
				if (i == player1.getX() && b == player1.getY()) {
					loadedimage = images[3];
					g.drawImage(loadedimage, newx + 43, newy + 12, null);
				}

				newx += 64;
				newy -= 32;

			}

		}

	}
	
	//Returns the location of the view.
	public Point getViewLoc() { return view.getLocation();	}
	public Map getMap() { return map; }
	public Player getPlayer() { return player1; }
	public View getView() { return view; }
	
}
