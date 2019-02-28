package Main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Entities.Player;
import Entities.PlayerClass;
import Graphics.FrameRenderer;
import Graphics.Window;
import Input.Keyboard;
import Level.Level;
import UI.CoinsText;
import UI.DebugOut;
import UI.UIComponent;

/**
 * Main game driver. Contains all window aspects and calls updates.
 * @author jaydenlefebvre
 *
 */
public class DungeonGame implements Runnable {

	//Graphics and window stuff
	private int scale = 3;
	//private static double aspectRatio = 16.0/9.0;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = screenSize.height;
	private int width = screenSize.width;
	private Window window; //Is JFrame
	static String title = "Dungeon Game";//title
	private BufferStrategy bs; 
	private ArrayList<UIComponent> uicomps = new ArrayList<UIComponent>();
	private DebugOut debuglog;
	private long waveEnd;

	//Thread stuff
	private Thread gameThread;
	private boolean running = false; //if game is running or not

	//IO and Entities
	private Keyboard keyboard;
	private Player player;
	public Level world;
	private int wave;


	//Level management
	private boolean countdownGoing;

	//STATIC VARIABLES
	private final static int FRAMELENGTH = 1000000; //Time between physics frames in nanoseconds
	private final static long NANOPERSEC = 1000000000;
	private final static int TARGETFPS = 70;
	private final static int STATICUPDATELENGTH = (int)(NANOPERSEC/TARGETFPS); //Fixed FPS. FOR TESTING


	/**
	 * Called by launcher. Starts thread
	 */
	public synchronized void start() {
		gameThread = new Thread(this);
		//Makes thread and calls run
		gameThread.start();
	}

	/**
	 * Called by Thread. Equivalent to main()
	 */
	@Override
	public void run() {
		init(); //Initializes entire game

		//Vars for fps and such
		long lastFixedUpdate = 0, lastUpdate = 0, lastSlowUpdate = 0;
		int fps = 0;

		//MAIN GAME BLOCK
		while(running) {
			if(System.nanoTime() - lastFixedUpdate >= FRAMELENGTH) { //Fixed physics update (movement, etc.)
				fixedUpdate();

				//FPS Stuff
				lastFixedUpdate = System.nanoTime();
			}
			if(System.nanoTime() - lastUpdate >= STATICUPDATELENGTH) {
				update(); //Frame update as often as possible

				//FPS Stuff
				fps = (int) (NANOPERSEC/(System.nanoTime()-lastUpdate));
				lastUpdate = System.nanoTime();
			}
			if(System.currentTimeMillis() - lastSlowUpdate >= 1000) {	//Update title text every second
				slowUpdate(fps);

				lastSlowUpdate = System.currentTimeMillis();
			}
		}
	}

	/**
	 * Called at a fixed rate. Updates ALLLLL IO, movement, animators, etc..
	 */
	private void fixedUpdate() {
		//IO
		keyboard.updateInput();

		//Movement UNIVERSAL
		world.fixedUpdate();

		//UI components
		for(UIComponent i : uicomps) {
			i.fixedUpdate();
		}
		
		//END OF GAME
		if(!world.isRunning()) {
			debuglog.debugLog("GAME OVER!");
			world.gameOver();
			update();
			update();
			update();
			running = false;
		}

		//Wave reset, does countdown
		if(world.getEnemies().size() == 0) {
			if(countdownGoing == false) {
				world.wave++;
				waveEnd = System.currentTimeMillis();
				countdownGoing = true;
			}
			if(System.currentTimeMillis()-waveEnd >= 4000) {
				countdownGoing = false;
			}
		}
		
		//Countdown display
		if(countdownGoing) {
			debuglog.debugLog(4-((System.currentTimeMillis()-waveEnd)/1000)+"...");
		}
		
		//Countdown done
		if(wave < world.getWave() && !countdownGoing) {
			wave = world.getWave();
			world.makeWave();
			debuglog.debugLog("Wave "+wave+" started!");
		}
		
		//If 50 gold collected, level up and restore HP
		if(player.getGold() >= 100) {
			player.addGold(-100);
			debuglog.debugLog("HP restored.");
			player.restoreHP();
		}
	}

	/**
	 * Called as often as possible, unless static FPS cap (see optional green comment code). Renders frames and puts them to screen.
	 */
	private void update() {
		FrameRenderer screen = new FrameRenderer(getFrameWidth(), getFrameHeight());	//CREATES FRAME RENDERER AT SCALED PIXEL RATIO

		//UPDATES THE WHOLE WORLD - everything
		world.update(screen);

		//Render colliders DEBUGONLY - uncomment to see colliders
		//world.renderColliders(screen);

		
		//Updates UI components last to be on top
		for(UIComponent i : uicomps) {
			i.update(screen);
		}

		//Draw rendered frame to screen
		bs.getDrawGraphics().drawImage(screen.getFrame(), 0, 0, width, height, null);
		bs.show();//show the buffers
	}

	/**
	 * Does things that should happen once per second, i.e. window title update fps
	 * @param fps
	 */
	private void slowUpdate(int fps) {
		window.getFrame().setTitle(title+"!   "+"Wave "+wave+"   "+fps+"FPS");

	}

	/**
	 * Initialize window (JFrame), screen (pixel equivalent), input sources, etc.
	 */
	private void init() {
		running = true;
		window = new Window(title, width, height);//initializes window object
		window.getCanvas().createBufferStrategy(3);
		bs = window.getCanvas().getBufferStrategy();

		//Keyboard for IO
		keyboard = new Keyboard();
		window.getFrame().addKeyListener(keyboard);

		System.out.println("Screen size: "+width+"x"+height);

		//Sets up player
		player = new Player(PlayerClass.WARRIOR, "Player", keyboard, getFrameWidth()/2, getFrameHeight()/2);

		//Makes level generator, generates level (tiles, enemies, coins)
		world = new Level(player, Level.generateBox(getFrameWidth(), getFrameHeight(), 30, 14, 10));

		//levelname = world.getLevelname();

		//UI Init
		uicomps.add(new CoinsText(player));

		//Debuglog screen output
		debuglog = new DebugOut(getFrameWidth()/2, 20);
		uicomps.add(debuglog);

	}

	/**
	 * @return the rendered frame height in px
	 */
	public int getFrameHeight() {
		return height/scale;
	}

	/**
	 * @return the rendered frame width in px
	 */
	public int getFrameWidth() {
		return width/scale;
	}



}
