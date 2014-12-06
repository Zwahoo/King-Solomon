package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {
	
	private static int invalidModifierNum = -1000;
	
	public static int checkResponse(ResponseOption r, HashMap<String, Integer> resources, ArrayList<Integer> partyStats){
		
		ArrayList<Integer> currentResources = new ArrayList<Integer>();
		currentResources.add(resources.get(MainGame.FOOD_KEY));
		currentResources.add(resources.get(MainGame.WATER_KEY));
		currentResources.add(resources.get(MainGame.VALUABLES_KEY));
		currentResources.add(resources.get(MainGame.AMMO_KEY));
		currentResources.add(resources.get(MainGame.MEDICINE_KEY));
		currentResources.add(resources.get(MainGame.MORALE_KEY));
		currentResources.add(resources.get(MainGame.STAMINA_KEY));
		currentResources.add(resources.get(MainGame.PACK_ANIMALS_KEY));
		
		ArrayList<Long> resourceModifiers = new ArrayList<Long>();
		resourceModifiers.addAll(r.getResourceModifiers());
		
		
		ArrayList<Integer> currentPartyStats = new ArrayList<Integer>();
		currentPartyStats.addAll(partyStats);
		
		ArrayList<Long> partyStatModifiers = new ArrayList<Long>();
		partyStatModifiers.addAll(r.getPartyStatModifiers());
		
		
		int resourceModifier = 0;
		for (int i = 0; i < currentResources.size(); i++){
			int currentResource = currentResources.get(i);
			int currentResourceModifier = resourceModifiers.get(i).intValue();
			
			if(currentResourceModifier != invalidModifierNum) {
				resourceModifier += currentResource - currentResourceModifier;
			}
		}
		
		int partyStatModifier = 0;
		for (int i = 0; i < currentPartyStats.size(); i++){
			int currentPartyStat = currentPartyStats.get(i);
			int currentPartyStatModifier = partyStatModifiers.get(i).intValue();
			
			if(currentPartyStatModifier != invalidModifierNum) {
				partyStatModifier += currentPartyStat - currentPartyStatModifier;
			}
		}
		
		int totalModifier = resourceModifier + partyStatModifier;
		
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
