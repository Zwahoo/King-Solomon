package MainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Components.Button;
import Components.Textbox;

public class EndGameScreen implements DrawScreen {

	private Button closeButton;

	private Textbox scoreDisplayBox;

	private Textbox descriptionOfEndBox;

	private int score;

	private String methodOfDeath;

	private boolean finished = false;

	public EndGameScreen(int score, boolean victory) {
		this.score = score;
		if (victory) {
			methodOfDeath = "You Won! You got to King Solomon's mines! You escape with your riches and live a long, happy life.";
		} else {
			methodOfDeath = "You lost. Your party is dead, and you never got to see the fabled King Solomon's Mines.";
		}
		initTextboxes();
		initButtons();
	}

	private void initTextboxes() {
		descriptionOfEndBox = new Textbox(methodOfDeath, gameframe.windowWidth/4, gameframe.windowHeight/4, gameframe.windowWidth/2, gameframe.windowHeight/8, MainGame.input);
		scoreDisplayBox = new Textbox("" + score, (gameframe.windowWidth/2) - 100, gameframe.windowHeight/2, 200, gameframe.windowHeight/16, MainGame.input);
	}

	private void initButtons() {
		closeButton = new Button((gameframe.windowWidth/2) - 100, (gameframe.windowHeight/2) + 200, 200, gameframe.windowHeight/16, "Close Game", MainGame.input) {
			@Override
			public void onClick() {
				finish();
			}
		};
	}

	@Override
	public boolean update() {
		descriptionOfEndBox.update();
		scoreDisplayBox.update();
		closeButton.update();
		return finished;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, gameframe.windowWidth, gameframe.windowHeight);
		descriptionOfEndBox.draw(g);
		scoreDisplayBox.draw(g);
		closeButton.draw(g);
	}

	@Override
	public void finish() {
		ArrayList<Integer> modesList = new ArrayList<Integer>();
		modesList.add(-1);
		finished = true;
		MainGame.input.removeInputListener(closeButton, modesList);
		System.exit(1);
	}

}
