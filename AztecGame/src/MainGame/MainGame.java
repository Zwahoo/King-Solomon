package MainGame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

import Components.StatsBar;

public class MainGame {

	private Map map; //The map containing all the tiles making up the world
	private Player player1; //The player
	private View view; //The "camera." What the player is seeing. Has a location, can move around.
	public static InputManager input; // This registers all the mouse and keyboard
	public static Integer currentMode;
	
	public static final Integer START_DAY_MODE = 0; // choose move, investigate, or rest
	public static final Integer MOVEMENT_MODE = 1; // movement on map
	public static final Integer EVENT_MODE = 2; // investigation or resting

	Rectangle drawRect;
		
	StatsBar statsBar;
	int statBarWidth;
	static int statBarHeight;
	
	//Stats
	private static LinkedHashMap <String, Integer> stats;
	private static boolean statsChanged = false; // set this whenever the stats change
	
	//Event stuff
	public ArrayList<Event> events;
	
	public static final String FOOD_KEY = "Food";
	public static final String WATER_KEY = "Water";
	public static final String VALUABLES_KEY = "Valuables";
	public static final String AMMO_KEY = "Ammo";
	public static final String MEDICINE_KEY = "Medicine";
	public static final String MORALE_KEY = "Morale";
	public static final String STAMINA_KEY = "Stamina";
	public static final String PACK_ANIMALS_KEY = "Pack Animals";
	
	//Party
	public static ArrayList<PartyMember> party;
	
	//Death Row (To Be Deleted)
	private BufferedImage loadedimage;
	private BufferedImage[] images = new BufferedImage[10];
	
	public static EventDrawer eventDrawer = null;
	public static StartDayDrawer startDayDrawer = null;
	
	//Starts the game, takes in the window frame, width, and height.
	public MainGame(gameframe frame, int width, int height) throws IOException {
		
		party = new ArrayList<PartyMember>();
		party.add(new PartyMember("The Gentleman", "Gentleman", 0,
				"Quite.", PartyMemberStats.AVERAGE_ABE_STATS));
		party.add(new PartyMember("Happy Hunter", "Hunter", 100,
				"Likes cooking meat and long walks on the savannah.", PartyMemberStats.HAPPY_HUNTER_STATS));
		party.add(new PartyMember("Merry Mercenary", "Mercenary", 100,
				"Was once payed $100,000 to kill the Queen. Married her instead.", PartyMemberStats.MERRY_MERCENARY_STATS));
		party.add(new PartyMember("Nifty Naturalist", "Naturalist", 100,
				"Once saved six men using only a single leaf of poison ivy, and three blades of grass.", PartyMemberStats.NIFTY_NATURALIST_STATS));
		party.add(new PartyMember("Marvelous Missionary", "Missionary", 100,
				"Holds the current leading Convert-to-Failure ratio in the entire South African Pro Missionary League.", PartyMemberStats.MARVELOUS_MISSIONARY_STATS));
		party.add(new PartyMember("Exuberant Explorer", "Explorer", 100,
				"Made the world's first Atlas at age 6.", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
		party.add(new PartyMember("Giddy Guide", "Guide", 100,
				"He's Giddy!", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
		
		//Load in the events
		events = new ArrayList<Event>();
		loadEvents();
		
		//Create map
		map = new Map("assets/testMap.png", this);
//		map = new Map(10, 10, this);
		// Creates the player
		player1 = new Player(map);
		player1.getCurrentTile().reveal();
		
		// Creates a view
		view = new View(width, height);
		
		// sets up mouse input stuff (INPUT MUST BE INSTANTIATED LAST)
		input = new InputManager(frame, this);

		//Sets the stats of the party
		initStats();
		
		
		
		statBarWidth = frame.getWidth();
		statBarHeight = (int) ((gameframe.windowHeight)*.05);
		statsBar = new StatsBar(getStatString(), statBarWidth, statBarHeight, input); // create stats bar
		
		// preloads images used for drawing dem sweet sweet grayfixs
		try {
			images[0] = ImageIO.read(new File("assets/water.png"));
			images[1] = ImageIO.read(new File("assets/newJungle.png"));
			images[2] = ImageIO.read(new File("assets/newMountains.png"));
			images[3] = ImageIO.read(new File("assets/marker.png"));
			images[4] = ImageIO.read(new File("assets/statusbar.png"));
			images[5] = ImageIO.read(new File("assets/unknown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initSelectangles();
		
		currentMode = START_DAY_MODE; // temporary, should be BEGIN_DAY_MODE
		startDayDrawer = new StartDayDrawer();

		// //CHECKPOINT
		
		
	}
	
	private void initStats() {
		stats = new LinkedHashMap<String, Integer>();
		setPartyStat(MORALE_KEY, 100);
		setPartyStat(STAMINA_KEY, 100);
		setPartyStat(FOOD_KEY, 20);
		setPartyStat(WATER_KEY, 20);
		setPartyStat(AMMO_KEY, 10);
		setPartyStat(MEDICINE_KEY, 10);
		setPartyStat(VALUABLES_KEY, 0);
		setPartyStat(PACK_ANIMALS_KEY, 0);
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
		if (statsChanged) {
			statsBar.setText(getStatString());
			statsChanged = false;
		}
		if (eventDrawer!=null)
			eventDrawer.update();
		if (startDayDrawer!=null)
			startDayDrawer.update();
	}
	
	//Draw any drawable objects in the game world.
	public void draw(Graphics g) {
		int newx;
		int newy;
		// loop for drawing each tile
		for (int i = 0; i < map.width; i++) {
			newx = view.getLocation().x + (i * 64);
			newy = view.getLocation().y + (i * 32);
			for (int b = map.height-1; b >=0; b--) {
				int curTileX = i;
				int curTileY = map.height - b - 1;
				Tile tile = map.getTile(curTileX, curTileY);

				// if(!view.polygonIsInView(tile.selectangle)) continue; //Skip
				// if it isn't in view

				// draws the tile
				g.drawImage(images[tile.getImageIndex()], newx, newy, null);

				// draws player marker
				if (curTileX == player1.getX() && curTileY == player1.getY()) {
					loadedimage = images[3];
					g.drawImage(loadedimage, newx + 43, newy + 12, null);
				}

				newx += 64;
				newy -= 32;

			}

		}
		//statsBar.setText(getStatString());
		if (eventDrawer!=null)
			eventDrawer.draw(g);
		//See logic in update() method for when stats bar text gets updates
		if (startDayDrawer!=null)
			startDayDrawer.draw(g);
        statsBar.draw(g);
	}
	
	//Converts the HashMap of Stats and Values into a set and then into a string
	//Edit this to change what gets printed in the stat bar
	private static String getStatString() {
		String retVal = "";
		for( String s : stats.keySet()) {
			retVal += s + ": " + stats.get(s) + " | ";
		}
		retVal = retVal.substring(0, retVal.length() - 3); //Remove the last bar.
		return retVal;
	}
	
	
	public void handleMoveStatChanges() {
		incPartyStat(FOOD_KEY, -1);
		incPartyStat(WATER_KEY, -1);
		incPartyStat(STAMINA_KEY, -10);
	}
	
	
	//Returns the location of the view.
	public Point getViewLoc() { return view.getLocation();	}
	public Map getMap() { return map; }
	public Player getPlayer() { return player1; }
	public View getView() { return view; }
	//Returns a clone, so the stats can't be modified using the get method.
	@SuppressWarnings("unchecked")
	public static HashMap<String, Integer> getStats() {
		return (LinkedHashMap<String, Integer>) stats.clone();
	}
	public static ArrayList<PartyMember> getParty(){ return party; }
	//Sets the stat, and raises the partyStatsChanged flag.
	public static void setPartyStat(String statName, int val) {
		if(val < 0) return; //No negative stats
		stats.put(statName, val);
		statsChanged = true;
	}
	//Sets the stat, and raises the partyStatsChanged flag.
		public static void incPartyStat(String statName, int val) {
			if(!stats.containsKey(statName)) {
				System.out.println("Couldn't find stat " + statName + " to increment!");
				return;
			}
			setPartyStat(statName, stats.get(statName) + val);
		}
		
	public static void incRandomPersonStat(String statName, int val){
		int ran1 = (int)(Math.random() * (party.size()));
		party.get(ran1).incStat(statName, val);;
	}
	
	public void loadEvents(){
		File dir = new File("assets/events/");
		  ArrayList<File> directoryListing = new ArrayList<File>();
		  for (File e : dir.listFiles()){
			  if (!e.isHidden()){
				  directoryListing.add(e);
			  }
		  }
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      Event newEvent = MapToEvent.createEvent(FileToMap.createMap(child.getPath()));
		      if (newEvent.isPartyMemberTargeted()){
			      newEvent.setAffectedPartyMemberRandomly(party);
		      }
		      events.add(newEvent);
		    }
		  }
	}

	public Event getRandomEvent(String loc) {
		int rand = (int)Math.floor(Math.random() * events.size());
		if(rand == events.size()) rand--;
		return events.get(rand);
	}

	public static void launchEvent(Event e, ArrayList<PartyMember> presMembers) {
		currentMode = EVENT_MODE;
		eventDrawer = new EventDrawer(e, presMembers);
	}

	public static void closeEvent(String result, ResponseOption r) {
		String[] resourceKeys = {
				FOOD_KEY, WATER_KEY, VALUABLES_KEY,
				AMMO_KEY, MEDICINE_KEY, MORALE_KEY,
				STAMINA_KEY, PACK_ANIMALS_KEY
		};
		
		PartyMember keyMan = party.get(0);
		@SuppressWarnings("static-access")
		String[] partyStatKeys = {
				keyMan.MARKSMANSHIP_KEY, keyMan.PERCEPTION_KEY,
				keyMan.TACTICS_KEY, keyMan.LOYALTY_KEY,
				keyMan.AGILITY_KEY, keyMan.STRENGTH_KEY,
				keyMan.DIPLOMACY_KEY, keyMan.KNOWLEDGE_KEY
		};
		
		ArrayList<Long> resourceChange = new ArrayList<Long>();
		ArrayList<Long> partyStatChange = new ArrayList<Long>();
		
		if (result.equals("fail")){
			partyStatChange.addAll(r.getLosePartyStatChange());
			resourceChange.addAll(r.getLoseResourceChange());
		} else if (result.equals("pass")){
			for (int i = 0; i < resourceKeys.length; i++){
				resourceChange.add((long) 0);
			}
			for (int c = 0; c < partyStatKeys.length; c++){
				partyStatChange.add((long) 0);
			}
		} else if (result.equals("success")){
			partyStatChange.addAll(r.getWinPartyStatChange());
			resourceChange.addAll(r.getWinResourceChange());
		}
		for (int i = 0; i < resourceKeys.length - 1; i++){
			incPartyStat(resourceKeys[i], resourceChange.get(i).intValue());
		}
		for (int c = 0; c < partyStatKeys.length; c++){
			incRandomPersonStat(partyStatKeys[c], partyStatChange.get(c).intValue());
		}
		
		resourceChange.clear();
		partyStatChange.clear();
}

	public static void closeEvent() {
		eventDrawer.destroyer();
		currentMode = START_DAY_MODE;
		eventDrawer = null;
		startDayDrawer = new StartDayDrawer();
	}

	public static void closeStartDay(Integer newmode) {
		currentMode = newmode;
		//also, launch event if necessary.
		startDayDrawer = null;
	}
	
	
}
