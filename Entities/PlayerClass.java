package Entities;

/**
 * Enumerates player classes with abilities.
 * @author jaydenlefebvre
 *
 */
public enum PlayerClass {

	//ENUMS
	NONE("Resources/SpriteSheets/Entities/Warrior.png"), 
	WARRIOR(false, true, "Resources/SpriteSheets/Entities/Warrior.png"), 
	MAGE(true, false, "Resources/SpriteSheets/Entities/Warrior.png"),
	SHAMAN(true, true, "Resources/SpriteSheets/Entities/Warrior.png");
	
	//VARS PER ENUM
	private boolean magic, melee;
	private String src;
	
	//ENUM CONSTRUCTORS
	private PlayerClass(boolean canUseMagic, boolean canUseMeleeWeapons, String src) {
		this.magic = canUseMagic;
		this.melee = canUseMeleeWeapons;
		this.src = src;
	}
	
	private PlayerClass(String src) {
		this(false, false, src);
	}

	/**
	 * Returns camelcase name
	 */
	public String toString() {
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}
	
	protected boolean canUseMagic() {
		return magic;
	}
	
	protected boolean canUseMelee() {
		return melee;
	}

	public String getImageURL() {
		// TODO Auto-generated method stub
		return src;
	}
}
