package MainGame;

import java.util.ArrayList;

public class ResponseOption {
	
	//General Response Info
	private String text;
	private ArrayList<Long> cost = new ArrayList<Long>();
	private ArrayList<Long> requirements = new ArrayList<Long>();
	
	//Needed for determining win/lose
	private ArrayList<Long> resourceModifiers = new ArrayList<Long>();
	private ArrayList<Long> partyStatModifiers = new ArrayList<Long>();
	
	//Pass Info
	private String passText;
	private boolean killPersonPass;
	private String passFollowUp;
	
	//Victory Info
	private String winText;
	public String winFollowUp;
	private ArrayList<Long> winResourceChange = new ArrayList<Long>();
	private ArrayList<Long> winPartyStatChange = new ArrayList<Long>();
	private boolean killPersonWin;
	private int rewardDisperseWin;

	//Lose Info
	private String loseText;
	public String loseFollowUp;
	private ArrayList<Long> loseResourceChange = new ArrayList<Long>();
	private ArrayList<Long> losePartyStatChange = new ArrayList<Long>();
	private boolean killPersonLose;
	private int rewardDisperseLose;
	
	private PartyMember selectedMember = null;
		
	public ResponseOption(String text, ArrayList<Long> resourceStatCost, 
			ArrayList<Long> partyStatRequirement, 
			ArrayList<Long> resourceModifiers, ArrayList<Long> partyStatModifiers,
			String passText, boolean killPersonPass, String passFollowUp,
			String winText, String winFollowUp, ArrayList<Long> winResourceChange,
			ArrayList<Long> winPartyStatChange, boolean killPersonWin, int rewardDisperseWin,
			String loseText, String loseFollowUp, ArrayList<Long> loseResourceChange, 
			ArrayList<Long> losePartyStatChange, boolean killPersonLose, int rewardDisperseLose){
		
		this.text = text;
		this.cost.addAll(resourceStatCost);
		this.requirements.addAll(partyStatRequirement);
		this.resourceModifiers.addAll(resourceModifiers);
		this.partyStatModifiers.addAll(partyStatModifiers);
		
		this.passText = passText;
		this.killPersonPass = killPersonPass;
		this.passFollowUp = passFollowUp;
		
		this.winText = winText;
		this.winFollowUp = winFollowUp;
		this.winResourceChange.addAll(winResourceChange);
		this.winPartyStatChange.addAll(winPartyStatChange);
		this.killPersonWin = killPersonWin;
		this.rewardDisperseWin = rewardDisperseWin;
		
		this.loseText = loseText;
		this.loseFollowUp = loseFollowUp;
		this.loseResourceChange.addAll(loseResourceChange);
		this.losePartyStatChange.addAll(losePartyStatChange);
		this.killPersonLose = killPersonLose;
		this.rewardDisperseLose = rewardDisperseLose;
		
		appendTextWithCosts();
		appendTextWithPartyRequirements();
	}
	
	//Replaces the string {playername} with the selected player's name.
	public String handleNameReplacement(String str) {
		if(selectedMember != null) {
			return str.replaceAll("\\{playername\\}", selectedMember.getName());
		}
		return str;
	}
	
	public void appendTextWithCosts(){
		String[] resourceKeys = {
				MainGame.FOOD_KEY, MainGame.WATER_KEY, MainGame.VALUABLES_KEY,
				MainGame.AMMO_KEY, MainGame.MEDICINE_KEY, MainGame.MORALE_KEY,
				MainGame.STAMINA_KEY, MainGame.PACK_ANIMALS_KEY
		};
		boolean hasBeenEdited = false;
		for (int i = 0; i < cost.size(); i++){
			if (cost.get(i) > 0 && !hasBeenEdited){
				text += " (Costs: " + cost.get(i) + " " + resourceKeys[i];
				hasBeenEdited = true;
			}
			else if (cost.get(i) > 0 && hasBeenEdited){
				text += ", " + cost.get(i) + " " + resourceKeys[i];
			}
		}
		
		if (hasBeenEdited){
			text += ")";
		}
		else {
			text += " (Costs: Nothing)";
		}
	}
	
	public void appendTextWithPartyRequirements(){
		ArrayList<Long> totalCurrentPartyStats = new ArrayList<Long>();
		totalCurrentPartyStats.addAll(MainGame.getTotalCurrentPartyStats());
		PartyMember keyMan = MainGame.party.get(0);
		String[] partyStatKeys = {
				keyMan.MARKSMANSHIP_KEY, keyMan.PERCEPTION_KEY,
				keyMan.TACTICS_KEY, keyMan.LOYALTY_KEY,
				keyMan.AGILITY_KEY, keyMan.STRENGTH_KEY,
				keyMan.DIPLOMACY_KEY, keyMan.KNOWLEDGE_KEY
		};
		
		boolean hasBeenEdited = false;
		for (int i = 0; i < totalCurrentPartyStats.size(); i++){
			if ((totalCurrentPartyStats.get(i) < requirements.get(i)) && !hasBeenEdited){
				text += " You require more: " + partyStatKeys[i];
				hasBeenEdited = true;
			}
			else if (totalCurrentPartyStats.get(i) < requirements.get(i) && hasBeenEdited){
				text += ", " + partyStatKeys[i];
			}
		}
		if (hasBeenEdited){
			text += ".";
		}
	}
	
	public void setSelectedMember(PartyMember selectedMember) {
		this.selectedMember = selectedMember;
	}
	
	//Get and Set ALL the things!
	public String getText() {
		return handleNameReplacement(text);
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
	public ArrayList<Long> getResourceModifiers() {
		return resourceModifiers;
	}
	public void setResourceModifiers(ArrayList<Long> resourceModifiers) {
		this.resourceModifiers = resourceModifiers;
	}
	public ArrayList<Long> getPartyStatModifiers() {
		return partyStatModifiers;
	}
	public void setPartyStatModifiers(ArrayList<Long> partyStatModifiers) {
		this.partyStatModifiers = partyStatModifiers;
	}
	public String getPassText() {
		return handleNameReplacement(passText);
	}
	public void setPassText(String passText) {
		this.passText = passText;
	}
	/**
	 * @return the killPersonPass
	 */
	public boolean isKillPersonPass() {
		return killPersonPass;
	}


	/**
	 * @param killPersonPass the killPersonPass to set
	 */
	public void setKillPersonPass(boolean killPersonPass) {
		this.killPersonPass = killPersonPass;
	}


	public String getWinText() {
		return handleNameReplacement(winText);
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
	/**
	 * @return the killPersonWin
	 */
	public boolean isKillPersonWin() {
		return killPersonWin;
	}


	/**
	 * @param killPersonWin the killPersonWin to set
	 */
	public void setKillPersonWin(boolean killPersonWin) {
		this.killPersonWin = killPersonWin;
	}


	/**
	 * @return the rewardDisperseWin
	 */
	public int getRewardDisperseWin() {
		return rewardDisperseWin;
	}


	/**
	 * @param rewardDisperseWin the rewardDisperseWin to set
	 */
	public void setRewardDisperseWin(int rewardDisperseWin) {
		this.rewardDisperseWin = rewardDisperseWin;
	}


	public String getLoseText() {
		return handleNameReplacement(loseText);
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
	
	/**
	 * @return the killPersonLose
	 */
	public boolean isKillPersonLose() {
		return killPersonLose;
	}


	/**
	 * @param killPersonLose the killPersonLose to set
	 */
	public void setKillPersonLose(boolean killPersonLose) {
		this.killPersonLose = killPersonLose;
	}


	/**
	 * @return the rewardDisperseLose
	 */
	public int getRewardDisperseLose() {
		return rewardDisperseLose;
	}


	/**
	 * @param rewardDisperseLose the rewardDisperseLose to set
	 */
	public void setRewardDisperseLose(int rewardDisperseLose) {
		this.rewardDisperseLose = rewardDisperseLose;
	}


	public void testMe(){
		System.out.println("This worked!!");
	}
}
