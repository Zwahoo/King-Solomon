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
		
		responseOptionsMap1.put(MapToResponseOption.WIN_FOLLOW_UP_KEY, "filename");
		
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
		
		responseOptionsMap1.put(MapToResponseOption.LOSE_FOLLOW_UP_KEY, "filename");
		
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
		responseOptionsMap2.put(MapToResponseOption.TEXT_KEY,"Attack!");		
		responseOptionsMap2.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		responseOptionsMap2.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		responseOptionsMap2.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		responseOptionsMap2.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		responseOptionsMap2.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		responseOptionsMap2.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		responseOptionsMap2.put(MapToResponseOption.WIN_FOLLOW_UP_KEY, "filename");
		responseOptionsMap2.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		responseOptionsMap2.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		responseOptionsMap2.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		responseOptionsMap2.put(MapToResponseOption.LOSE_FOLLOW_UP_KEY, "filename");
		responseOptionsMap2.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		responseOptionsMap2.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		RESPONSE_OPTIONS_1.add(responseOptionsMap2);
		
		
		/*
		HashMap<String,Object> renameTHIS = new HashMap<String,Object>();
		renameTHIS.put(MapToResponseOption.TEXT_KEY,"Run Away!");		
		renameTHIS.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		renameTHIS.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		renameTHIS.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		renameTHIS.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		renameTHIS.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		renameTHIS.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		renameTHIS.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		renameTHIS.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		renameTHIS.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		renameTHIS.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		renameTHIS.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		RESPONSE_OPTIONS_1.add(renameTHIS);
		*/

		HashMap<String,Object> responseOptionMap3 = new HashMap<String,Object>();
		responseOptionMap3.put(MapToResponseOption.TEXT_KEY,"Ignore, and search for herbs.");		
		responseOptionMap3.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		responseOptionMap3.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		responseOptionMap3.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		responseOptionMap3.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		responseOptionMap3.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		responseOptionMap3.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		responseOptionMap3.put(MapToResponseOption.WIN_FOLLOW_UP_KEY, "filename");
		responseOptionMap3.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		responseOptionMap3.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		responseOptionMap3.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		responseOptionMap3.put(MapToResponseOption.LOSE_FOLLOW_UP_KEY, "filename");
		responseOptionMap3.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		responseOptionMap3.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		RESPONSE_OPTIONS_1.add(responseOptionMap3);
		

		HashMap<String,Object> responseOption4 = new HashMap<String,Object>();
		responseOption4.put(MapToResponseOption.TEXT_KEY,"Set up a system of organized trade.");		
		responseOption4.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		responseOption4.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		responseOption4.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		responseOption4.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		responseOption4.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		responseOption4.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		responseOption4.put(MapToResponseOption.WIN_FOLLOW_UP_KEY, "filename");
		responseOption4.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		responseOption4.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		responseOption4.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		responseOption4.put(MapToResponseOption.LOSE_FOLLOW_UP_KEY, "filename");
		responseOption4.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		responseOption4.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		RESPONSE_OPTIONS_1.add(responseOption4);
		

		HashMap<String,Object> renameTHIS = new HashMap<String,Object>();
		renameTHIS.put(MapToResponseOption.TEXT_KEY,"Run Away!");		
		renameTHIS.put(MapToResponseOption.RESOURCE_STAT_COST_KEY, resourceStatCost);
		renameTHIS.put(MapToResponseOption.PARTY_STAT_REQUIREMENT_KEY, partyStatRequirement);
		renameTHIS.put(MapToResponseOption.RESOURCE_MODIFIERS_KEY, resourceModifiers);
		renameTHIS.put(MapToResponseOption.PARTY_STAT_MODIFIERS_KEY, partyStatModifiers);
		renameTHIS.put(MapToResponseOption.PASS_TEXT_KEY, "You passed the test!");
		renameTHIS.put(MapToResponseOption.WIN_TEXT_KEY, "You won the challenge!");
		renameTHIS.put(MapToResponseOption.WIN_FOLLOW_UP_KEY, "filename");
		renameTHIS.put(MapToResponseOption.WIN_RESOURCE_CHANGE_KEY, winResourceChange);
		renameTHIS.put(MapToResponseOption.WIN_PARTY_STAT_CHANGE_KEY, winPartyStatChange);
		renameTHIS.put(MapToResponseOption.LOSE_TEXT_KEY, "You lost the challenge! You died.");
		renameTHIS.put(MapToResponseOption.LOSE_FOLLOW_UP_KEY, "filename");
		renameTHIS.put(MapToResponseOption.LOSE_RESOURCE_CHANGE_KEY, loseResourceChange);
		renameTHIS.put(MapToResponseOption.LOSE_PARTY_STAT_CHANGE_KEY, losePartyStatChange);
		RESPONSE_OPTIONS_1.add(renameTHIS);
	}
	
	
	
}
