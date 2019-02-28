package Graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Window object. Encompasses a JFrame to make it easier to use.
 * @author jaydenlefebvre
 *
 */
public class Window {
	public static JFrame frame;//JFrame
	private int width, height;//width,height of frame
	private Canvas canvas;//canvas to draw images on
	private String title;//title of frame

	/**
	 * Makes a window
	 * @param title
	 * @param width
	 * @param height
	 */
	public Window(String title, int width, int height){
		this.height = height;
		this.width = width;
		this.title = title;

		init();
	}

	/**
	 * @param 
	 * Initializes the JFrame and canvas components for the window that pops up when you run the game
	 */
	private void init() {
		frame = new JFrame(title);//makes JFrame object
		frame.setSize(width, height);//set height and width
		frame.setLocationRelativeTo(null);//set in mid of screen cause not relative to anything
		frame.setVisible(true);//frame is visible so we can see it
		frame.setResizable(false);//don't care if this is true or false. just didn't want to change size when playing. suggest not changing
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame when you press red x in top left

		canvas = new Canvas();//makes canvas object
		//sets min, max, and perferred size of the canvas
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);//not focusable//was playing around with this to test interactions

		frame.add(canvas);//add canvas to frame
		frame.pack();
	}

	//returns the canvas when called
	public Canvas getCanvas(){
		return canvas;
	}

	//returns the Jframe when called
	public JFrame getFrame(){
		return frame;
	}


}