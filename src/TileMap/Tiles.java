package TileMap;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.BoxCollider;


public enum Tiles {
	TOPEDGE(0, 7, TileColliders.TOPEDGE), 
	LEFTEDGE(3, 7, TileColliders.LEFTEDGE), 
	RIGHTEDGE(1, 7, TileColliders.RIGHTEDGE), 
	BOTTOMEDGE(2, 7, TileColliders.BOTTOMEDGE),
	SPACE(8, 5),
	TOPLEFT(5, 1, new TileColliders[]{TileColliders.TOPEDGE, TileColliders.LEFTEDGE}),
	TOPRIGHT(6, 1, new TileColliders[]{TileColliders.TOPEDGE, TileColliders.RIGHTEDGE}),
	BOTTOMLEFT(4, 1, new TileColliders[]{TileColliders.BOTTOMEDGE, TileColliders.LEFTEDGE}),
	BOTTOMRIGHT(7, 1, new TileColliders[]{TileColliders.BOTTOMEDGE, TileColliders.RIGHTEDGE}),
	HANGINGEDGE(9, 1);

	private boolean hasVariants;
	private int numVariants;
	private int row;
	private static TileMap tilemap = new TileMap("Resources/SpriteSheets/Dungeon.png");
	private boolean hasColliders;
	private ArrayList<BoxCollider> colliders = new ArrayList<BoxCollider>();


	/**
	 * Constructor for has collider(s)
	 * @param row
	 * @param numVariants
	 * @param colliders
	 * @param colliderDirection
	 */
	private Tiles(int row, int numVariants, TileColliders [] colliders) {
		this.row = row;
		this.numVariants = numVariants;
		this.hasVariants = numVariants > 1;
		this.hasColliders = true;
		
		//ADD EACH SINGLE BOX COLLIDER TO LIST
		for(TileColliders i : colliders) {
			this.colliders.add(i.getCollider());
		}
	}
	
	private Tiles(int row, int numVariants, TileColliders collider) {
		this.row = row;
		this.numVariants = numVariants;
		this.hasVariants = numVariants > 1;
		this.hasColliders = true;
		this.colliders.add(collider.getCollider());
	}

	/**
	 * Constructor for no colliders
	 * @param row
	 * @param numVariants
	 */
	private Tiles(int row, int numVariants) {
		this.row = row;
		this.numVariants = numVariants;
		this.hasVariants = numVariants > 1;
		this.hasColliders = false;
	}

	public BufferedImage getImage() {
		int variant;
		if(hasVariants) {
			variant = (int)(Math.random()*numVariants);
		} else {
			variant = 0;
		}
		return tilemap.getTile(row, variant);
	}
	
	

	/**
	 * @return if this tile type has a collider
	 */
	public boolean hasColliders() {
		return hasColliders;
	}

	/**
	 * @return the boxes of this type's colliders
	 */
	public ArrayList<BoxCollider> getColliders() {
		return colliders;
	}

	@Override
	public String toString() {
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}

}
