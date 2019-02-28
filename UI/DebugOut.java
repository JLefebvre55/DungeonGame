package UI;

/**
 * Text object for generic debug to screen. Fades after 3 seconds.
 * @author jaydenlefebvre
 *
 */
public class DebugOut extends TextObject {

	private long lastUpdate;
	
	public DebugOut(double x, double y) {
		super(x, y, "", true);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stuff
		if(isVisible && System.currentTimeMillis() - lastUpdate > 3000) {
			isVisible = false;
		}
	}
	
	public void debugLog(String text) {
		this.text = text;
		isVisible = true;
		lastUpdate = System.currentTimeMillis();
	}
	
}
