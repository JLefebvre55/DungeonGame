package UI;

import java.awt.Font;

import Graphics.FrameRenderer;
import Main.Engine;

/**
 * A renderable text object
 * @author jaydenlefebvre
 *
 */
public class TextObject extends UIComponent implements Engine{

	protected String text;
	protected Font font = new Font("Serif", Font.PLAIN, 10);
	protected boolean isVisible, centered;
	
	/**
	 * Text object
	 * @param x
	 * @param y
	 * @param text
	 */
	public TextObject(double x, double y, String text, boolean centered) {
		super(x, y);
		this.text = text;
		this.isVisible = true;
		this.centered = centered;
		// TODO Auto-generated constructor stub
	}
	
	public Font getFont() {
		return font;
	}
	
	public boolean isCentered() {
		return centered;
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		if(isVisible) {
			screen.renderText(this);
		}
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	public String getText() {
		return text;
	}
	
	
	
	
}
