package TileMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileMap {

	private BufferedImage sheet;
	public static final int RES = 16;

	public TileMap(String src){
		try{
			this.sheet = ImageIO.read(new File(src));;
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public BufferedImage getTile(int row, int variant){
		return sheet.getSubimage(RES*variant, RES*row, RES, RES);
	}

}
