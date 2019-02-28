package Actions;


import java.awt.image.BufferedImage;

import Animation.AttackAnimator;
import Entities.Direction;
import Entities.Entity;
import Graphics.FrameRenderer;
import Graphics.Renderable;
import Item.SpriteSheet;
import UI.FollowRenderable;

/**
 * Is an attack the player can do
 * @author jaydenlefebvre
 *
 */
public class Attack implements Main.Engine, FollowRenderable{

	private double range;
	private int maxdmg, mindmg, cooldown;
	public long lastHit;
	private AttackAnimator animator;
	private SpriteSheet sheet;
	private Renderable e;
	private double x, y, centerx, centery;
	private BufferedImage image;
	private Direction direction;

	//How far away from player to draw the sprite 
	private static final int drawradius = 16;

	/**
	 * Creates an attack
	 * @param range in px
	 * @param mindmg
	 * @param maxdmg
	 * @param cooldown in ms
	 */
	public Attack(int range, int mindmg, int maxdmg, int cooldown, Renderable e) {
		this.range = range;
		this.mindmg = mindmg;
		this.maxdmg = maxdmg;
		this.cooldown = cooldown;
		this.e = e;
		sheet = new SpriteSheet(Attacks.SWORD.getURL(), 10, 8);
		init();
	}
	
	/**
	 * Creates from attack enum
	 * @param atk
	 * @param mindmg
	 * @param maxdmg
	 * @param e
	 */
	public Attack(Attacks atk, int mindmg, int maxdmg, Renderable e) {
		this(atk.getRange(), mindmg, maxdmg, atk.getCooldown(), e);
	}

	private void init() {
		animator = new AttackAnimator(40, 0, sheet.getSprites());
		lastHit = System.currentTimeMillis();
		centerx = 5;
		centery = 5;
	}

	public void hitEntity(Entity e) {
		e.takeDamage(getDamage());
	}

	public long getLastHit() {
		return lastHit;
	}

	public double getRange() {
		return range;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setLastHit(long lasthit) {
		lastHit = lasthit;
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderAttack(this, direction, centerx + e.getX() + (direction == Direction.RIGHT ? drawradius : direction == Direction.LEFT ? -drawradius : 0), centery+e.getY() + (direction == Direction.DOWN ? drawradius : direction == Direction.UP ? -drawradius : 0));
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		followRenderable(e, 0, 0);
		image = animator.updateAnimation();
	}

	@Override
	public void followRenderable(Renderable o, double dx, double dy) {
		// TODO Auto-generated method stub
		this.x = o.getX() + dx;
		this.y = o.getY() + dy;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void reset() {
		direction = Direction.NONE;
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

	/**
	 * @return the centerx
	 */
	public double getCenterx() {
		return centerx;
	}

	/**
	 * @return the centery
	 */
	public double getCentery() {
		return centery;
	}

	public Direction getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	public int getDamage() {
		return (int)(Math.random()*(maxdmg-mindmg)+mindmg);
	}

	public AttackAnimator getAnimator() {
		// TODO Auto-generated method stub
		return animator;
	}

}
