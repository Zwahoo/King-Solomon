package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {
	public static String checkResponse(ResponseOption r, HashMap<String, Integer> resources, ArrayList<Integer> partyStats){
		ArrayList<Integer> currentResources = new ArrayList<Integer>();
		ArrayList<Integer> reqResources = new ArrayList<Integer>();
		reqResources.addAll(r.getCost());
		
		currentResources.add(resources.get(MainGame.MORALE_KEY));
		currentResources.add(resources.get(MainGame.STAMINA_KEY));
		currentResources.add(resources.get(MainGame.FOOD_KEY));
		currentResources.add(resources.get(MainGame.WATER_KEY));
		currentResources.add(resources.get(MainGame.AMMO_KEY));
		currentResources.add(resources.get(MainGame.MEDICINE_KEY));
		currentResources.add(resources.get(MainGame.VALUABLES_KEY));
		currentResources.add(resources.get(MainGame.PACK_ANIMALS_KEY));
		
		ArrayList<Integer> currentPartyStats = new ArrayList<Integer>();
		currentPartyStats.addAll(partyStats);
		ArrayList<Integer> reqPartyStats = new ArrayList<Integer>();
		reqPartyStats.addAll(r.getRequirements());
		
		ArrayList<Integer> resourceModifiers = new ArrayList<Integer>();
		for (int i = 0; i < currentResources.size(); i++){
			resourceModifiers.add(reqResources.get(i) - currentResources.get(i));
		}
		
		ArrayList<Integer> partyStatModifiers = new ArrayList<Integer>();
		for (int i = 0; i < currentPartyStats.size(); i++){
			partyStatModifiers.add(reqPartyStats.get(i) - currentPartyStats.get(i));
		}
		
		int totalModifier = 0;
		for (int e : resourceModifiers){
			totalModifier += e/3;
		}
		for (int e : partyStatModifiers){
			totalModifier += e;
		}
		
		double eventRoll = (Math.random() * 100) + totalModifier;
		
		String result = "";
		
		if (eventRoll < 40){
			result = "fail";
		} else if (eventRoll >= 40 && eventRoll < 60){
			result = "pass";
		} else if (eventRoll >= 60){
			result = "success";
		}
		
		return result;
	}
}
