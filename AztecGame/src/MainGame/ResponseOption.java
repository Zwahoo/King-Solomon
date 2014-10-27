package MainGame;

import java.util.ArrayList;

public class ResponseOption {
	
	//General Response Info
	private String text;
	private ArrayList<Integer> cost = new ArrayList<Integer>();
	private ArrayList<Integer> requirements = new ArrayList<Integer>();
	
	//Needed for determining win/lose
	private ArrayList<String> resourceModifiers = new ArrayList<String>();
	private ArrayList<String> partyStatModifiers = new ArrayList<String>();
	
	//Pass Info
	private String passText;
	
	//Victory Info
	private String winText;
	public String winFollowUp;
	private ArrayList<Integer> winResourceChange = new ArrayList<Integer>();
	private ArrayList<Integer> winPartyStatChange = new ArrayList<Integer>();

	//Lose Info
	private String loseText;
	public String loseFollowUp;
	private ArrayList<Integer> loseResourceChange = new ArrayList<Integer>();
	private ArrayList<Integer> losePartyStatChange = new ArrayList<Integer>();
	
	public ResponseOption(String text, ArrayList<Integer> resourceStatCost, 
			ArrayList<Integer> partyStatRequirement, 
			ArrayList<String> resourceModifiers, ArrayList<String> partyStatModifiers,
			String passText, String winText, String winFollowUp, ArrayList<Integer> winResourceChange,
			ArrayList<Integer> winPartyStatChange, String loseText, String loseFollowUp,
			ArrayList<Integer> loseResourceChange, ArrayList<Integer> losePartyStatChange){
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
	public ArrayList<Integer> getCost() {
		return cost;
	}
	public void setCost(ArrayList<Integer> resourceStatCost) {
		this.cost = resourceStatCost;
	}
	public ArrayList<Integer> getRequirements() {
		return requirements;
	}
	public void setRequirements(ArrayList<Integer> partyStatRequirement) {
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
	public ArrayList<Integer> getWinResourceChange() {
		return winResourceChange;
	}
	public void setWinResourceChange(ArrayList<Integer> winResourceChange) {
		this.winResourceChange = winResourceChange;
	}
	public ArrayList<Integer> getWinPartyStatChange() {
		return winPartyStatChange;
	}
	public void setWinPartyStatChange(ArrayList<Integer> winPartyStatChange) {
		this.winPartyStatChange = winPartyStatChange;
	}
	public String getLoseText() {
		return loseText;
	}
	public void setLoseText(String loseText) {
		this.loseText = loseText;
	}
	public ArrayList<Integer> getLoseResourceChange() {
		return loseResourceChange;
	}
	public void setLoseResourceChange(ArrayList<Integer> loseResourceChange) {
		this.loseResourceChange = loseResourceChange;
	}
	public ArrayList<Integer> getLosePartyStatChange() {
		return losePartyStatChange;
	}
	public void setLosePartyStatChange(ArrayList<Integer> losePartyStatChange) {
		this.losePartyStatChange = losePartyStatChange;
	}
	
	public void testMe(){
		System.out.println("This worked!!");
	}
}
