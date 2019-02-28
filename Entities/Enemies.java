package Entities;

/**
 * Enum for all enemy variations, bosses and normal
 * @author jaydenlefebvre
 *
 */
public enum Enemies {
	SKELETON(10, 0.05, 4, 1000, "Resources/SpriteSheets/Entities/Skeleton.png", 13, 17, 80, false),
	GOBLIN(8, 0.07, 2, 500, "Resources/SpriteSheets/Entities/Goblin.png", 16, 17, 70, false),
	SHROOMER(5, 0.1, 1, 400, "Resources/SpriteSheets/Entities/Shroomer.png", 16, 16, 50, false),
	ANCIENT(30, 0.01, 10, 2000, "Resources/SpriteSheets/Entities/Ancient.png", 30, 32, 250, true),
	GHOUL(20, 0.05, 6, 1200, "Resources/SpriteSheets/Entities/Ghoul.png", 16, 25, 100, true);
	
	//ALL enemy variables
	private int hp, spritewidth, spriteheight, dmg;
	private long cooldown;
	private double speed;
	private String url;
	private boolean isBoss;
	private int animationTime;
	
	private Enemies(int hp, double speed, int dmg, long cooldown, String url, int spritewidth, int spriteheight, int animationTime, boolean isBoss) {
		this.hp = hp;
		this.spritewidth = spritewidth;
		this.spriteheight = spriteheight;
		this.cooldown = cooldown;
		this.dmg = dmg;
		this.speed = speed;
		this.url = url;
		this.isBoss = isBoss;
		this.animationTime = animationTime;
		
	}
	
	public int getAnimationTime() {
		return animationTime;
	}

	/**
	 * Gets random non-boss enemy
	 * @return
	 */
	public static Enemies random() {
		Enemies i;
		do{
			i = Enemies.values()[(int)(Math.random()*Enemies.values().length)];
		}while(i.isBoss == true);
		return i;
	}
	
	/**
	 * Returns camelcase name
	 */
	public String toString() {
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}

	/**
	 * @return the hp
	 */
	public int getHP() {
		return hp;
	}

	/**
	 * @return the spritewidth
	 */
	public int getSpritewidth() {
		return spritewidth;
	}

	/**
	 * @return the spriteheight
	 */
	public int getSpriteheight() {
		return spriteheight;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the dmg
	 */
	public int getDmg() {
		return dmg;
	}

	/**
	 * @return the cooldown
	 */
	public long getCooldown() {
		return cooldown;
	}

	public boolean isBoss() {
		return isBoss;
	}

	/**
	 * Gets a random boss
	 * @return
	 */
	public static Enemies randomBoss() {
		// TODO Auto-generated method stub
		Enemies i;
		do{
			i = Enemies.values()[(int)(Math.random()*Enemies.values().length)];
		}while(i.isBoss == false);
		return i;
		
	}
	
	
	
}
