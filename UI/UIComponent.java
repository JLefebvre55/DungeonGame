package UI;


import Main.Engine;

/**
 * Abstract superclass for all UI components. Essentially a Renderable without an image.
 * @author jaydenlefebvre
 *
 */
public abstract class UIComponent implements Engine{
	
	protected double x, y;
	
	protected UIComponent(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	
	

}
