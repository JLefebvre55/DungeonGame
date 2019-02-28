package Entities;

import Actions.Attack;
import Actions.Attacks;
import Animation.EntityAnimator;
import Graphics.FrameRenderer;
import Input.Keyboard;

/**
 * Player entity class.
 * @author jaydenlefebvre
 *
 */
public class Player extends Entity {

	//CONSTANTS
	final static int STARTHP = 20;
	final static double STARTSPEED = 0.15;
	final static int TIMEBETWEEN = 80;

	//PER PLAYER
	private PlayerClass pc;
	private Keyboard keyboard;
	private Direction directionX = Direction.NONE, directionY = Direction.NONE;
	private Attack attack;
	public boolean isActive = true;

	/**
	 * New character creation. Starts at level 1, with defined health and zero gold
	 * @param pc - Class of player
	 * @param name
	 * @param keyboard
	 * @param startx - Start x pos
	 * @param starty - Start y pos
	 */
	public Player(PlayerClass pc, String name, Keyboard keyboard, int startx, int starty) {
		this(pc, name, STARTHP, 0, keyboard, startx, starty);
	}

	/**
	 * Null character
	 * @param keyboard - keyboard for input
	 * @param startx - starting x pos
	 * @param starty - starting y pos
	 */
	public Player(Keyboard keyboard, int startx, int starty) {
		this(PlayerClass.NONE, "???", keyboard, startx, starty);
	}

	/**
	 * Character with all aspects
	 * @param pc
	 * @param name
	 * @param level
	 * @param maxhp
	 * @param gold
	 * @param keyboard
	 * @param startx
	 * @param starty
	 */
	public Player(PlayerClass pc, String name, int maxhp, int gold, Keyboard keyboard, int startx, int starty) {
		super(name, new EntitySheet(pc.getImageURL(), 16, 18), TIMEBETWEEN, maxhp, gold, startx, starty, STARTSPEED);
		this.pc = pc;
		this.keyboard = keyboard;
		init();
	}

	/**
	 * Initialize all the things not initialized in super
	 */
	private void init() {
		sheet = new EntitySheet(pc.getImageURL(), 16, 18);
		attack = new Attack(Attacks.SWORD, 2, 5, this);
	}

	@Override
	public void update(FrameRenderer screen) {
		
		// TODO Auto-generated method stub
		attack.update(screen);
		super.update(screen);
	}

	@Override
	public void fixedUpdate() {
		move();
		// TODO Auto-generated method stub
		attack.fixedUpdate();
		super.fixedUpdate();
	}

	/**
	 * Does keyboard input and moves
	 */
	public void move() {
		//MOVEMENT DIRECTION PAIRS WITH DOUBLES CANCELLATION
		if(!(keyboard.isMoveLeft() && keyboard.isMoveRight())) {
			if(keyboard.isMoveLeft()) {
				x-=speed;
				directionX = Direction.LEFT;
			} else if(keyboard.isMoveRight()) {
				x+=speed;
				directionX = Direction.RIGHT;
			} else {
				directionX = Direction.NONE;
			}
		}
		if(!(keyboard.isMoveUp() && keyboard.isMoveDown())) {
			if(keyboard.isMoveUp()) {
				y-=speed;
				directionY = Direction.UP;
			} else if(keyboard.isMoveDown()) {
				y+=speed;
				directionY = Direction.DOWN;
			} else {
				directionY = Direction.NONE;
			}
		}
		if(keyboard.isCastRight()) {

			attack.setDirection(Direction.RIGHT);

		} else if(keyboard.isCastLeft()) {

			attack.setDirection(Direction.LEFT);

		} else if(keyboard.isCastUp()) {

			attack.setDirection(Direction.UP);

		} else if(keyboard.isCastDown()) {

			attack.setDirection(Direction.DOWN);

		} else {
			attack.reset();
		}
	}

	/**
	 * Moves in a direction. Used by collider bounce
	 * @param direction
	 */
	public void move(Direction direction) {
		//MOVEMENT DIRECTION PAIRS WITH DOUBLES CANCELLATION
		switch(direction) {
		case DOWN:
			y+=speed;
			break;
		case UP:
			y-=speed;
			break;
		case LEFT:
			x-=speed;
			break;
		case RIGHT:
			x+=speed;
			break;
		case NONE:
			break;
		}
	}

	public BoxCollider getCollider() {
		return collider;
	}

	public EntityAnimator getAnimator() {
		return animator;
	}

	public void addGold(int gold) {
		this.gold += gold;
	}

	public Direction getDirectionX() {
		return directionX;
	}

	public Direction getDirectionY() {
		return directionY;
	}

	@Override
	public Direction getDirection() {
		return (directionY != Direction.NONE ? directionY : directionX);
	}

	public Attack getAttack() {
		return attack;
	}
}
