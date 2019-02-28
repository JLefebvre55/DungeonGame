package Actions;

/**
 * Enum describes Attack variations
 * @author jaydenlefebvre
 *
 */
public enum Attacks {

	//PUNCH(2, 500, "", false, false),
	SWORD(50, 750, "Resources/SpriteSheets/Attacks/Sword.png", true, false);
	
	private int range, cooldown;
	private boolean melee, magic;
	private String url;
	
	Attacks(int range, int cooldown, String url, boolean melee, boolean magic){
		this.range = range;
		this.melee = melee;
		this.magic = magic;
		this.cooldown = cooldown;
		this.url = url;
	}
	
	public boolean requiresMelee() {
		return melee;
	}
	
	public boolean requiresMagic() {
		return magic;
	}
	
	
	/**
	 * Returns camelcase name
	 */
	public String toString() {
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @return the cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * @return the url
	 */
	public String getURL() {
		return url;
	}
	
	
	
	
}
