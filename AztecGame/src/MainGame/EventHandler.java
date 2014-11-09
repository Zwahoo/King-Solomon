package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {
	public static int checkResponse(ResponseOption r, HashMap<String, Integer> resources, ArrayList<Integer> partyStats){
		ArrayList<Integer> currentResources = new ArrayList<Integer>();
		ArrayList<Long> reqResources = new ArrayList<Long>();
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
		ArrayList<Long> reqPartyStats = new ArrayList<Long>();
		reqPartyStats.addAll(r.getRequirements());
		
		ArrayList<Integer> resourceModifiers = new ArrayList<Integer>();
		for (int i = 0; i < currentResources.size(); i++){
			int currentResource = currentResources.get(i);
			int reqResource = reqResources.get(i).intValue();
			resourceModifiers.add((int) (currentResource - reqResource));
		}
		
		ArrayList<Integer> partyStatModifiers = new ArrayList<Integer>();
		for (int i = 0; i < currentPartyStats.size(); i++){
			partyStatModifiers.add(reqPartyStats.get(i).intValue() - currentPartyStats.get(i));
		}
		
		int totalModifier = 0;
		for (int e : resourceModifiers){
			totalModifier += e/3;
		}
		for (int e : partyStatModifiers){
			totalModifier += e;
		}
		
		double eventRoll = (Math.random() * 100) + totalModifier;
		
		int result = 0;
		
		if (eventRoll < 40){
			result = 0;
		} else if (eventRoll >= 40 && eventRoll < 60){
			result = 1;
		} else if (eventRoll >= 60){
			result = 2;
		}
		
		return result;
	}
}
