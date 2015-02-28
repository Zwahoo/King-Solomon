package MainGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import Comparators.CmpByAgi;
import Comparators.CmpByDip;
import Comparators.CmpByKnow;
import Comparators.CmpByLoyal;
import Comparators.CmpByMarks;
import Comparators.CmpByPerc;
import Comparators.CmpByStr;
import Comparators.CmpByTact;

public class EventHandler {

	private static int invalidModifierNum = -1000;

	public static int checkResponse(ResponseOption r, HashMap<String, Integer> resources, ArrayList<Integer> partyStats)
	{

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
		for (int i = 0; i < currentResources.size(); i++) {
			int currentResource = currentResources.get(i);
			int currentResourceModifier = resourceModifiers.get(i).intValue();

			if (currentResourceModifier != invalidModifierNum) {
				resourceModifier += currentResource - currentResourceModifier;
			}
		}

		int partyStatModifier = 0;
		for (int i = 0; i < currentPartyStats.size(); i++) {
			int currentPartyStat = currentPartyStats.get(i);
			int currentPartyStatModifier = partyStatModifiers.get(i).intValue();

			if (currentPartyStatModifier != invalidModifierNum) {
				partyStatModifier += currentPartyStat - currentPartyStatModifier;
			}
		}

		int totalModifier = resourceModifier + partyStatModifier;

		double eventRoll = (Math.random() * 100) + totalModifier;

		int result = 0;

		if (eventRoll < 50) {
			result = 0;
		} else {
			result = 2;
		}

		return result;
	}

	public int getModifier()
	{
		getModFromTop(getTopThree());

		// TODO method stub
		return 0;
	}

	private void getModFromTop(Integer[][] topThree)
	{
		// TODO Auto-generated method stub

	}

	public static Integer[][] getTopThree()
	{
		Integer[][] topThrees = new Integer[PartyMember.NUM_KEYS][3];

		HashMap<String, Comparator<PartyMember>> mapOfCmps = new HashMap<>();
		mapOfCmps.put(PartyMember.MARKSMANSHIP_KEY, new CmpByMarks());
		mapOfCmps.put(PartyMember.PERCEPTION_KEY, new CmpByPerc());
		mapOfCmps.put(PartyMember.TACTICS_KEY, new CmpByTact());
		mapOfCmps.put(PartyMember.LOYALTY_KEY, new CmpByLoyal());
		mapOfCmps.put(PartyMember.AGILITY_KEY, new CmpByAgi());
		mapOfCmps.put(PartyMember.STRENGTH_KEY, new CmpByStr());
		mapOfCmps.put(PartyMember.DIPLOMACY_KEY, new CmpByDip());
		mapOfCmps.put(PartyMember.KNOWLEDGE_KEY, new CmpByKnow());

		int statIndex = 0;
		for (String key : mapOfCmps.keySet()) {
			ArrayList<PartyMember> sortedArray = new ArrayList<>();
			sortedArray.addAll(MainGame.getParty());
			Collections.sort(sortedArray, mapOfCmps.get(key));
			Integer[] topThree = (Integer[]) sortedArray.subList(0, Math.min(3, sortedArray.size())).toArray();
			topThrees[statIndex] = topThree;
			statIndex++;
		}

		return topThrees;

	}
}
