package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Jackson Ekis
 * Date: October 19, 2014
 * Purpose: To create an ArrayList of ResponseOptions from an imported Map.
 */

public class MapToResponseOption {

		public static final String RESPONSE_OPTIONS_KEY = "responseOptions";
	
		public static final String TEXT_KEY = "text";
		//ArrayList<Integer>
		public static final String RESOURCE_STAT_COST_KEY = "resourceStatCost";
		//ArrayList<Integer>
		public static final String PARTY_STAT_REQUIREMENT_KEY = "partyStatRequirement";
		
		//Needed for determining win/lose
		//ArrayList<String>
		public static final String RESOURCE_MODIFIERS_KEY = "resourceModifiers";
		//ArrayList<String>
		public static final String PARTY_STAT_MODIFIERS_KEY = "partyStatModifiers";
		
		//Pass Info
		public static final String PASS_TEXT_KEY = "passText";
		
		//Victory Info
		public static final String WIN_TEXT_KEY = "winText";
		//ArrayList<Integer>
		public static final String WIN_RESOURCE_CHANGE_KEY = "winResourceChange";
		//ArrayList<Integer>
		public static final String WIN_PARTY_STAT_CHANGE_KEY = "winPartyStatChange";

		//Lose Info
		public static final String LOSE_TEXT_KEY = "loseText";
		//ArrayList<Integer>
		public static final String LOSE_RESOURCE_CHANGE_KEY = "loseResourceChange";
		//ArrayList<Integer>
		public static final String LOSE_PARTY_STAT_CHANGE_KEY = "losePartyStatChange";
		
		/**
		 * This method creates an ArrayList of response options using
		 * a map with an appropriate key. This map comes straight from the
		 * FileToMap class, no changes necessary. It only needs to have response options
		 * as a valid key.
		 * 
		 * @param importedMap The Map taken from the FileToMap class.
		 * @return responseOptions An ArrayList of Response Options to be fed back into an Event.
		 */
		@SuppressWarnings("unchecked")
		public static ArrayList<ResponseOption> createResponseOptions(HashMap<String, Object> importedMap){
			ArrayList<HashMap<String, Object>> tempArray = new ArrayList<HashMap<String, Object>>();
			tempArray.addAll((ArrayList<HashMap<String, Object>>)importedMap.get(RESPONSE_OPTIONS_KEY));
			
			ArrayList<ResponseOption> responseOptions = new ArrayList<ResponseOption>();
			for (HashMap<String, Object> e : tempArray){
				String text = (String)e.get(TEXT_KEY);
				
				ArrayList<Integer> resourceStatCost = new ArrayList<Integer>();
				resourceStatCost.addAll((ArrayList<Integer>)e.get(RESOURCE_STAT_COST_KEY));
				
				ArrayList<Integer> partyStatRequirement = new ArrayList<Integer>();
				partyStatRequirement.addAll((ArrayList<Integer>)e.get(PARTY_STAT_REQUIREMENT_KEY));
				
				ArrayList<String> resourceModifiers = new ArrayList<String>();
				resourceModifiers.addAll((ArrayList<String>)e.get(RESOURCE_MODIFIERS_KEY));
				
				ArrayList<String> partyStatModifiers = new ArrayList<String>();
				partyStatModifiers.addAll((ArrayList<String>)e.get(PARTY_STAT_MODIFIERS_KEY));
				
				String passText = (String)e.get(PASS_TEXT_KEY);
				
				String winText = (String)e.get(WIN_TEXT_KEY);
				
				ArrayList<Integer> winResourceChange = new ArrayList<Integer>();
				winResourceChange.addAll((ArrayList<Integer>)e.get(WIN_RESOURCE_CHANGE_KEY));
				
				ArrayList<Integer> winPartyStatChange = new ArrayList<Integer>();
				winPartyStatChange.addAll((ArrayList<Integer>)e.get(WIN_PARTY_STAT_CHANGE_KEY));
				
				String loseText = (String)e.get(LOSE_TEXT_KEY);
				
				ArrayList<Integer> loseResourceChange = new ArrayList<Integer>();
				loseResourceChange.addAll((ArrayList<Integer>)e.get(LOSE_RESOURCE_CHANGE_KEY));
				
				ArrayList<Integer> losePartyStatChange = new ArrayList<Integer>();
				losePartyStatChange.addAll((ArrayList<Integer>)e.get(LOSE_PARTY_STAT_CHANGE_KEY));
				
				ResponseOption newResponseOption = new ResponseOption(text, resourceStatCost, partyStatRequirement,
						resourceModifiers, partyStatModifiers, passText, winText, winResourceChange, 
						winPartyStatChange, loseText, loseResourceChange, losePartyStatChange);
				
				responseOptions.add(newResponseOption);
			}
			return responseOptions;
		}
}
