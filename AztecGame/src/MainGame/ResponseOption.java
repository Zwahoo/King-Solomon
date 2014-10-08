package MainGame;

import java.util.ArrayList;

public class ResponseOption {
	
	//General Response Info
	private String text;
	private ArrayList<Integer> resourceStatCost;
	private ArrayList<Integer> partyStatRequirement;
	
	//Needed for determining win/lose
	ArrayList<String> resourceModifiers;
	ArrayList<String> partyStatModifiers;
	
	//Pass Info
	private String passText;
	
	//Victory Info
	private String winText;
	private ArrayList<Integer> winResourceChange;
	private ArrayList<Integer> winPartyStatChange;

	//Lose Info
	private String loseText;
	private ArrayList<Integer> loseResourceChange;
	private ArrayList<Integer> losePartyStatChange;
	
}
