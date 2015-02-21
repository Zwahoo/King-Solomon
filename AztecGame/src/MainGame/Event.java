package MainGame;

import java.util.ArrayList;
import java.util.HashMap;


public class Event {

	//General Info
	private String eventID;
	private String eventType;
	private ArrayList<String> possibleLocations = new ArrayList<String>();
	private ArrayList<String> reqParty = new ArrayList<String>();
	
	private String introText;
	private ArrayList<ResponseOption> responseOptions = new ArrayList<ResponseOption>();
	private HashMap<String, String> advice = new HashMap<String,String>();
	private String fleePassText;
	private String fleeFailText;
	
	private boolean partyMemberTargeted;
	private PartyMember affectedPartyMember;

	private String frequency;
	
	//Frequency Keys
	public final HashMap<String, Double> eventFrequencies = new HashMap<String, Double>() {
		{
			put("Likely", 0.75);
			put("Common", 0.60);
			put("Uncommon", 0.40);
			put("Rare", 0.25);
		}
	};
	//public final double LIKELY_FREQUENCY = .75; //Percent chance of occurring
	//public final double COMMON_FREQUENCY = .60;
	//public final double UNCOMMON_FREQUENCY = .40;
	//public final double RARE_FREQUENCY = .25;
	
	public boolean playJingles = EventDrawer.defaultPlayJingles;
	
	public Event(String eventID, String eventType, 
			ArrayList<String> possibleLocations, ArrayList<String> reqParty, 
			String introText, ArrayList<ResponseOption> responseOptions, 
			HashMap<String, String> advice, String fleePassText, 
			String fleeFailText, boolean partyMemberTargeted, String frequency){
		this.eventID = eventID;
		this.eventType = eventType;
		this.possibleLocations.addAll(possibleLocations);
		this.reqParty.addAll(reqParty);
		this.introText = introText;
		this.responseOptions.addAll(responseOptions);
		this.advice.putAll(advice);
		this.fleePassText = fleePassText;
		this.fleeFailText = fleeFailText;
		this.setPartyMemberTargeted(partyMemberTargeted);
		this.frequency = frequency;
	}
	
	//Replaces the string {playername} with the selected player's name.
	public String handleNameReplacement(String str) {
		if(affectedPartyMember != null) {
			return str.replaceAll("\\{playername\\}", affectedPartyMember.getName());
		}
		return str;
	}
	
	//ALL the getters and setters!
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public ArrayList<String> getPossibleLocations() {
		return possibleLocations;
	}
	public void setPossibleLocations(ArrayList<String> possibleLocations) {
		this.possibleLocations = possibleLocations;
	}
	public ArrayList<String> getReqParty() {
		return reqParty;
	}
	public void setReqParty(ArrayList<String> reqParty) {
		this.reqParty = reqParty;
	}
	public String getIntroText() {
		return handleNameReplacement(this.introText);
	}
	public void setIntroText(String introText) {
		this.introText = introText;
	}
	public ArrayList<ResponseOption> getResponseOptions() {
		return responseOptions;
	}
	public void setResponseOptions(ArrayList<ResponseOption> responseOptions) {
		this.responseOptions = responseOptions;
	}
	public HashMap<String, String> getAdvice() {
		return advice;
	}
	public void setAdvice(HashMap<String, String> advice) {
		this.advice = advice;
	}
	public String getFleePassText() {
		return handleNameReplacement(fleePassText);
	}
	public void setFleePassText(String fleePassText) {
		this.fleePassText = fleePassText;
	}
	public String getFleeFailText() {
		return handleNameReplacement(fleeFailText);
	}
	public void setFleeFailText(String fleeFailText) {
		this.fleeFailText = fleeFailText;
	}
	
	public boolean isPartyMemberTargeted() {
		return partyMemberTargeted;
	}
	
	public void setPartyMemberTargeted(boolean partyMemberTargeted) {
		this.partyMemberTargeted = partyMemberTargeted;
	}

	public boolean setAffectedPartyMemberRandomly(ArrayList<PartyMember> partyMembers){
		if(partyMembers.size() <= 1) {
			return false;
		}
		int ran1 = (int)Math.floor(Math.random()*partyMembers.size());
		while(partyMembers.get(ran1).isGentleman()) {
			ran1 = (int)Math.floor(Math.random()*partyMembers.size());
		}
		this.setAffectedPartyMember(partyMembers.get(ran1));
		return true;
	}
	
	public void setAffectedPartyMember(PartyMember partyMember){
		this.affectedPartyMember = partyMember;
		for(ResponseOption resp : this.responseOptions) {
			resp.setSelectedMember(affectedPartyMember);
		}
	}
	public PartyMember getAffectedPartyMember(){
		return affectedPartyMember;
	}
	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void testMe(){
		System.out.println(eventID + " works!");
	}
}
