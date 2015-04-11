package IntroSequence2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Components.*;
import MainGame.DrawScreen;
import MainGame.IntroSequence;
import MainGame.PartyMember;
import MainGame.PartyMemberStats;

public class GentlemanEditScreen implements DrawScreen {

	Button nameButton;
	Button continueButton;
	Textbox statsText;
	ArrayList<GentlemanStatView> statViewList;
	ArrayList<ImageButton> primaryButtons;
	ArrayList<ImageButton> secondaryButtons;
	
	
	boolean finished;
	
	int statViewX = 50;
	int statViewY = 50;
	
	public String gentlemanName;
	
	public GentlemanEditScreen() {
		gentlemanName = "Choose Name";
		finished = false;
		initStatView();
		initPrimaryBtns();
		initSecondaryBtns();
		
		continueButton = new Button(500, 800, 100, 50, "Continue", IntroSequence.input);
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
		for(String stat : defaultStats.keySet()) {
			if(stat.equals(PartyMember.LOYALTY_KEY)) continue;
			
			GentlemanStatView statView = new GentlemanStatView(stat, defaultStats.get(stat), statViewX, yPos, IntroSequence.input);
			yPos += statView.height;
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
