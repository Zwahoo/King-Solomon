package MainGame;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import Components.*;

public class StatSelect implements DrawScreen {
	
	public int windowWidth = 1000;
	public int windowHeight = 800;
	
	public static int unused = 10;
	
	Textbox titleBox;
	
	int titleTextboxWidth = 350;
	int titleTextboxHeight = 50;
	
	
	
	public StatSelect() {
		titleBox = new Textbox("Select Gentleman's Stats",
				windowWidth/2 - titleTextboxWidth/2, 10,
				titleTextboxWidth, titleTextboxHeight, IntroSequence.input);
		titleBox.SetFont(new Font("Georgia", Font.PLAIN, 30));
		titleBox.textBuffer = 10;
	}
	
	public void draw(Graphics g) {
		titleBox.draw(g);
	}
	
	public boolean update() {
		titleBox.update();
		return false;
	}
	
	public void finish() {
		
	}
	
}
