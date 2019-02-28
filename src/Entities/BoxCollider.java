package Entities;

import java.awt.Rectangle;

import Graphics.FrameRenderer;
import Graphics.Renderable;
import Main.Engine;
import UI.FollowRenderable;

/**
 * Box collider class
 * @author jaydenlefebvre
 *
 */
public class BoxCollider implements FollowRenderable, Engine{

	private Rectangle box;
	private double x = 0.0, y = 0.0, dx, dy; //TOP LEFT
	private Renderable e;
	private Direction direction;
	private boolean isSolid;

	/**
	 * For copying another collider, used especially in tile instantiation of enums
	 * @param other
	 * @param e
	 */
	public BoxCollider(BoxCollider other, Renderable e) {
		this(e, other.dx, other.dy, (int)other.getBox().getWidth(), (int)other.getBox().getHeight(), other.direction, other.isSolid);
	}
	
	/**
	 * For new DIRECTIONAL SOLID collider construction
	 * @param e - Renderable to exist around
	 * @param dx - Offset from renderable x
	 * @param dy - Offset from renderable y
	 * @param width - width of collider
	 * @param height - height of collider
	 * @param direction - Direction 
	 * @param isSolid
	 */
	public BoxCollider(Renderable e, double dx, double dy, int width, int height, Direction direction, boolean isSolid) {
		this.dx = dx;
		this.dy = dy;
		this.e = e;
		this.box = new Rectangle(0, 0, width, height);
		this.direction = direction;
		this.isSolid = isSolid;
	}
	
	/**
	 * 
	 * @param e - Object to stick to
	 * @param dx - Difference in x
	 * @param dy - Difference in y
	 * @param width of collider
	 * @param height of collider
	 * @param isSolid - Is a solid collider? I.e. bounceoff tilews
	 */
	public BoxCollider(Renderable e, double dx, double dy, int width, int height) {
		this(e, dx, dy, width, height, Direction.NONE, false);
	}

	@Override
	public void followRenderable(Renderable e, double dx, double dy) {
		this.x = e.getX()+dx;
		this.y = e.getY()+dy;
		box = new Rectangle((int)x, (int)y, box.width, box.height);
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderCollider(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		followRenderable(e, dx, dy);
	}
	
	public Renderable getRenderable() {
		return e;
	}
	
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	
	public Rectangle getBox() {
		return box;
	}
	
	/**
	 * Checks for intersection
	 * @param other
	 * @return
	 */
	public boolean collidesWith(BoxCollider other) {
		return box.intersects(other.getBox());
	}
	
	public Direction getDirection() {
		return direction;
	}
	
}
