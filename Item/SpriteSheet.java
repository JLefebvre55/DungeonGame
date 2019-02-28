package Item;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage sheet;
	private int spritewidth, spriteheight, numsprites;

	public SpriteSheet(String src, int spritewidth, int spriteheight){
		try{
			this.sheet = ImageIO.read(new File(src));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		this.spritewidth = spritewidth;
		this.spriteheight = spriteheight;
		numsprites = sheet.getWidth()/spritewidth;
	}

	public BufferedImage getSprite(int index) {
		return sheet.getSubimage(spritewidth*index, 0, spritewidth, spriteheight);
	}

	public ArrayList<BufferedImage> getSprites() {
		ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
		for(int i = 0; i < numsprites; i++) {
			temp.add(sheet.getSubimage(spritewidth*i, 0, spritewidth, spriteheight));
		}
		return temp;
	}

	public int getNumSprites() {
		return sheet.getWidth()/spritewidth;
	}

}
