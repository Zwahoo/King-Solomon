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
	
	
	public Event(String eventID, String eventType, 
			ArrayList<String> possibleLocations, ArrayList<String> reqParty, 
			String introText, ArrayList<ResponseOption> responseOptions, 
			HashMap<String, String> advice, String fleePassText, 
			String fleeFailText){
		this.eventID = eventID;
		this.eventType = eventType;
		this.possibleLocations.addAll(possibleLocations);
		this.reqParty.addAll(reqParty);
		this.introText = introText;
		this.responseOptions.addAll(responseOptions);
		this.advice.putAll(advice);
		this.fleePassText = fleePassText;
		this.fleeFailText = fleeFailText;
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
		return introText;
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
		return fleePassText;
	}
	public void setFleePassText(String fleePassText) {
		this.fleePassText = fleePassText;
	}
	public String getFleeFailText() {
		return fleeFailText;
	}
	public void setFleeFailText(String fleeFailText) {
		this.fleeFailText = fleeFailText;
	}
	
	public void testMe(){
		System.out.println("This works!");
	}
}
