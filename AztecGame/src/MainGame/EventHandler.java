package MainGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import Comparators.CmpByAgi;
import Comparators.CmpByDip;
import Comparators.CmpByKnow;
import Comparators.CmpByLoyal;
import Comparators.CmpByMarks;
import Comparators.CmpByPerc;
import Comparators.CmpByStr;
import Comparators.CmpByTact;

public class EventHandler {

	private static int dieRollSides = 50;
	private static int defaultWinCutoff = 25;
	private static int invalidModifierNum = -1000;

	public static int checkResponse(ResponseOption r, HashMap<String, Integer> resources, ArrayList<Integer> partyStats)
	{

		/*ArrayList<Integer> currentResources = new ArrayList<Integer>();
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

		int resourceModifier = 0;
		for (int i = 0; i < currentResources.size(); i++) {
			int currentResource = currentResources.get(i);
			int currentResourceModifier = resourceModifiers.get(i).intValue();

			if (currentResourceModifier != invalidModifierNum) {
				resourceModifier += currentResource - currentResourceModifier;
			}
		} */

		ArrayList<Integer> currentPartyStats = new ArrayList<Integer>();
		currentPartyStats.addAll(partyStats);

		ArrayList<Long> partyStatModifiers = new ArrayList<Long>();
		partyStatModifiers.addAll(r.getPartyStatModifiers());
		
		int modifier = getModifier(partyStatModifiers);

		double eventRoll = (Math.random() * dieRollSides) + modifier;

		int result = 0;

		if (eventRoll < defaultWinCutoff) {
			result = 0;
		} else {
			result = 2;
		}

		return result;
	}
	
	/**
	 * Calculates and returns the die roll modifier for the event.
	 * @param partyStatModifiers The list of stat modifiers for the chosen response.
	 * @return The value to add to the event result die roll.
	 */
	public static int getModifier(ArrayList<Long> partyStatModifiers)
	{
		return getModFromTop(getTopThree(), partyStatModifiers);
	}
	
	/**
	 * Calculates a modifier based off of the top three values for each stat.
	 * @param topThree Top three values for each stat. Format: (1/2/3, statNum)
	 * @param partyStatModifiers The list of stat modifiers for the given event.
	 * @return The calculated value to add to the event result die roll.
	 */
	private static int getModFromTop(Integer[][] topThree, ArrayList<Long> partyStatModifiers)
	{
        int statNum = 0;

        int totalMod = 0;
        for(long modifier : partyStatModifiers) {
            if (modifier == invalidModifierNum) continue;
            
            int top0 = topThree[0][statNum];
            int top1 = topThree[1][statNum];
            int top2 = topThree[2][statNum];

            double[] weights = getWeights(modifier, top0, top1, top2);

            double weightedAvg = ((weights[0] * top0 + weights[1] * top1 + weights[2] * top2) / (weights[0] + weights[1] + weights[2]));

            totalMod += ((int)Math.floor(weightedAvg) - modifier);

            statNum++;
        }
        
        return totalMod;
	}
	
	/**
	 * Calculates the weights for a weighted average of
	 * the top three values for a given stat.
	 * @param modifier The modifier to compare stats against
	 * @param top0 The largest top value
	 * @param top1 The second largest top value
	 * @param top2 The third largest top value
	 * @return An int[3] where [0] = weight for top0, [1] = weight for top1 and [2] = weight for top2
	 */
	private static double[] getWeights(long modifier, int top0, int top1, int top2)
    {
        int minWeight = 1;

        double dif1 = top0 - modifier;
        double dif2 = top1 - modifier;
        double dif3 = top2 - modifier;

        double addNum = Math.max(0, minWeight - dif3);

        dif1 += addNum;
        dif2 += addNum;
        dif3 += addNum;

        double[] toRet = new double[3];
        //A top value will be < 0 iff less than 3 members, in which case weight non members as 0.
        if(top0 >= 0) toRet[0] = dif1; else toRet[0] = 0;
        if(top1 >= 0) toRet[1] = dif2; else toRet[1] = 0;
        if(top2 >= 0) toRet[2] = dif3; else toRet[2] = 0;
        
        return toRet;
    }
	
	/**
	 * Finds the top three values for each stat.
	 * @return int[][] with format int[0/1/2][statNum] where [0][stat] >= [1][stat] >= [2][stat]
	 */
	public static Integer[][] getTopThree()
	{
		Integer[][] topThrees = new Integer[3][PartyMember.NUM_KEYS];
		
		LinkedHashMap<String, Comparator<PartyMember>> mapOfCmps = new LinkedHashMap<>();
		mapOfCmps.put(PartyMember.MARKSMANSHIP_KEY, new CmpByMarks());
		mapOfCmps.put(PartyMember.PERCEPTION_KEY, new CmpByPerc());
		mapOfCmps.put(PartyMember.TACTICS_KEY, new CmpByTact());
		mapOfCmps.put(PartyMember.LOYALTY_KEY, new CmpByLoyal());
		mapOfCmps.put(PartyMember.AGILITY_KEY, new CmpByAgi());
		mapOfCmps.put(PartyMember.STRENGTH_KEY, new CmpByStr());
		mapOfCmps.put(PartyMember.DIPLOMACY_KEY, new CmpByDip());
		mapOfCmps.put(PartyMember.KNOWLEDGE_KEY, new CmpByKnow());

		for (String key : mapOfCmps.keySet()) {
			ArrayList<PartyMember> sortedArray = new ArrayList<>();
			sortedArray.addAll(MainGame.getParty());
			Collections.sort(sortedArray, mapOfCmps.get(key));
			List<PartyMember> topThreeMem = sortedArray.subList(0, Math.min(3, sortedArray.size()));
			for(int i=0; i<3; i++) {
				if(i < topThreeMem.size()) {
					topThrees[i][PartyMember.getEventCreatorIndexOfStat(key)] = topThreeMem.get(i).getStat(key);
				} else {
					topThrees[i][PartyMember.getEventCreatorIndexOfStat(key)] = -1;
				}
			}
		}

		return topThrees;

	}
}
