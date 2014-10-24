package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public final class EventMaps {
	//Testing purposes only
	//Make sure this number is equal to the number of event maps.
	public static final int EVENT_MAP_NUM = 2;
	
	public static final HashMap<String, Object> EVENT_MAP_1;
	static
	{
		EVENT_MAP_1 = new HashMap<String, Object>();
		EVENT_MAP_1.put(MapToEvent.EVENT_ID_KEY, "LionAttack");
		
		EVENT_MAP_1.put(MapToEvent.EVENT_TYPE_KEY, "OneChoice");
		
		ArrayList<String> possibleLocations = new ArrayList<String>();
		possibleLocations.add("Plains");
		possibleLocations.add("Jungle");
		EVENT_MAP_1.put(MapToEvent.POSSIBLE_LOCATIONS_KEY, possibleLocations);
		
		ArrayList<String> reqParty = new ArrayList<String>();
		reqParty.add("Hunter");
		reqParty.add("Naturalist");
		reqParty.add("Guide");
		EVENT_MAP_1.put(MapToEvent.REQ_PARTY_KEY, reqParty);
		
		EVENT_MAP_1.put(MapToEvent.INTRO_TEXT_KEY, "A wild lion attacked you!");
		
		EVENT_MAP_1.put(MapToEvent.RESPONSE_OPTIONS_KEY, ResponseOptionMaps.RESPONSE_OPTIONS_1);
		
		HashMap<String,String> advice = new HashMap<String,String>();
		advice.put("Nifty Naturalist", "NRun!");
		advice.put("Merry Mercenary", "ULions are fast, we shouldn't run.");
		advice.put("Giddy Guide", "NAhh!");
		EVENT_MAP_1.put(MapToEvent.ADVICE_KEY, advice);
		
		EVENT_MAP_1.put(MapToEvent.FLEE_PASS_TEXT_KEY, "You successfully fled!");
		
		EVENT_MAP_1.put(MapToEvent.FLEE_FAIL_TEXT_KEY, "You failed to flee!");
	}
	
	public static final HashMap<String, Object> EVENT_MAP_2;
	static
	{
		EVENT_MAP_2 = new HashMap<String, Object>();
		EVENT_MAP_2.put(MapToEvent.EVENT_ID_KEY, "TribeAttack");
		EVENT_MAP_2.put(MapToEvent.EVENT_TYPE_KEY, "OneChoice");
		
		ArrayList<String> possibleLocations = new ArrayList<String>();
		possibleLocations.add("Jungle");
		EVENT_MAP_2.put(MapToEvent.POSSIBLE_LOCATIONS_KEY, possibleLocations);
		
		ArrayList<String> reqParty = new ArrayList<String>();
		reqParty.add("Missionary");
		reqParty.add("Guide");
		reqParty.add("Mercenary");
		EVENT_MAP_2.put(MapToEvent.REQ_PARTY_KEY, reqParty);
		
		EVENT_MAP_2.put(MapToEvent.INTRO_TEXT_KEY, "A tribe attacks!");
		
		EVENT_MAP_2.put(MapToEvent.RESPONSE_OPTIONS_KEY, ResponseOptionMaps.RESPONSE_OPTIONS_1);
		
		HashMap<String, String> advice = new HashMap<String, String>();
		advice.put("Nifty Naturalist", "UMaybe we should try talking to them...");
		advice.put("Merry Mercenary", "UKILL THEM ALL!!");
		advice.put("Marvelous Missionary", "NAhh! Natives!");
		EVENT_MAP_2.put(MapToEvent.ADVICE_KEY, advice);
		
		EVENT_MAP_2.put(MapToEvent.FLEE_PASS_TEXT_KEY, "You successfully evaded the tribe!");
		
		EVENT_MAP_2.put(MapToEvent.FLEE_FAIL_TEXT_KEY, "The tribe caught up to you! You must fight for your life!");
	}
}
