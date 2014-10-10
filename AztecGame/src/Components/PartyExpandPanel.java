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
	static final int defaultWidth = 550;
	static final int defaultHeight = 480;

	Color backColor = new Color(210, 150, 50);
	Color borderColor = new Color(80, 50, 50);
	private int borderSize = 5;
	
	
	Color memberInfoPanelTextCol = new Color(0, 0, 0);
	private Font memberNameFont; //The font to use for the name of a party member.
	private Font memberGeneralFont; //The font to use for the main text for a party member.
	int memberInfoPanelWidth = 140;
	int memberInfoPanelHeight = 170;
	int memberInfoPanelHorizSep = 30;
	int memberInfoPanelVertSep = 40;
	int lineHeight;
	
	public PartyExpandPanel(int x, int y) {
		myRect = new Rectangle(x, y, defaultWidth, defaultHeight);
		memberNameFont = new Font("Georgia", Font.BOLD, 14);
		memberGeneralFont = new Font("Georgia", Font.PLAIN, 12);
		lineHeight = getLineHeight();
	}
	
	public void update() {
		
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
			
			//Draw Name
			g.setColor(memberInfoPanelTextCol);
			g.setFont(memberNameFont);
			g.drawString(member.getName(), xLoc, yLoc);
			
			//Draw Stats
			//Graphics doesn't handle new lines, so we have to do that manually
			g.setFont(memberGeneralFont);
			String toWrite = member.generateStatsString("\n");
			String[] lines = toWrite.split("\n");
			int tmpY = lineHeight; //Skip one line because we already wrote the name
			for(String line : lines) {
				g.drawString(line, xLoc, yLoc + tmpY);
				tmpY += lineHeight;
			}
			
			xLoc += memberInfoPanelWidth + memberInfoPanelHorizSep;
			if( (xLoc + memberInfoPanelWidth) > myRect.x + myRect.width - borderSize * 2) {
				xLoc = myRect.x + borderSize + memberInfoPanelHorizSep;
				yLoc += memberInfoPanelHeight + memberInfoPanelVertSep;
			}
		}
		
	}
	
	private int getLineHeight() {
		Component c = new JButton();
		int fontOne = c.getFontMetrics(memberGeneralFont).getHeight() + 3;
		int fontTwo = c.getFontMetrics(memberNameFont).getHeight() + 3;
		return Math.max(fontOne, fontTwo);
	}
	
}