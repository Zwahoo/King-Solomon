package MainGame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.simple.parser.*;

/**
 * 
 * @author Jackson Ekis
 * Date: 10/25/14
 * Purpose: Takes in a .txt file written in JSON and turns it into a map.
 *          This map is used in the MapToEvent and MapToResponseOption classes.
 *
 */

public class FileToMap {
	//Do keep in mind that not all of JSON is imported. You will need to import as you add more.
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> createMap(String filename){
		JSONParser parser = new JSONParser();
		String inputFile = filename + ".txt";
		
		String input = readFromFile(inputFile);
		
		Object obj = null;
		try {
			obj = parser.parse(input);
		} catch (ParseException e){
			System.out.println("The event called " + filename + " could not be read.");
			e.printStackTrace();
		}
		
		HashMap<String, Object> hashMap = (HashMap<String, Object>)obj;
		
		return hashMap;
	}
	
	public static String readFromFile(String filename){
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
		} catch (IOException e){
			e.printStackTrace();
		}
		return text;
	}
}
