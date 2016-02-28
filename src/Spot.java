/**
 * This code created by 
 * Documented by Omar Alamoudi
 * */
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spot {
	private float locX;
	private float locY;
	private float tempLocX;
	private float tempLocY;
	private float opacity;
	private Tile tile;
	private Shape square;
	private int spotNum;

    /**
     * Creating spot object
     * */
	public Spot()
	{
		locX = 0;
		locY = 0;
		tempLocX = 0;
		tempLocY = 0;
		this.tile = null;
		square = null;
		this.spotNum = 0;
		opacity = 1;
	}
    
    /**
     * Creating spot object with parameter
     * @param x the horizontal location of the tile
     * @param y the vertical location of the tile
     * @param tile the tile wanted to locate
     * @param spotNum the number of the spot 
     * */
	public Spot(float x,float y, Tile tile,int spotNum)
	{
		locX = x;
		locY = y;
		this.tile = tile;
		square = new Rectangle2D.Float(x*.7f, y*.7f,105*.7f, 105*.7f);
		this.spotNum = spotNum;
		opacity = 1;
	}
	

	/*
     * list of setters and getters
     * */

    /**
     * Get location x
     * @return location of x
     * */
	public float getLocX() {
		return this.locX;
	}

    /**
     * Get location y
     * @return location of y
     * */
	public float getLocY() {
		return this.locY;
	}

    /**
     * Get tile
     * @return tile
     * */
	public Tile getTile() {
		return this.tile;
	}
	
    /**
     * Get shape
     * @return square
     * */
    public Shape getShape(){
		return this.square;
	}
	
    /**
     * Get spot number
     * @return spot number
     * */
    public int getSpotNum(){
		return spotNum;
	}

    /**
     * Set tile
     * @param tile 
     * */
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
    /**
     * Set opacity
     * @param opacity 
     * */
    public void setOpacity(float opacity)
	{
		this.opacity = opacity;
	}
    
    /**
     * Display a tile
     * @param g graphic object
     * */
    public void displayTile(Graphics g) {
    	float dispX;
    	float dispY;
		if(this.tile!=null)
		{
			if(tempLocX!= -1)
			{
				dispX = locX;
				dispY = locY;
			}
			else
			{
				dispX = tempLocX;
				dispY = tempLocY;
			}
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			g.drawImage(tile.getImg(),(int)(dispX*.7), (int)(dispY*.7), null);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		
	}
    public void displayMovedTile(int m, int n) {
		tempLocX = (float) m - 32;
		tempLocY = (float) n - 32;
	}
	/**
     * Display a tile
     * @param g graphic object
     * @param f float
     * */
    public void displayTile(Graphics g, float f, boolean mouseState) {
    	if(!mouseState)
    	{
    		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f));
			g.drawImage(tile.getImg(),(int)(locX*.7), (int)(locY*.7), null);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    		return;
    	}
    	float dispX;
    	float dispY;
		if(this.tile!=null)
		{
			if(tempLocX == 0)
			{
				dispX = locX;
				dispY = locY;
			}
			else
			{
				dispX = tempLocX;
				dispY = tempLocY;
			}
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f));
			g.drawImage(tile.getImg(),(int)(dispX), (int)(dispY), null);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
		
	}

	public void displayMovedTile(float x, float y) {
		tempLocX = x;
		tempLocY = y;
		
	}

}
