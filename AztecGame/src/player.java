//Zane Laughlin
//player class- manages supplies, player location, etc.

public class player {

	private tile currenttile;
	private tile[] neighbortiles = new tile[4];
	private String name;
	private int x;
	private int y;
	private tile dummy; //dummy
	private partymember[] partymembers  = new partymember[10];
	
	//attaches player to map
	public player(map m){
		currenttile = m.getTile(0,0);
		x = 1;
		y = 1;
		dummy = new tile("dummy", -1,-1);
		dummy.setSelectangle(-2, -3, -4, -5, -6, -7, -8, -9, -2, -3, -4, -5, -6, -7, -8, -9);
		this.setNeighbors(m);
		
	}
	
	//gets one of the neighbor tiles
	public tile getNeighbortile(int b){
		//System.out.println("ch1");
		return neighbortiles[b];
		
	}
	
	//sets the 4 neighbors or sets the neighbor to a dummy tile if no neighbor available
	public void setNeighbors(map m){
		if(x != 0){
			System.out.println("made it1");
			neighbortiles[0] = m.getTile(x-1, y);
		}
		else if(x == 0){
			System.out.println("made it2");
			neighbortiles[0] = dummy;
		}
		if(y != 0){
			System.out.println("made it1");
			neighbortiles[1] = m.getTile(x, y-1);
		}
		else if(y == 0){
			System.out.println("made it2");
			neighbortiles[1] = dummy;
		}
		if(x != 39){
			System.out.println("made it1");
			neighbortiles[2] = m.getTile(x+1, y);
		}
		else if(x == 39){
			System.out.println("made it2");
			neighbortiles[2] = dummy;
		}
		if(y != 39){
			System.out.println("made it1");
			neighbortiles[3] = m.getTile(x, y+1);
		}
		else if(y == 39){
			System.out.println("made it2");
			neighbortiles[3] = dummy;
		}
		
	}
	
	//get x
	public int getX(){
		return x;
	}
	
	//get y
	public int getY(){
		return y;
	}
	
	//changes the tile the player is on and sets the neighbors as well
	public void move(tile t, map m){
		x = t.getX();
		y = t.getY();
		currenttile = t;
		this.setNeighbors(m);
		currenttile.runEvent();
		
	}
	
	
}
