package MainGame;

import java.awt.Color;
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
	
	public static HashMap<String, TileType> tileTypes;
	
	//Stats
	private static LinkedHashMap <String, Integer> stats;
	private static boolean statsChanged = false; // set this whenever the stats change
	
	//Event stuff
	public ArrayList<Event> moveToEvents = new ArrayList<Event>();
	public ArrayList<Event> restEvents = new ArrayList<Event>();
	public ArrayList<Event> investigateEvents = new ArrayList<Event>();
	
	public static final String FOOD_KEY = "Food";
	public static final String WATER_KEY = "Water";
	public static final String VALUABLES_KEY = "Valuables";
	public static final String AMMO_KEY = "Ammo";
	public static final String MEDICINE_KEY = "Medicine";
	public static final String MORALE_KEY = "Morale";
	public static final String STAMINA_KEY = "Stamina";
	public static final String PACK_ANIMALS_KEY = "Pack Animals";
	
	//Event Frequencies
	public static final double MOVE_TO_FREQUENCY = .5;
	public static final double INVESTIGATE_FREQUENCY = .25;
	public static final double REST_FREQUENCY = .25;
	
	//Party
	public static ArrayList<PartyMember> party;
	
	
	//Death Row (To Be Deleted)
	private BufferedImage loadedimage;
	public static BufferedImage[] images = new BufferedImage[10];
	
	public static EventDrawer eventDrawer = null;
	public static StartDayDrawer startDayDrawer = null;
	
	//Starts the game, takes in the window frame, width, and height.
	public MainGame(gameframe frame, int width, int height) throws IOException {
		
	    party = new ArrayList<PartyMember>();
		party.add(new PartyMember("The Gentleman", "Gentleman", 0,
				"Quite.", PartyMemberStats.AVERAGE_ABE_STATS));
		party.get(0).setGentleman(true);
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
		
		//Initialize the different tile types.
		initTileTypes();
		//Load in the events
		loadEvents();
		
		//Create map
		map = new Map("assets/testMap.png", this);
		//map = new Map(10, 10, this);
		// Creates the player
		player1 = new Player(map);
		player1.getCurrentTile().reveal();
		
		// Creates a view
		view = new View(new Point(0, height/2), width, height);
		
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
	
	private void initTileTypes() {
		tileTypes = new HashMap<String, TileType>();
	    tileTypes.put("desert", new TileType("desert", false, true, 5, new Color(255, 100, 0)));
	    tileTypes.put("oasis", new TileType("oasis", false, true, 5, new Color(255, 200, 0)));
	    tileTypes.put("jungle", new TileType("jungle", false, true, 1, new Color(0, 255, 0)));
	    tileTypes.put("water", new TileType("water", false, false, 0, new Color(0, 0, 255)));
	    tileTypes.put("savannah", new TileType("savannah", false, true, 5, new Color(255, 255, 0)));
	    tileTypes.put("mountain", new TileType("mountain", true, false, 2, new Color(100, 50, 0)));
	    tileTypes.put("highland", new TileType("highland", false, true, 5, new Color(50, 100, 0)));
	    tileTypes.put("solomonsMines", new TileType("solomonsMines", false, true, 5, new Color(255, 255, 255)));
	    tileTypes.put("village", new TileType("village", false, true, 5, new Color(100, 100, 100)));
	    
	    //Highlands can see past everything.
	    tileTypes.get("highland").canSeeAll = true;
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
		// Draw map
		map.draw(g, view, player1);
		
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
		ArrayList<Event> events = new ArrayList<Event>();
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
		      events.add(newEvent);
		    }
		  }
		  eventListCreator(events);
	}
	
	public ArrayList<Event> eventListCreator(ArrayList<Event> events){
		ArrayList<Event> typeEventList = new ArrayList<Event>();
		for (Event e : events){
			if (e.getEventType().equalsIgnoreCase("move")){
				moveToEvents.add(e);
			} else if (e.getEventType().equalsIgnoreCase("rest")){
				restEvents.add(e);
			} else if (e.getEventType().equalsIgnoreCase("investigate")){
				investigateEvents.add(e);
			} else{
				System.out.println("Event called " + e.getEventID() + " has an incorrect type.");
			}
		}
		return typeEventList;
	}

	public Event getRandomMoveToEvent(String loc) {
		//This code takes into account frequency of event occurance.
		double origRandom = Math.random();
		
		if (origRandom < MOVE_TO_FREQUENCY){
			ArrayList<Event> eventList = new ArrayList<Event>();
			for (Event e : moveToEvents){
				double randomNum = Math.random();
				if (randomNum < e.LIKELY_FREQUENCY && e.getFrequency().equalsIgnoreCase("likely")){
					eventList.add(e);
				} else if (randomNum < e.COMMON_FREQUENCY && e.getFrequency().equalsIgnoreCase("common")) {
					eventList.add(e);
				} else if (randomNum < e.UNCOMMON_FREQUENCY && e.getFrequency().equalsIgnoreCase("uncommon")) {
					eventList.add(e);
				} else if (randomNum < e.RARE_FREQUENCY && e.getFrequency().equalsIgnoreCase("rare")){
					eventList.add(e);
				} else if (randomNum < e.RARE_FREQUENCY && e.getFrequency().substring(0,1).equals("1")){
					eventList.add(e);
				}
			}
			int randomNum2 = (int)Math.floor(Math.random() * eventList.size());
			if (eventList.size() > 0){
				Event randomlyChosenEvent = eventList.get(randomNum2);
				if (randomlyChosenEvent.getFrequency().substring(0,1).equals("1")){
					for (Event e : moveToEvents){
						if (randomlyChosenEvent.getEventID().equalsIgnoreCase(e.getEventID())){
							moveToEvents.remove(e);
							break;
						}
					}
				}
				return randomlyChosenEvent;
			}
			else {
				return getRandomMoveToEvent(loc);
			}
		}
		else {
			return null;
		}
		
		//This commented block uses pure randomization.
//		int rand = (int)Math.floor(Math.random() * moveToEvents.size());
//		if(rand == moveToEvents.size()) rand--;
//		return moveToEvents.get(rand);
	}
	
	public Event getRandomInvestigateEvent(String loc) {
		double origRandom = Math.random();
		
		if (origRandom < INVESTIGATE_FREQUENCY){
			ArrayList<Event> eventList = new ArrayList<Event>();
			for (Event e : investigateEvents){
				double randomNum = Math.random();
				if (randomNum < e.LIKELY_FREQUENCY && e.getFrequency().equalsIgnoreCase("likely")){
					eventList.add(e);
				} else if (randomNum < e.COMMON_FREQUENCY && e.getFrequency().equalsIgnoreCase("common")) {
					eventList.add(e);
				} else if (randomNum < e.UNCOMMON_FREQUENCY && e.getFrequency().equalsIgnoreCase("uncommon")) {
					eventList.add(e);
				} else if (randomNum < e.RARE_FREQUENCY && e.getFrequency().equalsIgnoreCase("rare")){
					eventList.add(e);
				} else if (randomNum < e.RARE_FREQUENCY && e.getFrequency().substring(0,1).equals("1")){
					eventList.add(e);
				}
			}
			int randomNum2 = (int)Math.floor(Math.random() * eventList.size());
			if (eventList.size() > 0){
				Event randomlyChosenEvent = eventList.get(randomNum2);
				if (randomlyChosenEvent.getFrequency().substring(0,1).equals("1")){
					for (Event e : investigateEvents){
						if (randomlyChosenEvent.getEventID().equalsIgnoreCase(e.getEventID())){
							investigateEvents.remove(e);
							break;
						}
					}
				}
				return randomlyChosenEvent;
			}
			else {
				return getRandomInvestigateEvent(loc);
			}
		}
		else {
			return null;
		}
	}
	
	public Event getRandomRestEvent(String loc) {
		double origRandom = Math.random();
		
		if (origRandom < REST_FREQUENCY){
			ArrayList<Event> eventList = new ArrayList<Event>();
			for (Event e : restEvents){
				double randomNum = Math.random();
				if (randomNum < e.LIKELY_FREQUENCY && e.getFrequency().equalsIgnoreCase("likely")){
					eventList.add(e);
				} else if (randomNum < e.COMMON_FREQUENCY && e.getFrequency().equalsIgnoreCase("common")) {
					eventList.add(e);
				} else if (randomNum < e.UNCOMMON_FREQUENCY && e.getFrequency().equalsIgnoreCase("uncommon")) {
					eventList.add(e);
				} else if (randomNum < e.RARE_FREQUENCY && e.getFrequency().equalsIgnoreCase("rare")){
					eventList.add(e);
				} else if (randomNum < e.RARE_FREQUENCY && e.getFrequency().substring(0,1).equals("1")){
					eventList.add(e);
				}
			}
			int randomNum2 = (int)Math.floor(Math.random() * eventList.size());
			if (eventList.size() > 0){
				Event randomlyChosenEvent = eventList.get(randomNum2);
				if (randomlyChosenEvent.getFrequency().substring(0,1).equals("1")){
					for (Event e : restEvents){
						if (randomlyChosenEvent.getEventID().equalsIgnoreCase(e.getEventID())){
							restEvents.remove(e);
							break;
						}
					}
				}
				return randomlyChosenEvent;
			}
			else {
				return getRandomRestEvent(loc);
			}
		}
		else {
			return null;
		}
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
