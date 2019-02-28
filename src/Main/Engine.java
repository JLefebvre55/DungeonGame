package Main;

import Graphics.FrameRenderer;

/**
 * ALL renderables must utilize update to be updated by main class
 * @author jaydenlefebvre
 *
 */
public interface Engine {

	/**
	 * Called by main update in driver. For graphics render to screen
	 * @param screen - Frame driver to render to
	 */
	public abstract void update(FrameRenderer screen);
	
	/**
	 * Called by main fixedupdate in driver. For physics and movement
	 */
	public abstract void fixedUpdate();
	
}
