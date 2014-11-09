package Components;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import MainGame.InputManager;

public class Textbox {
	
	Button upBtn; //The up button, for use if we need a scroll.
	Button downBtn; //The down button, for use if we need a scroll.
	InputManager input; //Handles input (mainly for buttons)
	int btnWidth = 20; //Width of scroll buttons.
	int btnHeight = 20; //Height of scroll buttons.
	int btnBuffer = 1; //Extra space to give buttons on all four sides.
	
	Rectangle drawRect; //Rectangle encompassing the text box as it is drawn.
	Rectangle textSpaceRect; //The rectangle defining the space in which the text should be drawn in the text box.
	int textBuffer = 3; //Padding to give on either side of the text as it's drawn in textSpaceRect
	int fullHeight; //The total height needed to display all text. If larger than drawRect's height, need scroll bar.
	
	String textOrig; //The text in its original form.
	ArrayList<String> lines; //The text, split into lines that will fit in the text box.
	
	int curScrollPos = 0; //Current scroll position
	int borderSize = 3; //Size of the text box's border
	int numLinesToShow; //The number of lines that will fit in the text box.
	int lineHeight; //The height of each line of text in the text box.
	boolean hasScroll = false; //Does the text box need a scroll bar?
	
	Color backCol = new Color(210, 150, 50); //The background color of the text box.
	Color borderCol = new Color(80, 50, 50); //The border color of the text box.
	Color textCol = new Color(0, 0, 0); //The color of the text in the text box.
	private Font myFont; //The font to use for the text in the text box.
	
	boolean visible = false;
	
	//Constructors
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
	
	public void setText(String newText) {
		textOrig = newText;
		lines = fitStr(newText);
		calculateFullHeight();
		checkNeedScroll();
	}
	
	//Sets the size of the draw rectangle from the given values.
	//Then calculates the size of the text rectangle.
	private void sizeRectangles(int drawX, int drawY, int drawWidth, int drawHeight) {
		drawRect = new Rectangle(drawX, drawY, drawWidth, drawHeight);
		textSpaceRect = new Rectangle(drawX + borderSize, drawY + borderSize, drawWidth - borderSize*2, drawHeight - borderSize*2);
	}
	
	//Splits the given string into lines that will fit in the text box.
	//Returns the lines as an array list of strings.
	protected ArrayList<String> fitStr(String orig) {
		//The list to fill with the separate lines.
		ArrayList<String> ret = new ArrayList<String>();
		
		//Split by new line markers first.
		String[] newLineSep = orig.split("\n");
		
		//Now check each line and ensure it fits within the text box.
		//If not, split it into new lines.
		for(String bigStr : newLineSep) {
			String[] words = bigStr.split(" ");
			String curLine = "";
			curLine += (words[0]);
			
			//Loop until the line's width is greater than the width of the text region of the text box.
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
			
			//Add the final line.
			ret.add(curLine);
		}
		
		return ret;
	}
	
	//Returns the pixel width of the given string.
	private int getStringWidth(String str) {
		Component c = new JTextField();
		int width = c.getFontMetrics(myFont).stringWidth(str);
		return width;
	}
	
	//Calculates and sets the line height, and full height of the text.
	//Also sets the number of lines to show at any one time in the text box.
	//TODO: This method does too many things. Split into multiple methods.
	protected void calculateFullHeight() {
		Component c = new JTextField();
		lineHeight = c.getFontMetrics(myFont).getHeight() + 3;
		fullHeight = lines.size() * lineHeight;
		numLinesToShow = (int)Math.floor((textSpaceRect.height - textBuffer*2)/lineHeight);
	}

	//Checks if the form needs a scroll bar.
	//Adds one if it does.
	protected void checkNeedScroll() {
		if(lines.size() > numLinesToShow) {
			hasScroll = true;
			
			//Move the text region to make space for the buttons.
			textSpaceRect.width -= (btnWidth + btnBuffer*2);
			//Refit the string to the smaller text field.
			lines = fitStr(textOrig);
			calculateFullHeight();
			
			//Places the buttons
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
	
	//Update the buttons, if we have them.
	public void update() {
		if(hasScroll) {
			upBtn.update();
			downBtn.update();
		}
	}
	
	//Draw all the things!
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
	
	public boolean getVisibility() {
		return visible;
	}
	
	public void setVisibility(boolean visibility) {
		visible = visibility;
	}
}
