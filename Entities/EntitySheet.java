package Entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Spritesheet object for all entities.
 * @author jaydenlefebvre
 *
 */
public class EntitySheet {

	private BufferedImage sheet;
	private int spritewidth, spriteheight;

	/**
	 * New entity spritesheet
	 * @param src
	 * @param spritewidth
	 * @param spriteheight
	 */
	public EntitySheet(String src, int spritewidth, int spriteheight){
		try{
			this.sheet = ImageIO.read(new File(src));;
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		this.spritewidth = spritewidth;
		this.spriteheight = spriteheight;
	}

	/**
	 * Gets default sprite in down facing direction
	 * @return
	 */
	public BufferedImage getDefaultSprite(){
		return sheet.getSubimage(spritewidth, spriteheight*Direction.DOWN.ordinal(), spritewidth, spriteheight);
	}

	/**
	 * Gets all frames for a given direction
	 * @param direction
	 * @return List of frames
	 */
	public ArrayList<BufferedImage> getSprites(Direction direction){
		ArrayList<BufferedImage> minisheet = new ArrayList<BufferedImage>();
		
		minisheet.add(sheet.getSubimage(0, direction.ordinal()*spriteheight, spritewidth, spriteheight));
		minisheet.add(sheet.getSubimage(spritewidth, direction.ordinal()*spriteheight, spritewidth, spriteheight));
		minisheet.add(sheet.getSubimage(0, direction.ordinal()*spriteheight, spritewidth, spriteheight));
		minisheet.add(sheet.getSubimage(2*spritewidth, direction.ordinal()*spriteheight, spritewidth, spriteheight));
		
		
		return minisheet;
	}

}
