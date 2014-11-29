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
	//--------------- CONSTNTS ---------------
	//Day Modes
	public static final Integer START_DAY_MODE = 0; // choose move, investigate, or rest
	public static final Integer MOVEMENT_MODE = 1; // movement on map
	public static final Integer EVENT_MODE = 2; // investigation or resting
	//Stat Keys
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
	//TODO Change to ~.75 and ~.25 once null events are written
	public static final double INVESTIGATE_FREQUENCY = 1;
	public static final double REST_FREQUENCY = 1;
	//Tile Image Index Locations
	public static final int WATER_TILE_INDEX = 0;
	public static final int JUNGLE_TILE_INDEX = 1;
	public static final int MOUNTAIN_TILE_INDEX = 2;
	public static final int DESERT_TILE_INDEX = 12;
	public static final int HIGHLAND_TILE_INDEX = 13;
	public static final int KING_SOLOMONS_MINES_TILE_INDEX = 14;
	public static final int OASIS_TILE_INDEX = 15;
	public static final int SAVANNAH_TILE_INDEX = 16;
	public static final int VILLAGE_TILE_INDEX = 17;
	//Other image index locations
	public static final int MARKER_INDEX = 3;
	public static final int STATUS_BAR_INDEX = 4;
	public static final int UNKNOWN_INDEX = 5;
	public static final int RED_TINT_INDEX = 6;
	public static final int BLUE_TINT_INDEX = 7;
	public static final int DARK_TINT_INDEX = 8;
	public static final int BLANK_INDEX = 9;
	public static final int DARK_RED_TINT_INDEX = 10;
	public static final int DARK_BLUE_TINT_INDEX = 11;
	
	
	public static Map map; //The map containing all the tiles making up the world
	private static Player player1; //The player
	private static View view; //The "camera." What the player is seeing. Has a location, can move around.
	public static InputManager input; // This registers all the mouse and keyboard
	public static Integer currentMode = -1;
	
	BufferedImage bkgImg;
	
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
	public ArrayList<Event> miscEvents = new ArrayList<Event>();

	//Party
	public static ArrayList<PartyMember> oldParty;
	public static ArrayList<PartyMember> party = new ArrayList<PartyMember>();
	
	private BufferedImage loadedimage;
	public static BufferedImage[] images = new BufferedImage[20];
	
	public static EventDrawer eventDrawer = null;
	public static StartDayDrawer startDayDrawer = null;
	
	//Reverts the event back to something more closely resembling it's start state.
	public void resetAllVals() {
		map = null;
		player1 = null;
		view = null;
		input = null;
		currentMode = -1;
		oldParty = null;
		party.clear();
		images = new BufferedImage[20];
		eventDrawer = null;
		startDayDrawer = null;
	}
	
	//Starts the game, takes in the window frame, width, and height.
	public MainGame(gameframe frame, int width, int height, HashMap<String, Integer> gentStats, HashMap<String, PartyMember> theParty) throws IOException {
		
		resetAllVals();
		
		//possibleParty = new ArrayList<PartyMember>();
		
//		for (String e : possibleParty.keySet()){
//			addPartyMemberToParty(possibleParty.get(e));
//		}
		
		//bkgImg = ImageIO.read(new File("assets/BkgImg.png"));
		
		PartyMember gentleman = new PartyMember("The Gentleman", "Gentleman", 0, "Quite.", "assets/Portraits/MemberImage.png", gentStats);
		gentleman.setGentleman(true);
		addPartyMemberToParty(gentleman);
		for(PartyMember member: theParty.values()) {
			addPartyMemberToParty(member);
		}
		
	    oldParty = new ArrayList<PartyMember>();
	    oldParty.add(new PartyMember("The Gentleman", "Gentleman", 0,
				"Quite.", "assets/Portraits/MemberImage.png", PartyMemberStats.AVERAGE_ABE_STATS));
	    oldParty.get(0).setGentleman(true);
	    oldParty.add(new PartyMember("Happy Hunter", "Hunter", 100,
				"Likes cooking meat and long walks on the savannah.", "assets/Portraits/MemberImage.png",  PartyMemberStats.HAPPY_HUNTER_STATS));
	    oldParty.add(new PartyMember("Merry Mercenary", "Mercenary", 100,
				"Was once payed $100,000 to kill the Queen. Married her instead.", "assets/Portraits/MemberImage.png",  PartyMemberStats.MERRY_MERCENARY_STATS));
	    oldParty.add(new PartyMember("Nifty Naturalist", "Naturalist", 100,
				"Once saved six men using only a single leaf of poison ivy, and three blades of grass.", "assets/Portraits/MemberImage.png",  PartyMemberStats.NIFTY_NATURALIST_STATS));
	    oldParty.add(new PartyMember("Marvelous Missionary", "Missionary", 100,
				"Holds the current leading Convert-to-Failure ratio in the entire South African Pro Missionary League.", "assets/Portraits/MemberImage.png",  PartyMemberStats.MARVELOUS_MISSIONARY_STATS));
	    oldParty.add(new PartyMember("Exuberant Explorer", "Explorer", 100,
				"Made the world's first Atlas at age 6.", "assets/Portraits/MemberImage.png", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	    oldParty.add(new PartyMember("Giddy Guide", "Guide", 100,
				"He's Giddy!", "assets/Portraits/MemberImage.png", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
		
		//Initialize the different tile types.
		initTileTypes();
		//Load in the events
		loadEvents();
		
		//Create map
		map = new Map("assets/Map.png", this);
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
			images[WATER_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/water.png"));
			images[JUNGLE_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/newJungle.png"));
			images[MOUNTAIN_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/newMountains.png"));
			images[MARKER_INDEX] = ImageIO.read(new File("assets/marker.png"));
			images[STATUS_BAR_INDEX] = ImageIO.read(new File("assets/statusbar.png"));
			images[UNKNOWN_INDEX] = ImageIO.read(new File("assets/Tiles/unknown.png"));
			images[RED_TINT_INDEX] = ImageIO.read(new File("assets/redTint.png"));
			images[BLUE_TINT_INDEX] = ImageIO.read(new File("assets/blueTint.png"));
			images[DARK_TINT_INDEX] = ImageIO.read(new File("assets/darkTint.png"));
			images[BLANK_INDEX] = ImageIO.read(new File("assets/blank.png"));
			images[DARK_RED_TINT_INDEX] = ImageIO.read(new File("assets/darkRedTint.png"));
			images[DARK_BLUE_TINT_INDEX] = ImageIO.read(new File("assets/darkBlueTint.png"));
			images[DESERT_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/desert.png"));
			images[HIGHLAND_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/highland.png"));
			images[KING_SOLOMONS_MINES_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/kingSolomonsMines.png"));
			images[OASIS_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/oasis.png"));
			images[SAVANNAH_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/savannah.png"));
			images[VILLAGE_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/village.png"));
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
	    tileTypes.put("desert", new TileType("desert", false, true, DESERT_TILE_INDEX, new Color(255, 100, 0)));
	    tileTypes.put("oasis", new TileType("oasis", false, true, OASIS_TILE_INDEX, new Color(255, 200, 0)));
	    tileTypes.put("jungle", new TileType("jungle", true, true, JUNGLE_TILE_INDEX, new Color(0, 255, 0)));
	    tileTypes.put("water", new TileType("water", false, false, WATER_TILE_INDEX, new Color(0, 0, 255)));
	    tileTypes.put("savannah", new TileType("savannah", false, true, SAVANNAH_TILE_INDEX, new Color(255, 255, 0)));
	    tileTypes.put("mountain", new TileType("mountain", true, false, MOUNTAIN_TILE_INDEX, new Color(100, 50, 0)));
	    tileTypes.put("highland", new TileType("highland", false, true, HIGHLAND_TILE_INDEX, new Color(50, 100, 0)));
	    tileTypes.put("solomonsMines", new TileType("solomonsMines", false, true, KING_SOLOMONS_MINES_TILE_INDEX, new Color(255, 255, 255)));
	    tileTypes.put("village", new TileType("village", false, true, VILLAGE_TILE_INDEX, new Color(100, 100, 100)));
	    
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
				map.getTileOverlay(i, b).setSelectangle(newx, newx, newx + 62,
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
		for (int x = 0; x < map.getWidth(); x++){
			for (int y = 0; y < map.getHeight(); y++){
				map.getTileOverlay(x, y).update();
			}
		}
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
		
		if(bkgImg != null) {
			g.drawImage(bkgImg, 0, 0, gameframe.windowWidth, gameframe.windowHeight, null);
		}
		
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
	
	public static void addPartyMemberToParty(PartyMember pm){
		party.add(pm);
	}
	
	public static void killPartyMember(PartyMember pm){
		party.remove(pm);
	}
	
	//Returns the location of the view.
	public static Point getViewLoc() { return view.getLocation();	}
	public Map getMap() { return map; }
	public static Player getPlayer() { return player1; }
	public View getView() { return view; }
	//Returns a clone, so the stats can't be modified using the get method.
	@SuppressWarnings("unchecked")
	public static HashMap<String, Integer> getStats() {
		return (LinkedHashMap<String, Integer>) stats.clone();
	}
	public static ArrayList<PartyMember> getParty(){ return party; }
	//Sets the stat, and raises the partyStatsChanged flag.
	public static void setPartyStat(String statName, int val) {
		if (statName.equalsIgnoreCase("Morale")){
			stats.put(statName, val);
		}else if (val < 0){
			stats.put(statName, 0); //No negative stats, defaults to 0
		}
		else {
			stats.put(statName, val);
		}
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
		int ran1 = (int)Math.floor(Math.random() * (party.size()));
		if (party.get(ran1).getStat(statName) + val < 0){
			party.get(ran1).setStat(statName, 0);
		}
		else {
			party.get(ran1).incStat(statName, val);
		}
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
			} else if (e.getEventType().equalsIgnoreCase("other")){
				miscEvents.add(e);
			} else {
				System.out.println("Event called " + e.getEventID() + " has an incorrect type called " + e.getEventType() + ".");
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

	public static void responseEffect(int result, ResponseOption r) {
		String[] resourceKeys = {
				FOOD_KEY, WATER_KEY, VALUABLES_KEY,
				AMMO_KEY, MEDICINE_KEY, MORALE_KEY,
				STAMINA_KEY, PACK_ANIMALS_KEY
		};
		
		//Need to change response option to have these fields and update them
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
		
		if (result == 0){
			partyStatChange.addAll(r.getLosePartyStatChange());
			resourceChange.addAll(r.getLoseResourceChange());
			for (int i = 0; i < partyStatChange.size(); i++){
				partyStatChange.set(i, -partyStatChange.get(i));
			}
			for (int i = 0; i < resourceChange.size(); i++){
				resourceChange.set(i, -resourceChange.get(i));
			}
		} else if (result == 1){
			for (int i = 0; i < resourceKeys.length; i++){
				resourceChange.add((long) 0);
			}
			for (int c = 0; c < partyStatKeys.length; c++){
				partyStatChange.add((long) 0);
			}
		} else if (result == 2){
			partyStatChange.addAll(r.getWinPartyStatChange());
			resourceChange.addAll(r.getWinResourceChange());
		}
		
		ArrayList<Long> resourceCost = new ArrayList<Long>();
		resourceCost.addAll(r.getCost());
		
		for (int i = 0; i < resourceChange.size(); i++){
			resourceChange.set(i, resourceChange.get(i) - resourceCost.get(i));
		}
		
		for (int i = 0; i < resourceKeys.length - 1; i++){
			incPartyStat(resourceKeys[i], resourceChange.get(i).intValue());
		}
		for (int c = 0; c < partyStatKeys.length; c++){
			incRandomPersonStat(partyStatKeys[c], partyStatChange.get(c).intValue());
		}
		
		if (r.isKillPersonLose() && result == 0){
			if (r.getRewardDisperseLose() == 0){
				r.killRandomMember();
			} else if (r.getRewardDisperseLose() == 1){
				r.killSelectedMember();
			} else if (r.getRewardDisperseLose() == 2){
				r.killRandomMember();
			} else {
				r.killSelectedMember();
			}
		} else if (r.isKillPersonPass() && result == 1){
			r.killSelectedMember();
		} else if (r.isKillPersonWin() && result == 2){
			if (r.getRewardDisperseWin() == 0){
				r.killRandomMember();
			} else if (r.getRewardDisperseWin() == 1){
				r.killSelectedMember();
			} else if (r.getRewardDisperseWin() == 2){
				r.killRandomMember();
			} else {
				r.killSelectedMember();
			}
		}
		
		resourceChange.clear();
		partyStatChange.clear();
}

	public static void closeEvent() {
		System.out.println("Closing Time");
		eventDrawer.destroyer();
		currentMode = START_DAY_MODE;
		eventDrawer = null;
		startDayDrawer = new StartDayDrawer();
	}

	public static void closeStartDay(Integer newmode, int startDayChoice) {
		currentMode = newmode;
		if (startDayChoice == 1) {
			MainGame.launchEvent(MainGame.player1.getCurrentTile().getInvestigateEvent(), MainGame.party);
		} else if (startDayChoice == 2) {
			MainGame.launchEvent(MainGame.player1.getCurrentTile().getRestEvent(), MainGame.party);
		}
		startDayDrawer.destroyer();
		startDayDrawer = null;
	}
	
	public static boolean checkStat(Long partyStat, Long partyRequirement){
		
		return false;
	}
	
	public static boolean checkResource(){
		
		return false;
	}
	
	public static ArrayList<Long> getTotalCurrentPartyStats(){
		PartyMember keyMan = party.get(0);
		String[] partyStatKeys = {
				keyMan.MARKSMANSHIP_KEY, keyMan.PERCEPTION_KEY,
				keyMan.TACTICS_KEY, keyMan.LOYALTY_KEY,
				keyMan.AGILITY_KEY, keyMan.STRENGTH_KEY,
				keyMan.DIPLOMACY_KEY, keyMan.KNOWLEDGE_KEY
		};
		ArrayList<Long> totalCurrentPartyStats = new ArrayList<Long>();
		for (int i = 0; i < partyStatKeys.length; i++){
			totalCurrentPartyStats.add((long)0);
		}
		for (PartyMember e : party){
			for (int i = 0; i < partyStatKeys.length; i++){
				totalCurrentPartyStats.set(i, totalCurrentPartyStats.get(i) + (long)(e.getStat(partyStatKeys[i])));
			}
		}
		return totalCurrentPartyStats;
	}
	
	public static boolean isResponsePossible(ResponseOption ro){
		
		ArrayList<Long> partyStatRequirements = new ArrayList<Long>();
		ArrayList<Long> resourceCosts = new ArrayList<Long>();
		
		partyStatRequirements.addAll(ro.getRequirements());
		resourceCosts.addAll(ro.getCost());
		
		ArrayList<Long> totalCurrentPartyStats = new ArrayList<Long>();
		totalCurrentPartyStats.addAll(getTotalCurrentPartyStats());
		
		//Key Chain
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
		
		
		
		boolean reqMet = true;
		boolean costsMet = true;
		
		for (int i = 0; i < partyStatRequirements.size(); i++){
			if (totalCurrentPartyStats.get(i) < partyStatRequirements.get(i)){
				reqMet = false;
			}
		}
		
		for (int i = 0; i < resourceCosts.size(); i++){
			if (stats.get(resourceKeys[i]) < resourceCosts.get(i)){
				costsMet = false;
			}
		}
		
		if (!(reqMet && costsMet)){
			return false;
		} else {
			return true;
		}
	}
	
	
}
