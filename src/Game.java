import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Game {
	private Image img;
	private Deck pile;
	private Player player_one;
	private Player player_two;
	private Board play_board;
	private Judge judge;
	private GUI gui;
	private int step;
	private boolean turn; //true = player 1, false = player 2.
	private boolean game_state; //True = running, False = game over.
	private boolean boardEnabler;
	private boolean guiEnabler;
	private boolean handEnabler; 
	private Spot tempCell;
	private Spot tempBoardCell;
	Random random = new Random();
	private int startRandomizer = random.nextInt(2);
	private ArrayList<Integer> tempList;
	private String music_folder = "rec/music/";
	
	public Game() {
		judge = new Judge();
		pile = new Deck();
		player_one = new Player();
		player_one = initHand(player_one, 7);
		player_two = new Player();
		player_two = initHand(player_two, 7);
		gui = new GUI(player_one.getScore(),player_two.getScore());
		Random random = new Random();
		random.nextInt(1);
		if (startRandomizer == 0) {
			turn = true;
		} else {
			turn = false;
		}
		game_state = true;
		play_board = new Board();
		step = 1;
		boardEnabler = false;
		guiEnabler = false;
		handEnabler = true;
		tempList = new ArrayList<Integer>();
	}

	private Player initHand(Player aPlayer, int initDraw) {
		for(int i = 0; i<initDraw;i++)
		{
			aPlayer.addTile(i,pile.remove());
		}
		return aPlayer;
	}

	public boolean updateGame(Graphics g) {
		play_board.displayBoard(g);
		if(turn) {
			gui.displayTurnOne(g);
			player_one.displayHand(g);
		} else {
			gui.displayTurnTwo(g);
			player_two.displayHand(g);
		}
		gui.displayGui(g);
		if(game_state == false)
		{
			game_state = true;
			return false;
		}
		return true;
	}

	public boolean boardUpdate() {
		return false;
	}

	public void clickCheck(MouseEvent e) {
		
		boolean m = true;
		while(m==true)
		{
			m = false;
			switch(step){
				case(1):			//select spot from hand
					if(tempCell!=null && play_board.clickCheck(e)!=null)
					{
						tempBoardCell = play_board.clickCheck(e);
						if(tempBoardCell.getTile()==null)
						{
							try {
								AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.music_folder + "tiledrop.wav"));
								Clip clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
								} catch  (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
									ex.printStackTrace();
								}
							play_board.addTile(tempBoardCell, tempCell.getTile());
							tempList.add(tempBoardCell.getSpotNum());
							if(turn)
								player_one.remove(tempCell);
							else
								player_two.remove(tempCell);
							++step;
						}
						if(turn)
							player_one.setHandOpacity(1f);
						else
							player_two.setHandOpacity(1f);
					}
					else{
						if(turn)
						{
							tempCell = player_one.clickCheck(e);
							player_one.setHandOpacity(1f);
							player_one.getHand().get(player_one.getHand().indexOf(tempCell)-1).setOpacity(.6f);
						}
						else
						{
							tempCell = player_two.clickCheck(e);
							player_two.setHandOpacity(1f);
							player_two.getHand().get(player_two.getHand().indexOf(tempCell)-1).setOpacity(.6f);
						}
					}
					break;
				case(2):			//select spot on board
					handEnabler = false;
					guiEnabler = true;
					if(gui.clickCheck(e))
					{
						++step;
						m = true;
					}
					else
					{
						tempBoardCell = play_board.clickCheck(e);
						if(tempBoardCell.getTile()!=null)
						{
							if(verifySpot(tempBoardCell.getSpotNum()))
							{
								System.out.println("verify accepted");
								tempList.add(tempBoardCell.getSpotNum());
								Collections.sort(tempList);
							}
							else
							{
								System.out.println("selected a bad tile");
								tempBoardCell = null;
							}
						if(gui.clickCheck(e))
							++step;
						}
						else
							System.out.println("invalid selection");
						break;
					}
				case(3):			//submit word to judge
						++step;
						if(tempList!=null)
						{
							ArrayList<Tile> tempListAlt = listConvert(tempList);
							if(judge.getResult(tempListAlt))
							{
								if(turn)
									player_one.setScoreRemoval(tempListAlt);
								else
									player_two.setScoreRemoval(tempListAlt);
								for(int x: tempList)
								{
									play_board.removeTile(x);
								}

							}
						}
						tempList = new ArrayList<Integer>();
					break;
				case(4):			//turn finalization
					if(turn)
						player_one.addTile(pile.remove());
					else
						player_two.addTile(pile.remove());
					if(play_board.isFull()||pile.isEmpty()||player_one.getScore()>=50||player_two.getScore()>=50) {
						System.out.println("game OVER");
						game_state = false;
					}
					else 
					{
						if(turn)
							turn = false;
						else
							turn = true;
						handEnabler = true;
						guiEnabler = false;
						boardEnabler = false;
						step = 1;
						tempCell = null;
						tempBoardCell = null;
					}
					break;
			}
		}
		gui.updateScore(player_one.getScore(), player_two.getScore());
	}

	private ArrayList<Tile> listConvert(ArrayList<Integer> aTempList) {
		ArrayList<Tile> tempTileList = new ArrayList<Tile>();
		for(int x: aTempList)
		{
			tempTileList.add(play_board.getTile(x));
		}
		return tempTileList;
	}

	private boolean verifySpot(int spotNum) {
		if(tempList.size() == 0)
			return true;
		else if(tempList.indexOf(spotNum)!=-1)
			return false;
		else if(tempList.size()==1)
		{
			if(tempList.get(0)+7==spotNum||tempList.get(0)-7==spotNum)
				return true;
			else
			{
				if((tempList.get(0)-1)%7-(spotNum-1)%7 == -1||(tempList.get(0)-1)%7-(spotNum-1)%7 == 1)
				{
					if(tempList.get(0)+1 == spotNum||tempList.get(0)-1 == spotNum)
						return true;
				}
			}
			return false;
		}
		else
		{
			if(tempList.get(0)+7==tempList.get(1)||tempList.get(0)-7==tempList.get(1))
			{
				if(tempList.get(0)-7==spotNum||tempList.get(tempList.size()-1)+7==spotNum)
					return true;
			}
			else if((tempList.get(0)-1)%7-(spotNum-1)%7==1||(tempList.get(tempList.size()-1)-1)%7-(spotNum-1)%7==-1)
			{
				if(tempList.get(0)-1 == spotNum||tempList.get(tempList.size()-1)+1 == spotNum)
					return true;
			}
			else
				return false;
			return false;
		}
	}
	
	public int getPlayerScore(boolean opt){
		if(opt)
			return player_one.getScore();
		else
			return player_two.getScore();
	}

}
