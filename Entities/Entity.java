package Entities;

import java.awt.image.BufferedImage;

import Animation.EntityAnimator;
import Graphics.FrameRenderer;
import Graphics.Layers;
import UI.NameText;

/**
 * Generic entity abstract superclass.
 * @author jaydenlefebvre
 *
 */
public abstract class Entity extends Graphics.Renderable{

	//Subclass Inheritance Only
	protected int hp, maxhp, gold;
	protected double speed;
	protected String name;
	protected Direction direction = Direction.NONE;
	protected EntitySheet sheet;
	protected NameText nt;
	protected BoxCollider collider;
	protected EntityAnimator animator;

	/**
	 * Entity superconstructor
	 * @param name
	 * @param sheet
	 * @param timebetween
	 * @param maxhp
	 * @param gold
	 * @param startx
	 * @param starty
	 * @param speed
	 */
	protected Entity(String name, EntitySheet sheet, int timebetween, int maxhp, int gold, double startx, double starty, double speed) {
		super(startx, starty, sheet.getDefaultSprite(), Layers.ENTITIES);
		this.speed = speed;
		this.name = name; 
		this.maxhp = maxhp; 
		this.gold = gold; 
		this.x = startx; 
		this.y = starty; 
		this.hp = maxhp; 
		this.sheet = sheet;
		nt = new NameText(this, 0, -5);
		animator = new EntityAnimator(timebetween, sheet, this);
		collider = new BoxCollider(this, 0, image.getHeight()-8, image.getWidth(), 8);
		//hpbar = new HPBar(this);
	}

	public void restoreHP() {
		hp = maxhp;
	}

	public void fixedUpdate() {
		nt.fixedUpdate();
		collider.fixedUpdate();
		image = animator.updateAnimation();
	}

	public void update(FrameRenderer screen) {
		nt.update(screen);
		screen.renderObject(this);
	}

	//Methods
	protected void dropGold(int drop) {
		gold -= drop;
	}

	public BufferedImage getImage() { return image; }

	/**
	 * Move an angle at a speed
	 * @param angle
	 * @param speed
	 */
	public void move(double angle, double speed) {
		// TODO Auto-generated method stub
		x+=Math.cos(angle)*speed;
		y+=Math.sin(angle)*speed;
	}

	/**
	 * @return the hp
	 */
	public int getHP() {
		return hp;
	}

	/**
	 * @return the maxhp
	 */
	public int getMaxHP() {
		return maxhp;
	}

	/**
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	public String toString() {
		return name;
	}

	@Override
	public Layers getLayer() {
		return this.layer;
	}

	public Direction getDirection() {
		return direction;
	}

	public void takeDamage(int dmg) {
		hp-=dmg;
	}

	/**
	 * Yay pythagoras
	 * @param e
	 * @return
	 */
	public double distanceTo(Entity e) {
		return Math.sqrt(Math.pow(x - e.getX(), 2) + Math.pow(y - e.getY(), 2));
	}

	/**
	 * Yay inverse tangent
	 * @param e
	 * @return
	 */
	public double angleTo(Entity e) {
		double angle;
		if(e.getX() == x) {
			angle = 0;
		} else {
			angle = Math.atan((e.getY()-y)/(e.getX()-x));
			if(e.getX() < x) {
				angle+=Math.PI;
			}
		}
		return angle;
	}

}
