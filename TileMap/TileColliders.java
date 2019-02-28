package TileMap;

import Entities.BoxCollider;
import Entities.Direction;

public enum TileColliders {
	TOPEDGE(new BoxCollider(null, 0, 0, 16, 6, Direction.DOWN, true)), 
	LEFTEDGE(new BoxCollider(null, 0, 0, 6, 16, Direction.RIGHT, true)), 
	RIGHTEDGE(new BoxCollider(null, 10, 0, 6, 16, Direction.LEFT, true)), 
	BOTTOMEDGE(new BoxCollider(null, 0, 10, 16, 6, Direction.UP, true));

	private BoxCollider collider;

	private TileColliders(BoxCollider collider) {
		this.collider = collider;
	}

	public BoxCollider getCollider() {
		return this.collider;
	}
}
