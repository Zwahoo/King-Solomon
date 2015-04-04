package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.InputManager;

public class ImageButton extends Button {

	private Image img;

	Color normImgCol = new Color(0, 0, 0);
	Color hoverImgCol = new Color(100, 100, 100);
	Color pressImgCol = new Color(200, 200, 200);

	public ImageButton(String imgFilePath, int x, int y, int width, int height, InputManager inputManager) {
		super(x, y, width, height, "", inputManager);
		File f = new File(imgFilePath);
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ImageButton(String imgFilePath, Point loc, Point size, InputManager inputManager) {
		super(loc, size, "", inputManager);
		File f = new File(imgFilePath);
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ImageButton(String imgFilePath, Rectangle rect, InputManager inputManager) {
		super(rect, "", inputManager);
		File f = new File(imgFilePath);
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		//Draw the border
		g.setColor(borderCol);
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		//Draw the Backdrop
		g.setColor(bkgCol);
		g.drawImage(img, getX() + borderSize, getY() + borderSize, getWidth() - (2 * borderSize), getHeight() - (2 * borderSize), null);
	}

	@Override
	//Changes the button mode, and modifies the colors.
	public void setMode(int mode) {
		if(mode == MODE_NORMAL) {
			setMouseOnButton(false);
			bkgCol = normImgCol;
			borderCol = normBor;
			curMode = MODE_NORMAL;
		}
		else if (mode == MODE_HOVER) {
			setMouseOnButton(true);
			bkgCol = hoverImgCol;
			borderCol = hoverBor;
			curMode = MODE_HOVER;

		}
		else if (mode == MODE_PRESSED) {
			setMouseOnButton(true);
			bkgCol = pressImgCol;
			borderCol = pressBor;
			curMode = MODE_PRESSED;
		} 
		else {
			System.out.println("Unknown Mode Request in Button.");
		}
	}
}
