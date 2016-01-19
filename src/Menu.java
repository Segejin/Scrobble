import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class Menu {
	private boolean menu_state; //True = running, False = game over.
	private Shape rulesSquare;
	private Shape creditsSquare;
	private Shape playGameSquare;
	private Image img;
	private String image_folder = "rec/image/";
	private String music_folder = "rec/music/";
	private int step;
	
	public Menu() {
		menu_state = true;
		rulesSquare = new Rectangle2D.Float(115*.7f, 690*.7f,210*.7f, 85*.7f);
		creditsSquare = new Rectangle2D.Float(830*.7f, 690*.7f,255*.7f, 85*.7f);
		playGameSquare = new Rectangle2D.Float(445*.7f, 400*.7f,305*.7f, 210*.7f);
		step = 1;	
	}
	
	public int clickCheck(MouseEvent e) {
		if(rulesClickCheck(e)) {
			return 1;
		}
		if(creditsClickCheck(e)) {
			return 2;
		}
		if(playGameClickCheck(e)) {
			return 3;
		}
		return 0;
	}
	
	
	public boolean rulesClickCheck(MouseEvent e) {
		if(rulesSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
	
	public boolean creditsClickCheck(MouseEvent e) {
		if(creditsSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
	
	public boolean playGameClickCheck(MouseEvent e) {
		if(playGameSquare.contains(e.getPoint())) {
			return true;
		}
		return false;
	}
}
