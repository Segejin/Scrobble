/**
 * This code was created by 
 * Documented by Omar Alamoudi
 * */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	private char letter;
	private int score;
	private Image img;
	private Image greyImg;
	private String image_folder = "rec/image/";

    /**
     * Initiate tile 
     * @param letter tile letter char
     * @param score tile score integer
     * */
	public Tile(char letter, int score) {
		this.letter = letter;
		this.score = score;
		try {
			this.img = ImageIO.read(new File(image_folder+letter+".jpg"));
			this.img = img.getScaledInstance((int)(105*.7),(int)(105*.7), Image.SCALE_DEFAULT);
			this.greyImg = ImageIO.read(new File(image_folder+letter+"_grey.jpg"));
			this.greyImg = img.getScaledInstance((int)(105*.7),(int)(105*.7), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * Get letter
     * @return letter char 
     * */
	public char getLetter() {
		return letter;
	}

    /**
     * Set letter
     * @param letter char
     * */
	public void setLetter(char letter) {
		this.letter = letter;
	}

    /**
     * Get score
     * @return score int
     * */
	public int getScore() {
		return score;
	}

    /**
     * Set score
     * @param score int
     * */
	public void setScore(int score) {
		this.score = score;
	}

    /**
     * Get Colored image
     * @return colored image
     * */
	public Image getImg(){
		return this.img;
	}

    /**
     * Get Grey image
     * @return grey image
     * */
	public Image getGreyImg(){
		return this.greyImg;
	}
	/*public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}*/
	
}
