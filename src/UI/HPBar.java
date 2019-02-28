package UI;

import javax.swing.JProgressBar;

import Entities.Entity;
import Graphics.FrameRenderer;
import Graphics.Renderable;

/**
 * HP bar above entities. UNIMPLEMENTED FOR NOW, REPLACED BY ADDING HP TO NAMETEXT. DO NOT MARK!!!!!!!!!
 * @author jaydenlefebvre
 *
 */
public class HPBar extends UIComponent implements FollowRenderable{

	private Entity e;
	private JProgressBar hpbar;
	
	public HPBar(Entity e) {
		super(e.getX()+e.getImage().getWidth()/2, e.getY()+10);
		this.e = e;
		hpbar = new JProgressBar(0, e.getMaxHP());
		hpbar.setStringPainted(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderHPBar(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		hpbar.setValue(e.getHP());
	}

	@Override
	public void followRenderable(Renderable o, double dx, double dy) {
		// TODO Auto-generated method stub
		
	}
	
	public JProgressBar getProgressBar() {
		return hpbar;
	}

}
