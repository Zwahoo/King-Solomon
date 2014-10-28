package MainGame;

import java.util.ArrayList;

public class ResponseOption {
	
	//General Response Info
	private String text;
	private ArrayList<Long> cost = new ArrayList<Long>();
	private ArrayList<Long> requirements = new ArrayList<Long>();
	
	//Needed for determining win/lose
	private ArrayList<String> resourceModifiers = new ArrayList<String>();
	private ArrayList<String> partyStatModifiers = new ArrayList<String>();
	
	//Pass Info
	private String passText;
	
	//Victory Info
	private String winText;
	public String winFollowUp;
	private ArrayList<Long> winResourceChange = new ArrayList<Long>();
	private ArrayList<Long> winPartyStatChange = new ArrayList<Long>();

	//Lose Info
	private String loseText;
	public String loseFollowUp;
	private ArrayList<Long> loseResourceChange = new ArrayList<Long>();
	private ArrayList<Long> losePartyStatChange = new ArrayList<Long>();
	
	public ResponseOption(String text, ArrayList<Long> resourceStatCost, 
			ArrayList<Long> partyStatRequirement, 
			ArrayList<String> resourceModifiers, ArrayList<String> partyStatModifiers,
			String passText, String winText, String winFollowUp, ArrayList<Long> winResourceChange,
			ArrayList<Long> winPartyStatChange, String loseText, String loseFollowUp,
			ArrayList<Long> loseResourceChange, ArrayList<Long> losePartyStatChange){
		this.text = text;
		this.cost.addAll(resourceStatCost);
		this.requirements.addAll(partyStatRequirement);
		this.resourceModifiers.addAll(resourceModifiers);
		this.partyStatModifiers.addAll(partyStatModifiers);
		this.passText = passText;
		this.winText = winText;
		this.winFollowUp = winFollowUp;
		this.winResourceChange.addAll(winResourceChange);
		this.winPartyStatChange.addAll(winPartyStatChange);
		this.loseText = loseText;
		this.loseFollowUp = loseFollowUp;
		this.loseResourceChange.addAll(loseResourceChange);
		this.losePartyStatChange.addAll(losePartyStatChange);
	}
	
	
	//Get and Set ALL the things!
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<Long> getCost() {
		return cost;
	}
	public void setCost(ArrayList<Long> resourceStatCost) {
		this.cost = resourceStatCost;
	}
	public ArrayList<Long> getRequirements() {
		return requirements;
	}
	public void setRequirements(ArrayList<Long> partyStatRequirement) {
		this.requirements = partyStatRequirement;
	}
	public ArrayList<String> getResourceModifiers() {
		return resourceModifiers;
	}
	public void setResourceModifiers(ArrayList<String> resourceModifiers) {
		this.resourceModifiers = resourceModifiers;
	}
	public ArrayList<String> getPartyStatModifiers() {
		return partyStatModifiers;
	}
	public void setPartyStatModifiers(ArrayList<String> partyStatModifiers) {
		this.partyStatModifiers = partyStatModifiers;
	}
	public String getPassText() {
		return passText;
	}
	public void setPassText(String passText) {
		this.passText = passText;
	}
	public String getWinText() {
		return winText;
	}
	public void setWinText(String winText) {
		this.winText = winText;
	}
	public ArrayList<Long> getWinResourceChange() {
		return winResourceChange;
	}
	public void setWinResourceChange(ArrayList<Long> winResourceChange) {
		this.winResourceChange = winResourceChange;
	}
	public ArrayList<Long> getWinPartyStatChange() {
		return winPartyStatChange;
	}
	public void setWinPartyStatChange(ArrayList<Long> winPartyStatChange) {
		this.winPartyStatChange = winPartyStatChange;
	}
	public String getLoseText() {
		return loseText;
	}
	public void setLoseText(String loseText) {
		this.loseText = loseText;
	}
	public ArrayList<Long> getLoseResourceChange() {
		return loseResourceChange;
	}
	public void setLoseResourceChange(ArrayList<Long> loseResourceChange) {
		this.loseResourceChange = loseResourceChange;
	}
	public ArrayList<Long> getLosePartyStatChange() {
		return losePartyStatChange;
	}
	public void setLosePartyStatChange(ArrayList<Long> losePartyStatChange) {
		this.losePartyStatChange = losePartyStatChange;
	}
	
	public void testMe(){
		System.out.println("This worked!!");
	}
}
