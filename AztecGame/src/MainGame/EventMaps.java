package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public final class EventMaps {
	public static final HashMap<String, Object> EVENT_MAP_1;
	static
	{
		EVENT_MAP_1 = new HashMap<String, Object>();
		EVENT_MAP_1.put(MapToEvent.eventIDKey, "LionAttack");
		
		EVENT_MAP_1.put(MapToEvent.eventTypeKey, "OneChoice");
		
		ArrayList<String> possibleLocations = new ArrayList<String>();
		possibleLocations.add("Plains");
		possibleLocations.add("Jungle");
		EVENT_MAP_1.put(MapToEvent.possibleLocationsKey, possibleLocations);
		
		ArrayList<String> reqParty = new ArrayList<String>();
		reqParty.add("Hunter");
		reqParty.add("Naturalist");
		reqParty.add("Guide");
		EVENT_MAP_1.put(MapToEvent.reqPartyKey, reqParty);
		
		EVENT_MAP_1.put(MapToEvent.introTextKey, "A wild lion attacked you!");
		
		ArrayList<ResponseOption> responseOptions = new ArrayList<ResponseOption>();
		responseOptions.add(new ResponseOption());
		responseOptions.add(new ResponseOption());
		EVENT_MAP_1.put(MapToEvent.responseOptionsKey, responseOptions);
		
		HashMap<String,String> advice = new HashMap<String,String>();
		advice.put("Johnny", "NRun!");
		advice.put("Adams", "ULions are fast, we shouldn't run.");
		advice.put("Bob", "NAhh!");
		EVENT_MAP_1.put(MapToEvent.adviceKey, advice);
		
		EVENT_MAP_1.put(MapToEvent.fleePassTextKey, "You successfully fled!");
		
		EVENT_MAP_1.put(MapToEvent.fleeFailTextKey, "You failed to flee!");
	}
}
