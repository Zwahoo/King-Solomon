package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import MainGame.InputListener;
import MainGame.InputManager;
import MainGame.MainGame;

public class Button extends InputListener {

	public static final int MODE_NORMAL = 0; //Default button "mode"
	public static final int MODE_HOVER = 1; //The mouse is hovering over the button, but not pressed
	public static final int MODE_PRESSED = 2; //The mouse has been clicked on the button, but not released yet.
	int curMode = MODE_NORMAL;

	private String myText; //Text on the button.
	protected Rectangle2D myRect; //Location and size of the button
	protected int borderSize = 2; //Size of the button's border
	private boolean mouseOnButton = false; //Is the mouse currently hovering over the button?
	private Font myFont; //Font used in the button's text

	Color bkgCol; //The backdrop color for the button (switches between off, hover, and press Col)
	Color borderCol; //The border color for the button (switches between off, hover, and press Bor)

	Color normColEnabled = new Color(210, 210, 215); //Back color for normal mode
	Color hoverColEnabled; //Back color for hover mode
	Color pressColEnabled; //Back color for press mode
	Color normBorEnabled = new Color(0, 0, 0); //Border color for normal mode
	Color hoverBorEnabled = new Color(0, 0, 0); //Border color for hover mode
	Color pressBorEnabled = new Color(0, 0, 0); //Border color for press mode
	Color fontColEnabled = new Color(0, 0, 0); //Color of the text

	Color normColDisabled = new Color(20, 20, 20); //Back color for normal mode
	Color hoverColDisabled = new Color(20, 20, 20); //Back color for hover mode
	Color pressColDisabled = new Color(20, 20, 20); //Back color for press mode
	Color normBorDisabled = new Color(0, 0, 0); //Border color for normal mode
	Color hoverBorDisabled = new Color(0, 0, 0); //Border color for hover mode
	Color pressBorDisabled = new Color(0, 0, 0); //Border color for press mode

	Color normCol; //Back color for normal mode
	Color hoverCol; //Back color for hover mode
	Color pressCol; //Back color for press mode
	Color normBor; //Border color for normal mode
	Color hoverBor; //Border color for hover mode
	Color pressBor; //Border color for press mode
	Color fontCol; //Color of the text

	public boolean isImpossible = false;

	Point stringLoc = null;

	String[] lines; //Have to use a list to separate new lines (drawString() doesn't handle them).

	//Constructors
	public Button(int x, int y, int width, int height, String str, InputManager inputManager) {
		this(new Point(x, y), new Point(width, height), str, inputManager);
	}
	public Button(Point loc, Point size, String str, InputManager inputManager) {
		this(new Rectangle(loc.x, loc.y, size.x, size.y), str, inputManager);
	}
	public Button(Rectangle rect, String str, InputManager inputManager) {

		if(MainGame.DISCO_MODE) {
			int backR = 50 + (int)(Math.random()*205);
			int backG = 50 + (int)(Math.random()*205);
			int backB = 50 + (int)(Math.random()*205);
			normColEnabled = new Color(backR, backG, backB); //Back color for normal mode
		}

		hoverColEnabled = new Color(normColEnabled.getRed() - 20, normColEnabled.getGreen() - 20, normColEnabled.getBlue() - 20); //Back color for hover mode
		pressColEnabled = new Color(normColEnabled.getRed() - 50, normColEnabled.getGreen() - 50, normColEnabled.getBlue() - 50); //Back color for press mode

		setEnabled(true);
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.START_DAY_MODE); temp.add(MainGame.MOVEMENT_MODE); temp.add(MainGame.EVENT_MODE); temp.add(-1);
		setInputManager(inputManager, temp); //Required for handling input

		myRect = new Rectangle(rect);
		setText(str);
		myFont = new Font("Arial", Font.PLAIN, 14);

		setMode(MODE_NORMAL);
	}

	public void setLines() {
		lines = myText.split("\n");
	}

	//Should be override when the button is created.
	public void onClick() {}

	//Draw the button
	public void draw(Graphics g) {
		//Draw the border
		g.setColor(borderCol);
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		//Draw the Backdrop
		g.setColor(bkgCol);
		g.fillRect(getX() + borderSize, getY() + borderSize, getWidth() - (2*borderSize), getHeight() - (2*borderSize));

		//Draw the text
		g.setFont(myFont);
		g.setColor(fontCol);

		int lineHeight = g.getFontMetrics().getHeight();
		int yOffset = (-1)*(lines.length/2)*(lineHeight);
		for(String str : lines){
			//Calculte the string location
			stringLoc = getStringDrawLoc(g, myFont, str);
			g.drawString(str, stringLoc.x, stringLoc.y + yOffset);
			yOffset += lineHeight + 2;
		}
	}

	//Sets the mode if mouse exits/exits the button.
	@Override
	public void update() {
		boolean mouseOn = pointIntersectsButton(getMouseLoc());
		if((mouseOn != mouseOnButton) && !isImpossible) {
			if(mouseOn) {
				setMode(MODE_HOVER);	
			} else {
				setMode(MODE_NORMAL);
			}
		}
	}

	//Returns the location to draw the given string.
	//The location will be centered in the button.
	private Point getStringDrawLoc(Graphics g, Font f, String str) {
		Rectangle2D stringBounds = f.getStringBounds(str, g.getFontMetrics().getFontRenderContext());

		int newX = (int) Math.round(myRect.getCenterX() - (stringBounds.getWidth()/2));
		int newY = (int) Math.round(myRect.getCenterY() + (stringBounds.getHeight()/4));

		return new Point(newX, newY);	
	}

	//Set the mouseOnButton variable.
	public void setMouseOnButton(boolean on) {
		if(on) {
			mouseOnButton = true;
		} else {
			mouseOnButton = false;
		}
	}

	//Changes the button mode, and modifies the colors.
	public void setMode(int mode) {
		if(mode == MODE_NORMAL) {
			setMouseOnButton(false);
			bkgCol = normCol;
			borderCol = normBor;
			curMode = MODE_NORMAL;
		}
		else if (mode == MODE_HOVER) {
			setMouseOnButton(true);
			bkgCol = hoverCol;
			borderCol = hoverBor;
			curMode = MODE_HOVER;

		}
		else if (mode == MODE_PRESSED) {
			setMouseOnButton(true);
			bkgCol = pressCol;
			borderCol = pressBor;
			curMode = MODE_PRESSED;
		} 
		else {
			System.out.println("Unknown Mode Request in Button.");
		}
	}

	public void setColor(Color bkgCol, Color borderCol){
		this.bkgCol = bkgCol;
		this.borderCol = borderCol;
	}

	public void setImpossible(boolean isImpossible){
		this.isImpossible = isImpossible;
	}

	@Override
	//When the mouse is pressed (but not yet released)
	public void mousePressed(int mouseButton, Point mouseLoc) {
		if(mouseOnButton && !isImpossible) {
			setMode(MODE_PRESSED);
		}
	}

	@Override
	//When the mouse as been released over the button, call the onClick method.
	public void mouseReleased(int mouseButton, Point mouseLoc) {
		if(mouseOnButton && !isImpossible) {
			onClick();
			setMode(MODE_HOVER);
		}
	}


	//Returns true if the given point intersects a button.
	public boolean pointIntersectsButton(Point pnt) {
		return myRect.contains(pnt);
	}

	//Getters
	public int getX() {
		return (int)Math.round(myRect.getX());
	}
	public int getY() {
		return (int)Math.round(myRect.getY());
	}
	public int getWidth() {
		return (int)Math.round(myRect.getWidth());
	}
	public int getHeight() {
		return (int)Math.round(myRect.getHeight());
	}

	//Setters
	public void setX(int x) {
		setLocation(x, getY());
		stringLoc = null; //Need to recalculate the string's draw location.
	}
	public void setY(int y) {
		setLocation(getX(), y);
	}
	public void setWidth(int width) {
		setSize(width, getHeight());
	}
	public void setHeight(int height) {
		setSize(getWidth(), height);
	}
	public void setLocation(int x, int y) {
		setLocation(new Point(x, y));
	}
	public void setLocation(Point p) {
		myRect.setRect(p.x, p.y, getWidth(), getHeight());
		stringLoc = null;
	}
	public void setSize(int width, int height) {
		myRect.setRect(getX(), getY(), width, height);
		stringLoc = null;
	}
	public void setText(String text) {
		myText = text;
		setLines();
	}
	public void enable() {
		isImpossible = false;
		normCol = normColEnabled;
		hoverCol = hoverColEnabled;
		pressCol = pressColEnabled;
		normBor = normBorEnabled;
		hoverBor = hoverBorEnabled;
		pressBor = 	pressBorEnabled;
		fontCol = fontColEnabled;
		setMode(curMode);

	}
	public void disable() {
		isImpossible = true;
		normCol = normColDisabled;
		hoverCol = hoverColDisabled;
		pressCol = pressColDisabled;
		normBor = normBorDisabled;
		hoverBor = hoverBorDisabled;
		pressBor = 	pressBorDisabled;
		setMode(curMode);
	}
	public void setEnabled(boolean val) {
		if(val) {
			enable();
		} else {
			disable();
		}
	}
}
