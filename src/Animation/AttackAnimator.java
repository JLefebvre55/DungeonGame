package Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Animator for attacks
 * @author jaydenlefebvre
 *
 */
public class AttackAnimator extends Animator{

	public AttackAnimator(long timeBetween, int startIndex, ArrayList<BufferedImage> frames) {
		super(timeBetween, startIndex, frames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage updateAnimation() {
		if(frameIndex == frames.size()) {
			currentImage = null;
		} else {
			if(System.currentTimeMillis() - lastAnimation >= timeBetween) {
				currentImage = frames.get(frameIndex);
				frameIndex++;
				lastAnimation = System.currentTimeMillis();
			}
		}
		return currentImage;
	}

	public void go() {
		frameIndex = startIndex;
	}
	
}
