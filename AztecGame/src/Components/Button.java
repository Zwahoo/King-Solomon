package Components;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import MainGame.*;

public class Button extends InputListener {
	
	public static final int MODE_NORMAL = 0;
	public static final int MODE_HOVER = 1;
	public static final int MODE_PRESSED = 2;
	
	private String myText;
	private Rectangle2D myRect;
	private int borderSize = 3;
	private boolean mouseOnButton = false;
	private Font myFont;
	
	Color drawCol;
	Color borderCol;
	Color offCol = new Color(210, 150, 50);
	Color onCol = new Color(210, 150, 50);
	Color pressCol = new Color(180, 130, 30);
	Color offBor = new Color(80, 50, 50);
	Color onBor = new Color(180, 70, 70);
	Color pressBor = new Color(180, 70, 70);
	Color fontCol = new Color(0, 0, 0);
	
	//Constructors
	public Button(int x, int y, int width, int height, String str, InputManager inputManager) {
		this(new Point(x, y), new Point(width, height), str, inputManager);
	}
	public Button(Point loc, Point size, String str, InputManager inputManager) {
		this(new Rectangle(loc.x, loc.y, size.x, size.y), str, inputManager);
	}
	public Button(Rectangle rect, String str, InputManager inputManager) {
		setInputManager(inputManager);
		this.myRect = new Rectangle(rect);
		this.myText = str;
		
		myFont = new Font("Cooper", Font.PLAIN, 12);
		
		setMode(MODE_NORMAL);
	}
	
	
	public void onClick() {}
	
	public void draw(Graphics g) {
		//Button border
		g.setColor(borderCol);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
		//Main Backdrop for the button
		g.setColor(drawCol);
		g.fillRect(getX() + borderSize, getY() + borderSize, getWidth() - (2*borderSize), getHeight() - (2*borderSize));
		
		//Draw the text
		g.setFont(myFont);
		g.setColor(fontCol);
		Point stringLoc = getStringDrawLoc(g, myFont, myText);
		
		g.drawString(myText, stringLoc.x, stringLoc.y);
	}
	
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
	
	private Point getStringDrawLoc(Graphics g, Font f, String str) {
		Rectangle2D stringBounds = f.getStringBounds(str, g.getFontMetrics().getFontRenderContext());
		
		int newX = (int) Math.round(myRect.getCenterX() - stringBounds.getWidth()/2);
		int newY = (int) Math.round(myRect.getCenterY() + stringBounds.getHeight()/4);
		
		return new Point(newX, newY);	
	}
	
	public void setMouseOnButton(boolean on) {
		if(on) {
			mouseOnButton = true;
		} else {
			mouseOnButton = false;
		}
	}
	
	public void setMode(int mode) {
		if(mode == MODE_NORMAL) {
			setMouseOnButton(false);
			drawCol = offCol;
			borderCol = offBor;
		}
		else if (mode == MODE_HOVER) {
			setMouseOnButton(true);
			drawCol = onCol;
			borderCol = onBor;
			
		}
		else if (mode == MODE_PRESSED) {
			setMouseOnButton(true);
			drawCol = pressCol;
			borderCol = pressBor;
		} else {
			System.out.println("Unknown Mode Request in Button.");
		}
	}
	
	@Override
	public void mousePressed(int mouseButton, Point mouseLoc) {
		if(mouseOnButton) {
			setMode(MODE_PRESSED);
		}
	}
	@Override
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
	
	public void setX(int x) {
		setLocation(x, getY());
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
	}
	public void setSize(int width, int height) {
		myRect.setRect(getX(), getY(), width, height);
	}
	
}
