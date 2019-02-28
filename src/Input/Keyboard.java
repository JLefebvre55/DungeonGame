package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard for IO
 * @author jaydenlefebvre
 *
 */
public class Keyboard implements KeyListener{

	private boolean [] keys = new boolean[65536];//keys 
	private boolean castup, castdown, castleft, castright, moveup, movedown, moveleft, moveright, action;//boolean for if pressed or not


	/**
	 * Updates input
	 */
	public void updateInput(){
		//keys correspond with WASD keys or up, right, left, down, space keys
		castup = keys[KeyEvent.VK_UP];
		castdown = keys[KeyEvent.VK_DOWN];
		castleft = keys[KeyEvent.VK_LEFT];
		castright = keys[KeyEvent.VK_RIGHT];
		moveright = keys[KeyEvent.VK_D];
		moveleft = keys[KeyEvent.VK_A];
		moveup = keys[KeyEvent.VK_W];
		movedown = keys[KeyEvent.VK_S];
		action = keys[KeyEvent.VK_SPACE];
	}

	/**
	 * A key has been pressed down, is called by KeyListener attached to window
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println(KeyEvent.getKeyText(e.getKeyCode())+" key pressed");
		//keys been pressed so its true
	}

	/**
	 * A key has been released, is called by KeyListener attached to window
	 */
	public void keyReleased(KeyEvent e) {
		//released so key pressed is false
		keys[e.getKeyCode()] = false;
		System.out.println(KeyEvent.getKeyText(e.getKeyCode())+" key released");
	}

	//Needed by interface
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	//GETTERS

	/**
	 * @return the castup
	 */
	public boolean isCastUp() {
		return castup;
	}

	/**
	 * @return the castdown
	 */
	public boolean isCastDown() {
		return castdown;
	}

	/**
	 * @return the castleft
	 */
	public boolean isCastLeft() {
		return castleft;
	}

	/**
	 * @return the castright
	 */
	public boolean isCastRight() {
		return castright;
	}

	/**
	 * @return the moveup
	 */
	public boolean isMoveUp() {
		return moveup;
	}

	/**
	 * @return the movedown
	 */
	public boolean isMoveDown() {
		return movedown;
	}

	/**
	 * @return the moveleft
	 */
	public boolean isMoveLeft() {
		return moveleft;
	}

	/**
	 * @return the moveright
	 */
	public boolean isMoveRight() {
		return moveright;
	}

	/**
	 * @return the action
	 */
	public boolean isAction() {
		return action;
	}

}
