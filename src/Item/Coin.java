package Item;

import Animation.Animator;
import Entities.BoxCollider;
import Graphics.FrameRenderer;
import Graphics.Layers;
import Graphics.Renderable;

/**
 * Is a coin. Can be picked up. Worth some dollars.
 * @author jaydenlefebvre
 *
 */
public class Coin extends Renderable{

	private Animator animator;
	private static SpriteSheet sheet = new SpriteSheet("Resources/SpriteSheets/Items/coin.png", 12, 12);;
	private BoxCollider collider;
	private static final int TIMEBETWEEN = 100;
	
	/**
	 * Makes a coin at a position
	 * @param x
	 * @param y
	 */
	public Coin(double x, double y) {
		super(x, y, sheet.getSprite(0), Layers.ITEMS);
		animator = new Animator(TIMEBETWEEN, 0, sheet.getSprites());
		collider = new BoxCollider(this, 0, 0, image.getWidth(), image.getHeight());
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderObject(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		image = animator.updateAnimation();
		collider.fixedUpdate();
	}
	
	public String toString() {
		return "Coin";
	}
	
	public BoxCollider getCollider() {
		return collider;
	}
	
	public static int getTimeBetween() {
		return TIMEBETWEEN;
	}
	
	public static SpriteSheet getSheet() {
		return sheet;
	}
}
