package MainGame;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author Jackson Ekis
 * Date: October 19, 2014
 * Purpose: Testing the MapToResponseOption class.
 */

public final class ResponseOptionMaps {
	//Testing purposes only
	public static final ArrayList<HashMap<String, Object>> RESPONSE_OPTIONS_1;
	static
	{
		RESPONSE_OPTIONS_1 = new ArrayList<HashMap<String,Object>>();
		
		HashMap<String,Object> responseOptionsMap1 = new HashMap<String,Object>();
		responseOptionsMap1.put(MapToResponseOption.TEXT_KEY,"Say Hello!");
		
		ArrayList<Integer> resourceStatCost = new ArrayList<Integer>();
		resourceStatCost.add(10); resourceStatCost.add(10); resourceStatCost.add(10);
		resourceStatCost.add(10); resourceStatCost.add(10); resourceStatCost.add(10);
		resourceStatCost.add(10); resourceStatCost.add(10); resourceStatCost.add(10);
		resourceStatCost.add(10);
		responseOptionsMap1.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		
		ArrayList<Integer> partyStatRequirement = new ArrayList<Integer>();
		partyStatRequirement.add(10); partyStatRequirement.add(10); partyStatRequirement.add(10); 
		partyStatRequirement.add(10); partyStatRequirement.add(10); partyStatRequirement.add(10); 
		partyStatRequirement.add(10); partyStatRequirement.add(10); 
		responseOptionsMap1.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		
		ArrayList<String> resourceModifiers = new ArrayList<String>();
		resourceModifiers.add("What is this?"); resourceModifiers.add("I don't know"); 
		responseOptionsMap1.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		
		ArrayList<String> partyStatModifiers = new ArrayList<String>();
		partyStatModifiers.add("What is this also?"); partyStatModifiers.add("I have no idea.");
		responseOptionsMap1.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		
		responseOptionsMap1.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		
		responseOptionsMap1.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		
		ArrayList<Integer> winResourceChange = new ArrayList<Integer>();
		winResourceChange.add(10); winResourceChange.add(10); winResourceChange.add(10); 
		winResourceChange.add(10); winResourceChange.add(10); winResourceChange.add(10); 
		winResourceChange.add(10); winResourceChange.add(10); winResourceChange.add(10); 
		winResourceChange.add(10); 
		responseOptionsMap1.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		
		ArrayList<Integer> winPartyStatChange = new ArrayList<Integer>();
		winPartyStatChange.add(10); winPartyStatChange.add(10); winPartyStatChange.add(10); 
		winPartyStatChange.add(10); winPartyStatChange.add(10); winPartyStatChange.add(10); 
		winPartyStatChange.add(10); winPartyStatChange.add(10); 
		responseOptionsMap1.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		
		responseOptionsMap1.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		
		ArrayList<Integer> loseResourceChange = new ArrayList<Integer>();
		loseResourceChange.add(-10); loseResourceChange.add(-10); loseResourceChange.add(-10); 
		loseResourceChange.add(-10); loseResourceChange.add(-10); loseResourceChange.add(-10); 
		loseResourceChange.add(-10); loseResourceChange.add(-10); loseResourceChange.add(-10); 
		loseResourceChange.add(-10); 
		responseOptionsMap1.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		
		ArrayList<Integer> losePartyStatChange = new ArrayList<Integer>();
		losePartyStatChange.add(-10); losePartyStatChange.add(-10); losePartyStatChange.add(-10); 
		losePartyStatChange.add(-10); losePartyStatChange.add(-10); losePartyStatChange.add(-10); 
		losePartyStatChange.add(-10); losePartyStatChange.add(-10); 
		responseOptionsMap1.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		
		RESPONSE_OPTIONS_1.add(responseOptionsMap1);


		HashMap<String,Object> responseOptionsMap2 = new HashMap<String,Object>();
		responseOptionsMap2.put(MapToResponseOption.TEXT_KEY,"Run Away!");
		
		responseOptionsMap2.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		
		responseOptionsMap2.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		
		responseOptionsMap2.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		
		responseOptionsMap2.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		
		responseOptionsMap2.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		
		responseOptionsMap2.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		
		responseOptionsMap2.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		
		responseOptionsMap2.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		
		responseOptionsMap2.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		
		responseOptionsMap2.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		
		responseOptionsMap2.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		
		RESPONSE_OPTIONS_1.add(responseOptionsMap2);
	}
	
	
	
}
