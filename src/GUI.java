import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class GUI {
	final Color SCORE_COLOR = new Color(218, 202, 186);
	private final String turn = "*";
	private int scoreOne;
	private int scoreTwo;
	private Shape endTurnSquare;
	private Image img;
	private String music_folder = "rec/music/";
	private String image_folder = "rec/image/";
	private Shape undoPlacementSquare;
	private Shape wordCheckSquare;
	
	private Color aColor;
	private String aWord;
	private int aScore;
	public GUI(int score, int score2) {
		aWord = "";
		aScore = 0;
		aColor = Color.red;
		scoreOne = score;
		scoreTwo = score2;
		endTurnSquare = new Rectangle2D.Float(810*.7f, 760*.7f,360*.7f, 105*.7f);
		undoPlacementSquare = new Rectangle2D.Float(810*.7f, 630*.7f,360*.7f, 105*.7f);
		wordCheckSquare = new Rectangle2D.Float(810*.7f, 400*.7f,360*.7f, 135*.7f);
		try {
			img = ImageIO.read(new File(this.image_folder+"end_turn.jpg"));
			img = img.getScaledInstance((int)(360*.7),(int)(105*.7), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateScore(int score1,int score2){
			scoreOne = score1;
			scoreTwo = score2;
	}
	public boolean clickCheckEnd(MouseEvent e) {
		if(endTurnSquare.contains(e.getPoint()))
		{
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "button.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			System.out.println("clicked end square");
			return true;
		}
		return false;
	}

	public void displayGui(Graphics g) {
		//g.drawImage(img,(int)(1100*.7), (int)(850*.7), null);
		//((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		g.setFont(new Font("Arial Black", Font.PLAIN, 35));
		g.setColor(Color.BLACK);
		g.drawString("Player 1: " + Integer.toString(scoreOne), (int)(587),(int)(104));
		g.drawString("Player 2: " + Integer.toString(scoreTwo), (int)(587),(int)(204));
		g.setColor(SCORE_COLOR);
		g.setFont(new Font("Arial Black", Font.PLAIN, 35));
		g.drawString("Player 1: " + Integer.toString(scoreOne), (int)(585),(int)(107));
		g.drawString("Player 2: " + Integer.toString(scoreTwo), (int)(585),(int)(207));
		g.drawImage(img, (int)(810*.7f), (int)(765*.7f), null);
		
		//undo button
		((Graphics2D) g).draw(undoPlacementSquare);
		((Graphics2D) g).fill(undoPlacementSquare);
		g.setFont(new Font("Arial Black", Font.PLAIN, 35));
		g.setColor(Color.BLACK);
		g.drawString("UNDO", (int)(850*.7f),(int)(680*.7f));
		
		//word check
		g.setColor(SCORE_COLOR);
		((Graphics2D) g).draw(wordCheckSquare);
		((Graphics2D) g).fill(wordCheckSquare);
		g.setFont(new Font("Arial Black", Font.PLAIN, 15));
		g.setColor(Color.BLACK);
		g.drawString("WORD CHECK", (int)(860*.7f),(int)(430*.7f));
		g.setColor(aColor);
		g.setFont(new Font("Arial Black", Font.PLAIN, 20));
		g.drawString(aWord.toUpperCase() + ": "+aScore, (int)(880*.7f),(int)(480*.7f));
	}
		public void displayTurnOne (Graphics g) {
		g.finalize();
		g.setFont(new Font("Arial Black", Font.PLAIN, 55));
		g.setColor(Color.BLACK);
		g.drawString(turn, 558, 121);
		g.setColor(SCORE_COLOR);
		g.setFont(new Font("Arial Black", Font.PLAIN, 55));
		g.drawString(turn, 555, 124);
	}
	
	public void displayTurnTwo (Graphics g) {
		g.finalize();
		g.setFont(new Font("Arial Black", Font.PLAIN, 55));
		g.setColor(Color.BLACK);
		g.drawString(turn, 558, 221);
		g.setColor(SCORE_COLOR);
		g.setFont(new Font("Arial Black", Font.PLAIN, 55));
		g.drawString(turn, 555, 224);
	}
	public boolean clickCheckUndo(MouseEvent e) {
		if(undoPlacementSquare.contains(e.getPoint()))
		{
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "button.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			System.out.println("undoPlacement Clicked");
			return true;
		}
		return false;
	}
	public void updateWordCheck(String word, int wordScore,boolean flag) {
		aWord = word;
		aScore = wordScore;
		if(flag)
			aColor = Color.GREEN;
		else
			aColor = Color.RED;
	}
}
