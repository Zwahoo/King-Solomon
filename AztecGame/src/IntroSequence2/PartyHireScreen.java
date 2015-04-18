package IntroSequence2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Components.*;
import MainGame.DrawScreen;
import MainGame.IntroSequence;
import MainGame.PartyMember;

public class PartyHireScreen implements DrawScreen {
	
	Button nameButton;
	String memberName = "[Choose Name]";
	int nameBtnX = 50;
	int nameBtnY = 50;
	int nameBtnWidth = 200;
	int nameBtnHeight = 40;
	
	Textbox statBox;
	int statBoxX = 50;
	int statBoxY = nameBtnY + nameBtnHeight + 50;
	int statBoxWidth = 400;
	int statBoxHeight = 600;
	
	HashMap<String, Integer> stats = new HashMap<String, Integer>();
	
	String memberClass = "";
	
	String memberImage = "assets/Portraits/ExplorerImage.png";
		
	public PartyHireScreen(PartyMember memberToLoad) {
		if(memberToLoad != null) {
			memberName = memberToLoad.getName();
			stats = memberToLoad.getStats();
		}
		
		nameButton = new Button(nameBtnX, nameBtnY, nameBtnWidth, nameBtnHeight, memberName, IntroSequence.input) {
			public void onClick() {
				memberName = JOptionPane.showInputDialog("Please Enter a Name:", memberName);
				while(memberName.length() > 26) {
					memberName = JOptionPane.showInputDialog("Sorry! That name is too long.\nPlease Enter Another Name:");
				}
				nameButton.setText(memberName);
				nameButton.setFontColor(Color.black);
			}
		};
		nameButton.setFontColor(Color.red);
	}
	
	
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
	
	public PartyMember getCreatedMember() {
		PartyMember newMember = new PartyMember(memberName, memberClass, 0, "", memberImage, stats);
		return newMember;
	}

}
