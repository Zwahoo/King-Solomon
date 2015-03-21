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
	//--------------- CONSTANTS ---------------
	
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
	public static final double INVESTIGATE_FREQUENCY = 0.75;
	public static final double REST_FREQUENCY = 0.25;
	//Tile Image Index Locations
	public static final int GRAB_NEAREST_TILE_INDEX = -1;
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
	//Minimum Number of Party Members Required for Game Over
	public static final int MIN_PARTY_SIZE = 2;

	public static final boolean DISCO_MODE = false;
	
	//-----------------------------------------


	private static Player player1; //The player
	private static View view; //The "camera." What the player is seeing. Has a location, can move around.
	public static Map map; //The map containing all the tiles making up the world
	public static InputManager input; // This registers all the mouse and keyboard
	private static Integer currentMode = -1;
	private static gameframe frame;
	private static boolean finalEvent = false; //Set to true when launching the final event.
	private static boolean kickedMemberThisTurn = false;

	BufferedImage bkgImg;

	Rectangle drawRect;

	StatsBar statsBar;
	int statBarWidth;
	static int statBarHeight;

	public static HashMap<String, TileType> tileTypes;

	private boolean launchedFinalEvent = false;

	//Stats
	private static LinkedHashMap <String, Integer> stats;
	private static boolean statsChanged = false; // set this whenever the stats change

	//Event stuff
	public static ArrayList<Event> moveToEvents = new ArrayList<Event>();
	public static ArrayList<Event> restEvents = new ArrayList<Event>();
	public static ArrayList<Event> investigateEvents = new ArrayList<Event>();
	public static ArrayList<Event> miscEvents = new ArrayList<Event>();
	//Holds separate lists of valid events for tiles that always need an event
	public static HashMap<TileType, ArrayList<Event>> alwaysMoveToEvents = new HashMap<TileType, ArrayList<Event>>();

	//Party
	public static ArrayList<PartyMember> party = new ArrayList<PartyMember>();
	public static PartyMember keyMan;

	//Tile images
	public static BufferedImage[] tileImages = new BufferedImage[20];

	//Menu Drawers
	public static EventDrawer eventDrawer = null;
	public static StartDayDrawer startDayDrawer = null;

	public static int dayCounter = 1;
	
	//Reverts the event back to something more closely resembling it's start state.
	public void resetAllVals() {
		map = null;
		player1 = null;
		view = null;
		removeInputManager();
		setCurrentMode(-1);
		party.clear();
		tileImages = new BufferedImage[20];
		eventDrawer = null;
		startDayDrawer = null;
		frame = null;
		finalEvent = false;
		kickedMemberThisTurn = false;
		dayCounter = 1;
	}

	public static void removeInputManager() {
		if(input != null) {
			input.closeMe();
			input = null;
		}
	}

	//Starts the game, takes in the window frame, width, and height.
	public MainGame(gameframe frame, int width, int height, HashMap<String, Integer> gentStats, HashMap<String, PartyMember> theParty) throws IOException {

		resetAllVals();

		bkgImg = ImageIO.read(new File("assets/BkgImg.png"));

		PartyMember gentleman = new PartyMember("The Gentleman", "Gentleman", 0, "Quite.", "assets/Portraits/MemberImage.png", gentStats);
		gentleman.setGentleman(true);
		addPartyMemberToParty(gentleman);
		for(PartyMember member: theParty.values()) {
			addPartyMemberToParty(member);
		}
		keyMan = gentleman;

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
		Point viewLoc = new Point((int)Math.round(-width*1.35), (-1*height)/2);
		view = new View(viewLoc, width, height);

		//Store the gameframe
		this.frame = frame;

		// sets up mouse input stuff (INPUT MUST BE INSTANTIATED LAST)
		input = new InputManager(frame, this);

		//Sets the stats of the party
		initStats();


		//Set up the stat bar
		statBarWidth = frame.getWidth();
		statBarHeight = (int) ((gameframe.windowHeight)*.05);
		statsBar = new StatsBar(getStatsBarString(), statBarWidth, statBarHeight, input); // create stats bar

		// preloads images used for drawing dem sweet sweet grayfixs
		preloadTileImages();

		initSelectangles();

		setCurrentMode(START_DAY_MODE); // temporary, should be BEGIN_DAY_MODE. Hah. Temporary. Yeah, right...
		startDayDrawer = new StartDayDrawer(); //Draw the start day menu
	}
	
	/**
	 * Loads the images in for each tile
	 */
	private void preloadTileImages() {
		try {
			tileImages[WATER_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/water.png"));
			tileImages[JUNGLE_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/newJungle.png"));
			tileImages[MOUNTAIN_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/newMountains.png"));
			tileImages[MARKER_INDEX] = ImageIO.read(new File("assets/marker.png"));
			tileImages[STATUS_BAR_INDEX] = ImageIO.read(new File("assets/statusbar.png"));
			tileImages[UNKNOWN_INDEX] = ImageIO.read(new File("assets/Tiles/unknown.png"));
			tileImages[RED_TINT_INDEX] = ImageIO.read(new File("assets/redTint.png"));
			tileImages[BLUE_TINT_INDEX] = ImageIO.read(new File("assets/blueTint.png"));
			tileImages[DARK_TINT_INDEX] = ImageIO.read(new File("assets/darkTint.png"));
			tileImages[BLANK_INDEX] = ImageIO.read(new File("assets/blank.png"));
			tileImages[DARK_RED_TINT_INDEX] = ImageIO.read(new File("assets/darkRedTint.png"));
			tileImages[DARK_BLUE_TINT_INDEX] = ImageIO.read(new File("assets/darkBlueTint.png"));
			tileImages[DESERT_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/desert.png"));
			tileImages[HIGHLAND_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/highland.png"));
			tileImages[KING_SOLOMONS_MINES_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/kingSolomonsMines.png"));
			tileImages[OASIS_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/oasis.png"));
			tileImages[SAVANNAH_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/savannah.png"));
			tileImages[VILLAGE_TILE_INDEX] = ImageIO.read(new File("assets/Tiles/village.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets up all possible types of tiles
	 * And the data needed for each.
	 */
	private void initTileTypes() {
		tileTypes = new HashMap<String, TileType>();
		tileTypes.put("desert", new TileType("Desert", false, true, false, DESERT_TILE_INDEX, new Color(255, 100, 0)));
		tileTypes.put("oasis", new TileType("Oasis", false, true, false, OASIS_TILE_INDEX, new Color(255, 200, 0)));
		tileTypes.put("jungle", new TileType("Jungle", true, true, false, JUNGLE_TILE_INDEX, new Color(0, 255, 0)));
		tileTypes.put("water", new TileType("Water", false, false, false, WATER_TILE_INDEX, new Color(0, 0, 255)));
		tileTypes.put("savannah", new TileType("Savannah", false, true, false, SAVANNAH_TILE_INDEX, new Color(255, 255, 0)));
		tileTypes.put("mountain", new TileType("Mountain", true, false, false, MOUNTAIN_TILE_INDEX, new Color(100, 50, 0)));
		tileTypes.put("highland", new TileType("Highland", false, true, false, HIGHLAND_TILE_INDEX, new Color(50, 100, 0)));
		tileTypes.put("solomonsMines", new TileType("KingSolomonsMines", false, true, true, KING_SOLOMONS_MINES_TILE_INDEX, new Color(255, 255, 255)));
		tileTypes.put("village", new TileType("Village", false, true, true, GRAB_NEAREST_TILE_INDEX, new Color(100, 100, 100)));
		
		//Highlands can see past everything.
		tileTypes.get("highland").canSeeAll = true;
		tileTypes.get("village").setOverlay(VILLAGE_TILE_INDEX);
		
		for(TileType type : tileTypes.values()) {
			if(type.alwaysHaveEvent) alwaysMoveToEvents.put(type, new ArrayList<Event>());
		}
	}

	/**
	 * Set initial party stats
	 */
	private void initStats() {
		stats = new LinkedHashMap<String, Integer>();
		setPartyStat(MORALE_KEY, 100);
		setPartyStat(STAMINA_KEY, 100);
		setPartyStat(FOOD_KEY, 60);
		setPartyStat(WATER_KEY, 60);
		setPartyStat(AMMO_KEY, 100);
		setPartyStat(MEDICINE_KEY, 10);
		setPartyStat(VALUABLES_KEY, 20);
		setPartyStat(PACK_ANIMALS_KEY, 0);
	}

	/**
	 * Sets up the selectangles for the map.
	 */
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

	/*
	 * Update all components of the game world.
	 */
	public void update() {
		//Update Input
		input.update();
		
		//Update tile overlays
		updateTileOverlay();
		
		//Update stat bar
		updateStatsBar();
		
		//Update menu drawers
		updateMenuDrawers();
		
		//Boot a member if morale is below their threshold
		checkMorale();
		
		//Check lose condiditions
		checkEndGameConditions();
	}
	
	//Draw any drawable objects in the game world.
	public void draw(Graphics g) {

		if(bkgImg != null) {
			g.drawImage(bkgImg, 0, 0, gameframe.windowWidth, gameframe.windowHeight, null);
		}

		// Draw map
		map.draw(g, view, player1);

		//statsBar.setText(getStatString());
		if (eventDrawer!=null) {
			eventDrawer.draw(g);
		}
		//See logic in update() method for when stats bar text gets updates
		if (startDayDrawer!=null) {
			startDayDrawer.draw(g);
		}
		statsBar.draw(g);
	}


	/**
	 * Updates the tile overlay for each tile
	 */
	private void updateTileOverlay() {
		for (int x = 0; x < map.getWidth(); x++){
			for (int y = 0; y < map.getHeight(); y++){
				map.getTileOverlay(x, y).update();
			}
		}
	}
	
	/**
	 * Updates the status of the stats bar.
	 */
	private void updateStatsBar() {
		if (statsChanged) {
			statsBar.setText(getStatsBarString());
			statsChanged = false;
		}
	}
	
	/**
	 * Update both event and start day drawers if they're active.
	 */
	private void updateMenuDrawers() {
		if (eventDrawer!=null) {
			eventDrawer.update();
		}
		if (startDayDrawer!=null) {
			startDayDrawer.update();
		}
	}
	
	/**
	 * Check if any party members should be booted
	 * Due to low morale.
	 */
	private void checkMorale() {
		if(!kickedMemberThisTurn && (getCurrentMode() == START_DAY_MODE)) {
			for(int i = 0; i < party.size(); i++) {
				PartyMember curMember = party.get(i);
				if(curMember.isGentleman() == false) {
					//if morale < -member.loyalty: boot member
					if(stats.get(MORALE_KEY) < (-1*curMember.getStat(PartyMember.LOYALTY_KEY))) {
						closeStartDay(EVENT_MODE, -1);
						HashMap eventMap = FileToMap.createMap("assets/events/PlayerLeaves.txt");
						Event memberGone = MapToEvent.createEvent(eventMap);
						memberGone.playJingles = false;
						launchEvent(memberGone, party, curMember, false);
						kickedMemberThisTurn = true;
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Checks if any end game conditions have been met.
	 */
	private void checkEndGameConditions() {
		//Too many party gone
		checkTooFewParty();
		
		//No food left
		checkFoodDeath();
		
		//No water left.
		checkWaterDeath();
	}
	
	/**
	 * Checks if player no longer has a large enough
	 * party to complete the game
	 */
	private void checkTooFewParty() {
		if(!launchedFinalEvent && (getCurrentMode() == START_DAY_MODE) && (party.size() < MIN_PARTY_SIZE)) {
			closeStartDay(EVENT_MODE, -1);
			HashMap eventMap = FileToMap.createMap("assets/events/Thomas_TempPartyGone.txt");
			Event partyGone = MapToEvent.createEvent(eventMap);
			partyGone.playJingles = false;
			launchFinalEvent(partyGone, party);
			frame.sound.stopSound();
			Sound loseSound = new Sound("assets/sounds/Fatality.wav", false);
			launchedFinalEvent = true;
		}
	}
	
	/**
	 * Checks if the player has no food left
	 * and, if so, ends the game.
	 */
	private void checkFoodDeath() {
		if(!launchedFinalEvent && (getCurrentMode() == START_DAY_MODE) && (getStats().get(FOOD_KEY) <= 0)) {
			closeStartDay(EVENT_MODE, -1);
			HashMap eventMap = FileToMap.createMap("assets/events/FoodEvent.txt");
			Event foodGone = MapToEvent.createEvent(eventMap);
			foodGone.playJingles = false;
			frame.sound.stopSound();
			Sound loseSound = new Sound("assets/sounds/Fatality.wav", false);
			launchFinalEvent(foodGone, party);
			launchedFinalEvent = true;
		}
	}
	
	/**
	 * Checks if the player has no water left
	 * and, if so, ends the game.
	 */
	private void checkWaterDeath() {
		if(!launchedFinalEvent && (getCurrentMode() == START_DAY_MODE) && (getStats().get(WATER_KEY) <= 0)) {
			closeStartDay(EVENT_MODE, -1);
			HashMap eventMap = FileToMap.createMap("assets/events/WaterEvent.txt");
			Event waterGone = MapToEvent.createEvent(eventMap);
			waterGone.playJingles = false;
			frame.sound.stopSound();
			Sound loseSound = new Sound("assets/sounds/Fatality.wav", false);
			launchFinalEvent(waterGone, party);
			launchedFinalEvent = true;
		}
	}
	
	/**
	 * Creates the string to display in the stats bar
	 * @return A string of stats followed by their values
	 */
	private static String getStatsBarString() {
		String retVal = "";
		for( String s : stats.keySet()) {
			retVal += s + ": " + stats.get(s) + " | ";
		}
		retVal = retVal.substring(0, retVal.length() - 3); //Remove the last bar.
		return retVal;
	}

	/**
	 * Handles the stat changes that need to happen for a new day.
	 * Decreases food, water, and stamina
	 */
	public static void handleNewDayStatChanges()
	{
		incPartyStat(FOOD_KEY, -1 * party.size());
		incPartyStat(STAMINA_KEY, -3);
		
		boolean isNextToWater = false;
		for (int i = 1; i <= 7 ; i+=2){
			if (MainGame.input.getPlayerMovementHandler().getNeighborTile(i) != null){
				if (MainGame.input.getPlayerMovementHandler().getNeighborTile(i).getType().getName().equalsIgnoreCase("water"))
					isNextToWater = true;
			}
		}
		if (!isNextToWater){
			incPartyStat(WATER_KEY, -1 * party.size());
		}
	}

	/**
	 * Adds the given party member to the party
	 * @param pm The party member to add
	 */
	public static void addPartyMemberToParty(PartyMember pm){
		party.add(pm);
	}

	/**
	 * Removes the given party member from the party
	 * @param pm The party member to remove
	 */
	public static void killPartyMember(PartyMember pm){
		party.remove(pm);
	}
	
	/**
	 * Increments the stat to by the given amount and raises
	 * the partyStatsChanged flag.
	 * @param statName Stat to set
	 * @param val The value to which to set the stat
	 */
	public static void incPartyStat(String statName, int val) {
		if(!stats.containsKey(statName)) {
			System.out.println("Couldn't find stat " + statName + " to increment!");
			return;
		}
		setPartyStat(statName, stats.get(statName) + val);
	}
	
	

	/**
	 * Loads all events in the events folder into the game
	 */
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
		createEventLists(events);
	}
	
	/**
	 * Separates out the given event list into
	 * the distinct move-to, rest, and investigate
	 * event lists.
	 * @param events The list of events to parse
	 */
	public void createEventLists(ArrayList<Event> events){
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
			
			if(e.eventFrequencies.get(e.getFrequency()) > 0 && e.getEventType().equals(Event.moveToString)) {
				for(TileType type : alwaysMoveToEvents.keySet()) {
					if(e.getPossibleLocations().contains(type.getName())) {
						alwaysMoveToEvents.get(type).add(e);
					}
				}
			}
		}
	}

	/**
	 * Checks if the list contains the given string, ignoring case
	 * @param list The list to traverse
	 * @param str The string to check for
	 * @return True if the list contains the given string (ignoring case) false otherwise.
	 */
	public static boolean containsIgnoreCase(ArrayList<String> list, String str) {
		for(String str2 : list) {
			if(str2.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Provides a randomly selected move-to event
	 * @param type The type of tile the event will run on
	 * @return A randomly selected move-to event
	 */
	public Event getRandomMoveToEvent(TileType type) {
		//This code takes into account frequency of event occurance.
		double origRandom = Math.random();
		
		return getRandomEvent(type, null, MOVE_TO_FREQUENCY, moveToEvents);
	}

	/**
	 * Provides a randomly selected investigate event
	 * @param loc The type of tile the event will run on
	 * @return A randomly selected investigate event
	 */
	public Event getRandomInvestigateEvent(TileType loc) {
		double origRandom = Math.random();
		HashMap defaultMap = FileToMap.createMap("assets/events/GenericInvestigate.txt");
		Event defaultEvent = MapToEvent.createEvent(defaultMap);
		
		return getRandomEvent(loc, defaultEvent, INVESTIGATE_FREQUENCY, investigateEvents);
	}

	/**
	 * Provides a randomly selected rest event
	 * @param loc The type of tile the event will run on
	 * @return A randomly selected rest event
	 */
	public Event getRandomRestEvent(TileType loc) {
		HashMap defaultMap = FileToMap.createMap("assets/events/genericRest.txt");
		Event defaultEvent = MapToEvent.createEvent(defaultMap);
		
		return getRandomEvent(loc, defaultEvent, REST_FREQUENCY, restEvents);
	}

	/**
	 * Provides a random event
	 * @param type The type of tile the event will run on.
	 * @param defaultEvent The default event to play if no other event is selected (null if none).
	 * @param nonDefaultFreq The chance (between 0 and 1) of having a non-default event.
	 * @param eventList List of events from which to choose
	 * @return A randomly selected event
	 */
	public Event getRandomEvent(TileType type, Event defaultEvent, double nonDefaultFreq, ArrayList<Event> eventList) {
		return getRandomEvent(type, defaultEvent, nonDefaultFreq, eventList, 0);
	}
	
	/**
	 * Provides a random event
	 * @param type The type of tile the event will run on.
	 * @param defaultEvent The default event to play if no other event is selected (null if none).
	 * @param nonDefaultFreq The chance (between 0 and 1) of having a non-default event.
	 * @param eventList List of events from which to choose
	 * @param recNum The number of times this method has recursed (caps at 10).
	 * @return A randomly selected event
	 */
	public static Event getRandomEvent(TileType type, Event defaultEvent, double nonDefaultFreq, ArrayList<Event> eventList, int recNum) {
		if(recNum > 100) {
			return defaultEvent; //Don't recurse infinitely
		}
		
		double origRandom = Math.random();
		
		if(type.alwaysHaveEvent) {
			eventList = alwaysMoveToEvents.get(type);
		}
		
		if ((origRandom < nonDefaultFreq) || ((defaultEvent == null) && type.alwaysHaveEvent)){
			ArrayList<Event> tmpList = new ArrayList<Event>();
			//Adds events to temp list based on frequency
			for (Event e : eventList){
				if(!containsIgnoreCase(e.getPossibleLocations(), type.getName())) {
					continue;
				}
				double randomNum = Math.random();
				if ((randomNum < e.eventFrequencies.get(e.getFrequency()))){
					tmpList.add(e);
				} else if ((randomNum < e.eventFrequencies.get("Rare")) && e.getFrequency().substring(0,1).equals("1")){
					tmpList.add(e);
				}
			}
			//Randomly select an event from the temp list
			int randomNum2 = (int)Math.floor(Math.random() * tmpList.size());
			if (tmpList.size() > 0){
				Event randomlyChosenEvent = tmpList.get(randomNum2);
				//If only use once, remove from the list.
				if (randomlyChosenEvent.getFrequency().substring(0,1).equals("1")){
					for (Event e : eventList){
						if (randomlyChosenEvent.getEventID().equalsIgnoreCase(e.getEventID())){
							eventList.remove(e);
							break;
						}
					}
				}
				return randomlyChosenEvent;
			}
			else {
				return getRandomEvent(type, defaultEvent, nonDefaultFreq, eventList, recNum + 1);
			}
		}
		else {
			return defaultEvent;
		}
	}

	// --------------- LAUNCH EVENT METHODS ---------------
	/**
	 * Launch an event with a given set of party members
	 * @param e The event to launch
	 * @param presMembers The party members present for the event
	 */
	public static void launchEvent(Event e, ArrayList<PartyMember> presMembers) {
		launchEvent(e, presMembers, null, false);
	}
	/**
	 * Launch an event which will end the game
	 * @param e The event to launch
	 * @param presMembers The party members present for the event
	 */
	public static void launchFinalEvent(Event e, ArrayList<PartyMember> presMembers) {
		launchEvent(e, presMembers, null, true);
	}
	/**
	 * Launch an event which selects a player and will end the game.
	 * @param e The event to launch
	 * @param presMembers The party members present for the event
	 * @param toSelect The party member to select
	 */
	public static void launchFinalEventWithSelectedMember(Event e, ArrayList<PartyMember> presMembers, PartyMember toSelect) {
		launchEvent(e, presMembers, toSelect, true);
	}
	/**
	 * Launch an event
	 * @param e The event to launch
	 * @param presMembers The party members present for the event
	 * @param toSelect The party member to select (null if none)
	 * @param isFinalEvent Boolean indicating if this event should end the game. (True = end game. False = don't end game)
	 */
	public static void launchEvent(Event e, ArrayList<PartyMember> presMembers, PartyMember toSelect, boolean isFinalEvent) {
		boolean validevent = true;
		for (int i = 0; i < e.getReqParty().size(); i++) {
			if(!checkParty(e.getReqParty().get(i))){
				validevent = false;
			}
		}
		if (!validevent) { 
			e = newEvent(e.getEventType());
		}
		setCurrentMode(EVENT_MODE);
		eventDrawer = new EventDrawer(e, presMembers, toSelect);
		finalEvent = isFinalEvent;
	}
	// ----------------------------------------------------
	
	/**
	 * Manages the effects of selecting a response during an event.
	 * @param result Integer indicating whether the event was won or lost (0 = lose, 2 = win)
	 * @param selectedResponse Response option that was selected
	 */
	public static void responseEffect(int result, ResponseOption selectedResponse) {
		String[] resourceKeys = {
				FOOD_KEY, WATER_KEY, VALUABLES_KEY,
				AMMO_KEY, MEDICINE_KEY, MORALE_KEY,
				STAMINA_KEY, PACK_ANIMALS_KEY
		};

		//Need to change response option to have these fields and update them


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
			partyStatChange.addAll(selectedResponse.getLosePartyStatChange());
			resourceChange.addAll(selectedResponse.getLoseResourceChange());
			int i = 0;
			int j = 0;
			for (Long e : partyStatChange){
				partyStatChange.set(i, -1*e);
				i++;
			}
			for (Long c : resourceChange){
				resourceChange.set(j, -1*c);
				j++;
			}
		} else if (result == 1){
			for (int i = 0; i < resourceKeys.length; i++){
				resourceChange.add((long) 0);
			}
			for (int c = 0; c < partyStatKeys.length; c++){
				partyStatChange.add((long) 0);
			}
		} else if (result == 2){
			partyStatChange.addAll(selectedResponse.getWinPartyStatChange());
			resourceChange.addAll(selectedResponse.getWinResourceChange());
		}

		ArrayList<Long> resourceCost = new ArrayList<Long>();
		resourceCost.addAll(selectedResponse.getCost());

		for (int i = 0; i < resourceChange.size(); i++){
			resourceChange.set(i, resourceChange.get(i) - resourceCost.get(i));
		}

		for (int i = 0; i < (resourceKeys.length - 1); i++){
			incPartyStat(resourceKeys[i], resourceChange.get(i).intValue());
		}
		for (int c = 0; c < partyStatKeys.length; c++){
			incRandomPersonStat(partyStatKeys[c], partyStatChange.get(c).intValue());
		}

		if (selectedResponse.isKillPersonLose() && (result == 0)){
			if (selectedResponse.getRewardDisperseLose() == 0){
				selectedResponse.killRandomMember();
			} else if (selectedResponse.getRewardDisperseLose() == 1){
				selectedResponse.killSelectedMember();
			} else if (selectedResponse.getRewardDisperseLose() == 2){
				selectedResponse.killRandomMember();
			} else {
				selectedResponse.killSelectedMember();
			}
		} else if (selectedResponse.isKillPersonPass() && (result == 1)){
			selectedResponse.killSelectedMember();
		} else if (selectedResponse.isKillPersonWin() && (result == 2)){
			if (selectedResponse.getRewardDisperseWin() == 0){
				selectedResponse.killRandomMember();
			} else if (selectedResponse.getRewardDisperseWin() == 1){
				selectedResponse.killSelectedMember();
			} else if (selectedResponse.getRewardDisperseWin() == 2){
				selectedResponse.killRandomMember();
			} else {
				selectedResponse.killSelectedMember();
			}
		}

		resourceChange.clear();
		partyStatChange.clear();
	}

	/**
	 * Closes down the current event and starts the next day.
	 */
	public static void closeEvent() {

		//System.out.println("Closing Time");
		eventDrawer.destroyer();
		setCurrentMode(START_DAY_MODE);
		eventDrawer = null;
		startDayDrawer = new StartDayDrawer();

		if(finalEvent) {
			frame.returnGameToMenu();
		} 
	}

	/**
	 * Closes the start day menu and loads up a new game mode
	 * @param newmode New mode to load
	 * @param startDayChoice Which choice the player made in the start menu
	 */
	public static void closeStartDay(Integer newmode, int startDayChoice) {
		setCurrentMode(newmode);
		if (startDayChoice == 1) {
			MainGame.launchEvent(MainGame.player1.getCurrentTile().getInvestigateEvent(), MainGame.party);
		} else if (startDayChoice == 2) {
			MainGame.launchEvent(MainGame.player1.getCurrentTile().getRestEvent(), MainGame.party);
		} else if (startDayChoice == 3) {
			Event e = MapToEvent.createEvent(FileToMap.createMap("assets/events/collectWater.txt"));
			MainGame.launchEvent(e, MainGame.party);
		}
		kickedMemberThisTurn = false;
		startDayDrawer.destroyer();
		startDayDrawer = null;
		if (stats.get(STAMINA_KEY)==0){
			stats.put(MORALE_KEY, stats.get(MORALE_KEY) - 20);
		}
	}
	
	

	/**
	 * @return A list containing the sum of each party members value for each stat
	 */
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
				totalCurrentPartyStats.set(i, totalCurrentPartyStats.get(i) + (e.getStat(partyStatKeys[i])));
			}
		}
		return totalCurrentPartyStats;
	}

	/**
	 * Checks if the given response is possible
	 * given the current state of party stats and resources
	 * @param ro The Response option to check
	 * @return True if the response is possible, false otherwise
	 */
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
			if((resourceKeys[i] == MainGame.MORALE_KEY) && (resourceCosts.get(i) <= 0))
			{
				continue; //Morale may be negative
			}
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
	
	public static boolean checkParty(String type) {
		int check = 0;
		boolean booly = true;
		for (int i = 0; i < party.size(); i++) {
			if (!party.get(i).getType().equalsIgnoreCase(type)) {
				check = check + 1;
			}
			if (check == party.size()) {
				booly = false;
			}
			check = 0;
		}
		return booly;
	}
	
	public static Event newEvent(String type) {
		Event e = null;
		TileType tile = player1.getCurrentTile().getType();
		if (type.equalsIgnoreCase(Event.moveToString)) {
			e = getRandomEvent(tile,null,1, moveToEvents, 0);
		} else if (type.equalsIgnoreCase(Event.investigateString)) {
			HashMap defaultMap = FileToMap.createMap("assets/events/GenericInvestigate.txt");
			Event defaultEvent = MapToEvent.createEvent(defaultMap);
			e = getRandomEvent(tile, defaultEvent, INVESTIGATE_FREQUENCY, investigateEvents, 0);
		} else if (type.equalsIgnoreCase(Event.restString)) {
			HashMap defaultMap = FileToMap.createMap("assets/events/genericRest.txt");
			Event defaultEvent = MapToEvent.createEvent(defaultMap);
			e = getRandomEvent(tile, defaultEvent, REST_FREQUENCY, restEvents, 0);
		}
		return e;
	}
	

	/*
	 * ------------------------ GETTERS ------------------------ 
	 */
	public static Point getViewLoc() {
		return view.getLocation();
	}
	public Map getMap() {
		return map;
	}
	public static Player getPlayer() {
		return player1;
	}
	public View getView() {
		return view;
	}
	public static Integer getCurrentMode()
	{
		return currentMode;
	}
	public static ArrayList<PartyMember> getParty(){
		return party;
	}
	
	/**
	 * @return The map of stats in the form (string: stat key, int: stat value).
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Integer> getStats() {
		//Returns a clone, so the stats can't be modified using the get method.
		return (LinkedHashMap<String, Integer>) stats.clone();
	}


	/*
	 * ------------------------ SETTERS ------------------------ 
	 */
	public static void setCurrentMode(Integer currentMode)
	{
		if (currentMode == 0) {
			if (dayCounter == 3) {
				handleNewDayStatChanges();
				dayCounter = 1;
			} else {
				dayCounter++;
			}
		}
		MainGame.currentMode = currentMode;
	}
	
	/**
	 * Sets the stat, and raises the partyStatsChanged flag.
	 * @param statName The stat to set.
	 * @param val The value to which to set the stat.
	 */
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

	/**
	 * Increments the stat of a random party member
	 * @param statName The stat to increment
	 * @param val The amount to increment by
	 */
	public static void incRandomPersonStat(String statName, int val){
		int ran1 = (int)Math.floor(Math.random() * (party.size()));
		if ((party.get(ran1).getStat(statName) + val) < 0){
			party.get(ran1).setStat(statName, 0);
		}
		else {
			party.get(ran1).incStat(statName, val);
		}
	}
	
	
}
