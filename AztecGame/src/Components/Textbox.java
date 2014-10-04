package Components;

import java.util.ArrayList;

public class Textbox {
	
	Button upBtn;
	Button downBtn;
	
	int drawWidth;
	int drawHeight;
	int fullHeight;
	
	String textOrig;
	ArrayList<String> lines;
	
	int scrollLoc;
	int numLinesToShow;
	
	public Textbox(String str, int width, int height) {
		textOrig = str;
		lines = fitStr(str);
		drawWidth = width;
		drawHeight = height;
		scrollLoc = 0;
		calculateFullHeight();
		calculateNumLinesToShow();
	}
	
	private ArrayList<String> fitStr(String orig) {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(orig);
		return ret;
	}
	
	private void calculateFullHeight() {
		
	}
	
	private void calculateNumLinesToShow() {
		
	}
	
	public void draw() {
		
	}
	
}
