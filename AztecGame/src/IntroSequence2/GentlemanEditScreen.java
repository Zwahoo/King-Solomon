package IntroSequence2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Components.*;
import MainGame.DrawScreen;
import MainGame.IntroSequence;
import MainGame.PartyMember;
import MainGame.PartyMemberStats;
import MainGame.gameframe;

public class GentlemanEditScreen implements DrawScreen {

	Button nameButton;
	Button continueButton;
	Textbox statsText;
	ArrayList<GentlemanStatView> statViewList;
	ArrayList<ImageButton> primaryButtons;
	ArrayList<ImageButton> secondaryButtons;
	
	
	boolean finished;
	
	int statViewX = 50;
	int statViewY = 100;
	
	public String gentlemanName;
	

	int continueWidth = 200;
	int continueHeight = 40;
	int continueX = gameframe.windowWidth - continueWidth - 40;
	int continueY = gameframe.windowHeight - continueHeight - 40;
	
	
	public GentlemanEditScreen() {
		gentlemanName = "[Choose Name]";
		finished = false;
		initStatView();
		initPrimaryBtns();
		initSecondaryBtns();
		
		continueButton = new Button(continueX, continueY, continueWidth, continueHeight, "Continue", IntroSequence.input){
			public void onClick() {
				finished = true;
			}
		};
		
		nameButton = new Button(statViewX, 50, 200, 40, gentlemanName, IntroSequence.input) {
			public void onClick() {
				gentlemanName = JOptionPane.showInputDialog("Please Enter a Name:", gentlemanName);
				while(gentlemanName.length() > 26) {
					gentlemanName = JOptionPane.showInputDialog("Sorry! That name is too long.\nPlease Enter Another Name:");
				}
				nameButton.setText(gentlemanName);
				nameButton.setFontColor(Color.black);
			}
		};
		nameButton.setFontColor(Color.red);
	}
	
	@Override
	public boolean update() {
		for(GentlemanStatView statView : statViewList) {
			statView.update();
		}
		for(ImageButton btn : primaryButtons) {
			btn.update();
		}
		for(ImageButton btn : secondaryButtons) {
			btn.update();
		}
		continueButton.update();
		nameButton.update();
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		for(GentlemanStatView statView : statViewList) {
			statView.draw(g);
		}
		for(ImageButton btn : primaryButtons) {
			btn.draw(g);
		}
		for(ImageButton btn : secondaryButtons) {
			btn.draw(g);
		}
		continueButton.draw(g);
		nameButton.draw(g);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		
		IntroSequence.input.removeInputListener(continueButton, modesList);
		for(ImageButton btn : primaryButtons) {
			IntroSequence.input.removeInputListener(btn, modesList);
		}
		for(ImageButton btn : secondaryButtons) {
			IntroSequence.input.removeInputListener(btn, modesList);
		}
	}
	
	
	private void initStatView() {
		statViewList = new ArrayList<GentlemanStatView>();
		HashMap<String, Integer> defaultStats = PartyMemberStats.AVERAGE_ABE_STATS;

		int yPos = statViewY;

		GentlemanStatView label = new GentlemanStatView("Stat", 0, 0, 0, statViewX, yPos, 35, IntroSequence.input);
		label.defaultText.setText("Base");
		label.primaryText.setText("Primary");
		label.secondaryText.setText("Hobby");
		label.totalText.setText("Total");
		statViewList.add(label);
		yPos += label.height + 10;		
		for(String stat : defaultStats.keySet()) {
			if(stat.equals(PartyMember.LOYALTY_KEY)) continue;
			
			GentlemanStatView statView = new GentlemanStatView(stat, defaultStats.get(stat), statViewX, yPos, IntroSequence.input);
			yPos += statView.height + 10;
			statViewList.add(statView);
		}
	}
	
	private void initPrimaryBtns() {
		primaryButtons = new ArrayList<ImageButton>();
	}
	
	private void initSecondaryBtns() {
		secondaryButtons = new ArrayList<ImageButton>();
	}
	
}
