package MainGame;
//Zane Laughlin- map class- holds bunch of tiles
// hello

public class Map {
	
	int width;
	int height;
	private Tile[][] tiles;
	
	//random map generator
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		String a = null;
		Tile t;
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				int i = (int) ((Math.random()) * 3);
				if (i == 0){
					a = "water";
				}
				else if (i == 1){
					a = "jungle";
				}
				else if (i == 2){
					a = "mountain";
				}
				t = new Tile(a, x, y);
				tiles[x][y] = t;
				//System.out.println(x + ", " + y);
			}
		}
	}
	
	//map which is created from image?
	//public map(image i){
	//	
	//}
	
	//map which is generated from file data?
	//public map(file f){
	//	
	//}
	
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
    
}
