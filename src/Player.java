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
	public Player() {
		hand = new ArrayList<Spot>();
		scoreGen = new Score();
		float x = 16;
		int y = 790;
		for(int j = 0; j<7;j++)
		{
			hand.add(new Spot(x,y,null,49+j+1));
			x+=107.2;
		}
		score = 0;
	}

	public List<Spot> getHand() {
		return hand;
	}

	public void addTile(int x, Tile aTile) {
		hand.get(x).setTile(aTile);
	}
	public void addTile(Tile aTile) {
		hand.get(tileLoc).setTile(aTile);
	}
	public int getScore() {
		return score;
	}

	public void setScore(int a_score) {
		this.score += a_score;
	}

	public void displayHand(Graphics g) {
		for(Spot t: hand)
		{
			t.displayTile(g);
		}
		
	}

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

	public void remove(Spot tempCell) {
		//hand.remove(tempCell);

		tileLoc = hand.indexOf(tempCell);
		hand.get(tileLoc).setTile(null);
		//return tempCell;
	}

	public void setScoreRemoval(ArrayList<Tile> tileList) {
		setScore(scoreGen.getPoints(tileList));
		scoreGen.reset();
		
	}

	public void setHandOpacity(float aFloat) {
		for(Spot s: hand)
		{
			s.setOpacity(aFloat);
		}
		
	}
}
