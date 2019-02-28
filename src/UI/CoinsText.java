package UI;

import java.awt.image.BufferedImage;

import Animation.Animator;
import Entities.Player;
import Graphics.FrameRenderer;
import Item.Coin;

/**
 * UI component for coins text in top left corner. NOTE NO COINT OBJECT, JUST ANIMATOR AND ICON
 * @author jaydenlefebvre
 *
 */
public class CoinsText extends UIComponent implements Main.Engine{

	private Animator coinanimator;
	private TextObject text;
	private Player player;
	private static double dx, dy;
	private BufferedImage coinicon;
	
	public CoinsText(Player player) {
		super(5, 5);
		dx = Coin.getSheet().getSprite(0).getWidth()+2;
		dy = Coin.getSheet().getSprite(0).getHeight()-1;
		coinanimator = new Animator(Coin.getTimeBetween(), 0, Coin.getSheet().getSprites());
		this.player = player;
		text = new TextObject(x+dx, y+dy, "x "+player.getGold(), false);
	}
	
	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderImage(coinicon, x, y);
		screen.renderText(text);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		coinicon = coinanimator.updateAnimation();
		text = new TextObject(x+dx, y+dy, "x "+player.getGold(), false);
	}

	
	
}
