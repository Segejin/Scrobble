/**
 * This code created by
 * Documented by Omar Alamoudi 
 * */
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {
	//private List<Tile> hand;
	List<Spot> hand;
	private int score;
	private int tileLoc;
	private Score scoreGen;
	private String music_folder = "rec/music/";

    /**
     * Player initiated with new hand
     * */
	public Player() {
		this.hand = new ArrayList<Spot>();
		this.scoreGen = new Score();
		float x = 16;
		int y = 790;
		for(int j = 1; j<8;j++)
		{
			this.hand.add(new Spot(x,y,null,49+j));
			x+=107.2;
		}
		this.score = 0;
	}

    /**
     * Get hand
     * @return hand a list of spots
     * */
	public List<Spot> getHand() {
		return this.hand;
	}

    /**
     * Add a tile
     * @param x int the position on hand
     * @param tile Tile object 
     * */
	public void addTile(int x, Tile tile) {
		this.hand.get(x).setTile(tile);
	}

    /**
     * Add a tile
     * @param tile Tile object 
     * */
	public void addTile(Tile tile) {
		this.hand.get(tileLoc).setTile(tile);
	}

    /**
     * Get score
     * @return score int
     * */
	public int getScore() {
		return this.score;
	}

    /**
     * Set score
     * @param score int 
     * */
	public void setScore(int score) {
		this.score += score;
	}

    /**
     * Display hand
     * @param g graphic object
     * */
	public void displayHand(Graphics g) {
		for(Spot t: hand)
		{
			t.displayTile(g);
		}
		
	}

    /**
     * check if clicked
     * @param e mouse event
     * */
	public Spot clickCheck(MouseEvent e) {
		for(Spot cell: hand)
		{
			if(cell.getShape().contains(e.getPoint()))
			{
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "tilepickup.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
					} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				System.out.println("clicked square: " + cell.getSpotNum());
				return cell;
			}
		}
		return null;
		
	}

    /**
     * Remove tile from hand
     * @param tempCell temperery spot 
     * */
	public void remove(Spot tempCell) {
		//hand.remove(tempCell);

		tileLoc = hand.indexOf(tempCell);
		hand.get(tileLoc).setTile(null);
		//return tempCell;
	}

    /**
     * Set score removal
     * @param tileList arrayList of tiles
     * */
	public void setScoreRemoval(ArrayList<Tile> tileList) {
		setScore(scoreGen.getPoints(tileList));
		scoreGen.reset();
		
	}
    
    /**
     * Set hand opacity
     * @param aFloat
     * */
	public void setHandOpacity(float aFloat) {
		for(Spot s: hand)
		{
			s.setOpacity(aFloat);
		}
		
	}
}
