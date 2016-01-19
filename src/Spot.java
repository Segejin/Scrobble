import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spot {
	private float locX;
	private float locY;
	private float opacity;
	private Tile tile;
	private Shape square;
	private int spotNum;
	public Spot()
	{
		locX = 0;
		locY = 0;
		tile = null;
		square = null;
		spotNum = 0;
		opacity = 1;
	}
	public Spot(float x,float y, Tile aTile,int aSpotNum)
	{
		locX = x;
		locY = y;
		tile = aTile;
		square = new Rectangle2D.Float(x*.7f, y*.7f,105*.7f, 105*.7f);
		spotNum = aSpotNum;
		opacity = 1;
	}
	

	
	public float getLocX() {
		return locX;
	}
	public float getLocY() {
		return locY;
	}
	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	public void displayTile(Graphics g) {
		if(tile!=null)
		{
			g.drawImage(tile.getImg(),(int)(locX*.7), (int)(locY*.7), null);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		}
		
	}
	public void setOpacity(float aOpacity)
	{
		opacity = aOpacity;
	}
	public Shape getShape(){
		return square;
	}
	public int getSpotNum(){
		return spotNum;
	}

}
