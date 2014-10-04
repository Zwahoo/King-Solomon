package MainGame;
//Zane Laughlin- runs standard gamemode and window
//Thomas Sparks was Here!

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

/**
 * Main class for the game
 */
public class gameframe extends JFrame {

	private boolean isRunning = true;
	private Insets insets;
	private BufferedImage backBuffer;
	private int fps = 30;
	private int windowWidth = 1000;
	private int windowHeight = 800;
	private MainGame mainGame;

	// MAIN
	public static void main(String[] args) throws IOException {
		gameframe game = new gameframe();
		game.run();
		System.exit(0);
	}

	/**
	 * This method starts the game and runs it in a loop
	 * 
	 * @throws IOException
	 */
	// starts the game and runs loop
	public void run() throws IOException {

		this.initialize(); // initializes things which need initializing before
							// the game can run
		//System.out.println("what is going on here"); // CHECKPOINT
		
		
		// initially draws everything
		draw();
		// loop which handles fps
		while (isRunning) {
			// time
			long time = System.currentTimeMillis();

			update();
			draw();

			// runs an update 30 times a second
			time = (1000 / fps) - (System.currentTimeMillis() - time);

			// what the heck is even going on here man I mean seriously
			// Somethin' 'bout a thread runnin' fer the frame I 'reckon
			// It's limiting the framerate to fps... I think.
			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
				}
			}
		}

		setVisible(false);
	}

	/**
	 * This method will set up everything need for the game to run
	 * 
	 * @throws IOException
	 */
	void initialize() throws IOException {
		
		//set stats
				MainGame.partyStats = new HashMap<String, Integer>();
				MainGame.partyStats.put("Food", 0);
				MainGame.partyStats.put("Water", 0);
				MainGame.partyStats.put("Ammo", 0);
		MainGame.partyStats.put("Medicine", 0);
		MainGame.partyStats.put("Valuables", 0);
		MainGame.partyStats.put("Pack Animals", 0);
		MainGame.partyStats.put("Morale", 0);
		MainGame.partyStats.put("Hunger", 0);
		MainGame.partyStats.put("Thirst", 0);
		MainGame.partyStats.put("Stamina", 0);
				
		mainGame = new MainGame(this, windowWidth, windowHeight);

		// what do insets do again? well anyway they're important
		insets = getInsets();
		// sets size of frame
		setSize(insets.left + windowWidth + insets.right, insets.top
				+ windowHeight + insets.bottom);
		// sets image size to be the same size as the window
		backBuffer = new BufferedImage(windowWidth, windowHeight,
				BufferedImage.TYPE_INT_RGB);
		// sets title on top of game window
		setTitle("King Solomon's Mines");
		// sets window height again, just for funzies.
		setSize(windowWidth, windowHeight);
		// sets if the window can be resized
		setResizable(false);
		// Exit the program when the windows is closed.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// we want to see the frame, no?
		setVisible(true); // false for hard mode
		//System.out.println("what's up"); // CHECKPOINT

		//System.out.println("hello?????????????????????????????"); // hi
																	// //CHECKPOINT
		
		
	}

	/**
	 * This method will check for input, move things around and check for win
	 * conditions, etc
	 */
	// update function- run 30 times a second- keep that in mind
	// NOTE:
	// This whole function needs to be compartmentalized so it switches between
	// "move,"
	// "check map," and "menu" mode
	void update() {
		mainGame.update();
	}

	/**
	 * This method will draw everything
	 */
	// draws the graphics again when it is needed
	void draw() {
		// frame graphics
		Graphics g = getGraphics();
		// buffered image which will be drawn to frame (but is first applied to "g")
		Graphics bbg = backBuffer.getGraphics();
		// background color
		bbg.setColor(Color.BLACK);
		// background height and width
		bbg.fillRect(0, 0, windowWidth, windowHeight);
		
		mainGame.draw(bbg);
		
		// actually draws the images and stuff on screen
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}

	// world record speed run- explored all of Africa- Chris Earman- like 5
	// minutes
	// such pro
}