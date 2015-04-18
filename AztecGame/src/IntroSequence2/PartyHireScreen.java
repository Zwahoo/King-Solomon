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
	
	Textbox classBox;
	int classBoxX = 50;
	int classBoxY = statBoxY + statBoxHeight + 10;
	int classBoxWidth = statBoxWidth;
	int classBoxHeight = 50;
	
	Button finishButton;
	int finishWidth = 200;
	int finishHeight = 40;
	int finishX = gameframe.windowWidth - finishWidth - 40;
	int finishY = gameframe.windowHeight - finishHeight - 40;
	
	Button fireButton;
	int fireWidth = 200;
	int fireHeight = 40;
	int fireX = 50;
	int fireY = gameframe.windowHeight - finishHeight - 40;
	
	ArrayList<Button> classButtons;
	int classBtnWidth = 200;
	int classBtnHeight = 40;
	int classBtnX = gameframe.windowWidth - classBtnWidth - 40;
	int classBtnStartY = statBoxY;
	int classBtnYSpacer = 25;
	
	HashMap<String, Integer> stats = PartyMemberStats.HAPPY_HUNTER_STATS;
	String memberClass = PartyMemberStats.hunterStr;
	String memberImage = "assets/Portraits/ExplorerImage.png";
	
	public PartyHireScreen(PartyMember memberToLoad) {
		Color nameTextColor = Color.red;
		
		if(memberToLoad != null) {
			memberName = memberToLoad.getName();
			memberClass = memberToLoad.getType();
			stats = memberToLoad.getStats();
			nameTextColor = Color.black;
		}
		
		initClassButtons();
		
		statBox = new Textbox(getStatBoxText(), statBoxX, statBoxY, statBoxWidth, statBoxHeight, IntroSequence.input);
		classBox = new Textbox("Class: " + memberClass, classBoxX, classBoxY, classBoxWidth, classBoxHeight, IntroSequence.input);
		
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
		nameButton.setFontColor(nameTextColor);
		
		finishButton = new Button(finishX, finishY, finishWidth, finishHeight, "Finish", IntroSequence.input){
			@Override
			public void onClick() {
				ResourcesAndPartyHolder.switchToResources(getCreatedMember());
			}
		};
		
		fireButton = new Button(fireX, fireY, fireWidth, fireHeight, "Fire", IntroSequence.input){
			@Override
			public void onClick() {
				ResourcesAndPartyHolder.switchToResources(null);
			}
		};
	}
	
	public void initClassButtons() {
		classButtons = new ArrayList<Button>();
		
		int yPos = classBtnStartY;
		
		Button curBtn = new Button(classBtnX, yPos, classBtnWidth, classBtnHeight, PartyMemberStats.hunterStr, IntroSequence.input) {
			@Override
			public void onClick() {
				setClass(PartyMemberStats.hunterStr);
			}
		};
		yPos += curBtn.getHeight() + classBtnYSpacer;
		classButtons.add(curBtn);
		
		
		curBtn = new Button(classBtnX, yPos, classBtnWidth, classBtnHeight, PartyMemberStats.expStr, IntroSequence.input) {
			@Override
			public void onClick() {
				setClass(PartyMemberStats.expStr);
			}
		};
		yPos += curBtn.getHeight() + classBtnYSpacer;
		classButtons.add(curBtn);
		
		
		curBtn = new Button(classBtnX, yPos, classBtnWidth, classBtnHeight, PartyMemberStats.missStr, IntroSequence.input) {
			@Override
			public void onClick() {
				setClass(PartyMemberStats.missStr);
			}
		};
		yPos += curBtn.getHeight() + classBtnYSpacer;
		classButtons.add(curBtn);
		
		
		curBtn = new Button(classBtnX, yPos, classBtnWidth, classBtnHeight, PartyMemberStats.guideStr, IntroSequence.input) {
			@Override
			public void onClick() {
				setClass(PartyMemberStats.guideStr);
			}
		};
		yPos += curBtn.getHeight() + classBtnYSpacer;
		classButtons.add(curBtn);
		
		
		curBtn = new Button(classBtnX, yPos, classBtnWidth, classBtnHeight, PartyMemberStats.natStr, IntroSequence.input) {
			@Override
			public void onClick() {
				setClass(PartyMemberStats.natStr);
			}
		};
		yPos += curBtn.getHeight() + classBtnYSpacer;
		classButtons.add(curBtn);
		
		
		curBtn = new Button(classBtnX, yPos, classBtnWidth, classBtnHeight, PartyMemberStats.mercStr, IntroSequence.input) {
			@Override
			public void onClick() {
				setClass(PartyMemberStats.mercStr);
			}
		};
		yPos += curBtn.getHeight() + classBtnYSpacer;
		classButtons.add(curBtn);
	}
	
	public void setClass(String cls) {
		stats = PartyMemberStats.classStats.get(cls);
		memberClass = cls;		
		statBox.setText(getStatBoxText());
		classBox.setText("Class: " + memberClass);
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
		fireButton.update();
		classBox.update();
		for(Button classBtn : classButtons) {
			classBtn.update();
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		nameButton.draw(g);
		statBox.draw(g);
		finishButton.draw(g);
		fireButton.draw(g);
		classBox.draw(g);
		for(Button classBtn : classButtons) {
			classBtn.draw(g);
		}
	}

	@Override
	public void finish() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		
		IntroSequence.input.removeInputListener(nameButton, modesList);
		IntroSequence.input.removeInputListener(finishButton, modesList);
		IntroSequence.input.removeInputListener(fireButton, modesList);
		for(Button classBtn : classButtons) {
			IntroSequence.input.removeInputListener(classBtn, modesList);
		}
		
	}
	
	public PartyMember getCreatedMember() {
		PartyMember newMember = new PartyMember(memberName, memberClass, 0, "", memberImage, stats);
		return newMember;
	}

}