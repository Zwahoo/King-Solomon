package MainGame;

import java.util.HashMap;
//holds attributes of each party member

public class PartyMember {
	
	public static final String MARKSMANSHIP_KEY = "Marksmanship";
	public static final String PERCEPTION_KEY = "Perception";
	public static final String TACTICS_KEY = "Tactics";
	public static final String LOYALTY_KEY = "Loyalty";
	public static final String AGILITY_KEY = "Agility";
	public static final String STRENGTH_KEY = "Strength";
	public static final String DIPLOMACY_KEY = "Diplomacy";
	public static final String KNOWLEDGE_KEY = "Knowledge";
	
	
	
	private String name;
	private String type;
	private int pay;
	private String backgroundInfo;
	private String status;
	boolean diseased = false;
	boolean injured = false;
	
	HashMap<String, Integer> stats;
	
	
	
}
