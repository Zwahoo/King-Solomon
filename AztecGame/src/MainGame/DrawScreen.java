package MainGame;

import java.awt.Graphics;

public interface DrawScreen {
	/**
	 * Update any necessary controls.
	 * @return True if the scene is finished. Otherwise false.
	 */
	public abstract boolean update();
	/**
	 * Draw any necessary controls.
	 */
	public abstract void draw(Graphics g);
	/**
	 * Called once the scene is done. Should clean up any components that need to be removed.
	 */
	public abstract void finish();
}
