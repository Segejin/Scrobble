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

	public Tile(char aChar, int i) {
		this.letter = aChar;
		this.score = i;
		try {
			this.img = ImageIO.read(new File(image_folder+aChar+".jpg"));
			this.img = img.getScaledInstance((int)(105*.7),(int)(105*.7), Image.SCALE_DEFAULT);
			this.greyImg = ImageIO.read(new File(image_folder+aChar+"_grey.jpg"));
			this.greyImg = img.getScaledInstance((int)(105*.7),(int)(105*.7), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Image getImg(){
		return img;
	}
	public Image getGreyImg(){
		return greyImg;
	}
	/*public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}*/
	
}
