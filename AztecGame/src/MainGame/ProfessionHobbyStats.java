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
	
	public static String sportsKey = "Sports";
	public static String birdWatchingKey = "Bird Watching";
	public static String writingKey = "Writing";
	public static String shootingKey = "Shooting";
	public static String historianKey = "Historian";
	public static String socialiteKey = "Socialite";
	public static String wrestlerKey = "Wrestler";
	
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
		IMAGE_FILE_PATHS.put(sportsKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(birdWatchingKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(writingKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(shootingKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(historianKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(socialiteKey, "assets/Portraits/ExplorerImage.png");
		IMAGE_FILE_PATHS.put(wrestlerKey, "assets/Portraits/ExplorerImage.png");
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

	
	
	public static final ArrayList<String> SPORTS_STATS;
	static 
	{
		SPORTS_STATS = new ArrayList<String>();
		SPORTS_STATS.add(PartyMember.AGILITY_KEY);
	}
	
	public static final ArrayList<String> BIRD_WATCHING_STATS;
	static 
	{
		BIRD_WATCHING_STATS = new ArrayList<String>();
		BIRD_WATCHING_STATS.add(PartyMember.PERCEPTION_KEY);
	}
	
	public static final ArrayList<String> WRITING_STATS;
	static 
	{
		WRITING_STATS = new ArrayList<String>();
		WRITING_STATS.add(PartyMember.KNOWLEDGE_KEY);
	}
	
	public static final ArrayList<String> SHOOTING_STATS;
	static 
	{
		SHOOTING_STATS = new ArrayList<String>();
		SHOOTING_STATS.add(PartyMember.MARKSMANSHIP_KEY);
	}
	
	public static final ArrayList<String> HISTORIAN_STATS;
	static 
	{
		HISTORIAN_STATS = new ArrayList<String>();
		HISTORIAN_STATS.add(PartyMember.TACTICS_KEY);
	}
	
	public static final ArrayList<String> SOCIALITE_STATS;
	static 
	{
		SOCIALITE_STATS = new ArrayList<String>();
		SOCIALITE_STATS.add(PartyMember.DIPLOMACY_KEY);
	}
	
	public static final ArrayList<String> WRESTLER_STATS;
	static 
	{
		WRESTLER_STATS = new ArrayList<String>();
		WRESTLER_STATS.add(PartyMember.STRENGTH_KEY);
	}
	
	public static final HashMap<String, ArrayList<String>> HOBBY_STATS;
	static
	{
		HOBBY_STATS = new HashMap<String, ArrayList<String>>();
		HOBBY_STATS.put(noneKey, new ArrayList<String>());
		HOBBY_STATS.put(sportsKey, ProfessionHobbyStats.SPORTS_STATS);
		HOBBY_STATS.put(birdWatchingKey, ProfessionHobbyStats.BIRD_WATCHING_STATS);
		HOBBY_STATS.put(writingKey, ProfessionHobbyStats.WRITING_STATS);
		HOBBY_STATS.put(shootingKey, ProfessionHobbyStats.SHOOTING_STATS);
		HOBBY_STATS.put(historianKey, ProfessionHobbyStats.HISTORIAN_STATS);
		HOBBY_STATS.put(socialiteKey, ProfessionHobbyStats.SOCIALITE_STATS);
		HOBBY_STATS.put(wrestlerKey, ProfessionHobbyStats.WRESTLER_STATS);
	}
	
}
