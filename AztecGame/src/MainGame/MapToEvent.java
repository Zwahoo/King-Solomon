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
	private static String eventIDKey = "eventID";
	private static String eventTypeKey = "eventType";
	private static String possibleLocationsKey = "possibleLocations";
	private static String reqPartyKey = "reqParty";
	
	private static String introTextKey = "introText";
	private static String responseOptionsKey = "responseOptions";
	private static String adviceKey = "advice";
	private static String fleePassTextKey = "fleePassText";
	private static String fleeFailTextKey = "fleeFailText";
	
	
	/**
	 * Takes in the values of the imported map
	 * and maps them to their respective values.
	 * These values can then be retrieved by
	 * various getter methods to be input
	 * into Event objects when they are
	 * constructed.
	 */
	@SuppressWarnings("unchecked")
	public static Event createConventions(HashMap<String, Object> importedMap){
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
