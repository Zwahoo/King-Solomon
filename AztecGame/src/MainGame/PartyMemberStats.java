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
		AVERAGE_ABE_STATS.put(PartyMember.STRENGTH_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.LOYALTY_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.DIPLOMACY_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.AGILITY_KEY, 10);
		AVERAGE_ABE_STATS.put(PartyMember.TACTICS_KEY, 10);
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

	// Old Possible Party
	// public static final HashMap<String, PartyMember> possibleParty = new
	// HashMap<String, PartyMember>();
	// static {
	// possibleParty.put("Macumazahn", new PartyMember("Macumazahn", "Hunter",
	// 100,
	// "An African native that consantly looks gaunt, wears jewelry and skins.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.HAPPY_HUNTER_STATS));
	// possibleParty.put("Jan Kruger", new PartyMember("Jan Kruger", "Hunter",
	// 100,
	// "A Boer that is large, intimidating, and has brown hair.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.HAPPY_HUNTER_STATS));
	// possibleParty.put("Umbopa", new PartyMember("Umbopa", "Mercenary", 100,
	// "An African native that is large, intimidating, and has a large scar on his face. Usually leaves his chest bear.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.MERRY_MERCENARY_STATS));
	// possibleParty.put("Gunther Reinhart", new PartyMember("Gunther Reinhart",
	// "Mercenary", 100,
	// "A Prussian man with a medium build and black hair. He wears a military outfit and has a scruffy beard that gives him an intimidating appearance.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.MERRY_MERCENARY_STATS));
	// possibleParty.put("Wonai", new PartyMember("Wonai", "Naturalist", 100,
	// "An older African native that proudly wears the garb of a shaman.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.NIFTY_NATURALIST_STATS));
	// possibleParty.put("Roland Perry", new PartyMember("Roland Perry",
	// "Naturalist", 100,
	// "A British man with a small frame and brown hair. Often seen wearing a monocle and very fine clothes.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.NIFTY_NATURALIST_STATS));
	// possibleParty.put("Theunis Van Zyl", new PartyMember("Theunis Van Zyl",
	// "Missionary", 100,
	// "A Boer that looks like a mirror of the stereotypical priest. Has a small frame, white hair, and wears glasses with the classic clerical garb.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.MARVELOUS_MISSIONARY_STATS));
	// possibleParty.put("Duncan MacKinnon", new PartyMember("Duncan MacKinnon",
	// "Missionary", 100,
	// "A Scottish man with a large frame and red hair. He wears normal clothes and a wide brimmed hat.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.MARVELOUS_MISSIONARY_STATS));
	// possibleParty.put("Willem de Bruin", new PartyMember("Willem de Bruin",
	// "Explorer", 100,
	// "A Boer with a medium build and blonde hair. Usually seen with his favorite tan explorers' shirt and vest.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	// possibleParty.put("Jack Reed", new PartyMember("Jack Reed", "Explorer",
	// 100,
	// "An American man with a medium build and a 12 o' clock shadow. Has a handsome face and a great smile, and is often seen wearing a simple shirt.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	// possibleParty.put("Tariro", new PartyMember("Tariro", "Guide", 100,
	// "An African native with a friendly face. He is often seen wearing European clothes imported from the north.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	// possibleParty.put("Jakobus Kotze", new PartyMember("Jakobus Kotze",
	// "Guide", 100,
	// "A Boer with a medium build and brown hair. His sides are rather wide, and his often seen wearing simple farmers' clothes.",
	// "assets/Portraits/MemberImage.png",
	// PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	// }

	// New Possible Party
	public static final HashMap<String, PartyMember>	possibleParty	= new HashMap<>();
	static {
		possibleParty.put("Hunter", new PartyMember("Enter Name Here", "Hunter", 100,
				"Hunters focus on killing and have high marksmanship and agility.", "assets/Portraits/HunterImage.png",
				PartyMemberStats.HAPPY_HUNTER_STATS));
		possibleParty.put("Mercenary", new PartyMember("Enter Name Here", "Mercenary", 100,
				"Mercenaries focus on killing and defending and have high tactics and marksmanship.",
				"assets/Portraits/MercenaryImage.png", PartyMemberStats.MERRY_MERCENARY_STATS));
		possibleParty.put("Naturalist", new PartyMember("Enter Name Here", "Naturalist", 100,
				"Naturalists focus on healing and acting as doctors and have high knowledge and perception.",
				"assets/Portraits/NaturalistImage.png", PartyMemberStats.NIFTY_NATURALIST_STATS));
		possibleParty.put("Missionary", new PartyMember("Enter Name Here", "Missionary", 100,
				"Missionaries focus on converting others to their religion and have high diplomacy and loyalty.",
				"assets/Portraits/MissionaryImage.png", PartyMemberStats.MARVELOUS_MISSIONARY_STATS));
		possibleParty.put("Explorer", new PartyMember("Enter Name Here", "Explorer", 100,
				"Explorers focus on surviving in new environments and have high perception.",
				"assets/Portraits/ExplorerImage.png", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
		possibleParty.put("Guide", new PartyMember("Enter Name Here", "Guide", 100,
				"Guides are comfortable in their home environment and have rounded stats.",
				"assets/Portraits/GuideImage.png", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	}
	
	public static final HashMap<String, PartyMember> defaultParty = new HashMap<String, PartyMember>();
	static {
		defaultParty.put("Kevin", new PartyMember("Kevin", "Hunter", 100,
				"An African native that consantly looks gaunt, wears jewelry and skins.", "assets/Portraits/MemberImage.png", PartyMemberStats.HAPPY_HUNTER_STATS));
		defaultParty.put("Zane", new PartyMember("Zane", "Mercenary", 100,
				"Zane shoots first, and asks questions NEVER!", "assets/Portraits/MemberImage.png", PartyMemberStats.MERRY_MERCENARY_STATS));
		defaultParty.put("Oliver", new PartyMember("Oliver", "Naturalist", 100,
				"Oliver loves olives.", "assets/Portraits/MemberImage.png", PartyMemberStats.NIFTY_NATURALIST_STATS));
		defaultParty.put("Michael", new PartyMember("Michael", "Missionary", 100,
				"Likes fishing and long walks on the beach.", "assets/Portraits/MemberImage.png", PartyMemberStats.MARVELOUS_MISSIONARY_STATS));
		defaultParty.put("Thomas", new PartyMember("Thomas", "Explorer", 100,
				"Thomas doesn't know how he ended up on an expedition into Africa.", "assets/Portraits/MemberImage.png", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
		defaultParty.put("Nick", new PartyMember("Nick", "Guide", 100,
				"Hs a terrible sense of direction. Why is he a guide again?", "assets/Portraits/MemberImage.png", PartyMemberStats.EXUBERANT_EXPLORER_STATS));
	}
	
	public static String hunterStr = "Hunter";
	public static String mercStr = "Mercenary";
	public static String natStr = "Naturalist";
	public static String missStr = "Missionary";
	public static String expStr = "Explorer";
	public static String guideStr = "Guide";
	
	public static final HashMap<String, HashMap<String, Integer>> classStats = new HashMap<String, HashMap<String, Integer>>();
	static {
		classStats.put(hunterStr, HAPPY_HUNTER_STATS);
		classStats.put(mercStr, MERRY_MERCENARY_STATS);
		classStats.put(natStr, NIFTY_NATURALIST_STATS);
		classStats.put(missStr, MARVELOUS_MISSIONARY_STATS);
		classStats.put(expStr, EXUBERANT_EXPLORER_STATS);
		classStats.put(guideStr, GIDDY_GUIDE_STATS);
	}
}
