package MainGame;
//Zane Laughlin- runs standard gamemode and window
//Thomas Sparks was Here!

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * Main class for the game
 */
@SuppressWarnings({ "serial", "unused" })
public class gameframe extends JFrame {


	public static boolean DEBUG = true;
	boolean doSetup = false;
	
	
	private boolean isRunning = true;
	public static Insets insets;
	private BufferedImage backBuffer;
	private int fps = 30;
	public static int windowWidth = 1000;
	public static int windowHeight = 800;
	private MainGame mainGame;
	private IntroSequence introSeq;
	private boolean runIntroSequence = true;
	
	public static int topinset = 22;
	public static int bottominset = 0;
	public static int leftinset = 0;
	public static int rightinset = 0;
	
	public Sound sound;

	// frame graphics
	Graphics g = null;
	// buffered image which will be drawn to frame (but is first applied to "g")
	Graphics bbg = null;
	
	private HashMap<String, Integer> gentStats = PartyMemberStats.AVERAGE_ABE_STATS;
	private HashMap<String, PartyMember> party = PartyMemberStats.defaultParty;
	
	// MAIN
	public static void main(String[] args) throws IOException {
		gameframe game = new gameframe();
		game.initialize();
		game.run();
		System.exit(0);
	}

	/**
	 * This method starts the game and runs it in a loop
	 * leException 
	 * @throws IOException 
	 */
	// starts the game and runs loop
	public void run() throws IOException {
		boolean go = true;

		this.initialize(); // initializes things which need initializing before
							// the game can run.
		
		sound = new Sound("assets/sounds/KingSolomonsOverworldTheme.wav", true);
		
		while (go){
			if(doSetup) {
				if(!sound.isPlaying) sound = new Sound("assets/sounds/KingSolomonsOverworldTheme.wav", true);
				this.initializeIntroSequence(); // initializes things which need initializing before
									// the game can run
					
				// initially draws everything
				draw();
				// loop which handles fps
				while (runIntroSequence) {
					// time
					long time = System.currentTimeMillis();
		
					update();
					draw();
					
					runIntroSequence = !introSeq.finished;
					
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
				
				gentStats = introSeq.gentStats;
				party = introSeq.party;
				
				IntroSequence.removeInputManager();
				introSeq = null;
				
				isRunning = true;
			}
			//Runs the main game
			this.initializeMainGame();
			
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
		}
		

		setVisible(false);
	}
	
	public void returnGameToMenu() {
		IntroSequence.input = null;
		doSetup = true;
		runIntroSequence = true;
		isRunning = false;
		if(MainGame.input != null) {
			MainGame.removeInputManager();
		}
		mainGame = null;
	}
	
	private void initializeMainGame() throws IOException {
		mainGame = new MainGame(this, windowWidth, windowHeight, gentStats, party);
	}

	private void initializeIntroSequence() {
		introSeq = new IntroSequence(this);
	}

	/**
	 * This method will set up everything need for the game to run
	 * 
	 * @throws IOException
	 */
	void initialize() throws IOException {
			
		// offsets the frame to account for the top bar, border, etc.
		insets = getInsets();
		insets.set(topinset, leftinset, bottominset, rightinset);
		// sets size of frame
		setSize(insets.left + windowWidth + insets.right, insets.top
				+ windowHeight + insets.bottom);
		// sets image size to be the same size as the window
		backBuffer = new BufferedImage(windowWidth, windowHeight,
				BufferedImage.TYPE_INT_RGB);
		// frame graphics
		g = getGraphics();
		// buffered image which will be drawn to frame (but is first applied to "g")
		bbg = backBuffer.getGraphics();
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
		if(mainGame != null) mainGame.update();
		else if(introSeq != null) introSeq.update();
	}

	/**
	 * This method will draw everything
	 */
	// draws the graphics again when it is needed
	void draw() {
		// background color
		bbg.setColor(Color.BLACK);
		// background height and width
		bbg.fillRect(0, 0, windowWidth, windowHeight);
		
		if(mainGame != null) mainGame.draw(bbg);
		else if(introSeq != null) introSeq.draw(bbg);
		
		// actually draws the images and stuff on screen
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	
	public int getWidth() {
		return windowWidth;
	}
	public int getHeight(){
		return windowHeight;
	}
	// world record speed run- explored all of Africa- Chris Earman- like 5
	// minutes
	// such pro
}
