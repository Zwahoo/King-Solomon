//Zane Laughlin- runs standard gamemode and window
//Thomas Sparks was Here!

import java.awt.*; 
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame; 
import java.awt.Point;
import java.awt.Polygon;

/** 
 * Main class for the game 
 */ 
public class gameframe extends JFrame 
{        
	private boolean isRunning = true;
	private InputManager input;
	private Insets insets;
	private BufferedImage backBuffer;
	private int fps = 30;
	private int windowWidth = 1000;
	private int windowHeight = 800;
	private int x = 0;
	private BufferedImage loadedimage;
	private BufferedImage[] images = new BufferedImage[10];
	private map themap= new map();
	private int xcoor = 0;
	private int ycoor = 0;
	private String tiletype;
	private player player1 = new player(themap);
	private boolean menued;
	private String mode;
	
		//MAIN
        public static void main(String[] args) throws IOException 
        { 
        	gameframe game = new gameframe(); 
            game.run(); 
            System.exit(0); 
        } 
        
        /** 
         * This method starts the game and runs it in a loop 
         * @throws IOException 
         */ 
        //starts the game and runs loop
        public void run() throws IOException { 
        	
        			
                this.initialize(); 	//initializes things which need initializing before the game can run
                System.out.println("what is going on here");			//CHECKPOINT
                //initially draws everything
                checksight();
                draw();
                //loop which handles fps
                while(isRunning) 
                { 
                		//time
                        long time = System.currentTimeMillis(); 
                        
                        //runs an update 30 times a second
                        update(); 
                       
                        time = (1000 / fps) - (System.currentTimeMillis() - time); 
                        
                        //what the heck is even going on here man I mean seriously
                        //Somethin' 'bout a thread runnin' fer the frame I 'reckon
                        //It's limiting the framerate to fps... I think.
                        if (time > 0) 
                        { 
                                try 
                                { 
                                       Thread.sleep(time); 
                                } 
                                catch(Exception e){} 
                        } 
                } 
                
                setVisible(false); 
        } 
        
        /** 
         * This method will set up everything need for the game to run 
         * @throws IOException 
         */ 
        void initialize() throws IOException { 
        	
        	//preloads images used for drawing dem sweet sweet grayfixs
        	images[0] = ImageIO.read(new File("assets/water.png"));
        	images[1] = ImageIO.read(new File("assets/jungle.png"));
        	images[2] = ImageIO.read(new File("assets/mountain.png"));
        	images[3] = ImageIO.read(new File("assets/marker.png"));
        	images[4] = ImageIO.read(new File("assets/statusbar.png"));
        	images[5] = ImageIO.read(new File("assets/unknown.png"));
        	
        	//sets up mouse input stuff
        	input = new InputManager(this); 
        	//what do insets do again? well anyway they're important
        	insets = getInsets(); 
        	//sets size of frame
        	setSize(insets.left + windowWidth + insets.right, insets.top + windowHeight + insets.bottom); 
        	//sets image size to be the same size as the window
        	backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB); 
        	//sets title on top of game window
        	setTitle("King Solomon's Mines"); 
        	//sets window height again, just for funzies.
            setSize(windowWidth, windowHeight); 
            //sets if the window can be resized
            setResizable(false); 
            //Exit the program when the windows is closed.
            setDefaultCloseOperation(EXIT_ON_CLOSE); 
            //we want to see the frame, no?
            setVisible(true);  //false for hard mode
            System.out.println("what's up");									//CHECKPOINT
            
            //sets up initial tile selectangles- they are moved later
            int newx = 0;
            int newy = 0;
            for(int i = 0; i < 40; i++){
            	newx = (i * 64);
        		newy = (i * 32);
            	for(int b = 0; b < 40; b++){
            		themap.getTile(i, b).setSelectangle(newx, newx, newx + 62, newx+63,  newx+125,  newx+125,  newx+63,  newx+62, newy+32, newy+31, newy, newy, newy+31, newy+32, newy+63, newy+63);
            		newx += 64;
					newy -= 32;
            	}
            }
            
            System.out.println("hello?????????????????????????????");	//hi		//CHECKPOINT
        } 
        
        /** 
         * This method will check for input, move things 
         * around and check for win conditions, etc 
         */ 
        //update function- run 30 times a second- keep that in mind
        //NOTE:
        //This whole function needs to be compartmentalized so it switches between "move,"
        //"check map," and "menu" mode
        void update() 
        { 
        	checkscroll();
        	checkInput();	
        }
        
        /** 
         * This method will draw everything 
         */ 
        //draws the graphics again when it is needed
        void draw() 
        { 
        	int newx;
        	int newy;
        	//frame graphics
        	Graphics g = getGraphics(); 
        	//buffered image which will be drawn to frame (but is first applbied to "g")
        	Graphics bbg = backBuffer.getGraphics();
        	//background color
        	bbg.setColor(Color.BLACK); 
        	//background height and width
        	bbg.fillRect(0, 0, windowWidth, windowHeight); 
        	//don't know why this 
        	//bbg.setColor(Color.BLACK); 
        	
        	//loop for drawing each tile
        	for (int i = 0; i < 40; i++)
        	{
        		newx = xcoor + (i * 64);
        		newy = ycoor + (i * 32);
        		for (int b = 0; b < 40; b++)
            	{
        			tiletype = themap.getTile(i,b).getType();
        			
        			//this circuit determines which tile is to 
            		if (!themap.getTile(i,b).getRevealed()){
            			loadedimage = images[5];
            		}
            		else if (tiletype.equals("jungle")){
        				loadedimage = images[1];
            		}
            		else if (tiletype.equals("mountain")){
        				loadedimage = images[2];
					}
            		else if (tiletype.equals("water")){
        				loadedimage = images[0];
					}
            		
            		//draws the tile
					bbg.drawImage(loadedimage, newx, newy, null);
					
					//draws player marker
					if(i == player1.getX() && b == player1.getY()){
						loadedimage = images[3];
						bbg.drawImage(loadedimage, newx + 43, newy + 12, null);
					}
					
					newx += 64;
					newy -= 32;
					
            	}
        		
        	}
        	    
        	//actually draws the images and stuff on screen
        	g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
        
        //checks to see if the player activated the scrolling function
        public void checkscroll(){
        	
        	//checks to see if mouse is at the edge of the frame
        	if (input.mouseInWindow()){
        		int x = input.getMouseLoc().x;
        		int y = input.getMouseLoc().y;
        	
        		if (x < 100){
        			xcoor += 20;
        			for(int i = 0; i < 40; i++){
        				for(int b = 0; b < 40; b++){
            				themap.getTile(i,b).moveSelectangle(20,0);
            			}
        			}
        		}
        		if (x > 900){
        			xcoor -= 20;
        			for(int i = 0; i < 40; i++){
        				for(int b = 0; b < 40; b++){
            				themap.getTile(i,b).moveSelectangle(-20,0);
            			}
        			}
        		}
        		if (y < 100){
        			ycoor += 20;
        			for(int i = 0; i < 40; i++){
        				for(int b = 0; b < 40; b++){
            				themap.getTile(i,b).moveSelectangle(0,20);
            			}
        			}
        		}
        		if (y > 700){
        			ycoor -= 20;
        			for(int i = 0; i < 40; i++){
        				for(int b = 0; b < 40; b++){
            				themap.getTile(i,b).moveSelectangle(0,-20);
            			}
        			}
        		}
        		//redraws 
        		draw();
        	}
        }
        
        public void checkInput() {
        	//checks to see if something clickable was clicked on- may become its own function at some point maybe maybe not
        	if (input.mouseDown(MouseEvent.BUTTON1)) 
        	{ 
        		int x = input.getMouseLoc().x;
    	        int y = input.getMouseLoc().y;
    	        
    	        //checks to see if any of the tiles neighboring player were clicked- this can probably be reduced to loop
        		if(player1.getNeighbortile(0).checkcontains(x,y)){
        	        player1.move(player1.getNeighbortile(0), themap);
        	        System.out.println("moved");
        	        this.checksight();
        		} 
        		
        		else if(player1.getNeighbortile(1).checkcontains(x,y)){
        			player1.move(player1.getNeighbortile(1), themap);
        			System.out.println("moved");
        			this.checksight();
    			}
        		
        		else if(player1.getNeighbortile(2).checkcontains(x,y)){
        			player1.move(player1.getNeighbortile(2), themap);
        			System.out.println("moved");
        			this.checksight();
				}
        		
        		else if(player1.getNeighbortile(3).checkcontains(x,y)){
        			player1.move(player1.getNeighbortile(3), themap);
        			System.out.println("moved");
        			this.checksight();
				}
				
			}
		
        }
        
        public void checksight(){
        	int x = 0;
        	int y = 0;
        	for(int i = 0; i < 4; i++){
        		x = player1.getNeighbortile(i).getX();
        		y = player1.getNeighbortile(i).getY();
        		if(!player1.getNeighbortile(i).getType().equals("dummy")){
        			themap.getTile(x, y).reveal();
        		}
        	}
        }
        
        //world record speed run- explored all of Africa- Chris Earman- like 5 minutes
        //such pro
} 