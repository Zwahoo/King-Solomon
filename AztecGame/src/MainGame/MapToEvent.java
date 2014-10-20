package MainGame;

/**
 * @author Jackson Ekis
 * Date: 10/18/2014
 * 
 * Purpose: Takes in a map and creates an event, usually
 * from the "File to Map" class.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class MapToEvent {
	//Conventions
	public static final String EVENT_ID_KEY = "eventID";
	public static final String EVENT_TYPE_KEY = "eventType";
	public static final String POSSIBLE_LOCATIONS_KEY = "possibleLocations";
	public static final String REQ_PARTY_KEY = "reqParty";
	
	public static final String INTRO_TEXT_KEY = "introText";
	public static final String RESPONSE_OPTIONS_KEY = "responseOptions";
	public static final String ADVICE_KEY = "advice";
	public static final String FLEE_PASS_TEXT_KEY = "fleePassText";
	public static final String FLEE_FAIL_TEXT_KEY = "fleeFailText";
	
	
	/**
	 * Takes in a HashMap full of the values any growing Event needs.
	 * Casts all of the individual parts of the HashMap into
	 * variables that are then used to construct
	 * an Event that is returned to wherever this method
	 * is called. It basically turns a HashMap into an Event.
	 * 
	 * @param importedMap A map that comes from the FileToMap class.
	 * @return newEvent A new event created using the extracted parts from the imported Map.
	 */
	@SuppressWarnings("unchecked")
	public static Event createEvent(HashMap<String, Object> importedMap){
		String eventID = (String)importedMap.get(EVENT_ID_KEY);
		
		String eventType = (String)importedMap.get(EVENT_TYPE_KEY);
		
		ArrayList<String> possibleLocations = new ArrayList<String>();
		possibleLocations.addAll((ArrayList<String>)importedMap.get(POSSIBLE_LOCATIONS_KEY));
		
		ArrayList<String> reqParty = new ArrayList<String>();
		reqParty.addAll((ArrayList<String>)importedMap.get(REQ_PARTY_KEY));
		
		String introText = (String)importedMap.get(INTRO_TEXT_KEY);
		
		ArrayList<ResponseOption> responseOptions = new ArrayList<ResponseOption>();
		responseOptions.addAll(MapToResponseOption.createResponseOptions(importedMap));
		
		HashMap<String,String> advice = new HashMap<String,String>();
		advice.putAll((HashMap<String,String>)importedMap.get(ADVICE_KEY));
		
		String fleePassText = (String)importedMap.get(FLEE_PASS_TEXT_KEY);
		
		String fleeFailText = (String)importedMap.get(FLEE_FAIL_TEXT_KEY);
		
		Event newEvent = new Event(eventID, eventType, possibleLocations, reqParty, introText, responseOptions, advice, fleePassText, fleeFailText);
		return newEvent;
	}
}
