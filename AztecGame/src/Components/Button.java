package Components;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import MainGame.*;

public class Button extends InputListener {
	
	public static final int MODE_NORMAL = 0; //Default button "mode"
	public static final int MODE_HOVER = 1; //The mouse is hovering over the button, but not pressed
	public static final int MODE_PRESSED = 2; //The mouse has been clicked on the button, but not released yet.
	
	private String myText; //Text on the button.
	private Rectangle2D myRect; //Location and size of the button
	private int borderSize = 2; //Size of the button's border
	private boolean mouseOnButton = false; //Is the mouse currently hovering over the button?
	private Font myFont; //Font used in the button's text
	
	Color bkgCol; //The backdrop color for the button (switches between off, hover, and press Col)
	Color borderCol; //The border color for the button (switches between off, hover, and press Bor)
	
	Color normCol = new Color(210, 150, 50); //Back color for normal mode
	Color hoverCol = new Color(210, 150, 50); //Back color for hover mode
	Color pressCol = new Color(180, 130, 30); //Back color for press mode
	Color normBor = new Color(80, 50, 50); //Border color for normal mode
	Color hoverBor = new Color(100, 70, 70); //Border color for hover mode
	Color pressBor = new Color(100, 70, 70); //Border color for press mode
	Color fontCol = new Color(0, 0, 0); //Color of the text
	
	Point stringLoc = null;
	
	//Constructors
	public Button(int x, int y, int width, int height, String str, InputManager inputManager) {
		this(new Point(x, y), new Point(width, height), str, inputManager);
	}
	public Button(Point loc, Point size, String str, InputManager inputManager) {
		this(new Rectangle(loc.x, loc.y, size.x, size.y), str, inputManager);
	}
	public Button(Rectangle rect, String str, InputManager inputManager) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(MainGame.BEGIN_DAY_MODE); temp.add(MainGame.MOVEMENT_MODE); temp.add(MainGame.EVENT_MODE);
		setInputManager(inputManager, temp); //Required for handling input
		
		this.myRect = new Rectangle(rect);
		this.myText = str;
		
		myFont = new Font("Georgia", Font.PLAIN, 14);
		
		setMode(MODE_NORMAL);
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
		
		//Calculate the string location if it's not known
		if(stringLoc == null) {
			stringLoc = getStringDrawLoc(g, myFont, myText);
		}
		
		g.drawString(myText, stringLoc.x, stringLoc.y);
	}
	
	//Sets the mode if mouse exits/exits the button.
	public void update() {
		boolean mouseOn = pointIntersectsButton(getMouseLoc());
		if(mouseOn != mouseOnButton) {
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
		
		int newX = (int) Math.round(myRect.getCenterX() - stringBounds.getWidth()/2);
		int newY = (int) Math.round(myRect.getCenterY() + stringBounds.getHeight()/4);
		
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
		}
		else if (mode == MODE_HOVER) {
			setMouseOnButton(true);
			bkgCol = hoverCol;
			borderCol = hoverBor;
			
		}
		else if (mode == MODE_PRESSED) {
			setMouseOnButton(true);
			bkgCol = pressCol;
			borderCol = pressBor;
		} else {
			System.out.println("Unknown Mode Request in Button.");
		}
	}
	
	@Override
	//When the mouse is pressed (but not yet released)
	public void mousePressed(int mouseButton, Point mouseLoc) {
		if(mouseOnButton) {
			setMode(MODE_PRESSED);
		}
	}
	
	@Override
	//When the mouse as been released over the button, call the onClick method.
	public void mouseReleased(int mouseButton, Point mouseLoc) {
		if(mouseOnButton) {
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
		this.myText = text;
		stringLoc = null;
	}
	
}
