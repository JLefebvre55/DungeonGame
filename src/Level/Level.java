package Level;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import Entities.*;
import Graphics.FrameRenderer;
import Item.Coin;
import Main.Engine;
import TileMap.Tile;
import TileMap.TileMap;
import TileMap.Tiles;

/**
 * Level object. Contains tiles, coins, enemies, player, does levelgen, wave gen, etc.
 * @author jaydenlefebvre
 *
 */
public class Level implements Engine{

	private ArrayList<Tile> tiles;
	private ArrayList<Coin> coins;
	private ArrayList<Enemy> enemies;
	private Player player;
	private int maxx, minx, maxy, miny;
	public int wave;
	private int enemylevel = 1;
	private int bosslevel = 1;
	private boolean running = true;

	public String levelname;

	/**
	 * For level init
	 * @param levelname
	 * @param player
	 * @param other
	 */
	public Level(Player player, Level other) {
		this.player = player;
		add(other);
		for(Enemy i : enemies) {
			i.setTarget(player);
		}
	}

	/**
	 * For levelgen
	 * @param levelname
	 * @param tiles
	 * @param coins
	 */
	public Level(String levelname, ArrayList<Tile> tiles, ArrayList<Coin> coins, ArrayList<Enemy> enemies, int maxx, int minx, int maxy, int miny) {
		this.tiles = tiles;
		this.coins = coins;
		this.enemies = enemies;
		this.levelname = levelname;
		this.maxx = maxx;
		this.minx = minx;
		this.maxy = maxy;
		this.miny = miny;
	}

	@Override
	public String toString() {
		return levelname;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public ArrayList<Coin> getCoins() {
		return coins;
	}

	public String getLevelname() {
		return levelname;
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}

	/**
	 * Adds another level's tiles/items/entities to this
	 * @param other
	 */
	public void add(Level other) {
		tiles = other.getTiles();
		coins = other.getCoins();
		enemies = other.getEnemies();
		levelname = other.getLevelname();
		maxx = other.maxx;
		minx = other.minx;
		maxy = other.maxy;
		miny = other.miny;
	}


	@Override
	public void update(FrameRenderer screen) {

		//Render tiles
		for(int i = 0; i < tiles.size(); i++) {
			tiles.get(i).update(screen);
		}

		//Coins
		for(Coin i : coins) {
			i.update(screen);
		}

		//ENEMIES
		for(Enemy i : enemies) {
			i.update(screen);
		}

		//Render player
		player.update(screen);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub

		//Player
		player.fixedUpdate();

		//Coins
		for(Coin i : coins) {
			i.fixedUpdate();
		}

		//Tiles
		for(Tile i : tiles) {
			i.fixedUpdate();
		}

		//ENEMIES
		for(Enemy i : enemies) {
			if(!i.hasTarget()) {
				i.setTarget(player);
			}
			i.fixedUpdate();
		}

		//Do wall collisions
		doCollisions();

		//CHECK FOR HITS - apologies. it's a process.
		
		//IF THE PLAYER IS ATTACKING
		if(player.getAttack().getDirection() != null && player.getAttack().getDirection() != Direction.NONE) {
			
			//IF THE PLAYER COOLDOWN HAS RESET
			if(System.currentTimeMillis() - player.getAttack().getLastHit() >= player.getAttack().getCooldown()) {
				
				//Do animation and set last hit, regardless of enemy in range or not. Swing even if you miss.
				player.getAttack().getAnimator().go();
				player.getAttack().setLastHit(System.currentTimeMillis());
				
				//For each enemy, if is in range AND within minimum and maximum of player attack angle, hit and do knockback of 10
				for(Enemy e : enemies) {
					if(player.distanceTo(e) <= player.getAttack().getRange() && player.getAttack().getDirection().getRadians() - Math.PI/4 < player.angleTo(e) && player.getAttack().getDirection().getRadians() + Math.PI/4 > player.angleTo(e)) {
						player.getAttack().hitEntity(e);

						//KNOCKBACK
						e.move(player.angleTo(e), 10);
						//break;
						System.out.println("STAB! The player hit a/an "+e);
					}
				}
			}
		}

		//Check enemy deaths and drop coins
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getHP() <=0) {
				for(int x = 0; x < enemies.get(i).getGold(); x++) {
					coins.add(new Coin(enemies.get(i).getX() + (Math.random()*-10 + 5), enemies.get(i).getY() + (Math.random()*-10 + 5)));
				}
				System.out.println("The player killed a/an "+enemies.get(i));
				enemies.remove(i);
			}
		}

		//Player death game over
		if(player.getHP() <= 0) {
			running = false;
		}
	}

	public boolean isRunning() {
		return running;
	}

	/**
	 * Renders all colliders. Used ONLY for debugging
	 * @param screen
	 */
	public void renderColliders(FrameRenderer screen) {
		for(Tile i : tiles) {
			for(BoxCollider c : i.getColliders()) {
				c.update(screen);
			}
		}

		if(player.isActive) {
			player.getCollider().update(screen);
		}

		for(Coin i : coins) {

			i.getCollider().update(screen);

		}

		for(Enemy e : enemies) {
			e.getCollider().update(screen);
		}
	}

	/**
	 * Do all collisions
	 */
	private void doCollisions() {
		//Player and enemies w/ Tiles
		for(Tile i : tiles) {
			for(BoxCollider c : i.getColliders()) {
				doColliderBounce(c, player);
				for(Enemy e : enemies) {
					doColliderBounce(c, e);
				}
			}
		}

		//Coin pickup
		for(int i = 0; i < coins.size(); i++) {
			if(player.getCollider().collidesWith(coins.get(i).getCollider())) {
				coins.remove(i);
				player.addGold(1);
				System.out.println("Picked up a coin. Ka-ching $");
			}
		}
	}

	/**
	 * Bounce off collider
	 * @param c - Wall to bounce off
	 * @param e - Doing the bouncing
	 */
	private void doColliderBounce(BoxCollider c, Entity e) {
		if(player.getCollider().collidesWith(c)) {
			//if(player.getDirectionX().inverse().equals(c.getDirection()) || player.getDirectionY().inverse().equals(c.getDirection())) {
			player.move(c.getDirection());
			System.out.println("Boing! Bounced off a wall");
			//}
		}
	}

	/**
	 * Generates enemies for this wave
	 */
	public void makeWave() {
		if(wave % 10 == 0) {
			//BOSS ROUND
			Point sp = Level.getSpawnPoint(maxx, minx, maxy, miny);
			enemies.add(new Enemy(Enemies.randomBoss(), bosslevel, sp.x, sp.y, player));
		} else {

			int x = wave/2+1;
			for(; x > 0; x--) {
				Point sp = Level.getSpawnPoint(maxx, minx, maxy, miny);
				enemies.add(new Enemy(Enemies.random(), enemylevel, sp.x, sp.y, player));
			}
		}
	}

	/**
	 * Gets a random spawnpoint along the wall edge for enemies
	 * @param maxx
	 * @param minx
	 * @param maxy
	 * @param miny
	 * @return point
	 */
	private static Point getSpawnPoint(int maxx, int minx, int maxy, int miny) {
		int x = (int) (Math.random()*4);
		if(x == 0) {		//TOP
			return new Point((int)(Math.random()*(maxx-minx)+minx), miny);
		} else if(x == 1) { //RIGHT
			return new Point(maxx, (int)(Math.random()*(maxy-miny)+miny));
		} else if(x == 2) { //BOTTOM
			return new Point((int)(Math.random()*(maxx-minx)+minx), maxy);
		} else {			//LEFT
			return new Point(minx, (int)(Math.random()*(maxy-miny)+miny));
		}
	}

	/**
	 * Creates box-type level
	 * @param screenwidth
	 * @param screenheight
	 * @param width in tiles
	 * @param height in tiles
	 * @param numcoins
	 * @return
	 */
	public static Level generateBox(int screenwidth, int screenheight, int width, int height, int numcoins) {

		ArrayList<Tile> tiles = new ArrayList<Tile>();
		ArrayList<Coin> coins = new ArrayList<Coin>();
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();

		//BOX CENTER AT SCREEN CENTER; ERGO ORIGIN IS AS FOLLOWS 
		int x1t = (screenwidth/TileMap.RES)/2 - width/2;
		int y1t = (screenheight/TileMap.RES)/2 - height/2;

		//CORNERS
		tiles.add(new Tile(Tiles.TOPLEFT, x1t, y1t));
		tiles.add(new Tile(Tiles.TOPRIGHT, x1t+width, y1t));
		tiles.add(new Tile(Tiles.BOTTOMLEFT, x1t, y1t+height));
		tiles.add(new Tile(Tiles.BOTTOMRIGHT, x1t+width, y1t+height));

		//Edges
		for(int x = x1t+1; x < x1t+width; x++) {
			tiles.add(new Tile(Tiles.TOPEDGE, x, y1t));
		}
		for(int x = x1t+1; x < x1t+width; x++) {
			tiles.add(new Tile(Tiles.BOTTOMEDGE, x, y1t+height));
		}
		for(int y = y1t+1; y < y1t+height; y++) {
			tiles.add(new Tile(Tiles.LEFTEDGE, x1t, y));
		}
		for(int y = y1t+1; y < y1t+height; y++) {
			tiles.add(new Tile(Tiles.RIGHTEDGE, x1t+width, y));
		}

		//FILL
		for(int x = x1t+1; x < x1t+width; x++) {
			for(int y = y1t+1; y < y1t+height; y++) {
				tiles.add(new Tile(Tiles.SPACE, x, y));
			}
		}

		//HANGING EDGE
		for(int x = x1t; x <= x1t+width; x++) {
			tiles.add(new Tile(Tiles.HANGINGEDGE, x, y1t+height+1));
		}

		Rectangle renderSpace = new Rectangle(screenwidth/2-width/2*16+6, screenheight/2-height/2*16+6, width*16-6, height*16-6);

		//COINS
		for(int i = 0; i < numcoins; i++) {
			coins.add(new Coin((int)(Math.random()*renderSpace.width+renderSpace.x), (int)(Math.random()*renderSpace.height+renderSpace.y)));
		}


		//Make level
		return new Level("Level 1", tiles, coins, enemies, (x1t+width)*16-10, (x1t)*16+6, (y1t+height)*16-10, (y1t)*16+6);
	}

	public int getWave() {
		// TODO Auto-generated method stub
		return wave;
	}

	public void gameOver() {
		// TODO Auto-generated method stub
		player.isActive = false;
	}



}
