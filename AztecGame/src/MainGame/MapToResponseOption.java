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
		public static final String KILL_PERSON_PASS_KEY = "killPersonPass";
		public static final String PASS_FOLLOW_UP_KEY = "passFollowUp";
		
		//Victory Info
		public static final String WIN_TEXT_KEY = "winText";
		//ArrayList<Integer>
		public static final String WIN_FOLLOW_UP_KEY = "winFollowUp";
		public static final String WIN_RESOURCE_CHANGE_KEY = "winResourceChange";
		//ArrayList<Integer>
		public static final String WIN_PARTY_STAT_CHANGE_KEY = "winPartyStatChange";
		public static final String KILL_PERSON_WIN_KEY = "killPersonWin";
		public static final String REWARD_DISPERSE_WIN_KEY = "rewardDisperseWin";

		//Lose Info
		public static final String LOSE_TEXT_KEY = "loseText";
		//ArrayList<Integer>
		public static final String LOSE_FOLLOW_UP_KEY = "loseFollowUp";
		public static final String LOSE_RESOURCE_CHANGE_KEY = "loseResourceChange";
		//ArrayList<Integer>
		public static final String LOSE_PARTY_STAT_CHANGE_KEY = "losePartyStatChange";
		public static final String KILL_PERSON_LOSE_KEY = "killPersonLose";
		public static final String REWARD_DISPERSE_LOSE_KEY = "rewardDisperseLose";
		
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
				
				ArrayList<Long> resourceStatCost = new ArrayList<Long>();
				resourceStatCost.addAll((ArrayList<Long>)e.get(RESOURCE_STAT_COST_KEY));
				
				ArrayList<Long> partyStatRequirement = new ArrayList<Long>();
				partyStatRequirement.addAll((ArrayList<Long>)e.get(PARTY_STAT_REQUIREMENT_KEY));
				
				ArrayList<Long> resourceModifiers = new ArrayList<Long>();
				resourceModifiers.addAll((ArrayList<Long>)e.get(RESOURCE_MODIFIERS_KEY));
				
				ArrayList<Long> partyStatModifiers = new ArrayList<Long>();
				partyStatModifiers.addAll((ArrayList<Long>)e.get(PARTY_STAT_MODIFIERS_KEY));
				
				String passText = (String)e.get(PASS_TEXT_KEY);
				
				boolean killPersonPass = (boolean)e.get(KILL_PERSON_PASS_KEY);
				
				String passFollowUp = (String)e.get(PASS_FOLLOW_UP_KEY);
				
				String winText = (String)e.get(WIN_TEXT_KEY);
				
				String winFollowUp = (String)e.get(WIN_FOLLOW_UP_KEY);
				
				ArrayList<Long> winResourceChange = new ArrayList<Long>();
				winResourceChange.addAll((ArrayList<Long>)e.get(WIN_RESOURCE_CHANGE_KEY));
				
				ArrayList<Long> winPartyStatChange = new ArrayList<Long>();
				winPartyStatChange.addAll((ArrayList<Long>)e.get(WIN_PARTY_STAT_CHANGE_KEY));
				
				boolean killPersonWin = (boolean)e.get(KILL_PERSON_WIN_KEY);
				
				Long rewardDisperseWinLong = (Long)e.get(REWARD_DISPERSE_WIN_KEY);
				int rewardDisperseWin = rewardDisperseWinLong.intValue();
				
				String loseText = (String)e.get(LOSE_TEXT_KEY);
				
				String loseFollowUp = (String)e.get(LOSE_FOLLOW_UP_KEY);
				
				ArrayList<Long> loseResourceChange = new ArrayList<Long>();
				loseResourceChange.addAll((ArrayList<Long>)e.get(LOSE_RESOURCE_CHANGE_KEY));
				
				ArrayList<Long> losePartyStatChange = new ArrayList<Long>();
				losePartyStatChange.addAll((ArrayList<Long>)e.get(LOSE_PARTY_STAT_CHANGE_KEY));
				
				boolean killPersonLose = (boolean)e.get(KILL_PERSON_LOSE_KEY);
				
				Long rewardDisperseLoseLong = (Long)e.get(REWARD_DISPERSE_LOSE_KEY);
				int rewardDisperseLose = rewardDisperseLoseLong.intValue();
				
				ResponseOption newResponseOption = new ResponseOption(text, resourceStatCost, partyStatRequirement,
						resourceModifiers, partyStatModifiers, passText, killPersonPass, passFollowUp, winText, winFollowUp, winResourceChange, 
						winPartyStatChange, killPersonWin, rewardDisperseWin, 
						loseText, loseFollowUp, loseResourceChange, losePartyStatChange, killPersonLose, rewardDisperseLose);
				
				responseOptions.add(newResponseOption);
			}
			return responseOptions;
		}
}
