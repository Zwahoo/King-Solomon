package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public final class EventMaps {
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
		
		ArrayList<ResponseOption> responseOptions = new ArrayList<ResponseOption>();
		responseOptions.add(new ResponseOption());
		responseOptions.add(new ResponseOption());
		EVENT_MAP_1.put(MapToEvent.RESPONSE_OPTIONS_KEY, responseOptions);
		
		HashMap<String,String> advice = new HashMap<String,String>();
		advice.put("Johnny", "NRun!");
		advice.put("Adams", "ULions are fast, we shouldn't run.");
		advice.put("Bob", "NAhh!");
		EVENT_MAP_1.put(MapToEvent.ADVICE_KEY, advice);
		
		EVENT_MAP_1.put(MapToEvent.FLEE_PASS_TEXT_KEY, "You successfully fled!");
		
		EVENT_MAP_1.put(MapToEvent.FLEE_FAIL_TEXT_KEY, "You failed to flee!");
	}
}
