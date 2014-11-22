package MainGame;

import java.util.HashMap;

//This class just contains a bunch of stat sets, one for each of the characters.
public final class PartyMemberStats {
	
	
	/* For the Copy Pastes!
	public static final HashMap<String, Integer> TOREFACTOR_STATS;
	static
	{
		TOREFACTOR_STATS = new HashMap<String, Integer>();
		TOREFACTOR_STATS.put(PartyMember.MARKSMANSHIP_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.PERCEPTION_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.TACTICS_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.LOYALTY_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.AGILITY_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.STRENGTH_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.DIPLOMACY_KEY, 10);
		TOREFACTOR_STATS.put(PartyMember.KNOWLEDGE_KEY, 10);
	}
	 */
	
	public static final HashMap<String, String> STAT_DESCRIPTIONS;
	static
	{
		STAT_DESCRIPTIONS = new HashMap<String, String>();
		STAT_DESCRIPTIONS.put(PartyMember.MARKSMANSHIP_KEY, "How accurate the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.PERCEPTION_KEY, "How observant the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.TACTICS_KEY, "How strategic the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.LOYALTY_KEY, "How loyal the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.AGILITY_KEY, "How agile the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.STRENGTH_KEY, "How strong the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.DIPLOMACY_KEY, "How charismatic the party member is.");
		STAT_DESCRIPTIONS.put(PartyMember.KNOWLEDGE_KEY, "How smart the party member is.");
		
	}
	
	public static final HashMap<String, Integer> AVERAGE_ABE_STATS;
	static
	{
		AVERAGE_ABE_STATS = new HashMap<String, Integer>();
		AVERAGE_ABE_STATS.put(PartyMember.MARKSMANSHIP_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.PERCEPTION_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.TACTICS_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.LOYALTY_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.AGILITY_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.STRENGTH_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.DIPLOMACY_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.KNOWLEDGE_KEY, 10);
	}
	
	public static final HashMap<String, Integer> HAPPY_HUNTER_STATS;
	static
	{
		HAPPY_HUNTER_STATS = new HashMap<String, Integer>();
		HAPPY_HUNTER_STATS.put(PartyMember.MARKSMANSHIP_KEY, 18);
		HAPPY_HUNTER_STATS.put(PartyMember.PERCEPTION_KEY, 14);
		HAPPY_HUNTER_STATS.put(PartyMember.TACTICS_KEY, 14);
		HAPPY_HUNTER_STATS.put(PartyMember.LOYALTY_KEY, 10);
		HAPPY_HUNTER_STATS.put(PartyMember.AGILITY_KEY, 10);
		HAPPY_HUNTER_STATS.put(PartyMember.STRENGTH_KEY, 10);
		HAPPY_HUNTER_STATS.put(PartyMember.DIPLOMACY_KEY, 2);
		HAPPY_HUNTER_STATS.put(PartyMember.KNOWLEDGE_KEY, 2);
	}
	
	public static final HashMap<String, Integer> MERRY_MERCENARY_STATS;
	static
	{
		MERRY_MERCENARY_STATS = new HashMap<String, Integer>();
		MERRY_MERCENARY_STATS.put(PartyMember.MARKSMANSHIP_KEY, 14);
		MERRY_MERCENARY_STATS.put(PartyMember.PERCEPTION_KEY, 10);
		MERRY_MERCENARY_STATS.put(PartyMember.TACTICS_KEY, 18);
		MERRY_MERCENARY_STATS.put(PartyMember.LOYALTY_KEY, 2);
		MERRY_MERCENARY_STATS.put(PartyMember.AGILITY_KEY, 14);
		MERRY_MERCENARY_STATS.put(PartyMember.STRENGTH_KEY, 14);
		MERRY_MERCENARY_STATS.put(PartyMember.DIPLOMACY_KEY, 3);
		MERRY_MERCENARY_STATS.put(PartyMember.KNOWLEDGE_KEY, 5);
	}
	
	public static final HashMap<String, Integer> NIFTY_NATURALIST_STATS;
	static
	{
		NIFTY_NATURALIST_STATS = new HashMap<String, Integer>();
		NIFTY_NATURALIST_STATS.put(PartyMember.MARKSMANSHIP_KEY, 2);
		NIFTY_NATURALIST_STATS.put(PartyMember.PERCEPTION_KEY, 14);
		NIFTY_NATURALIST_STATS.put(PartyMember.TACTICS_KEY, 4);
		NIFTY_NATURALIST_STATS.put(PartyMember.LOYALTY_KEY, 14);
		NIFTY_NATURALIST_STATS.put(PartyMember.AGILITY_KEY, 8);
		NIFTY_NATURALIST_STATS.put(PartyMember.STRENGTH_KEY, 5);
		NIFTY_NATURALIST_STATS.put(PartyMember.DIPLOMACY_KEY, 13);
		NIFTY_NATURALIST_STATS.put(PartyMember.KNOWLEDGE_KEY, 20);
	}
	
	public static final HashMap<String, Integer> MARVELOUS_MISSIONARY_STATS;
	static
	{
		MARVELOUS_MISSIONARY_STATS = new HashMap<String, Integer>();
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.MARKSMANSHIP_KEY, 5);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.PERCEPTION_KEY, 5);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.TACTICS_KEY, 5);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.LOYALTY_KEY, 22);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.AGILITY_KEY, 5);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.STRENGTH_KEY, 10);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.DIPLOMACY_KEY, 18);
		MARVELOUS_MISSIONARY_STATS.put(PartyMember.KNOWLEDGE_KEY, 10);
	}
	
	
	public static final HashMap<String, Integer> EXUBERANT_EXPLORER_STATS;
	static
	{
		EXUBERANT_EXPLORER_STATS = new HashMap<String, Integer>();
		EXUBERANT_EXPLORER_STATS.put(PartyMember.MARKSMANSHIP_KEY, 10);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.PERCEPTION_KEY, 18);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.TACTICS_KEY, 2);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.LOYALTY_KEY, 10);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.AGILITY_KEY, 13);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.STRENGTH_KEY, 4);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.DIPLOMACY_KEY, 10);
		EXUBERANT_EXPLORER_STATS.put(PartyMember.KNOWLEDGE_KEY, 13);
	}
	
	public static final HashMap<String, Integer> GIDDY_GUIDE_STATS;
	static
	{
		GIDDY_GUIDE_STATS = new HashMap<String, Integer>();
		GIDDY_GUIDE_STATS.put(PartyMember.MARKSMANSHIP_KEY, 8);
		GIDDY_GUIDE_STATS.put(PartyMember.PERCEPTION_KEY, 14);
		GIDDY_GUIDE_STATS.put(PartyMember.TACTICS_KEY, 8);
		GIDDY_GUIDE_STATS.put(PartyMember.LOYALTY_KEY, 8);
		GIDDY_GUIDE_STATS.put(PartyMember.AGILITY_KEY, 9);
		GIDDY_GUIDE_STATS.put(PartyMember.STRENGTH_KEY, 16);
		GIDDY_GUIDE_STATS.put(PartyMember.DIPLOMACY_KEY, 8);
		GIDDY_GUIDE_STATS.put(PartyMember.KNOWLEDGE_KEY, 9);
	}
}
