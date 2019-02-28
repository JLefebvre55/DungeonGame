package Graphics;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import Actions.Attack;
import Entities.BoxCollider;
import Entities.Direction;
import TileMap.Tile;
import TileMap.TileMap;
import UI.HPBar;
import UI.TextObject;

/**
 * Renderer for screen frames. ALL update calls use this.
 * @author jaydenlefebvre
 *
 */
public class FrameRenderer {
	public int width, height;
	private BufferedImage frame;
	private Graphics2D frameg;
	private static Color bg = new Color(33, 30, 39);

	/**
	 * Makes a renderer
	 * @param width in px
	 * @param height in px
	 */
	public FrameRenderer(int width, int height) {
		this.width = width;
		this.height = height;
		this.frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		init();
	}

	/**
	 * Initialize frame
	 */
	private void init(){
		//Graphics object to draw on frame image
		frameg = frame.createGraphics();


		//BACKGROUND MANAGEMENT
		frameg.setColor(bg);
		frameg.fillRect(0, 0, width, height);

	}

	public void renderHPBar(HPBar hpbar) {
		//UNUSED FOR NOW - SEE HPBAR
	}

	/**
	 * Renders an image to a location on screen
	 * @param image
	 * @param x
	 * @param y
	 */
	public void renderImage(BufferedImage image, double x, double y) {
		frameg.drawImage(image, (int)x, (int)y, null);
	}

	/**
	 * Renders a collider as a green rectanlge. Only used in debugging
	 * @param collider
	 */
	public void renderCollider(BoxCollider collider) {
		frameg.setColor(Color.GREEN);
		frameg.draw(collider.getBox());
	}

	/**
	 * Renders a tile to frame
	 * @param tile - Tile to render
	 */
	public void renderTile(Tile tile) {
		frameg.drawImage(tile.getImage(), tile.getTileX()*TileMap.RES, tile.getTileY()*TileMap.RES, null);
	}

	/**
	 * Renders a textobject to screen. Centers if centered
	 * @param text
	 */
	public void renderText(TextObject text) {
		frameg.setFont(text.getFont());
		frameg.setColor(Color.WHITE);
		double x;
		if(text.isCentered()) {
			// Determine the X coordinate for the text
			x = text.getX() - frameg.getFontMetrics(text.getFont()).stringWidth(text.getText())/2;
		} else {
			x = text.getX();
		}
		frameg.drawString(text.getText(), (int)x, (int)text.getY());
	}

	/**
	 * Renders an attack to screen using direction and image rotation around x,y
	 * @param e
	 * @param dir
	 * @param x
	 * @param y
	 */
	public void renderAttack(Attack e, Direction dir, double x, double y) {
		if(e.getImage() != null && dir != null && dir != Direction.NONE) {
			AffineTransform tx = AffineTransform.getRotateInstance(dir.getRadians(), e.getCenterx(), e.getCentery());
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

			// Drawing the rotated image at the required drawing locations
			frameg.drawImage(op.filter(e.getImage(), null), (int)Math.round(x), (int)Math.round(y), null);
		}

	}

	/**
	 * Renders an object to frame
	 * @param e - Object to render
	 */
	public void renderObject(Renderable e) {
		frameg.drawImage(e.getImage(), (int)e.getX(), (int)e.getY(), null);

	}

	/**
	 * Get rendered frame
	 * @return rendered frame
	 */
	public BufferedImage getFrame() {
		return frame;
	}
}