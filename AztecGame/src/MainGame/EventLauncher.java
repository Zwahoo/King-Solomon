package MainGame;

import java.util.ArrayList;

public class EventLauncher {
	
	public static void launchEvent(Event toLaunch, ArrayList<PartyMember> presMembers) {
		
		System.out.println("Event Launched!");
		System.out.println("\tName: " + toLaunch.getEventID());
		System.out.println("\tPresent Party: ");
		for(PartyMember member : presMembers) {
			System.out.println("\t\t" + member.getName());
		} 
		
	}
}
