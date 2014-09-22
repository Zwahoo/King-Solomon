//Zane Laughlin- map class- holds bunch of tiles
// hello

public class map {

	private tile[][] tiles = new tile[40][40];
	
	//random map generator
	public map(){
		String a = null;
		tile t;
		for (int x = 0; x < 40; x++){
			for (int y = 0; y < 40; y++){
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
				t = new tile(a, x, y);
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
	
	public tile getTile(int x, int y){
		//System.out.println("wat");
		return tiles[x][y];
	}
	
	//public static void main(String[] args){
	//	map t = new map();
	//}
}
