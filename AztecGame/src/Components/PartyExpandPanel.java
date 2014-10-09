package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PartyExpandPanel {
	
	Rectangle myRect;
	static final int defaultWidth = 500;
	static final int defaultHeight = 500;

	Color backColor = new Color(210, 150, 50);
	Color borderColor = new Color(80, 50, 50);
	private int borderSize = 5;
	
	public PartyExpandPanel(int x, int y) {
		myRect = new Rectangle(x, y, defaultWidth, defaultHeight);
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(borderColor);
		g.fillRect(myRect.x, myRect.y, myRect.width, myRect.height);
		g.setColor(backColor);
		g.fillRect(myRect.x + borderSize, myRect.y + borderSize, myRect.width - borderSize*2, myRect.height - borderSize*2);
	}
	
}