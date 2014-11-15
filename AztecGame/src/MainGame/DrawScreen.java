package MainGame;

import java.awt.Graphics;

public interface DrawScreen {
	public abstract boolean update();
	public abstract void draw(Graphics g);
}
