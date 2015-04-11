package MainGame;

import java.util.ArrayList;
import java.util.HashMap;

public final class ProfessionHobbyStats {

	public static String noneKey = "None";
	public static String gunsmithKey = "Gunsmith";
	public static String fighterKey = "Prize Fighter";
	public static String ambassadorKey = "Ambassador";
	public static String veteranKey = "Veteran";
	public static String doctorKey = "Doctor";
	public static String officerKey = "Officer";
	public static String saboteurKey = "Saboteur";
	
	public static final HashMap<String, String> IMAGE_FILE_PATHS;
	static {
		IMAGE_FILE_PATHS = new HashMap<String, String>();
		IMAGE_FILE_PATHS.put(noneKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(gunsmithKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(fighterKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(ambassadorKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(veteranKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(doctorKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(officerKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(saboteurKey, "assets/Portraits/ExplorerImage.png");
	}
	
	public static final ArrayList<String> GUNSMITH_STATS;
	static 
	{
		GUNSMITH_STATS = new ArrayList<String>();
		GUNSMITH_STATS.add(PartyMember.STRENGTH_KEY);
		GUNSMITH_STATS.add(PartyMember.MARKSMANSHIP_KEY);
	}

	public static final ArrayList<String> FIGHTER_STATS;
	static 
	{
		FIGHTER_STATS = new ArrayList<String>();
		FIGHTER_STATS.add(PartyMember.STRENGTH_KEY);
		FIGHTER_STATS.add(PartyMember.AGILITY_KEY);
	}

	public static final ArrayList<String> AMBASSADOR_STATS;
	static 
	{
		AMBASSADOR_STATS = new ArrayList<String>();
		AMBASSADOR_STATS.add(PartyMember.KNOWLEDGE_KEY);
		AMBASSADOR_STATS.add(PartyMember.DIPLOMACY_KEY);
	}

	public static final ArrayList<String> VETERAN_STATS;
	static 
	{
		VETERAN_STATS = new ArrayList<String>();
		VETERAN_STATS.add(PartyMember.MARKSMANSHIP_KEY);
		VETERAN_STATS.add(PartyMember.TACTICS_KEY);
	}

	public static final ArrayList<String> DOCTOR_STATS;
	static 
	{
		DOCTOR_STATS = new ArrayList<String>();
		DOCTOR_STATS.add(PartyMember.KNOWLEDGE_KEY);
		DOCTOR_STATS.add(PartyMember.PERCEPTION_KEY);
	}

	public static final ArrayList<String> OFFICER_STATS;
	static 
	{
		OFFICER_STATS = new ArrayList<String>();
		OFFICER_STATS.add(PartyMember.PERCEPTION_KEY);
		OFFICER_STATS.add(PartyMember.TACTICS_KEY);
	}

	public static final ArrayList<String> SABOTEUR_STATS;
	static 
	{
		SABOTEUR_STATS = new ArrayList<String>();
		SABOTEUR_STATS.add(PartyMember.AGILITY_KEY);
		SABOTEUR_STATS.add(PartyMember.DIPLOMACY_KEY);
	}
	

	public static final HashMap<String, ArrayList<String>> PROFESSION_STATS;
	static
	{
		PROFESSION_STATS = new HashMap<String, ArrayList<String>>();
		PROFESSION_STATS.put(noneKey, new ArrayList<String>());
		PROFESSION_STATS.put(gunsmithKey, ProfessionHobbyStats.GUNSMITH_STATS);
		PROFESSION_STATS.put(fighterKey, ProfessionHobbyStats.FIGHTER_STATS);
		PROFESSION_STATS.put(ambassadorKey, ProfessionHobbyStats.AMBASSADOR_STATS);
		PROFESSION_STATS.put(veteranKey, ProfessionHobbyStats.VETERAN_STATS);
		PROFESSION_STATS.put(doctorKey, ProfessionHobbyStats.DOCTOR_STATS);
		PROFESSION_STATS.put(officerKey, ProfessionHobbyStats.OFFICER_STATS);
		PROFESSION_STATS.put(saboteurKey, ProfessionHobbyStats.SABOTEUR_STATS);
	}

	
}
