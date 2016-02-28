import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


public class Driver extends Applet implements Runnable, MouseListener {
	
	private static JFrame frame;
	private Game game;
	private Menu menu;
	private Rules rules;
	private Credits credits;
	private Over over;
	private Image img;
	private boolean newGame = true;
	private boolean mouseState = false;				//false = up
	private String image_folder = "rec/image/";
	private String music_folder = "rec/music/";
	public enum GameState {
		GAME, MENU, RULES, CREDITS, OVER
	}
	GameState gameState = GameState.MENU;
	//GameState gameState = GameState.GAME;
	@Override
	public void init() {
	
		if(gameState == GameState.MENU) {
			setSize((int)(1200*.7),(int)(900*.7));
			try {
				img = ImageIO.read(new File(this.image_folder+"menu.jpg"));
				img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setFocusable(true);
			Frame frame = new Frame();
			frame.setTitle("Uhhh...Yeah...");
			addMouseListener(this);
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "bgm.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
					ex.printStackTrace();
				}
		} else {
			//setSize((int)(1200*.7),(int)(900*.7));
			//setBackground(Color.BLACK);
			try {
				img = ImageIO.read(new File(this.image_folder+"board_final.jpg"));
				img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void start() {
		game = new Game();
		menu = new Menu();
		rules = new Rules();
		credits = new Credits();
		over = new Over();
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	@Override
	public void run() {
		repaint();
		while (true) {
			switch(gameState)
			{
				case MENU:
					break;
				
				case RULES:
					break;
					
				case CREDITS:
					break;
				
				case GAME:
					if(newGame == true)
					{
						game = null;
						game = new Game();
						newGame = false;
						repaint();
					}
					else
						//newGame = updateGame();
						updateGame();
					//enabled = true;
					//gameState = runGame();
					///gameState = game.runGame(enabled, myDrops, hud, newGame);
					//repaint();
					break;
				case OVER:
					newGame = true;
					break;
			}
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean updateGame() {
		//if(game.boardUpdate())
		//repaint();
		//System.out.println("hi");
		if(mouseState)
		{
			game.displayMovingTile(frame.getX(),frame.getY());
		}
		repaint();
		return true;
			
		//}
		//return GameState.GAME;
	}

	@Override
	public void paint(Graphics g) {
		switch(gameState)
		{
			case MENU:
				g.drawImage(img, 0, 0, null);
				break;
			case RULES:
				g.drawImage(img, 0, 0, null);
				break;
			case CREDITS:
				g.drawImage(img, 0, 0, null);
				break;
			case GAME:
				if(!newGame)
					g.drawImage(img, 0, 0, null);
				if(!game.updateGame(g,mouseState)) {
					gameState = GameState.OVER;
					try {
						img = ImageIO.read(new File(this.image_folder+"quit_menu.jpg"));
						img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					repaint();
				}
				
				break;	
			case OVER:
				g.drawImage(img, 0, 0, null);
				if(game.getPlayerScore(true)>game.getPlayerScore(false))
				{
					g.setFont(new Font("Arial Black", Font.PLAIN, 35));
					g.setColor(Color.BLACK);
					g.drawString("Player 1 WINS", (int)(347),(int)(354));
					g.setColor(new Color(218, 202, 186));
					g.setFont(new Font("Arial Black", Font.PLAIN, 35));
					g.drawString("Player 1 WINS", (int)(347),(int)(354));
				}
				else if(game.getPlayerScore(true)<game.getPlayerScore(false))
				{
					g.setFont(new Font("Arial Black", Font.PLAIN, 35));
					g.setColor(Color.BLACK);
					g.drawString("Player 2 WINS", (int)(347),(int)(354));
					g.setColor(new Color(218, 202, 186));
					g.setFont(new Font("Arial Black", Font.PLAIN, 35));
					g.drawString("Player 2 WINS", (int)(347),(int)(354));
				}
				else{
					g.setFont(new Font("Arial Black", Font.PLAIN, 35));
					g.setColor(Color.BLACK);
					g.drawString("DRAW", (int)(347),(int)(354));
					g.setColor(new Color(218, 202, 186));
					g.setFont(new Font("Arial Black", Font.PLAIN, 35));
					g.drawString("DRAW", (int)(345),(int)(357));
				}
				break;
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(gameState.equals(GameState.MENU)) {
			int gameSwitch = menu.clickCheck(e);
			if(gameSwitch == 1) {
				gameState = GameState.RULES;
				try {
					img = ImageIO.read(new File(this.image_folder+"rules.jpg"));
					img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			else if(gameSwitch == 2) {
				gameState = GameState.CREDITS;
				try {
					img = ImageIO.read(new File(this.image_folder+"credits.jpg"));
					img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			else if(gameSwitch == 3) {
				gameState = GameState.GAME;
				try {
					img = ImageIO.read(new File(this.image_folder+"board_final.jpg"));
					img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				gameState = GameState.MENU;
			}
			repaint();
		}
		else if(gameState.equals(GameState.RULES)) {
			if(credits.clickCheck(e))
			{
				gameState = GameState.MENU;
				try {
					img = ImageIO.read(new File(this.image_folder+"menu.jpg"));
					img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				repaint();
			}
		}
		else if(gameState.equals(GameState.CREDITS)) {
			if(credits.clickCheck(e))
			{
				gameState = GameState.MENU;
				try {
					img = ImageIO.read(new File(this.image_folder+"menu.jpg"));
					img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				repaint();
			}
		} 
		else if(gameState.equals(GameState.OVER)){
			if(over.clickCheck(e)) 
			{
				gameState = GameState.MENU;
					try {
						img = ImageIO.read(new File(this.image_folder+"menu.jpg"));
						img = img.getScaledInstance((int)(1200*.7),(int)(900*.7), Image.SCALE_DEFAULT);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					repaint();
			}
		}
		else {
			if(game.clickCheck(e))
				mouseState = true;
			System.out.println("Mouse down");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(gameState.equals(GameState.GAME))
		{
			System.out.println("Mouse up");
			if(!mouseState)
				return;
			mouseState = false;
			game.resetTile(e);
		}
		
	}

	public Driver() throws IOException
	{
		/*File yourFile = new File("highScore.txt");
		if(!yourFile.exists()) {
		    yourFile.createNewFile();
		    highScore = new HighScore();
		}
		else
			highScore = new HighScore("highScore.txt");*/
	}
	public Driver(Scanner in) throws IOException
	{
		/*File yourFile = new File("highScore.txt");
		if(!yourFile.exists()) {
		    yourFile.createNewFile();
		    highScore = new HighScore();
		} 
		else
			highScore = new HighScore("highScore.txt");*/
	}
	
	public void runMenu() {	
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
				ex.printStackTrace();
			}
	}

	public static void main(String[] args) throws IOException {
		 frame = new JFrame();
		 frame.setSize((int)(856),(int)(700));
		 Scanner in = new Scanner(System.in);
		 final Applet applet = new Driver(in);

		 frame.getContentPane().add(applet);
		 frame.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent we) {
		            applet.stop();
		            applet.destroy();
		            System.exit(0);
		        }
		    });
		frame.setVisible(true);
		applet.init();
		applet.start();
	}

}
