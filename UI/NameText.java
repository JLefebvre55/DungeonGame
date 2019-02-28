package UI;


import Entities.Entity;
import Graphics.FrameRenderer;
import Graphics.Renderable;

public class NameText extends TextObject implements FollowRenderable{
	
	private double dx, dy;
	private Entity following;
	
	/**
	 * Entity title name text constructor
	 * @param e - Entity to hover over
	 * @param dx - delta x from entity
	 * @param dy - delta y from entity
	 */
	public NameText(Entity e, int dx, int dy) {
		super(e.getX()+dx, e.getY()+dy, e.toString(), true);
		this.dx = dx;
		this.dy = dy;
		this.following = e;
		//game.registerRenderable(this, Layers.UI);
	}
	
	@Override
	public String toString() {
		return "NameText for Entity \""+following+"\"";
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderText(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		text = following+" "+following.getHP()+"/"+following.getMaxHP();
		followRenderable(following, dx, dy);
	}

	@Override
	public void followRenderable(Renderable e, double dx, double dy) {
		// TODO Auto-generated method stub
		this.x = e.getX() + dx;
		this.y = e.getY() + dy;
	}
}
