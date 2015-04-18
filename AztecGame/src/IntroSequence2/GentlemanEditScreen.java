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
import MainGame.ProfessionHobbyStats;
import MainGame.gameframe;

public class GentlemanEditScreen implements DrawScreen {

	Button nameButton;
	Button continueButton;
	Textbox statsText;
	ArrayList<GentlemanStatView> statViewList;
	ArrayList<ImageButton> primaryButtons;
	ArrayList<ImageButton> secondaryButtons;
	
	Textbox profText;
	Textbox hobbyText; 
	
	Color normTBColor = new Color(210, 210, 215);
	
	boolean finished;
	
	public String gentlemanName;
	
	int primaryImgBtnX = gameframe.windowWidth - 230;
	int imgButtonY = 120;
	int imgButtonWidth = 50;
	int imgButtonHeight = 50;
	int secondaryX = gameframe.windowWidth - imgButtonWidth - 50;
	int imgBtnYSpacer = 21;
	
	int continueWidth = 200;
	int continueHeight = 40;
	int continueX = gameframe.windowWidth - continueWidth - 40;
	int continueY = gameframe.windowHeight - continueHeight - 40;

	int statViewX = 50;
	int statViewY = 110;
	int statYSpacer = 20;
	
	int profHobbyTextHeight = 40;
	int profHobbyTextWidth;
	//int hobbyTextY = gameframe.windowHeight - profHobbyTextHeight - 40;
	//int profTextY = hobbyTextY - profHobbyTextHeight - 10;
	int profTextY = 580;
	int hobbyTextY = profTextY + profHobbyTextHeight + 10;
		
	String professionKey = "None";
	String hobbyKey = "None";
	
	public GentlemanEditScreen() {
		gentlemanName = "[Choose Name]";
		finished = false;
		initStatView();
		initPrimaryBtns();
		initSecondaryBtns();
		
		profHobbyTextWidth = statViewList.get(0).getTotalWidth();
		
		profText = new Textbox("Profession: " + professionKey, statViewX, profTextY, profHobbyTextWidth, profHobbyTextHeight, IntroSequence.input);
		hobbyText = new Textbox("Hobby: " + hobbyKey, statViewX, hobbyTextY, profHobbyTextWidth, profHobbyTextHeight, IntroSequence.input);
		
		
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
		profText.update();
		hobbyText.update();
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
		profText.draw(g);
		hobbyText.draw(g);
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
		label.isNumeric = false;
		
		label.defaultText.setText("Base");
		label.primaryText.setText("Primary");
		label.secondaryText.setText("Hobby");
		label.totalText.setText("Total");
		label.nameText.makeLabel();
		label.defaultText.makeLabel();
		label.primaryText.makeLabel();
		label.secondaryText.makeLabel();
		label.totalText.makeLabel();
		
		
		statViewList.add(label);
		yPos += label.height + statYSpacer - 20;		
		for(String stat : defaultStats.keySet()) {
			if(stat.equals(PartyMember.LOYALTY_KEY)) continue;
			
			GentlemanStatView statView = new GentlemanStatView(stat, defaultStats.get(stat), statViewX, yPos, IntroSequence.input);
			yPos += statView.height + statYSpacer;
			statViewList.add(statView);
		}
	}
	
	private void initPrimaryBtns() {
		int yPos = imgButtonY;
		
		primaryButtons = new ArrayList<ImageButton>();
		
		//Loop unraveling... that's a thing
		
		ImageButton gunBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.gunsmithKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.gunsmithKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(gunBtn);

		ImageButton fightBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.fighterKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.fighterKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(fightBtn);

		ImageButton ambBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.ambassadorKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.ambassadorKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(ambBtn);

		ImageButton vetBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.veteranKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.veteranKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(vetBtn);

		ImageButton docBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.doctorKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.doctorKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(docBtn);

		ImageButton officerBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.officerKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.officerKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(officerBtn);

		ImageButton sabBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.saboteurKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.saboteurKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(sabBtn);
		
		ImageButton noneBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.noneKey),
				primaryImgBtnX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadProfession(ProfessionHobbyStats.noneKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		primaryButtons.add(noneBtn);

	}
	
	private void initSecondaryBtns() {
		int yPos = imgButtonY;
		
		secondaryButtons = new ArrayList<ImageButton>();
		
		ImageButton histBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.historianKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.historianKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(histBtn);
		
		ImageButton soialiteBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.socialiteKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.socialiteKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(soialiteBtn);
		
		ImageButton sportsBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.sportsKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.sportsKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(sportsBtn);
		
		ImageButton birdBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.birdWatchingKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.birdWatchingKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(birdBtn);
		
		ImageButton shootBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.shootingKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.shootingKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(shootBtn);
		
		ImageButton writeBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.writingKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.writingKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(writeBtn);
		
		ImageButton wrestleBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.wrestlerKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.wrestlerKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(wrestleBtn);
		

		ImageButton noneBtn = new ImageButton(ProfessionHobbyStats.IMAGE_FILE_PATHS.get(ProfessionHobbyStats.noneKey),
				secondaryX, yPos, imgButtonWidth, imgButtonHeight, IntroSequence.input) {
			public void onClick() {
				loadHobby(ProfessionHobbyStats.noneKey);
			}
		};
		yPos += imgButtonHeight + this.imgBtnYSpacer;
		secondaryButtons.add(noneBtn);

	}
	
	public void loadProfession(String prof) {
		this.professionKey = prof;
		this.profText.setText("Profession: " + this.professionKey);
		ArrayList<String> stats = ProfessionHobbyStats.PROFESSION_STATS.get(prof);
		
		for(GentlemanStatView view : statViewList) {
			if(!view.isNumeric) continue;
			if(stats.contains(view.statName)) {
				view.setPrimary(10);
				view.primaryText.setBackColor(new Color(39, 164, 123));
			} else {
				view.setPrimary(0);
				view.primaryText.setBackColor(normTBColor);
			}
		}
	}
	
	public void loadHobby(String hob) {
		this.hobbyKey = hob;
		this.hobbyText.setText("Hobby: " + this.hobbyKey);
		ArrayList<String> stats = ProfessionHobbyStats.HOBBY_STATS.get(hob);
		
		for(GentlemanStatView view : statViewList) {
			if(!view.isNumeric) continue;
			if(stats.contains(view.statName)) {
				view.setSecondary(5);
				view.secondaryText.setBackColor(new Color(39, 164, 123));
			} else {
				view.setSecondary(0);
				view.secondaryText.setBackColor(normTBColor);
			}
		}
	}

	public HashMap<String, Integer> getGentStats() {
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
		for(GentlemanStatView view : statViewList) {
			ret.put(view.statName, view.total);
		}
		return ret;
	}
	
}
