package MainGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Components.Button;

public class StartScreen implements DrawScreen {
	
	Button startButton;
	BufferedImage titleImage;
	boolean finished = false;
	boolean hasDrawnBkgImg = false;
	
	int startWidth = 200;
	int startHeight = 40;
	int startX = 40;
	int startY = gameframe.windowHeight - startHeight - 40;
	
	public StartScreen() {
		try {
			titleImage = ImageIO.read(new File("assets/Title Page.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		startButton = new Button(startX, startY, startWidth, startHeight, "Start", IntroSequence.input) {
			@Override
			public void onClick() {
				finished = true;
			}
		};	
	}
	
	@Override
	public boolean update() {
		startButton.update();
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		if(!hasDrawnBkgImg) {
			g.drawImage(titleImage, 0, 0, gameframe.windowWidth, gameframe.windowHeight, null);
		}
		startButton.draw(g);
	}
	
	@Override
	public void finish() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		IntroSequence.input.removeInputListener(startButton, modesList);
	}
}
