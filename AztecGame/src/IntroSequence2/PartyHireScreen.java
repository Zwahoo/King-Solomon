package IntroSequence2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Components.*;
import MainGame.DrawScreen;
import MainGame.IntroSequence;
import MainGame.PartyMember;
import MainGame.PartyMemberStats;
import MainGame.gameframe;

public class PartyHireScreen implements DrawScreen {
	
	public Button nameButton;
	public String memberName = "[Choose Name]";
	int nameBtnX = 50;
	int nameBtnY = 50;
	int nameBtnWidth = 200;
	int nameBtnHeight = 40;
	
	Textbox statBox;
	int statBoxX = 50;
	int statBoxY = nameBtnY + nameBtnHeight + 50;
	int statBoxWidth = 300;
	int statBoxHeight = 400;
	
	Button finishButton;
	int finishWidth = 200;
	int finishHeight = 40;
	int finishX = gameframe.windowWidth - finishWidth - 40;
	int finishY = gameframe.windowHeight - finishHeight - 40;
	
	HashMap<String, Integer> stats = PartyMemberStats.HAPPY_HUNTER_STATS;
	
	String memberClass = PartyMemberStats.hunterStr;
	
	String memberImage = "assets/Portraits/ExplorerImage.png";
	
	public PartyHireScreen(PartyMember memberToLoad) {
		if(memberToLoad != null) {
			memberName = memberToLoad.getName();
			memberClass = memberToLoad.getType();
			stats = memberToLoad.getStats();
		}
		
		statBox = new Textbox(getStatBoxText(), statBoxX, statBoxY, statBoxWidth, statBoxHeight, IntroSequence.input);
		
		nameButton = new Button(nameBtnX, nameBtnY, nameBtnWidth, nameBtnHeight, memberName, IntroSequence.input) {
			@Override
			public void onClick() {
				memberName = JOptionPane.showInputDialog("Please Enter a Name:", memberName);
				if(memberName == null) {
					return;
				}
				while(memberName.length() > 26) {
					memberName = JOptionPane.showInputDialog("Sorry! That name is too long.\nPlease Enter Another Name:");
				}
				nameButton.setText(memberName);
				nameButton.setFontColor(Color.black);
			}
		};
		nameButton.setFontColor(Color.red);
		
		finishButton = new Button(finishX, finishY, finishWidth, finishHeight, "Finish", IntroSequence.input){
			@Override
			public void onClick() {
				ResourcesAndPartyHolder.switchToResources(getCreatedMember());
			}
		};
	}
	
	public String getStatBoxText() {
		String toRet = "Member's Stats:\n\n";
		for(String stat : stats.keySet()) {
			toRet += (stat + ": " + stats.get(stat) + "\n");
		}
		return toRet;
	}
	
	@Override
	public boolean update() {
		nameButton.update();
		statBox.update();
		finishButton.update();
		return false;
	}

	@Override
	public void draw(Graphics g) {
		nameButton.draw(g);
		statBox.draw(g);
		finishButton.draw(g);
	}

	@Override
	public void finish() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		
		IntroSequence.input.removeInputListener(nameButton, modesList);
		IntroSequence.input.removeInputListener(finishButton, modesList);
	}
	
	public PartyMember getCreatedMember() {
		PartyMember newMember = new PartyMember(memberName, memberClass, 0, "", memberImage, stats);
		return newMember;
	}

}