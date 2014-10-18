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
	public static String eventIDKey = "eventID";
	public static String eventTypeKey = "eventType";
	public static String possibleLocationsKey = "possibleLocations";
	public static String reqPartyKey = "reqParty";
	
	public static String introTextKey = "introText";
	public static String responseOptionsKey = "responseOptions";
	public static String adviceKey = "advice";
	public static String fleePassTextKey = "fleePassText";
	public static String fleeFailTextKey = "fleeFailText";
	
	
	/**
	 * Takes in a HashMap full of the values any growing Event needs.
	 * Casts all of the individual parts of the HashMap into
	 * variables that are then used to construct
	 * an Event that is returned to wherever this method
	 * is called. It basically turns a HashMap into an Event.
	 */
	@SuppressWarnings("unchecked")
	public static Event createEvent(HashMap<String, Object> importedMap){
		String eventID = (String)importedMap.get(eventIDKey);
		
		String eventType = (String)importedMap.get(eventTypeKey);
		
		ArrayList<String> possibleLocations = new ArrayList<String>();
		possibleLocations.addAll((ArrayList<String>)importedMap.get(possibleLocationsKey));
		
		ArrayList<String> reqParty = new ArrayList<String>();
		reqParty.addAll((ArrayList<String>)importedMap.get(reqPartyKey));
		
		String introText = (String)importedMap.get(introTextKey);
		
		ArrayList<ResponseOption> responseOptions = new ArrayList<ResponseOption>();
		responseOptions.addAll((ArrayList<ResponseOption>)importedMap.get(responseOptionsKey));
		
		HashMap<String,String> advice = new HashMap<String,String>();
		advice.putAll((HashMap<String,String>)importedMap.get(adviceKey));
		
		String fleePassText = (String)importedMap.get(fleePassTextKey);
		
		String fleeFailText = (String)importedMap.get(fleeFailTextKey);
		
		Event newEvent = new Event(eventID, eventType, possibleLocations, reqParty, introText, responseOptions, advice, fleePassText, fleeFailText);
		return newEvent;
	}
}
