package Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Direction;
import Entities.Entity;
import Entities.EntitySheet;

/**
 * Animator for entities. Works with directions n such
 * @author jaydenlefebvre
 *
 */
public class EntityAnimator{

	private ArrayList<ArrayList<BufferedImage>> frameDirs = new ArrayList<ArrayList<BufferedImage>>();

	//Timings, both in ms
	private Entity e;
	private long lastAnimation;
	private long timeBetween;
	private BufferedImage currentImage;
	private int frameIndex;

	/**
	 * Animates entities
	 * @param timeBetween - ms between sprite change
	 * @param sheet - Spritesheet
	 * @param e - entity to animate
	 */
	public EntityAnimator(long timeBetween, EntitySheet sheet, Entity e) {
		//Sets default sprite
		this.currentImage = sheet.getDefaultSprite();
		this.timeBetween = timeBetween;
		this.e = e;

		//Initialize all frames, by direction
		for(int i = 0; i < Direction.values().length-1; i++) {
			frameDirs.add(sheet.getSprites(Direction.values()[i]));
		}
		lastAnimation = System.currentTimeMillis();
	}

	public BufferedImage updateAnimation() {
		// TODO Auto-generated method stub

		//ONLY ANIMATE IF MOVING & IS TIME TO ANIMATE
		//ANIMATION CORRESPONDS TO DIRECTION, Y SUPERCEDES X

		if(e.getDirection() != Direction.NONE) {
			currentImage = frameDirs.get(e.getDirection().ordinal()).get(frameIndex);
			if(System.currentTimeMillis() - lastAnimation >= timeBetween) {
				frameIndex = (frameIndex+1)%frameDirs.get(e.getDirection().ordinal()).size();
				lastAnimation = System.currentTimeMillis();
			}
		}


		return currentImage;

	}


}
