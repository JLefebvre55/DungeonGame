package UI;

import Graphics.Renderable;

/**
 * Interface implemented to follow another object
 * @author jaydenlefebvre
 *
 */
public interface FollowRenderable {

	/**
	 * Follows an entity at a fixed x/y delta
	 * @param e - Entity to follow
	 * @param dx - delta x
	 * @param dy - delta y
	 */
	abstract void followRenderable(Renderable o, double dx, double dy);
	
}
