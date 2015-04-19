package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;

import MainGame.*;

public class PartyExpandPanel {
	
	Rectangle myRect;
	int rightX, lowerY;
	public int numCols = 0;
	public int numRows = 0;
	private int numPartyMembers;
	
	Color backColor = new Color(210, 210, 215);
	Color borderColor = new Color(0, 0, 0);
	private int borderSize = 5;
	Color memberInfoPanelTextCol = new Color(0, 0, 0);
	
	private Font memberNameFont; //The font to use for the name of a party member.
	private Font memberGeneralFont; //The font to use for the main text for a party member.
	int memberInfoPanelWidth = 140;
	int memberInfoPanelHeight = 170;
	int memberInfoPanelHorizSep = 30;
	int memberInfoPanelVertSep = 40;
	int lineHeight;
	
	public PartyExpandPanel(int rightX, int lowerY) {
		
		if(MainGame.DISCO_MODE) {
			int backR = 50 + (int)(Math.random()*205);
			int backG = 50 + (int)(Math.random()*205);
			int backB = 50 + (int)(Math.random()*205);
		
			backColor = new Color(backR, backG, backB); //The background color of the text box.
		}
		
		this.rightX = rightX;
		this.lowerY = lowerY;
		setRowsAndCols();
		memberNameFont = new Font("Georgia", Font.BOLD, 14);
		memberGeneralFont = new Font("Georgia", Font.PLAIN, 12);
		lineHeight = getLineHeight();
	}
	
	public void setRowsAndCols() {
		numPartyMembers = MainGame.party.size();
		
		numRows = (int) Math.floor(Math.sqrt(numPartyMembers));
		numCols = (int) Math.ceil((double)numPartyMembers/(double)numRows);
		
		myRect = new Rectangle(rightX - calcWidth(), lowerY - calcHeight(), calcWidth(), calcHeight());	
	}
	
	private int calcWidth() {
		return ((memberInfoPanelWidth * numCols) + (memberInfoPanelHorizSep * (numCols + 1)) + (2*borderSize));
	}
	private int calcHeight() {
		return ((memberInfoPanelHeight * numRows) + (memberInfoPanelVertSep * (numRows + 1)) + (2*borderSize));
	}
	
	public void update() {
		if(MainGame.party.size() != numPartyMembers) {
			setRowsAndCols();
		}
	}
	
	public void draw(Graphics g) {
		//Draw Backdrop
		g.setColor(borderColor);
		g.fillRect(myRect.x, myRect.y, myRect.width, myRect.height);
		g.setColor(backColor);
		g.fillRect(myRect.x + borderSize, myRect.y + borderSize, myRect.width - borderSize*2, myRect.height - borderSize*2);
		
		int xLoc = myRect.x + borderSize + memberInfoPanelHorizSep;
		int yLoc = myRect.y + borderSize + memberInfoPanelVertSep;
		
		
		for(PartyMember member : MainGame.party) {
			
			drawPartyMemberInfo(g, member, xLoc, yLoc);
			
			xLoc += memberInfoPanelWidth + memberInfoPanelHorizSep;
			if( (xLoc + memberInfoPanelWidth) > myRect.x + myRect.width - borderSize * 2) {
				xLoc = myRect.x + borderSize + memberInfoPanelHorizSep;
				yLoc += memberInfoPanelHeight + memberInfoPanelVertSep;
			}
		}
		
	}
	
	private void drawPartyMemberInfo(Graphics g, PartyMember member, int xLoc, int yLoc) {

		//Draw Name
		g.setColor(memberInfoPanelTextCol);
		g.setFont(memberNameFont);
		g.drawString(member.getName(), xLoc, yLoc);
		
		g.setFont(memberGeneralFont);
		String toWrite = member.generateStatsString("\n");
		String[] lines = toWrite.split("\n");
		int tmpY = lineHeight; //Skip one line because we already wrote the name
		for(String line : lines) {
			g.drawString(line, xLoc, yLoc + tmpY);
			tmpY += lineHeight;
		}
	}
	
	private int getLineHeight() {
		Component c = new JButton();
		int fontOne = c.getFontMetrics(memberGeneralFont).getHeight() + 3;
		int fontTwo = c.getFontMetrics(memberNameFont).getHeight() + 3;
		return Math.max(fontOne, fontTwo);
	}
	
}