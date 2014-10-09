package MainGame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;

import Components.Button;
import Components.StatsBar;
import Components.Textbox;

public class MainGame {

	private Map map; //The map containing all the tiles making up the world
	private Player player1; //The player
	private View view; //The "camera." What the player is seeing. Has a location, can move around.
	private InputManager input; // This registers all the mouse and keyboard

	Rectangle drawRect;
	
	StatsBar statsBar;
	int statBarWidth;
	int statBarHeight;
	
	//Stats
	private static HashMap <String, Integer> partyStats;
	private static boolean partyStatsChanged = false; // set this whenever the stats change
	
	public static final String FOOD_KEY = "Food";
	public static final String WATER_KEY = "Water";
	public static final String VALUABLES_KEY = "Valuables";
	public static final String AMMO_KEY = "Ammo";
	public static final String MEDICINE_KEY = "Medicine";
	public static final String MORALE_KEY = "Morale";
	public static final String HUNGER_KEY = "Hunger";
	public static final String THIRST_KEY = "Thirst";
	public static final String STAMINA_KEY = "Stamina";
	public static final String PACK_ANIMALS_KEY = "Pack Animals";
	
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

		//Sets the stats of the party
		initStats();
		
		statBarWidth = frame.getWidth();
		statBarHeight = 40;
		statsBar = new StatsBar(getStatString(), statBarWidth, statBarHeight, input); // create stats bar
		
		// preloads images used for drawing dem sweet sweet grayfixs
		try {
			images[0] = ImageIO.read(new File("assets/water.png"));
			images[1] = ImageIO.read(new File("assets/jungle.png"));
			images[2] = ImageIO.read(new File("assets/mountain.png"));
			images[3] = ImageIO.read(new File("assets/marker.png"));
			images[4] = ImageIO.read(new File("assets/statusbar.png"));
			images[5] = ImageIO.read(new File("assets/unknown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initSelectangles();
	}
	
	private void initStats() {
		partyStats = new HashMap<String, Integer>();
		setPartyStat(FOOD_KEY, 0);
		setPartyStat(WATER_KEY, 0);
		setPartyStat(AMMO_KEY, 0);
		setPartyStat(MEDICINE_KEY, 0);
		setPartyStat(VALUABLES_KEY, 0);
		setPartyStat(PACK_ANIMALS_KEY, 0);
		setPartyStat(MORALE_KEY, 0);
		setPartyStat(HUNGER_KEY, 0);
		setPartyStat(THIRST_KEY, 0);
		setPartyStat(STAMINA_KEY, 0);
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
		if (partyStatsChanged) {
			statsBar.setText(getStatString());
			partyStatsChanged = false;
		}
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
		//statsBar.setText(getStatString());
		//See logic in update() method for when stats bar text gets updates
        statsBar.draw(g);
	}
	
	//Converts the HashMap of Stats and Values into a set and then into a string
	//Edit this to change what gets printed in the stat bar
	private static String getStatString() {
		String retVal = "";
		for( String s : partyStats.keySet()) {
			retVal += s + ": " + partyStats.get(s) + " | ";
		}
		retVal = retVal.substring(0, retVal.length() - 3); //Remove the last bar.
		return retVal;
	}
	
	
	//Returns the location of the view.
	public Point getViewLoc() { return view.getLocation();	}
	public Map getMap() { return map; }
	public Player getPlayer() { return player1; }
	public View getView() { return view; }
	//Returns a clone, so the stats can't be modified using the get method.
	public HashMap<String, Integer> getStats() {
		return (HashMap<String, Integer>) partyStats.clone();
	}
	//Sets the stat, and raises the partyStatsChanged flag.
	public static void setPartyStat(String statName, int val) {
		partyStats.put(statName, val);
		partyStatsChanged = true;
	}
	//Sets the stat, and raises the partyStatsChanged flag.
		public static void incPartyStat(String statName, int val) {
			if(!partyStats.containsKey(statName)) {
				System.out.println("Couldn't find stat " + statName + " to increment!");
				return;
			}
			partyStats.put(statName, partyStats.get(statName) + val);
			partyStatsChanged = true;
		}
	
	
}
