package Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Generic superclass for linear animator
 * @author jaydenlefebvre
 *
 */
public class Animator {

	protected long lastAnimation;
	protected long timeBetween;
	protected BufferedImage currentImage;
	protected int frameIndex, startIndex;
	protected ArrayList<BufferedImage> frames;

	/**
	 * Creates an animator
	 * @param timeBetween - time in ms between frames
	 * @param startIndex - first frame
	 * @param frames - All frames (linear x animation)
	 */
	public Animator(long timeBetween, int startIndex, ArrayList<BufferedImage> frames) {
		this.timeBetween = timeBetween;
		this.startIndex = startIndex;
		this.frameIndex = startIndex;
		this.frames = frames;
		currentImage = frames.get(startIndex);
	}

	public BufferedImage updateAnimation() {
		if(System.currentTimeMillis() - lastAnimation >= timeBetween) {
			currentImage = frames.get(frameIndex);
			frameIndex = (frameIndex+1)%frames.size();
			lastAnimation = System.currentTimeMillis();
		}
		return currentImage;
	}
}
