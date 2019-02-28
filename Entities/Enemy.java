package Entities;

import Graphics.FrameRenderer;

/**
 * An enemy
 * @author jaydenlefebvre
 *
 */
public class Enemy extends Entity{

	protected Player target;
	private static final int FOLLOWDISTANCE = 20;
	private Enemies type;
	private long lastHit;

	/**
	 * Enemy constructor
	 * @param name
	 * @param level
	 * @param maxHP
	 * @param startx
	 * @param starty
	 * @param speed
	 */
	public Enemy(Enemies type, int level, int startx, int starty, Player player) {
		this(type.toString(), type, startx, starty, player);
	}

	/**
	 * Same, with custom name
	 * @param name
	 * @param type
	 * @param level
	 * @param maxHP
	 * @param startx
	 * @param starty
	 * @param player
	 */
	public Enemy(String name, Enemies type, int startx, int starty, Player player) {
		super(name, new EntitySheet(type.getUrl(), type.getSpritewidth(), type.getSpriteheight()), type.getAnimationTime(), type.getHP()+(int)(Math.random()*-2+1), (type.isBoss() ? (int)(Math.random()*4+10) : (int)(Math.random()*5+1)), startx, starty, type.getSpeed());
		this.type = type;
		this.target = player;
	}

	public void setTarget(Player player) {
		this.target = player;
	}

	public BoxCollider getCollider() {
		return collider;
	}

	public boolean hasTarget() {
		// TODO Auto-generated method stub
		return !(target == null);
	}

	public void fixedUpdate() {
		move();
		super.fixedUpdate();
	}

	public void update(FrameRenderer screen) {
		screen.renderObject(this);
		super.update(screen);
	}

	public void move() {
		// TODO Auto-generated method stub
		double angle;
		if(target.getX() == x) {
			angle = 0;
		} else {
			angle = Math.atan((target.getY()-y)/(target.getX()-x));
			if(target.getX() < x) {
				angle+=Math.PI;
			}
		}

		//SET MVMT DIRECTION
		if(angle < Math.PI/4 && angle > -Math.PI/4) {
			direction = Direction.RIGHT;
		} else if(angle >= Math.PI/4 && angle <= 3*Math.PI/4) {
			direction = Direction.DOWN;
		} else if(angle > 3*Math.PI/4 || angle < -3*Math.PI/4) {
			direction = Direction.LEFT;
		} else {
			direction = Direction.UP;
		}

		if(distanceTo(target) > FOLLOWDISTANCE+speed){
			move(angle, speed);
		} else if( distanceTo(target) < FOLLOWDISTANCE-speed){
			move(angle+Math.PI, speed);
		} else {
			direction = Direction.NONE;
			if(System.currentTimeMillis() - lastHit >= type.getCooldown()) {
				attack(target);
				lastHit = System.currentTimeMillis();
			}
		}
	}

	private void attack(Entity player) {
		player.takeDamage(getDmg());
		//Knockback
		player.move(angleTo(player), 3);
	}

	private int getDmg() {
		return (int)(Math.random()*type.getDmg()+1);
	}
}
