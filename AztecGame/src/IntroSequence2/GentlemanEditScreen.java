package IntroSequence2;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import Components.*;
import MainGame.DrawScreen;
import MainGame.InputManager;
import MainGame.IntroSequence;
import MainGame.PartyMember;
import MainGame.PartyMemberStats;

public class GentlemanEditScreen implements DrawScreen {

	Button nameButton;
	Button continueButton;
	Textbox statsText;
	ArrayList<GentlemanStatView> statViewList;
	
	boolean finished;
	
	int statViewX;
	int statViewY;
	
	public String gentlemanName;
	
	public GentlemanEditScreen() {
		gentlemanName = "Choose Name";
		finished = false;
		initStatView();
	}
	
	@Override
	public boolean update() {
		for(GentlemanStatView statView : statViewList) {
			statView.update();
		}
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		for(GentlemanStatView statView : statViewList) {
			statView.draw(g);
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}
	
	
	int yPos = statViewY;
	private void initStatView() {
		statViewList = new ArrayList<GentlemanStatView>();
		HashMap<String, Integer> defaultStats = PartyMemberStats.AVERAGE_ABE_STATS;
		
		for(String stat : defaultStats.keySet()) {
			if(stat.equals(PartyMember.LOYALTY_KEY)) continue;
			
			GentlemanStatView statView = new GentlemanStatView(stat, defaultStats.get(stat), statViewX, yPos, IntroSequence.input);
			yPos += statView.height;
			statViewList.add(statView);
		}
	}
	
}
