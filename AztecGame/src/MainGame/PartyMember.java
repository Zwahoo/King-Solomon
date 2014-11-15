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
	boolean diseased;
	boolean injured;
	
	private HashMap<String, Integer> stats;
	
	public PartyMember(String name, String type, int pay, String backgroundInfo, HashMap<String, Integer> stats) {
		this.name = name;
		this.type = type;
		this.pay = pay;
		this.backgroundInfo = backgroundInfo;
		this.stats = stats;
		
		status = "Healthy";
		diseased = false;
		injured = false;
	}
	
	public String generateInfoString(String between) {
		String toRet = name;
		toRet += between;
		toRet += generateStatsString(between);
		return toRet;
	}
	public String generateStatsString(String between) {
		String toRet = "";
		for(String stat : stats.keySet()) {
			toRet += (stat + ": " + stats.get(stat) + "" + between);
		}
		//Remove last between
		toRet = toRet.substring(0, toRet.length() - between.length());
		return toRet;
	}
	
	
	
	//Getters and Setters
	public int getStat(String key) {
		if(!stats.containsKey(key)) {
			System.out.println("Trying to obtain unknown stat: " + key + " for party member: " + name);
			return -1;
		}
		return stats.get(key);
	}		
	public void incStat(String key, int increase){
		if(!stats.containsKey(key)) {
			System.out.println("Trying to obtain unknown stat: " + key + " for party member: " + name);
		}
		else {
			int currentValue = stats.get(key);
			stats.put(key, currentValue + increase);
		}
	}
	public void setStat(String key, int val){
		if(!stats.containsKey(key)) {
			System.out.println("Trying to obtain unknown stat: " + key + " for party member: " + name);
		}
		else {
			stats.put(key, val);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public String getBackgroundInfo() {
		return backgroundInfo;
	}
	public void setBackgroundInfo(String backgroundInfo) {
		this.backgroundInfo = backgroundInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isDiseased() {
		return diseased;
	}
	public void setDiseased(boolean diseased) {
		this.diseased = diseased;
	}
	public boolean isInjured() {
		return injured;
	}
	public void setInjured(boolean injured) {
		this.injured = injured;
	}	
}
