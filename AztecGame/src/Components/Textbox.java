package Components;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import MainGame.InputManager;

public class Textbox {
	
	Button upBtn;
	Button downBtn;
	InputManager input;
	int btnWidth = 20;
	int btnHeight = 20;
	int btnBuffer = 1;
	
	Rectangle drawRect;
	Rectangle textSpaceRect;
	int textBuffer = 3;
	int fullHeight;
	
	String textOrig;
	ArrayList<String> lines;
	
	int curScrollPos = 0;
	int borderSize = 3;
	int numLinesToShow;
	int lineHeight;
	boolean hasScroll = false;
	
	Color backCol = new Color(210, 150, 50);
	Color borderCol = new Color(80, 50, 50);
	Color textCol = new Color(0, 0, 0);
	private Font myFont;
	
	public Textbox(String str, int x, int y, int width, int height, InputManager input) {
		this(str, new Point(x, y), width, height, input);
	}
	public Textbox(String str, Point pos, int width, int height, InputManager input) {
		this.input = input;
		textOrig = str;
		myFont = new Font("Georgia", Font.PLAIN, 14);
		sizeRectangles(pos.x, pos.y, width, height);
		lines = fitStr(str);
		calculateFullHeight();
		checkNeedScroll();
	}
	
	private void sizeRectangles(int drawX, int drawY, int drawWidth, int drawHeight) {
		drawRect = new Rectangle(drawX, drawY, drawWidth, drawHeight);
		textSpaceRect = new Rectangle(drawX + borderSize, drawY + borderSize, drawWidth - borderSize*2, drawHeight - borderSize*2);
	}
	
	private ArrayList<String> fitStr(String orig) {
		ArrayList<String> ret = new ArrayList<String>();
		//Split by new line markers first.
		String[] newLineSep = orig.split("\n");
		
		//Now check each line and ensure it fits within the textbox.
		//If not, split it into new lines.
		for(String bigStr : newLineSep) {
			String[] words = bigStr.split(" ");
			String curLine = "";
			curLine += (words[0]);
			
			for(int i=1; i<words.length; i++) {
				String str = words[i];
				String newStr = curLine + (" " + str);
				if(getStringWidth(newStr) < (textSpaceRect.width - 2*textBuffer)) {
					curLine = newStr;
				} else {
					ret.add(curLine);
					curLine = str;
				}
			}
			ret.add(curLine);
		}
		
		return ret;
	}
	
	private int getStringWidth(String str) {
		Component c = new JTextField();
		int width = c.getFontMetrics(myFont).stringWidth(str);
		return width;
	}
	
	private void calculateFullHeight() {
		Component c = new JTextField();
		lineHeight = c.getFontMetrics(myFont).getHeight() + 3;
		fullHeight = lines.size() * lineHeight;
		numLinesToShow = (int)Math.floor((textSpaceRect.height - textBuffer*2)/lineHeight);
	}

	
	private void checkNeedScroll() {
		if(lines.size() > numLinesToShow) {
			hasScroll = true;
			textSpaceRect.width -= (btnWidth + btnBuffer*2); //Move the text region to make space for the buttons.
			lines = fitStr(textOrig); //Refit the string.
			int xLoc = drawRect.x + drawRect.width - btnWidth - btnBuffer;
			upBtn = new Button(xLoc, drawRect.y + btnBuffer, btnWidth, btnHeight, "^", input) {
				@Override
				public void onClick() {
					if(curScrollPos > 0) {
						curScrollPos--;
					}
				}
			};
			downBtn = new Button(xLoc, drawRect.y + drawRect.height - btnHeight - btnBuffer, btnWidth, btnHeight, "v", input) {
				@Override
				public void onClick() {
					if(curScrollPos < (lines.size() - numLinesToShow)) {
						curScrollPos++;
					}
				}
			};
		}
	}
	
	public void update() {
		if(hasScroll) {
			upBtn.update();
			downBtn.update();
		}
	}
	
	public void draw(Graphics g) {
		//Draw border
		g.setColor(borderCol);
		g.fillRect(drawRect.x, drawRect.y, drawRect.width, drawRect.height);
		
		//Draw backdrop
		g.setColor(backCol);
		g.fillRect(textSpaceRect.x, textSpaceRect.y, textSpaceRect.width, textSpaceRect.height);
		
		//Draw Text
		g.setFont(myFont);
		g.setColor(textCol);
		int verticalOffset = 15;
		int dispLine = 0;
		for(int i=curScrollPos; i<lines.size() && dispLine <= numLinesToShow; i++) {
			g.drawString(lines.get(i), textSpaceRect.x + textBuffer, textSpaceRect.y + textBuffer + verticalOffset + (dispLine)*lineHeight);
			dispLine++;
		}
		
		if(hasScroll) {
			upBtn.draw(g);
			downBtn.draw(g);
		}
	}
	
}
