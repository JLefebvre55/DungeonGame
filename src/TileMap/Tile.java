package TileMap;

import java.util.ArrayList;

import Entities.BoxCollider;
import Graphics.FrameRenderer;
import Graphics.Layers;
import Graphics.Renderable;

public class Tile extends Renderable {

	private Tiles type;
	private int tilex, tiley;
	private ArrayList<BoxCollider> colliders = new ArrayList<BoxCollider>();
	private boolean hasColliders;

	/**
	 * Constructs tile
	 * @param type - Type of tile (enum)
	 * @param x - TILE-wise x coordinate
	 * @param y - TILE-wise y coordinate
	 */
	public Tile(Tiles type, int x, int y) {
		super(x*16, y*16, type.getImage(), Layers.TILES);
		this.type = type;
		this.tilex = x;
		this.tiley = y;
		this.hasColliders = type.hasColliders();
		init();
	}

	/**
	 * Initializes tile image and colliders
	 */
	private void init() {
			image = type.getImage();
		
		
		if(hasColliders) {
			for(BoxCollider i : type.getColliders()) {
				colliders.add(new BoxCollider(i, this));
			}
		}
	}

	//UPDATES

	/**
	 * Sends this tile to screen to be rendered
	 */
	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderTile(this);
	}


	//GETTERS

	/**
	 * TILE-wise x
	 */
	public int getTileX() {
		return tilex;
	}


	/**
	 * TILE-wise y
	 */
	public int getTileY() {
		return tiley;
	}

	@Override
	public double getX() {
		return tilex*16;
	}

	@Override
	public double getY() {
		return tiley*16;
	}

	@Override
	public String toString() {
		return this.type.name().charAt(0)+this.type.name().substring(1).toLowerCase()+" tile";
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub

		for(BoxCollider c : colliders) {
			c.fixedUpdate();
		}
	}

	/**
	 * @return the colliders
	 */
	public ArrayList<BoxCollider> getColliders() {
		return colliders;
	}

	public Tiles getType() {
		return type;
	}
}
