package Graphics;

import java.awt.image.BufferedImage;

/**
 * Superclass for ALL graphical objects
 * @author jaydenlefebvre
 *
 */
public abstract class Renderable implements Main.Engine{
	
	protected double x, y;
	
	protected BufferedImage image;
	
	protected Layers layer;
	
	protected Renderable(double x, double y, BufferedImage image, Layers layer) {
		this.x = x;
		this.y = y;
		this.layer = layer;
		this.image = image;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Layers getLayer() {
		return layer;
	}

	public BufferedImage getImage() {
		return image;
	}

}
